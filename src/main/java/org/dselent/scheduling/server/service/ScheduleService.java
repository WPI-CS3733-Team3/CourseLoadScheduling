package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.UpdateScheduleDto;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {

	//view all currently created classes
    public List<SectionsInfo> viewAllSchedule(Integer termsId) throws SQLException;
    
    //view all currently created classes for one user
    public List<SectionsInfo> viewOneSchedule(Integer termsId, Integer usersId) throws SQLException;
    
    //remove a class from the current schedule
    public Integer removeClassSchedule(Integer sectionId) throws SQLException;
    
    //add a class to the schedule
    public Integer addClassSchedule(Integer sectionId, Integer facultyId) throws SQLException;
    
    //changes information about a class
    public Schedule updateSchedule(UpdateScheduleDto dto) throws SQLException;
	
}
