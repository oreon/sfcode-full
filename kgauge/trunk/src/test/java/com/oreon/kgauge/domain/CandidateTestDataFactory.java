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

			candidate.setFirstName("Lavendar");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.08 10:33:01 EST"));
			candidate.getUser().setUsername("zeta7900");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("Mark");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("John");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("Eric");
			candidate.getContactDetails().setEmail("Wilson39403");

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
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.07 11:23:33 EDT"));
			candidate.getUser().setUsername("beta31226");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("Mark");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("Mark");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("epsilon85531");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("beta");
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.07 02:04:39 EDT"));
			candidate.getUser().setUsername("beta50836");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("Eric");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("Mark2004");

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
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.22 06:34:39 EDT"));
			candidate.getUser().setUsername("Wilson68270");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("gamma");
			candidate.getContactDetails().setEmail("zeta63837");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("zeta");
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.04.23 16:07:59 EDT"));
			candidate.getUser().setUsername("gamma59948");
			candidate.getUser().setPassword("Lavendar");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Mark");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("alpha");
			candidate.getContactDetails().setEmail("Lavendar38298");

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
