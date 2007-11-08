package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Event;
import com.oreon.olympics.domain.service.EventService;
import com.oreon.olympics.domain.dao.EventDao;
import java.util.List;
import com.oreon.olympics.domain.service.EventService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EventServiceImplBase extends BaseServiceImpl<Event>
		implements
			EventService {

	private static final Logger log = Logger
			.getLogger(EventServiceImplBase.class);

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public GenericDAO<Event> getDao() {
		return eventDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Event save(Event event) {
		Long id = event.getId();

		eventDao.save(event);

		return event;
	}

	public void delete(Event event) {
		eventDao.delete(event);
	}

	public Event load(Long id) {
		return eventDao.load(id);
	}

	public List<Event> loadAll() {
		return eventDao.loadAll();
	}

	public List<Event> searchByExample(Event event) {
		return eventDao.searchByExample(event);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
