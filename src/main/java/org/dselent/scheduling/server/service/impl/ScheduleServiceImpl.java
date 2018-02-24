package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.dselent.scheduling.server.dto.UpdateScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	//Dao's needed to access the database
	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private CustomDao customDao;
	
	@Autowired
	private SectionsDao sectionsDao;
	
	 public ScheduleServiceImpl(){
	    //
	 }
	 
	//view all currently created classes. Specified by term. If null, displays for all terms.
	@Override
    public List<SectionsInfo> viewAllSchedule(Integer termsId) throws SQLException{
		//display classes for all terms, if none specified.
		//Since the term selection will be in a menu, there is no other "incorrect" input to account for.
		if(termsId == null) {
			return customDao.getSectionsInfo();
		}
		else {
			return customDao.getSectionsInfo(termsId);
		}
    }
    
    //view all currently created classes for one faculty member.
    @Override
    public List<SectionsInfo> viewOneSchedule(Integer termsId, Integer facultyId) throws SQLException{
    	//if no faculty attached, return an empty list, since there shouldn't be any classes anyway
    	if(facultyId == null) {
    		System.out.println("Faculty Not specified.");
    		return new ArrayList<SectionsInfo>();
    	}
    	
    	//display classes for all terms, if none specified.
    	//Since the term selection will be in a menu, there is no other "incorrect" input to account for.
    	if(termsId == null) {   	
    		return customDao.getOneFacultySectionsInfo(facultyId);
    	}
    	else {
    		return customDao.getOneFacultySectionsInfo(facultyId, termsId);
    	}
    }
    
    //remove a class from the current schedule
    @Transactional
    @Override
    public Integer removeClassSchedule(Integer scheduleId) throws SQLException{
    	//specify the scheduleId to be removed and builds the queryTerm for it
    	QueryTerm deleteTerms = new QueryTerm();
    	
    	deleteTerms.setColumnName(Schedule.getColumnName(Schedule.Columns.ID));
    	deleteTerms.setComparisonOperator(ComparisonOperator.EQUAL);
    	deleteTerms.setLogicalOperator(null);
    	deleteTerms.setValue(scheduleId);
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(deleteTerms);
    	
    	return scheduleDao.delete(qtList);
    }
    
    //add a class to the schedule
    @Transactional
    @Override
    public Integer addClassSchedule(Integer sectionId, Integer facultyId) throws SQLException{
    	//creates the new schedule to add
    	Schedule schedule = new Schedule();
    	
    	schedule.setSectionsID(sectionId);
    	schedule.setFacultyID(facultyId);
    	
    	//builds the query term to add the information to the schedule table
    	List<String> scheduleInsertColNameList = new ArrayList<>();
    	List<String> scheduleKeyHolderColNameList = new ArrayList<>();
    	
    	scheduleInsertColNameList.add(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID));
    	scheduleInsertColNameList.add(Schedule.getColumnName(Schedule.Columns.FACULTY_ID));
    	
    	scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.ID));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.CREATED_AT));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.UPDATED_AT));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.DELETED));

   		scheduleDao.insert(schedule, scheduleInsertColNameList, scheduleKeyHolderColNameList);

    	return schedule.getId();//or just schedule?
    }
    
    //changes information about a class
    @Transactional
    @Override
    public Schedule updateSchedule(UpdateScheduleDto dto) throws SQLException{
    	//update course section info--query term stays the same for each change.
    	QueryTerm qt1 = new QueryTerm();
    	
    	qt1.setColumnName(Sections.getColumnName(Sections.Columns.ID));
    	qt1.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt1.setLogicalOperator(null);
    	qt1.setValue(scheduleDao.findById(dto.getScheduleId()).getSectionsID());
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(qt1);

    	//queries to update the rows.
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.DAYS_ID), dto.getDaysId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.START_ID), dto.getStartId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.END_ID), dto.getEndId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.TERMS_ID), dto.getTermsId(), qtList);

    	//update facultyId queryTerm
    	QueryTerm qt2 = new QueryTerm();

    	qt2.setColumnName(Schedule.getColumnName(Schedule.Columns.ID));
    	qt2.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt2.setLogicalOperator(null);
    	qt2.setValue(dto.getScheduleId());
    	
    	List<QueryTerm> qtList2 = new ArrayList<QueryTerm>();
    	qtList2.add(qt2);
    	
    	//query to update facultyId
    	scheduleDao.update(Schedule.getColumnName(Schedule.Columns.FACULTY_ID), dto.getFacultyId(), qtList2);
    	
    	//return the new schedule information
    	return scheduleDao.findById(dto.getScheduleId());
    }
	
	
}
