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

			examCreator.setFirstName("pi");
			examCreator.setLastName("Malissa");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.05 06:54:35 GMT"));
			examCreator.getUser().setUserName("pi60326");
			examCreator.getUser().setPassword("gamma");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("zeta");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("pi");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("Lavendar");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("Malissa53316");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("pi");
			examCreator.setLastName("alpha");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.13 01:22:24 GMT"));
			examCreator.getUser().setUserName("zeta44807");
			examCreator.getUser().setPassword("Wilson");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("delta");
			examCreator.getContactDetails().setCity("alpha");
			examCreator.getContactDetails().setState("epsilon");
			examCreator.getContactDetails().setCountry("Eric");
			examCreator.getContactDetails().setZip("Mark");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("alpha14405");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("gamma");
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.12 05:50:10 GMT"));
			examCreator.getUser().setUserName("epsilon55503");
			examCreator.getUser().setPassword("pi");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("Malissa");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("gamma");
			examCreator.getContactDetails().setEmail("Eric81248");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("John");
			examCreator.setLastName("Mark");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.22 22:20:43 GMT"));
			examCreator.getUser().setUserName("theta12814");
			examCreator.getUser().setPassword("beta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Mark");
			examCreator.getContactDetails().setCity("alpha");
			examCreator.getContactDetails().setState("zeta");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("zeta");
			examCreator.getContactDetails().setPhone("alpha");
			examCreator.getContactDetails().setEmail("epsilon51971");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("pi");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.23 22:03:30 GMT"));
			examCreator.getUser().setUserName("John88408");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Wilson");
			examCreator.getContactDetails().setCity("Malissa");
			examCreator.getContactDetails().setState("pi");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("delta90660");

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
