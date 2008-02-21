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

			candidate.setFirstName("John");
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.11 22:24:07 EDT"));
			candidate.getUser().setUserName("Eric10957");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("gamma");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("beta");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("pi");
			candidate.getContactDetails().setEmail("Lavendar54962");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateTwo() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("John");
			candidate.setLastName("delta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.06 00:56:54 EST"));
			candidate.getUser().setUserName("gamma23826");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("theta");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("alpha");
			candidate.getContactDetails().setCountry("theta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Eric");
			candidate.getContactDetails().setEmail("zeta17604");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("pi");
			candidate.setLastName("beta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.04 13:32:29 EST"));
			candidate.getUser().setUserName("delta40925");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("alpha");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("delta");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("pi12528");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("alpha");
			candidate.setLastName("Mark");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.02 04:27:27 EST"));
			candidate.getUser().setUserName("alpha24045");
			candidate.getUser().setPassword("alpha");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("beta");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("delta");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("epsilon");
			candidate.getContactDetails().setEmail("zeta21266");

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
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.01.29 14:04:07 EST"));
			candidate.getUser().setUserName("gamma22801");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("Wilson");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("Malissa");
			candidate.getContactDetails().setPhone("Eric");
			candidate.getContactDetails().setEmail("epsilon91385");

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
