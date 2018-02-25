package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.StartTimeDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.StartTime;
import org.dselent.scheduling.server.service.TimeService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService {
	
	@Autowired
	private StartTimeDao startTimeDao;
	
	public TimeServiceImpl() {
		
	}

	@Override
	public List<StartTime> getStartTimes() throws SQLException {
		List<StartTime> startTimeList = new ArrayList<>();
		List<String> selectColumnNameList = StartTime.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(StartTime.getColumnName(StartTime.Columns.TIME), ColumnOrder.ASC);
		orderByList.add(p);

		startTimeList = startTimeDao.select(selectColumnNameList, queryTermList, orderByList);

		return startTimeList;
	}

}
