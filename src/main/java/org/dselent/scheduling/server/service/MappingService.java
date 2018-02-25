package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.EndTime;
import org.dselent.scheduling.server.model.Frequency;
import org.dselent.scheduling.server.model.SectionType;
import org.dselent.scheduling.server.model.StartTime;
import org.dselent.scheduling.server.model.Terms;
import org.springframework.stereotype.Service;

@Service
public interface MappingService {
	public List<StartTime> getStartTimes() throws SQLException;
	public List<EndTime> getEndTimes() throws SQLException;
	public List<Terms> getTerms() throws SQLException;
	public List<Frequency> getFrequencies() throws SQLException;
	public List<SectionType> getSectionTypes() throws SQLException;
}
