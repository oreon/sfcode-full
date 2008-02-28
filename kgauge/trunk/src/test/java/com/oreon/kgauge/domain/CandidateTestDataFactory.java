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

			candidate.setFirstName("Malissa");
			candidate.setLastName("Eric");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.03.03 02:19:34 GMT"));
			candidate.getUser().setUserName("alpha9770");
			candidate.getUser().setPassword("pi");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Malissa");
			candidate.getContactDetails().setCity("Eric");
			candidate.getContactDetails().setState("Wilson");
			candidate.getContactDetails().setCountry("pi");
			candidate.getContactDetails().setZip("Mark");
			candidate.getContactDetails().setPhone("theta");
			candidate.getContactDetails().setEmail("Eric44730");

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
			candidate.setLastName("alpha");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.26 20:09:02 GMT"));
			candidate.getUser().setUserName("John96866");
			candidate.getUser().setPassword("delta");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("delta");
			candidate.getContactDetails().setCity("Malissa");
			candidate.getContactDetails().setState("theta");
			candidate.getContactDetails().setCountry("delta");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("Wilson");
			candidate.getContactDetails().setEmail("Lavendar35252");

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
			candidate.setLastName("delta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.19 22:49:34 GMT"));
			candidate.getUser().setUserName("beta56609");
			candidate.getUser().setPassword("Lavendar");
			candidate.getUser().setEnabled(false);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("zeta");
			candidate.getContactDetails().setCountry("Eric");
			candidate.getContactDetails().setZip("beta");
			candidate.getContactDetails().setPhone("Mark");
			candidate.getContactDetails().setEmail("Mark19719");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFour() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("Lavendar");
			candidate.setLastName("theta");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.19 02:11:13 GMT"));
			candidate.getUser().setUserName("alpha23079");
			candidate.getUser().setPassword("John");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("pi");
			candidate.getContactDetails().setCity("epsilon");
			candidate.getContactDetails().setState("Eric");
			candidate.getContactDetails().setCountry("zeta");
			candidate.getContactDetails().setZip("gamma");
			candidate.getContactDetails().setPhone("Eric");
			candidate.getContactDetails().setEmail("alpha42994");

			register(candidate);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return candidate;
	}

	public Candidate createCandidateFive() {
		Candidate candidate = new Candidate();

		try {

			candidate.setFirstName("alpha");
			candidate.setLastName("John");
			candidate.setDateOfBirth(dateFormat
					.parse("2008.02.14 09:17:53 GMT"));
			candidate.getUser().setUserName("Wilson95542");
			candidate.getUser().setPassword("Malissa");
			candidate.getUser().setEnabled(true);
			candidate.getContactDetails().setStreetAddress("Lavendar");
			candidate.getContactDetails().setCity("pi");
			candidate.getContactDetails().setState("alpha");
			candidate.getContactDetails().setCountry("pi");
			candidate.getContactDetails().setZip("John");
			candidate.getContactDetails().setPhone("zeta");
			candidate.getContactDetails().setEmail("alpha61710");

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
