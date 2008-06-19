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

			examCreator.setFirstName("Eric");
			examCreator.setLastName("Eric");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.24 20:09:12 EDT"));
			examCreator.getUser().setUsername("Mark52185");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("pi");
			examCreator.getContactDetails().setCity("beta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("Wilson");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("John28796");

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
			examCreator.setLastName("Wilson");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.14 05:38:39 EDT"));
			examCreator.getUser().setUsername("delta61265");
			examCreator.getUser().setPassword("Mark");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("theta");
			examCreator.getContactDetails().setState("Lavendar");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("alpha4832");

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
			examCreator.setLastName("delta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.20 00:18:07 EDT"));
			examCreator.getUser().setUsername("gamma14580");
			examCreator.getUser().setPassword("beta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Eric");
			examCreator.getContactDetails().setCity("epsilon");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("beta96967");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("zeta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.12 16:28:39 EDT"));
			examCreator.getUser().setUsername("Lavendar92285");
			examCreator.getUser().setPassword("alpha");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Malissa");
			examCreator.getContactDetails().setCity("John");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("pi");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("alpha65115");

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
			examCreator.setLastName("Wilson");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.21 13:16:25 EDT"));
			examCreator.getUser().setUsername("beta48468");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("delta");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("John");
			examCreator.getContactDetails().setEmail("delta99140");

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
