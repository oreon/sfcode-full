
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.service.CandidateService;
import com.oreon.kgauge.dao.CandidateDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.GrantedRole;

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

		existingCandidate = candidateDao.findByUsername(candidate.getUser()
				.getUsername());
		ensureUnique(candidate, existingCandidate, "Entity.exists.withUsername");

		existingCandidate = candidateDao.findByEmail(candidate
				.getContactDetails().getEmail());
		ensureUnique(candidate, existingCandidate, "Entity.exists.withEmail");

	}

	private void assignDefaultAuthority(Candidate candidate) {
		GrantedRole authority = new GrantedRole();
		authority.setName("ROLE_CANDIDATE");

		candidate.getUser().addGrantedRole(authority);
	}

	public Candidate getLoggedInCandidate() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();

		if (authentication == null) {
			log.warn("Couldn't find Security Context ");
			return null;
		}

		String userName = ((UserDetails) authentication.getPrincipal())
				.getUsername();

		return findByUsername(userName);
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

	public Candidate findByUsername(String username) {
		return candidateDao.findByUsername(username);
	}

	public Candidate findByEmail(String email) {
		return candidateDao.findByEmail(email);
	}

	public List<Candidate> searchByExample(Candidate candidate) {
		return candidateDao.searchByExample(candidate);
	}

	public List<Candidate> searchByExample(Candidate candidate,
			List<Range> rangeObjects) {
		return candidateDao.searchByExample(candidate, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Candidate getFilterRecord() {
		return null;
	}

	/**
	 * <query name="examsTakenByCandidate" retType="List<ExamInstance>">
	select e from ExamInstance e where e.candidate.id = ?1
	</query>
	 */

	public java.util.List findExamInstances(Long candidateId) {
		return candidateDao.findExamInstances(candidateId);
	}

	/**
	 * ${query}=select e from ExamInstance e where e.candidate.id = ?1 and sum(e.answeredQuestion.answerChoice) >= e.exam.passMarks
	 */

	public Long findNumberOfCertifications() {
		return candidateDao.findNumberOfCertifications();
	}

}
