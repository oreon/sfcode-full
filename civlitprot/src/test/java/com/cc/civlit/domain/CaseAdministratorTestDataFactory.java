package com.cc.civlit.domain;

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

import com.cc.civlit.service.CaseAdministratorService;

@Transactional
public class CaseAdministratorTestDataFactory
		extends
			AbstractTestDataFactory<CaseAdministrator> {

	private List<CaseAdministrator> caseAdministrators = new ArrayList<CaseAdministrator>();

	private static final Logger logger = Logger
			.getLogger(CaseAdministratorTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CaseAdministratorService caseAdministratorService;

	public CaseAdministratorService getCaseAdministratorService() {
		return caseAdministratorService;
	}

	public void setCaseAdministratorService(
			CaseAdministratorService caseAdministratorService) {
		this.caseAdministratorService = caseAdministratorService;
	}

	public void register(CaseAdministrator caseAdministrator) {
		caseAdministrators.add(caseAdministrator);
	}

	public CaseAdministrator createCaseAdministratorOne() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		try {

			caseAdministrator.setFirstName("alpha");
			caseAdministrator.setLastName("zeta");
			caseAdministrator.setDateOfBirth(dateFormat
					.parse("2008.08.16 12:37:49 EDT"));
			caseAdministrator.setEmail("delta18710");

			register(caseAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return caseAdministrator;
	}

	public CaseAdministrator createCaseAdministratorTwo() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		try {

			caseAdministrator.setFirstName("Malissa");
			caseAdministrator.setLastName("pi");
			caseAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.23 00:20:36 EDT"));
			caseAdministrator.setEmail("Eric55800");

			register(caseAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return caseAdministrator;
	}

	public CaseAdministrator createCaseAdministratorThree() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		try {

			caseAdministrator.setFirstName("Lavendar");
			caseAdministrator.setLastName("Eric");
			caseAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.02 23:11:42 EDT"));
			caseAdministrator.setEmail("John45708");

			register(caseAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return caseAdministrator;
	}

	public CaseAdministrator createCaseAdministratorFour() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		try {

			caseAdministrator.setFirstName("beta");
			caseAdministrator.setLastName("zeta");
			caseAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.09 18:18:22 EDT"));
			caseAdministrator.setEmail("theta34132");

			register(caseAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return caseAdministrator;
	}

	public CaseAdministrator createCaseAdministratorFive() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		try {

			caseAdministrator.setFirstName("alpha");
			caseAdministrator.setLastName("gamma");
			caseAdministrator.setDateOfBirth(dateFormat
					.parse("2008.07.19 00:27:49 EDT"));
			caseAdministrator.setEmail("Malissa51965");

			register(caseAdministrator);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return caseAdministrator;
	}

	public CaseAdministrator loadOneRecord() {
		List<CaseAdministrator> caseAdministrators = caseAdministratorService
				.loadAll();

		if (caseAdministrators.isEmpty()) {
			persistAll();
			caseAdministrators = caseAdministratorService.loadAll();
		}

		return caseAdministrators.get(new Random().nextInt(caseAdministrators
				.size()));
	}

	public List<CaseAdministrator> getAllAsList() {

		if (caseAdministrators.isEmpty()) {

			createCaseAdministratorOne();
			createCaseAdministratorTwo();
			createCaseAdministratorThree();
			createCaseAdministratorFour();
			createCaseAdministratorFive();

		}

		return caseAdministrators;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (CaseAdministrator caseAdministrator : caseAdministrators) {
			try {
				caseAdministratorService.save(caseAdministrator);
			} catch (BusinessException be) {
				logger.warn(" CaseAdministrator "
						+ caseAdministrator.getDisplayName()
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
			CaseAdministrator caseAdministrator = createRandomCaseAdministrator();
			caseAdministratorService.save(caseAdministrator);
		}
	}

	public CaseAdministrator createRandomCaseAdministrator() {
		CaseAdministrator caseAdministrator = new CaseAdministrator();

		caseAdministrator.setFirstName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		caseAdministrator.setLastName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		caseAdministrator
				.setDateOfBirth((java.util.Date) RandomValueGeneratorFactory
						.createInstance("Date"));
		caseAdministrator.setEmail((String) RandomValueGeneratorFactory
				.createInstance("String"));

		return caseAdministrator;
	}

}
