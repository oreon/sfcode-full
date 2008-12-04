package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.kgauge.service.CandidateService;

@Transactional
public class CandidateTestDataFactory
		extends
			AbstractTestDataFactory<Candidate> {

	private List<Candidate> candidates = new ArrayList<Candidate>();

	private static final Logger logger = Logger
			.getLogger(CandidateTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CandidateService candidateService;

	public CandidateService getCandidateService() {
		return candidateService;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	public void register(Candidate candidate) {
		candidates.add(candidate);
	}

	public Candidate createCandidateOne() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Wilson");
			candidate.setLastName("epsilon");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.24 12:06:11 EST"));
			candidate.getUser().setUsername("Wilson70163");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("Lavendar");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Mark69751");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("beta");
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.04 23:52:51 EST"));
			candidate.getUser().setUsername("epsilon99715");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("alpha");
			candidate.getContactDetails().setState("zeta");
			candidate.getContactDetails().setCountry("Lavendar");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Mark1231");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("John");
			candidate.setLastName("Mark");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.30 22:14:29 EST"));
			candidate.getUser().setUsername("Malissa64096");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Malissa");
			candidate.getContactDetails().setCity("Lavendar");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("pi");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Lavendar57110");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Eric");
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.22 03:31:09 EST"));
			candidate.getUser().setUsername("zeta59137");
			candidate.getUser().setPassword("Malissa");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("theta");
			candidate.getContactDetails().setCity("alpha");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("theta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("Mark81815");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("epsilon");
			candidate.setLastName("theta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.15 13:39:31 EST"));
			candidate.getUser().setUsername("gamma9267");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("Eric85666");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate loadOneRecord() {
		List<Candidate> candidates = candidateService.loadAll();

		if (candidates.isEmpty()) {
			persistAll();
			candidates = candidateService.loadAll();
		}

		return candidates.get(new Random().nextInt(candidates.size()));
	}

	public List<Candidate> getAllAsList() {

		if (candidates.isEmpty()) {

			createCandidateOne();
			createCandidateTwo();
			createCandidateThree();
			createCandidateFour();
			createCandidateFive();

		}

		return candidates;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Candidate candidate : candidates) {
			try {
				candidateService.save(candidate);
			} catch (BusinessException be) {
				logger.warn(" Candidate " + candidate.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Candidate candidate = createRandomCandidate();
			candidateService.save(candidate);
		}
	}

	public Candidate createRandomCandidate() {
		Candidate candidate = new Candidate();

		candidate.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		candidate.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		candidate.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		candidate.getUser().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
		candidate.getContactDetails().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setPhone(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getContactDetails().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return candidate;
	}

}
