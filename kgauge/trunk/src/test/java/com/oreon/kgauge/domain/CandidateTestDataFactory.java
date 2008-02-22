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

			candidate.setFirstName("Mark");
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.08 23:44:25 EST"));
			candidate.getUser().setUserName("delta42518");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("zeta");
			candidate.getContactDetails().setCity("John");
			candidate.getContactDetails().setState("beta");
			candidate.getContactDetails().setCountry("Wilson");
			candidate.getContactDetails().setZip("epsilon");
			candidate.getContactDetails().setPhone("Malissa");
			candidate.getContactDetails().setEmail("epsilon71580");

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
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.18 23:00:00 EST"));
			candidate.getUser().setUserName("alpha35229");
			candidate.getUser().setPassword("beta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("gamma");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("alpha");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("theta19846");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateThree() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Lavendar");
			candidate.setLastName("pi");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.20 20:21:05 EST"));
			candidate.getUser().setUserName("Malissa76295");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("alpha");
			candidate.getContactDetails().setCity("Wilson");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("theta");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("epsilon15994");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Mark");
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.16 02:37:13 EDT"));
			candidate.getUser().setUserName("Eric30157");
			candidate.getUser().setPassword("Wilson");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("gamma");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("alpha");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("Lavendar");
			candidate.getContactDetails().setPhone("beta");
			candidate.getContactDetails().setEmail("pi78588");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("John");
			candidate.setLastName("zeta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.09 23:03:20 EST"));
			candidate.getUser().setUserName("epsilon64496");
			candidate.getUser().setPassword("zeta");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("epsilon");
			candidate.getContactDetails().setCity("theta");
			candidate.getContactDetails().setState("Lavendar");
			candidate.getContactDetails().setCountry("alpha");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("delta");
			candidate.getContactDetails().setEmail("Eric28689");

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
