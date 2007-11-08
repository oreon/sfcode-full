package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Team;
import com.oreon.olympics.domain.service.TeamService;
import com.oreon.olympics.domain.dao.TeamDao;
import java.util.List;
import com.oreon.olympics.domain.service.TeamService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TeamServiceImplBase extends BaseServiceImpl<Team>
		implements
			TeamService {

	private static final Logger log = Logger
			.getLogger(TeamServiceImplBase.class);

	private TeamDao teamDao;

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	@Override
	public GenericDAO<Team> getDao() {
		return teamDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Team save(Team team) {
		Long id = team.getId();

		teamDao.save(team);

		return team;
	}

	public void delete(Team team) {
		teamDao.delete(team);
	}

	public Team load(Long id) {
		return teamDao.load(id);
	}

	public List<Team> loadAll() {
		return teamDao.loadAll();
	}

	public List<Team> searchByExample(Team team) {
		return teamDao.searchByExample(team);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
