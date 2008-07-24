package com.cc.civlit.service;

import com.cc.civlit.domain.LitigationCase;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class LitigationCaseDaoTest extends AbstractJpaTests {

	protected LitigationCase litigationCaseInstance = new LitigationCase();

	protected LitigationCaseService litigationCaseService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setLitigationCaseService(
			LitigationCaseService litigationCaseService) {
		this.litigationCaseService = litigationCaseService;
	}

	protected TestDataFactory litigationCaseTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("litigationCaseTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			litigationCaseInstance.setName("theta");
			litigationCaseInstance.setAccountName("Mark");
			litigationCaseInstance.setCourtFileNumber("Malissa");
			litigationCaseInstance.setStyleOfCase("delta");
			litigationCaseInstance
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT1);
			litigationCaseInstance
					.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

			TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("divsionTestDataFactory");

			litigationCaseInstance
					.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
							.loadOneRecord());

			litigationCaseService.save(litigationCaseInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			LitigationCase litigationCase = new LitigationCase();

			try {

				litigationCase.setName("pi");
				litigationCase.setAccountName("John");
				litigationCase.setCourtFileNumber("Mark");
				litigationCase.setStyleOfCase("theta");
				litigationCase
						.setProceedingType(com.cc.civlit.domain.ProceedingType.PT2);
				litigationCase
						.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

				TestDataFactory divsionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("divsionTestDataFactory");

				litigationCase
						.setDivsion((com.cc.civlit.domain.courtdivisions.Divsion) divsionTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			litigationCaseService.save(litigationCase);
			assertNotNull(litigationCase.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			LitigationCase litigationCase = (LitigationCase) litigationCaseTestDataFactory
					.loadOneRecord();

			litigationCase.setName("Wilson");
			litigationCase.setAccountName("gamma");
			litigationCase.setCourtFileNumber("Mark");
			litigationCase.setStyleOfCase("theta");
			litigationCase
					.setProceedingType(com.cc.civlit.domain.ProceedingType.PT2);
			litigationCase
					.setCaseType(com.cc.civlit.domain.CaseType.INSOLVENCY);

			litigationCaseService.save(litigationCase);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(litigationCaseService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = litigationCaseService.getCount();
			LitigationCase litigationCase = (LitigationCase) litigationCaseTestDataFactory
					.loadOneRecord();
			litigationCaseService.delete(litigationCase);
			newCount = litigationCaseService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			LitigationCase litigationCase = litigationCaseService
					.load(litigationCaseInstance.getId());
			assertNotNull(litigationCase.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<LitigationCase> litigationCases = litigationCaseService
					.searchByExample(litigationCaseInstance);
			assertTrue(!litigationCases.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
