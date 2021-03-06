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

			candidate.setFirstName("Eric");
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2009.01.02 15:09:35 EST"));
			candidate.getUser().setUsername("delta47837");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("delta");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("Malissa");
			candidate.getContactDetails().setEmail("pi6755");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Eric");
			candidate.setLastName("epsilon");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.09 04:09:35 EST"));
			candidate.getUser().setUsername("Mark21722");
			candidate.getUser().setPassword("Lavendar");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("alpha35002");

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
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.13 03:54:34 EST"));
			candidate.getUser().setUsername("theta40384");
			candidate.getUser().setPassword("Malissa");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("theta");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("Wilson53832");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("pi");
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.20 07:33:28 EST"));
			candidate.getUser().setUsername("Malissa76142");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("zeta");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("theta49094");

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
					.parse("2008.12.20 03:01:14 EST"));
			candidate.getUser().setUsername("gamma85449");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Wilson");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("delta78112");

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
