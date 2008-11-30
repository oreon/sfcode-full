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

			examCreator.setFirstName("pi");
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.21 14:23:18 EST"));
			examCreator.getUser().setUsername("Malissa53458");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("epsilon");
			examCreator.getContactDetails().setCity("epsilon");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("Eric");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("alpha50082");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorTwo() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("theta");
			examCreator.setLastName("pi");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.24 13:31:40 EST"));
			examCreator.getUser().setUsername("John78067");
			examCreator.getUser().setPassword("delta");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("Malissa");
			examCreator.getContactDetails().setState("theta");
			examCreator.getContactDetails().setCountry("pi");
			examCreator.getContactDetails().setZip("delta");
			examCreator.getContactDetails().setPhone("Mark");
			examCreator.getContactDetails().setEmail("gamma47282");

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
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.29 22:22:46 EST"));
			examCreator.getUser().setUsername("alpha12454");
			examCreator.getUser().setPassword("Eric");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("Mark");
			examCreator.getContactDetails().setCountry("John");
			examCreator.getContactDetails().setZip("zeta");
			examCreator.getContactDetails().setPhone("delta");
			examCreator.getContactDetails().setEmail("pi65196");

			register(examCreator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return examCreator;
	}

	public ExamCreator createExamCreatorFour() {
		ExamCreator examCreator = new ExamCreator();

		try {

			examCreator.setFirstName("Lavendar");
			examCreator.setLastName("Eric");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.15 16:13:51 EST"));
			examCreator.getUser().setUsername("alpha83931");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Mark");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("alpha");
			examCreator.getContactDetails().setCountry("Malissa");
			examCreator.getContactDetails().setZip("Mark");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("Lavendar17864");

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
			examCreator.setLastName("Wilson");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.22 21:48:20 EST"));
			examCreator.getUser().setUsername("delta60525");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("John");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("theta");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("Lavendar");
			examCreator.getContactDetails().setPhone("theta");
			examCreator.getContactDetails().setEmail("Malissa53711");

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
