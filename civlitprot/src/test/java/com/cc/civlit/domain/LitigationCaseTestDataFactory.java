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

import com.cc.civlit.service.LitigationCaseService;

@Transactional
public class LitigationCaseTestDataFactory
		extends
			AbstractTestDataFactory<LitigationCase> {

	private List<LitigationCase> litigationCases = new ArrayList<LitigationCase>();

	private static final Logger logger = Logger
			.getLogger(LitigationCaseTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	LitigationCaseService litigationCaseService;

	public LitigationCaseService getLitigationCaseService() {
		return litigationCaseService;
	}

	public void setLitigationCaseService(
			LitigationCaseService litigationCaseService) {
		this.litigationCaseService = litigationCaseService;
	}

	public void register(LitigationCase litigationCase) {
		litigationCases.add(litigationCase);
	}

	public LitigationCase createLitigationCaseOne() {
		LitigationCase litigationCase = new LitigationCase();

		try {

			litigationCase.setName("Wilson");
			litigationCase.setAccountName("delta");
			litigationCase.setCourtFileNumber("zeta");
			litigationCase.setStyleOfCase("epsilon");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT1);
			litigationCase.setCaseType(com.cc.civlit.domain.CaseType.OTHER);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCase
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			litigationCase
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(litigationCase);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return litigationCase;
	}

	public LitigationCase createLitigationCaseTwo() {
		LitigationCase litigationCase = new LitigationCase();

		try {

			litigationCase.setName("pi");
			litigationCase.setAccountName("beta");
			litigationCase.setCourtFileNumber("epsilon");
			litigationCase.setStyleOfCase("John");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT2);
			litigationCase
					.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCase
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			litigationCase
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(litigationCase);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return litigationCase;
	}

	public LitigationCase createLitigationCaseThree() {
		LitigationCase litigationCase = new LitigationCase();

		try {

			litigationCase.setName("epsilon");
			litigationCase.setAccountName("delta");
			litigationCase.setCourtFileNumber("zeta");
			litigationCase.setStyleOfCase("delta");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT1);
			litigationCase
					.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCase
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			litigationCase
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(litigationCase);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return litigationCase;
	}

	public LitigationCase createLitigationCaseFour() {
		LitigationCase litigationCase = new LitigationCase();

		try {

			litigationCase.setName("gamma");
			litigationCase.setAccountName("Mark");
			litigationCase.setCourtFileNumber("alpha");
			litigationCase.setStyleOfCase("John");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT1);
			litigationCase
					.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCase
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			litigationCase
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(litigationCase);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return litigationCase;
	}

	public LitigationCase createLitigationCaseFive() {
		LitigationCase litigationCase = new LitigationCase();

		try {

			litigationCase.setName("beta");
			litigationCase.setAccountName("gamma");
			litigationCase.setCourtFileNumber("John");
			litigationCase.setStyleOfCase("Eric");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT1);
			litigationCase.setCaseType(com.cc.civlit.domain.CaseType.OTHER);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCase
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("firmTestDataFactory");

			litigationCase
					.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
							.loadOneRecord());

			register(litigationCase);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return litigationCase;
	}

	public LitigationCase loadOneRecord() {
		List<LitigationCase> litigationCases = litigationCaseService.loadAll();

		if (litigationCases.isEmpty()) {
			persistAll();
			litigationCases = litigationCaseService.loadAll();
		}

		return litigationCases
				.get(new Random().nextInt(litigationCases.size()));
	}

	public List<LitigationCase> getAllAsList() {

		if (litigationCases.isEmpty()) {

			createLitigationCaseOne();
			createLitigationCaseTwo();
			createLitigationCaseThree();
			createLitigationCaseFour();
			createLitigationCaseFive();

		}

		return litigationCases;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (LitigationCase litigationCase : litigationCases) {
			try {
				litigationCaseService.save(litigationCase);
			} catch (BusinessException be) {
				logger.warn(" LitigationCase "
						+ litigationCase.getDisplayName()
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
			LitigationCase litigationCase = createRandomLitigationCase();
			litigationCaseService.save(litigationCase);
		}
	}

	public LitigationCase createRandomLitigationCase() {
		LitigationCase litigationCase = new LitigationCase();

		litigationCase.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		litigationCase.setAccountName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		litigationCase.setCourtFileNumber((String) RandomValueGeneratorFactory
				.createInstance("String"));
		litigationCase.setStyleOfCase((String) RandomValueGeneratorFactory
				.createInstance("String"));
		litigationCase
				.setProceedingType((ProceedingType) RandomValueGeneratorFactory
						.createInstance("ProceedingType"));
		litigationCase.setCaseType((CaseType) RandomValueGeneratorFactory
				.createInstance("CaseType"));

		TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("divsionTestDataFactory");

		litigationCase
				.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
						.loadOneRecord());

		TestDataFactory firmTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("firmTestDataFactory");

		litigationCase.setFirm((com.cc.civlit.domain.Firm) firmTestDataFactory
				.loadOneRecord());

		return litigationCase;
	}

}
