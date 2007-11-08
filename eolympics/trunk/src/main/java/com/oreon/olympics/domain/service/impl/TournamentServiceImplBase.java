package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Tournament;
import com.oreon.olympics.domain.service.TournamentService;
import com.oreon.olympics.domain.dao.TournamentDao;
import java.util.List;
import com.oreon.olympics.domain.service.TournamentService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TournamentServiceImplBase extends BaseServiceImpl<Tournament>
		implements
			TournamentService {

	private static final Logger log = Logger
			.getLogger(TournamentServiceImplBase.class);

	private TournamentDao tournamentDao;

	public void setTournamentDao(TournamentDao tournamentDao) {
		this.tournamentDao = tournamentDao;
	}

	@Override
	public GenericDAO<Tournament> getDao() {
		return tournamentDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Tournament save(Tournament tournament) {
		Long id = tournament.getId();

		tournamentDao.save(tournament);

		return tournament;
	}

	public void delete(Tournament tournament) {
		tournamentDao.delete(tournament);
	}

	public Tournament load(Long id) {
		return tournamentDao.load(id);
	}

	public List<Tournament> loadAll() {
		return tournamentDao.loadAll();
	}

	public List<Tournament> searchByExample(Tournament tournament) {
		return tournamentDao.searchByExample(tournament);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
