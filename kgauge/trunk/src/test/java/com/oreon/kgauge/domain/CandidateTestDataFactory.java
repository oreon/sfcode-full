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

			candidate.setFirstName("beta");
			candidate.setLastName("epsilon");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.23 02:58:26 EST"));
			candidate.getUser().setUserName("Wilson10540");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("epsilon");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("Eric");
			candidate.getContactDetails().setPhone("gamma");
			candidate.getContactDetails().setEmail("delta8331");

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
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.04 14:32:18 EST"));
			candidate.getUser().setUserName("theta56170");
			candidate.getUser().setPassword("gamma");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Malissa");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("Mark");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("Lavendar81381");

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
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.02 14:41:46 EST"));
			candidate.getUser().setUserName("Lavendar94668");
			candidate.getUser().setPassword("Lavendar");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("theta");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("beta45278");

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
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.14 01:50:04 EDT"));
			candidate.getUser().setUserName("alpha1053");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Wilson");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Eric");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Lavendar68881");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Wilson");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.11 21:02:18 EST"));
			candidate.getUser().setUserName("delta82437");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("Lavendar");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Malissa");
			candidate.getContactDetails().setEmail("theta14523");

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
