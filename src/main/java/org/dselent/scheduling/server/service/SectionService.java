package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.AddSectionDto;
import org.dselent.scheduling.server.dto.EditSectionDto;
import org.dselent.scheduling.server.model.CourseSections;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface SectionService
{
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param registerUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */
	public List<Integer> addSection(AddSectionDto addSectionDto) throws SQLException;
	public Sections editSection(EditSectionDto editSectionDto) throws SQLException;
    public Integer removeSection(Integer id) throws SQLException;
    public List<CourseSections> viewSection(Integer courseId) throws SQLException;
	public List<SectionsInfo> viewAllInfo() throws SQLException;
	public List<SectionsInfo> viewOneFaculty(Integer facultyId, Integer termsId) throws SQLException;
}
