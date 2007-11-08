package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.EventInstance;
import com.oreon.olympics.domain.service.EventInstanceService;
import com.oreon.olympics.domain.dao.EventInstanceDao;
import java.util.List;
import com.oreon.olympics.domain.service.EventInstanceService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class EventInstanceServiceImplBase
		extends
			BaseServiceImpl<EventInstance> implements EventInstanceService {

	private static final Logger log = Logger
			.getLogger(EventInstanceServiceImplBase.class);

	private EventInstanceDao eventInstanceDao;

	public void setEventInstanceDao(EventInstanceDao eventInstanceDao) {
		this.eventInstanceDao = eventInstanceDao;
	}

	@Override
	public GenericDAO<EventInstance> getDao() {
		return eventInstanceDao;
	}

	//// Delegate all crud operations to the Dao ////

	public EventInstance save(EventInstance eventInstance) {
		Long id = eventInstance.getId();

		eventInstanceDao.save(eventInstance);

		return eventInstance;
	}

	public void delete(EventInstance eventInstance) {
		eventInstanceDao.delete(eventInstance);
	}

	public EventInstance load(Long id) {
		return eventInstanceDao.load(id);
	}

	public List<EventInstance> loadAll() {
		return eventInstanceDao.loadAll();
	}

	public List<EventInstance> searchByExample(EventInstance eventInstance) {
		return eventInstanceDao.searchByExample(eventInstance);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
