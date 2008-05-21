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

			candidate.setFirstName("pi");
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.27 15:14:40 EDT"));
			candidate.getUser().setUsername("epsilon32038");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("theta");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("Eric84468");

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
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.14 05:17:28 EDT"));
			candidate.getUser().setUsername("pi78083");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("Wilson11245");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("zeta");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.02 14:05:46 EDT"));
			candidate.getUser().setUsername("pi43010");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("gamma");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Malissa11467");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("delta");
			candidate.setLastName("delta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.19 07:52:26 EDT"));
			candidate.getUser().setUsername("theta99385");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Wilson");
			candidate.getContactDetails().setEmail("Eric46333");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Lavendar");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.06.13 19:46:55 EDT"));
			candidate.getUser().setUsername("alpha96248");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("gamma");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("epsilon95787");

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
