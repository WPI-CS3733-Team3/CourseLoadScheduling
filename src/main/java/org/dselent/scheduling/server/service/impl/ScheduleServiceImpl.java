package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.dselent.scheduling.server.dto.UpdateScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;
	
	@Autowired
	private CustomDao customDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private SectionsDao sectionsDao;
	
	 public ScheduleServiceImpl(){
	    //
	 }
	 
	//view all currently created classes
	@Override
    public List<Schedule> viewAllSchedule(Integer termsId) throws SQLException{
		//SELECT * FROM schedule WHERE termsId = :termsId
		
		
		//column name list
		
		
		//scheduleDao.select
		
		//Another custom query here!?!?
    	
		
    	return null;
    }
    
    //view all currently created classes for one user
    @Override
    public List<SectionsInfo> viewOneSchedule(Integer termsId, Integer usersId) throws SQLException{
    	//get facultyId from the userId
    	User user = usersDao.findById(usersId);
    	Integer facultyId = user.getFacultyId();
    	
    	//if no faculty attached, return an empty list, since they shouldn't have any classes anyway
    	if(facultyId == null) {
    		return new ArrayList<SectionsInfo>();
    	}
    	
    	//***potentially add in some sort of check for term here....***
    	return customDao.getOneFacultySectionsInfo(facultyId);
    }
    
    //remove a class from the current schedule
    @Transactional
    @Override
    public Integer removeClassSchedule(Integer sectionId) throws SQLException{
    	//DELETE FROM schedule WHERE section_id = :sectionId
    	QueryTerm deleteTerms = new QueryTerm();
    	
    	deleteTerms.setColumnName(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID));
    	deleteTerms.setComparisonOperator(ComparisonOperator.EQUAL);
    	deleteTerms.setLogicalOperator(null);
    	deleteTerms.setValue(sectionId);
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(deleteTerms);
    	
    	return scheduleDao.delete(qtList);
    }
    
    //add a class to the schedule
    @Transactional
    @Override
    public List<Integer> addClassSchedule(Integer sectionId, Integer facultyId) throws SQLException{
    	List<Integer> rowsAffectedList = new ArrayList<>();
    	
    	//any validation logic here
    	
    	Schedule schedule = new Schedule();
    	
    	schedule.setSectionsID(sectionId);
    	schedule.setFacultyID(facultyId);
    	
    	
    	List<String> scheduleInsertColNameList = new ArrayList<>();
    	List<String> scheduleKeyHolderColNameList = new ArrayList<>();
    	
    	scheduleInsertColNameList.add(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID));
    	scheduleInsertColNameList.add(Schedule.getColumnName(Schedule.Columns.FACULTY_ID));
    	
    	scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.ID));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.CREATED_AT));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.UPDATED_AT));
   		scheduleKeyHolderColNameList.add(Schedule.getColumnName(Schedule.Columns.DELETED));

    	rowsAffectedList.add(scheduleDao.insert(schedule, scheduleInsertColNameList, scheduleKeyHolderColNameList));
    	
    	return rowsAffectedList;
    }
    
    //changes information about a class**********************************************************
    @Transactional
    @Override
    public Schedule updateSchedule(UpdateScheduleDto dto) throws SQLException{
    	
    	//updates schedule table if faculty id changes, but updates just the sections
    	//if only section info changes (will probs update both anyways)
    	
    	//create update query
    	
    	//save rows affected int, then return as specified
    	QueryTerm qt1 = new QueryTerm();
    	QueryTerm qt2 = new QueryTerm();
    	
    	qt1.setColumnName(Sections.getColumnName(Sections.Columns.ID));
    	qt1.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt1.setLogicalOperator(null);
    	qt1.setValue(dto.getSectionsId());
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(qt1);

    	sectionsDao.update(Sections.getColumnName(Sections.Columns.DAYS_ID), dto.getDaysId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.START_ID), dto.getStartId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.END_ID), dto.getEndId(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.TERMS_ID), dto.getTermsId(), qtList);


    	qt2.setColumnName(Schedule.getColumnName(Schedule.Columns.ID));
    	qt2.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt2.setLogicalOperator(null);
    	qt2.setValue(dto.getId());//******************************
    	
    	List<QueryTerm> qtList2 = new ArrayList<QueryTerm>();
    	qtList.add(qt2);
    	
    	scheduleDao.update(Schedule.getColumnName(Schedule.Columns.FACULTY_ID), dto.getFacultyId(), qtList);
    	
    	return scheduleDao.findById(dto.getId());
    }
	
	
}
