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

			candidate.setFirstName("epsilon");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.30 06:51:01 EST"));
			candidate.getUser().setUsername("beta95324");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("Eric");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Lavendar28161");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("pi");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.30 19:08:47 EST"));
			candidate.getUser().setUsername("gamma68474");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Malissa");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("John79257");

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
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.20 20:41:01 EST"));
			candidate.getUser().setUsername("John39316");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Wilson");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("Malissa");
			candidate.getContactDetails().setEmail("theta46352");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("theta");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.16 07:32:07 EST"));
			candidate.getUser().setUsername("alpha28182");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Lavendar");
			candidate.getContactDetails().setState("zeta");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("epsilon55510");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("delta");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.01 07:23:16 EST"));
			candidate.getUser().setUsername("pi95535");
			candidate.getUser().setPassword("Malissa");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("zeta15856");

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
