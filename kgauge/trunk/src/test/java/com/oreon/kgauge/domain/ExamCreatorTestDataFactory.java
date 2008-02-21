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

import com.oreon.kgauge.service.ExamCreatorService;

@Transactional
public class ExamCreatorTestDataFactory
		extends
			AbstractTestDataFactory<ExamCreator> {

	private List<ExamCreator> examCreators = new ArrayList<ExamCreator>();

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ExamCreatorService examCreatorService;

	public ExamCreatorService getExamCreatorService() {
		return examCreatorService;
	}

	public void setExamCreatorService(ExamCreatorService examCreatorService) {
		this.examCreatorService = examCreatorService;
	}

	public void register(ExamCreator examCreator) {
		examCreators.add(examCreator);
	}

	public ExamCreator createExamCreatorOne() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("Mark");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.09 01:16:23 EST"));
			examCreator.getUser().setUserName("zeta68384");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("delta");
			examCreator.getContactDetails().setCity("theta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("Mark");
			examCreator.getContactDetails().setPhone("beta");
			examCreator.getContactDetails().setEmail("John63756");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("alpha");
			examCreator.setLastName("Wilson");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.18 16:50:15 EST"));
			examCreator.getUser().setUserName("gamma14034");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("theta");
			examCreator.getContactDetails().setCity("alpha");
			examCreator.getContactDetails().setState("theta");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("pi");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("Wilson95877");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Mark");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.01 15:59:43 EST"));
			examCreator.getUser().setUserName("beta62980");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("gamma91170");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("epsilon");
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.16 03:05:50 EDT"));
			examCreator.getUser().setUserName("delta91873");
			examCreator.getUser().setPassword("epsilon");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("alpha");
			examCreator.getContactDetails().setCity("pi");
			examCreator.getContactDetails().setState("Malissa");
			examCreator.getContactDetails().setCountry("Malissa");
			examCreator.getContactDetails().setZip("pi");
			examCreator.getContactDetails().setPhone("beta");
			examCreator.getContactDetails().setEmail("Mark83047");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Mark");
			examCreator.setLastName("epsilon");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.22 23:47:28 EST"));
			examCreator.getUser().setUserName("pi83350");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("pi");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("pi");
			examCreator.getContactDetails().setCountry("Mark");
			examCreator.getContactDetails().setZip("Wilson");
			examCreator.getContactDetails().setPhone("alpha");
			examCreator.getContactDetails().setEmail("zeta58134");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator loadOneRecord() {
		List<ExamCreator> examCreators = examCreatorService.loadAll();

		if (examCreators.isEmpty()) {
			persistAll();
			examCreators = examCreatorService.loadAll();
		}

		return examCreators.get(new Random().nextInt(examCreators.size()));
	}

	public List<ExamCreator> getAllAsList() {

		if (examCreators.isEmpty()) {

			createExamCreatorOne();
			createExamCreatorTwo();
			createExamCreatorThree();
			createExamCreatorFour();
			createExamCreatorFive();

		}

		return examCreators;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (ExamCreator examCreator : examCreators) {
			examCreatorService.save(examCreator);
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
			ExamCreator examCreator = createRandomExamCreator();
			examCreatorService.save(examCreator);
		}
	}

	public ExamCreator createRandomExamCreator() {
		ExamCreator examCreator = new ExamCreator();

		examCreator.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		examCreator.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		examCreator.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
				.createInstance("Date"));
		examCreator.getUser().setUserName(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("Boolean"));
		examCreator.getContactDetails().setStreetAddress(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setCity(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setState(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setCountry(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setZip(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setPhone(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getContactDetails().setEmail(
				(String) RandomValueGeneratorFactory.createInstance("String"));

		return examCreator;
	}

}
