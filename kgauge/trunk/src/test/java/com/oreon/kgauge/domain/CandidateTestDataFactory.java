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
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.01 01:40:33 EST"));
			candidate.getUser().setUsername("Wilson54623");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("John58990");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("epsilon");
			candidate.setLastName("Mark");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.28 23:54:58 EST"));
			candidate.getUser().setUsername("gamma29276");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("Wilson");
			candidate.getContactDetails().setEmail("Eric45125");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Mark");
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.18 10:48:51 EST"));
			candidate.getUser().setUsername("Lavendar808");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("alpha");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("Lavendar");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("Wilson");
			candidate.getContactDetails().setEmail("gamma94609");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Wilson");
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.19 20:32:11 EST"));
			candidate.getUser().setUsername("delta66009");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Malissa39492");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("pi");
			candidate.setLastName("theta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.09 00:14:26 EST"));
			candidate.getUser().setUsername("alpha29512");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("beta39547");

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
