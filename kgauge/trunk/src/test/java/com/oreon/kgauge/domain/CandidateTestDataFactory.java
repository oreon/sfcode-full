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

			candidate.setFirstName("zeta");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.28 09:33:09 EDT"));
			candidate.getUser().setUsername("epsilon182");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Wilson");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("zeta25180");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Mark");
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.12 08:18:07 EDT"));
			candidate.getUser().setUsername("Malissa28631");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("Wilson66115");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("gamma");
			candidate.setLastName("delta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.13 15:47:34 EDT"));
			candidate.getUser().setUsername("epsilon56675");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("Eric");
			candidate.getContactDetails().setEmail("delta81911");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("epsilon");
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.28 18:18:40 EDT"));
			candidate.getUser().setUsername("Mark99983");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("theta");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("Malissa57128");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("theta");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.21 13:43:41 EDT"));
			candidate.getUser().setUsername("alpha394");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Eric");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("Mark");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("alpha76322");

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
