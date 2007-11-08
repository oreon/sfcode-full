package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.TeamEventInstance;
import com.oreon.olympics.domain.service.TeamEventInstanceService;
import com.oreon.olympics.domain.dao.TeamEventInstanceDao;
import java.util.List;
import com.oreon.olympics.domain.service.TeamEventInstanceService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TeamEventInstanceServiceImplBase
		extends
			BaseServiceImpl<TeamEventInstance>
		implements
			TeamEventInstanceService {

	private static final Logger log = Logger
			.getLogger(TeamEventInstanceServiceImplBase.class);

	private TeamEventInstanceDao teamEventInstanceDao;

	public void setTeamEventInstanceDao(
			TeamEventInstanceDao teamEventInstanceDao) {
		this.teamEventInstanceDao = teamEventInstanceDao;
	}

	@Override
	public GenericDAO<TeamEventInstance> getDao() {
		return teamEventInstanceDao;
	}

	//// Delegate all crud operations to the Dao ////

	public TeamEventInstance save(TeamEventInstance teamEventInstance) {
		Long id = teamEventInstance.getId();

		teamEventInstanceDao.save(teamEventInstance);

		return teamEventInstance;
	}

	public void delete(TeamEventInstance teamEventInstance) {
		teamEventInstanceDao.delete(teamEventInstance);
	}

	public TeamEventInstance load(Long id) {
		return teamEventInstanceDao.load(id);
	}

	public List<TeamEventInstance> loadAll() {
		return teamEventInstanceDao.loadAll();
	}

	public List<TeamEventInstance> searchByExample(
			TeamEventInstance teamEventInstance) {
		return teamEventInstanceDao.searchByExample(teamEventInstance);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
