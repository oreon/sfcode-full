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

import com.oreon.kgauge.service.ExamCreatorService;

@Transactional
public class ExamCreatorTestDataFactory
		extends
			AbstractTestDataFactory<ExamCreator> {

	private List<ExamCreator> examCreators = new ArrayList<ExamCreator>();

	private static final Logger logger = Logger
			.getLogger(ExamCreatorTestDataFactory.class);

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
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.10 20:11:44 EST"));
			examCreator.getUser().setUsername("delta9361");
			examCreator.getUser().setPassword("Wilson");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("Malissa");
			examCreator.getContactDetails().setCountry("Mark");
			examCreator.getContactDetails().setZip("delta");
			examCreator.getContactDetails().setPhone("alpha");
			examCreator.getContactDetails().setEmail("alpha34364");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("gamma");
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.29 19:11:11 EST"));
			examCreator.getUser().setUsername("zeta87442");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Wilson");
			examCreator.getContactDetails().setCity("Wilson");
			examCreator.getContactDetails().setState("pi");
			examCreator.getContactDetails().setCountry("zeta");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("Eric15659");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorThree() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Lavendar");
			examCreator.setLastName("Malissa");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.15 02:11:44 EST"));
			examCreator.getUser().setUsername("gamma17325");
			examCreator.getUser().setPassword("epsilon");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("Eric");
			examCreator.getContactDetails().setCountry("alpha");
			examCreator.getContactDetails().setZip("zeta");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("Eric81648");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("pi");
			examCreator.setLastName("pi");
			examCreator.setDateOfBirth(dateFormat
					.parse("2009.01.12 23:01:44 EST"));
			examCreator.getUser().setUsername("pi70458");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("delta");
			examCreator.getContactDetails().setCity("Mark");
			examCreator.getContactDetails().setState("Eric");
			examCreator.getContactDetails().setCountry("beta");
			examCreator.getContactDetails().setZip("John");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("gamma34653");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFive() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Malissa");
			examCreator.setLastName("zeta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.19 11:08:57 EST"));
			examCreator.getUser().setUsername("alpha81466");
			examCreator.getUser().setPassword("epsilon");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("Malissa");
			examCreator.getContactDetails().setState("alpha");
			examCreator.getContactDetails().setCountry("Eric");
			examCreator.getContactDetails().setZip("Lavendar");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("epsilon84090");

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
			try {
				examCreatorService.save(examCreator);
			} catch (BusinessException be) {
				logger.warn(" ExamCreator " + examCreator.getDisplayName()
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
		examCreator.getUser().setUsername(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser().setPassword(
				(String) RandomValueGeneratorFactory.createInstance("String"));
		examCreator.getUser()
				.setEnabled(
						(Boolean) RandomValueGeneratorFactory
								.createInstance("boolean"));
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
