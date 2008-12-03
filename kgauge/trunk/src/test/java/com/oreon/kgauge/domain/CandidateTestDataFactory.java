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
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.06 17:36:14 EST"));
			candidate.getUser().setUsername("epsilon12919");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("zeta");
			candidate.getContactDetails().setState("Mark");
			candidate.getContactDetails().setCountry("epsilon");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("Wilson34262");

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
			candidate.setLastName("Malissa");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.23 04:24:32 EST"));
			candidate.getUser().setUsername("delta19701");
			candidate.getUser().setPassword("theta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Mark");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("epsilon");
			candidate.getContactDetails().setCountry("gamma");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("Mark35232");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("delta");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.13 22:13:27 EST"));
			candidate.getUser().setUsername("Eric89261");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Eric");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("theta");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("zeta43157");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("John");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.11.12 05:26:47 EST"));
			candidate.getUser().setUsername("beta96118");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Malissa");
			candidate.getContactDetails().setCountry("pi");
			candidate.getContactDetails().setZip("zeta");
			candidate.getContactDetails().setPhone("Lavendar");
			candidate.getContactDetails().setEmail("gamma88292");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Mark");
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.12.22 11:36:14 EST"));
			candidate.getUser().setUsername("John37573");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("pi");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Lavendar68746");

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
