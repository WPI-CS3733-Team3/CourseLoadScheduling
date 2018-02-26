package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.EndTimeDao;
import org.dselent.scheduling.server.dao.FrequencyDao;
import org.dselent.scheduling.server.dao.SectionTypeDao;
import org.dselent.scheduling.server.dao.StartTimeDao;
import org.dselent.scheduling.server.dao.TermsDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.EndTime;
import org.dselent.scheduling.server.model.Frequency;
import org.dselent.scheduling.server.model.SectionType;
import org.dselent.scheduling.server.model.StartTime;
import org.dselent.scheduling.server.model.Terms;
import org.dselent.scheduling.server.service.MappingService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingServiceImpl implements MappingService {
	
	@Autowired
	private StartTimeDao startTimeDao;
	
	@Autowired
	private EndTimeDao endTimeDao;
	
	@Autowired
	private TermsDao termsDao;
	
	@Autowired
	private FrequencyDao frequencyDao;
	
	@Autowired
	private SectionTypeDao sectionTypeDao;
	
	public MappingServiceImpl() {
		
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
	
	@Override
	public List<EndTime> getEndTimes() throws SQLException {
		List<EndTime> endTimeList = new ArrayList<>();
		List<String> selectColumnNameList = EndTime.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(EndTime.getColumnName(EndTime.Columns.TIME), ColumnOrder.ASC);
		orderByList.add(p);

		endTimeList = endTimeDao.select(selectColumnNameList, queryTermList, orderByList);

		return endTimeList;
	}
	
	@Override
	public List<Terms> getTerms() throws SQLException {
		List<Terms> termsList = new ArrayList<>();
		List<String> selectColumnNameList = Terms.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Terms.getColumnName(Terms.Columns.NAME), ColumnOrder.ASC);
		orderByList.add(p);

		termsList = termsDao.select(selectColumnNameList, queryTermList, orderByList);

		return termsList;
	}
	
	@Override
	public List<Frequency> getFrequencies() throws SQLException {
		List<Frequency> frequencyList = new ArrayList<>();
		List<String> selectColumnNameList = Frequency.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Frequency.getColumnName(Frequency.Columns.FREQUENCY), ColumnOrder.ASC);
		orderByList.add(p);

		frequencyList = frequencyDao.select(selectColumnNameList, queryTermList, orderByList);

		return frequencyList;
	}
	
	@Override
	public List<SectionType> getSectionTypes() throws SQLException {
		List<SectionType> sectionTypeList = new ArrayList<>();
		List<String> selectColumnNameList = SectionType.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(SectionType.getColumnName(SectionType.Columns.TYPE), ColumnOrder.ASC);
		orderByList.add(p);

		sectionTypeList = sectionTypeDao.select(selectColumnNameList, queryTermList, orderByList);

		return sectionTypeList;
	}
	
	

}
