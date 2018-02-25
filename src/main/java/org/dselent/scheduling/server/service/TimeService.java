package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.StartTime;
import org.springframework.stereotype.Service;

@Service
public interface TimeService {
	public List<StartTime> getStartTimes() throws SQLException;
}
