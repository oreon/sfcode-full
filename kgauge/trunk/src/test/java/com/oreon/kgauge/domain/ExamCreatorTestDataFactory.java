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

			examCreator.setFirstName("Eric");
			examCreator.setLastName("zeta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.07 04:50:10 EST"));
			examCreator.getUser().setUserName("John83997");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("John");
			examCreator.getContactDetails().setPhone("Wilson");
			examCreator.getContactDetails().setEmail("John47807");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("beta");
			examCreator.setLastName("Mark");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.14 05:59:05 EST"));
			examCreator.getUser().setUserName("Mark87131");
			examCreator.getUser().setPassword("Wilson");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("alpha");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("Wilson");
			examCreator.getContactDetails().setPhone("Malissa");
			examCreator.getContactDetails().setEmail("Eric53497");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("gamma");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.15 04:27:23 EST"));
			examCreator.getUser().setUserName("Wilson71481");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("zeta");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("Wilson");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("pi");
			examCreator.getContactDetails().setPhone("delta");
			examCreator.getContactDetails().setEmail("epsilon37553");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("zeta");
			examCreator.setLastName("Eric");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.27 22:56:17 EST"));
			examCreator.getUser().setUserName("epsilon17479");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("theta");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("zeta");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("epsilon");
			examCreator.getContactDetails().setEmail("delta85205");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Lavendar");
			examCreator.setLastName("delta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.11 11:05:45 EDT"));
			examCreator.getUser().setUserName("delta56905");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("epsilon");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("Malissa");
			examCreator.getContactDetails().setCountry("alpha");
			examCreator.getContactDetails().setZip("Lavendar");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("beta74933");

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
