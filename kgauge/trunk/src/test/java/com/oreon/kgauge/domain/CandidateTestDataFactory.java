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

			candidate.setFirstName("Mark");
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.18 04:17:37 EDT"));
			candidate.getUser().setUserName("pi23886");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Eric");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("Malissa");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Mark3620");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("zeta");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.26 11:38:10 EST"));
			candidate.getUser().setUserName("Lavendar80112");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("beta90166");

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
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.12 13:20:57 EDT"));
			candidate.getUser().setUserName("Wilson15713");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("Eric");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Wilson42962");

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
					.parse("2008.02.24 16:48:10 EST"));
			candidate.getUser().setUserName("alpha65804");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("theta");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("beta82940");

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
			candidate.setLastName("theta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.02 18:20:57 EDT"));
			candidate.getUser().setUserName("Lavendar17195");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("John");
			candidate.getContactDetails().setEmail("pi35511");

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
		candidate.getUser().setUserName(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		candidate.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("Boolean"));
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
