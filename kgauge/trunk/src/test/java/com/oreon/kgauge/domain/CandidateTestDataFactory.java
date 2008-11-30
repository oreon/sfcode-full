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

			candidate.setFirstName("theta");
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.13 09:10:28 EST"));
			candidate.getUser().setUsername("John76456");
			candidate.getUser().setPassword("Lavendar");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("alpha");
			candidate.getContactDetails().setState("epsilon");
			candidate.getContactDetails().setCountry("theta");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("John");
			candidate.getContactDetails().setEmail("John41548");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("delta");
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.22 10:45:30 EST"));
			candidate.getUser().setUsername("Mark2091");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Eric");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("Mark81976");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("theta");
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.17 11:27:08 EST"));
			candidate.getUser().setUsername("epsilon31495");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("Wilson");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("Malissa");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("Mark16279");

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
					.parse("2008.12.14 12:33:15 EST"));
			candidate.getUser().setUsername("theta60641");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("alpha");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Malissa88644");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Malissa");
			candidate.setLastName("Mark");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.03 06:56:03 EST"));
			candidate.getUser().setUsername("Malissa55179");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("Wilson9270");

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
