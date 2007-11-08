package com.oreon.olympics.domain.service.impl;

import com.oreon.olympics.domain.Participation;
import com.oreon.olympics.domain.service.ParticipationService;
import com.oreon.olympics.domain.dao.ParticipationDao;
import java.util.List;
import com.oreon.olympics.domain.service.ParticipationService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ParticipationServiceImplBase
		extends
			BaseServiceImpl<Participation> implements ParticipationService {

	private static final Logger log = Logger
			.getLogger(ParticipationServiceImplBase.class);

	private ParticipationDao participationDao;

	public void setParticipationDao(ParticipationDao participationDao) {
		this.participationDao = participationDao;
	}

	@Override
	public GenericDAO<Participation> getDao() {
		return participationDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Participation save(Participation participation) {
		Long id = participation.getId();

		participationDao.save(participation);

		return participation;
	}

	public void delete(Participation participation) {
		participationDao.delete(participation);
	}

	public Participation load(Long id) {
		return participationDao.load(id);
	}

	public List<Participation> loadAll() {
		return participationDao.loadAll();
	}

	public List<Participation> searchByExample(Participation participation) {
		return participationDao.searchByExample(participation);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
