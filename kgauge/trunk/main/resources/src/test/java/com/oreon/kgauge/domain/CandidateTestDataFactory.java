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

			candidate.setFirstName("Malissa");
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.27 05:16:56 EDT"));
			candidate.getUser().setUsername("theta72240");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("Lavendar");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Wilson");
			candidate.getContactDetails().setEmail("John31164");

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
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.04 00:22:31 EDT"));
			candidate.getUser().setUsername("beta28590");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("theta");
			candidate.getContactDetails().setCity("Mark");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("Eric38608");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("alpha");
			candidate.setLastName("theta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.17 19:59:11 EDT"));
			candidate.getUser().setUsername("epsilon82278");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("alpha");
			candidate.getContactDetails().setZip("pi");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("pi32652");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("gamma");
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.20 23:03:36 EDT"));
			candidate.getUser().setUsername("alpha72747");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("theta");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("delta13754");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("gamma");
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.05.20 21:55:51 EDT"));
			candidate.getUser().setUsername("beta89977");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("Malissa");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("Malissa");
			candidate.getContactDetails().setEmail("delta46997");

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
