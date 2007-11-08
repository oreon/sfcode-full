package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Athlete;
import com.oreon.olympics.domain.service.AthleteService;
import com.oreon.olympics.domain.dao.AthleteDao;
import java.util.List;
import com.oreon.olympics.domain.service.AthleteService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AthleteServiceImplBase extends BaseServiceImpl<Athlete>
		implements
			AthleteService {

	private static final Logger log = Logger
			.getLogger(AthleteServiceImplBase.class);

	private AthleteDao athleteDao;

	public void setAthleteDao(AthleteDao athleteDao) {
		this.athleteDao = athleteDao;
	}

	@Override
	public GenericDAO<Athlete> getDao() {
		return athleteDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Athlete save(Athlete athlete) {
		Long id = athlete.getId();

		athleteDao.save(athlete);

		return athlete;
	}

	public void delete(Athlete athlete) {
		athleteDao.delete(athlete);
	}

	public Athlete load(Long id) {
		return athleteDao.load(id);
	}

	public List<Athlete> loadAll() {
		return athleteDao.loadAll();
	}

	public List<Athlete> searchByExample(Athlete athlete) {
		return athleteDao.searchByExample(athlete);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
