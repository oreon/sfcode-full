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
			examCreator.setLastName("epsilon");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.16 10:46:21 EDT"));
			examCreator.getUser().setUsername("pi97713");
			examCreator.getUser().setPassword("Malissa");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("theta");
			examCreator.getContactDetails().setState("Lavendar");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("alpha");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("Lavendar88804");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Wilson");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.10 04:17:59 EDT"));
			examCreator.getUser().setUsername("pi29380");
			examCreator.getUser().setPassword("Mark");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("gamma");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("gamma");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("alpha");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("gamma69029");

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
			examCreator.setLastName("John");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.18 04:31:52 EDT"));
			examCreator.getUser().setUsername("alpha56738");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Wilson");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("delta");
			examCreator.getContactDetails().setCountry("Wilson");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("Eric");
			examCreator.getContactDetails().setEmail("gamma34101");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("alpha");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.04.12 23:27:59 EDT"));
			examCreator.getUser().setUsername("alpha37855");
			examCreator.getUser().setPassword("pi");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("beta");
			examCreator.getContactDetails().setCity("John");
			examCreator.getContactDetails().setState("Malissa");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("Mark85750");

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
					.parse("2008.04.23 10:23:34 EDT"));
			examCreator.getUser().setUsername("gamma4399");
			examCreator.getUser().setPassword("epsilon");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("theta");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("Lavendar");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("Malissa");
			examCreator.getContactDetails().setEmail("Lavendar37999");

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
