package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.service.CandidateService;

@Transactional
public class CandidateTestDataFactory
		extends
			AbstractTestDataFactory<Candidate> {

	private List<Candidate> candidates = new ArrayList<Candidate>();

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
			candidate.setLastName("Lavendar");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.15 10:52:24 EST"));
			candidate.getUser().setUserName("Eric88109");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("theta");
			candidate.getContactDetails().setCity("beta");
			candidate.getContactDetails().setState("gamma");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("beta55031");

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
					.parse("2008.02.16 11:52:56 EST"));
			candidate.getUser().setUserName("Lavendar59624");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Wilson");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("gamma");
			candidate.getContactDetails().setEmail("pi88070");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Malissa");
			candidate.setLastName("gamma");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.12 12:41:51 EDT"));
			candidate.getUser().setUserName("John44997");
			candidate.getUser().setPassword("Mark");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("Wilson");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("John56567");

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
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.28 18:16:49 EST"));
			candidate.getUser().setUserName("Eric51161");
			candidate.getUser().setPassword("epsilon");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("John");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Eric71184");

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
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.23 22:49:04 EST"));
			candidate.getUser().setUserName("Eric48154");
			candidate.getUser().setPassword("Eric");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("John");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("Eric");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("theta17182");

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
			candidateService.save(candidate);
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
