package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dto.AddSectionDto;
import org.dselent.scheduling.server.dto.EditSectionDto;
import org.dselent.scheduling.server.model.CourseSections;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SectionServiceImpl implements SectionService
{
	@Autowired
	private SectionsDao sectionsDao;

	@Autowired
	private CustomDao customDao;
	
	
	public SectionServiceImpl()
	{
		//
	}

	/*
	 * (non-Javadoc)
	 * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
	 */
	@Transactional
	@Override
	public List<Integer> addSection(AddSectionDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		Sections sections = new Sections();
		sections.setName(dto.getName());
		//sections.setCrn(dto.getCrn());
		sections.setTermsID(dto.getTermID());
		sections.setSectionTypeID(dto.getSectionTypeID());
		sections.setDaysID(dto.getDaysID());
		sections.setCoursesID(dto.getCoursesID());
		sections.setStartID(dto.getStartID());
		sections.setEndID(dto.getEndID());

		List<String> sectionInsertColumnNameList = new ArrayList<>();
		List<String> sectionKeyHolderColumnNameList = new ArrayList<>();

		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.NAME));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.CRN));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.TERMS_ID));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.SECTION_TYPE_ID));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.DAYS_ID));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.COURSES_ID));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.START_ID));
		sectionInsertColumnNameList.add(Sections.getColumnName(Sections.Columns.END_ID));

		sectionKeyHolderColumnNameList.add(Sections.getColumnName(Sections.Columns.ID));
		sectionKeyHolderColumnNameList.add(Sections.getColumnName(Sections.Columns.CREATED_AT));
		sectionKeyHolderColumnNameList.add(Sections.getColumnName(Sections.Columns.UPDATED_AT));
		sectionKeyHolderColumnNameList.add(Sections.getColumnName(Sections.Columns.DELETED));


		rowsAffectedList.add(sectionsDao.insert(sections, sectionInsertColumnNameList, sectionKeyHolderColumnNameList));

		return rowsAffectedList;
	}

	@Transactional
    @Override
    public Sections editSection(EditSectionDto dto) throws SQLException{
    	//update course section info--query term stays the same for each change.
    	QueryTerm qt1 = new QueryTerm();
    	
    	qt1.setColumnName(Sections.getColumnName(Sections.Columns.ID));
    	qt1.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt1.setLogicalOperator(null);
    	qt1.setValue(dto.getId());
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(qt1);

    	//queries to update the rows.
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.NAME), dto.getName(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.TERMS_ID), dto.getTermID(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.SECTION_TYPE_ID), dto.getSectionTypeID(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.DAYS_ID), dto.getDaysID(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.COURSES_ID), dto.getCoursesID(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.START_ID), dto.getStartID(), qtList);
    	sectionsDao.update(Sections.getColumnName(Sections.Columns.END_ID), dto.getEndID(), qtList);
    	
    	//return the new section information
    	return sectionsDao.findById(dto.getId());
	}

	//

	@Override
	public Integer removeSection(Integer id) throws SQLException
	{
		//Cascade removing schedule stuff here?********************************
		
		QueryTerm removeSection = new QueryTerm();
        
		removeSection.setColumnName(Sections.getColumnName(Sections.Columns.ID));
		removeSection.setComparisonOperator(ComparisonOperator.EQUAL);
		removeSection.setLogicalOperator(null);
		removeSection.setValue(id);
		        
		List<QueryTerm> qtList = new ArrayList<QueryTerm>();
		qtList.add(removeSection);
		        
		return sectionsDao.delete(qtList);
	}  
	
	
	@Override
	public List<CourseSections> viewSection(Integer courseId) throws SQLException{
		if(courseId == null) {
    		return null;
    	}
    	return customDao.getSectionsForCourse(courseId);
	}
	
	@Override
	public List<SectionsInfo> viewOneFaculty(Integer facultyId) throws SQLException{
		if(facultyId == null) {
    		return null;
    	}
    	return customDao.getOneFacultySectionsInfo(facultyId);
	}
}