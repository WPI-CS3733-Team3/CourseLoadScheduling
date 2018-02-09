package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dto.AddSectionDto;
import org.dselent.scheduling.server.dto.EditSectionDto;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SectionServiceImpl implements SectionService
{
	@Autowired
	private SectionsDao sectionsDao;

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

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...
		//No constraints for section requests (I think?)

		Sections sections = new Sections();
		sections.setName(dto.getName());
		sections.setCrn(dto.getCrn());
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

	public List<Integer> editSection(EditSectionDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		Sections sections = new Sections();
		Faculty faculty = new Faculty(); //Unsure about this part???
		sections.setName(dto.getName());
		faculty.setId(dto.getFacultyID()); //<-- here too
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

	//

	@Override
	public Sections removeSection(Integer id)
	{
		// TODO Auto-generated method stub
		return null;
	}   

}