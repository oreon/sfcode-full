
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.oreon.kgauge.dao.CandidateDao;
import com.oreon.kgauge.domain.Authority;
import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.service.CandidateService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CandidateServiceImplBase extends BaseServiceImpl<Candidate>
		implements
			CandidateService {

	private static final Logger log = Logger
			.getLogger(CandidateServiceImplBase.class);

	private CandidateDao candidateDao;

	public void setCandidateDao(CandidateDao candidateDao) {
		this.candidateDao = candidateDao;
	}

	@Override
	public GenericDAO<Candidate> getDao() {
		return candidateDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Candidate save(Candidate candidate) {
		Long id = candidate.getId();
		checkUniqueConstraints(candidate);
		candidateDao.save(candidate);

		if (id == null) //creating user for first time, assign authority
			assignDefaultAuthority(candidate);

		return candidate;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Candidate candidate) {
		Candidate existingCandidate = null;

		existingCandidate = candidateDao.findByUserName(candidate.getUser()
				.getUserName());
		ensureUnique(candidate, existingCandidate, "Entity.exists.withUserName");

		existingCandidate = candidateDao.findByEmail(candidate
				.getContactDetails().getEmail());
		ensureUnique(candidate, existingCandidate, "Entity.exists.withEmail");

	}

	private void assignDefaultAuthority(Candidate candidate) {
		Authority authority = new Authority();
		authority.setName("ROLE_CANDIDATE");

		candidate.getUser().addAuthoritie(authority);
	}

	public void delete(Candidate candidate) {
		candidateDao.delete(candidate);
	}

	public Candidate load(Long id) {
		return candidateDao.load(id);
	}

	public List<Candidate> loadAll() {
		return candidateDao.loadAll();
	}

	public Candidate findByUserName(String userName) {
		return candidateDao.findByUserName(userName);
	}

	public Candidate findByEmail(String email) {
		return candidateDao.findByEmail(email);
	}

	public List<Candidate> searchByExample(Candidate candidate) {
		return candidateDao.searchByExample(candidate);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
