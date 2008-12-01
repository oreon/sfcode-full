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

			candidate.setFirstName("gamma");
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.23 02:12:32 EST"));
			candidate.getUser().setUsername("pi36731");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Malissa");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("theta");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("delta438");

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
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.24 12:39:44 EST"));
			candidate.getUser().setUsername("Eric58334");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("Mark");
			candidate.getContactDetails().setState("gamma");
			candidate.getContactDetails().setCountry("pi");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("delta57349");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Eric");
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.09 01:50:17 EST"));
			candidate.getUser().setUsername("beta68192");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("beta22308");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Malissa");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.09 19:22:32 EST"));
			candidate.getUser().setUsername("Eric40920");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Eric");
			candidate.getContactDetails().setCity("Wilson");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("pi");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Eric38781");

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
			candidate.setLastName("Wilson");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.16 16:22:32 EST"));
			candidate.getUser().setUsername("epsilon98938");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("Wilson42205");

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
