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

			examCreator.setFirstName("alpha");
			examCreator.setLastName("Eric");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.23 21:43:54 EST"));
			examCreator.getUser().setUserName("gamma71729");
			examCreator.getUser().setPassword("Lavendar");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("beta");
			examCreator.getContactDetails().setCity("Eric");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("Wilson");
			examCreator.getContactDetails().setEmail("Wilson32410");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Mark");
			examCreator.setLastName("delta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.27 15:56:08 EST"));
			examCreator.getUser().setUserName("theta41809");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Mark");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("Eric");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("Malissa");
			examCreator.getContactDetails().setEmail("Mark52187");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("John");
			examCreator.setLastName("Eric");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.19 05:33:21 EST"));
			examCreator.getUser().setUserName("alpha45990");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("John");
			examCreator.getContactDetails().setCity("pi");
			examCreator.getContactDetails().setState("John");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("John44988");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Malissa");
			examCreator.setLastName("Mark");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.12 04:06:41 EST"));
			examCreator.getUser().setUserName("gamma30778");
			examCreator.getUser().setPassword("Eric");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Mark");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("alpha");
			examCreator.getContactDetails().setZip("alpha");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("delta90702");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("theta");
			examCreator.setLastName("gamma");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.01.29 21:40:01 EST"));
			examCreator.getUser().setUserName("Malissa29105");
			examCreator.getUser().setPassword("Eric");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("pi");
			examCreator.getContactDetails().setPhone("alpha");
			examCreator.getContactDetails().setEmail("John67135");

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
