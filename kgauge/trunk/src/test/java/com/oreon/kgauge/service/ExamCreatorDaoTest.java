package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.ExamCreator;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class ExamCreatorDaoTest extends AbstractJpaTests {

	protected ExamCreator examCreatorInstance = new ExamCreator();

	protected ExamCreatorService examCreatorService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setExamCreatorService(ExamCreatorService examCreatorService) {
		this.examCreatorService = examCreatorService;
	}

	protected TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("examCreatorTestDataFactory");

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

			examCreatorInstance.setFirstName("zeta");
			examCreatorInstance.setLastName("beta");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.02.16 12:19:16 EST"));
			examCreatorInstance.getUser().setUserName("Malissa58323");
			examCreatorInstance.getUser().setPassword("Wilson");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("Wilson");
			examCreatorInstance.getContactDetails().setCity("gamma");
			examCreatorInstance.getContactDetails().setState("Malissa");
			examCreatorInstance.getContactDetails().setCountry("Malissa");
			examCreatorInstance.getContactDetails().setZip("Eric");
			examCreatorInstance.getContactDetails().setPhone("zeta");
			examCreatorInstance.getContactDetails().setEmail("alpha77242");

			examCreatorService.save(examCreatorInstance);
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
			ExamCreator examCreator = new ExamCreator();

			try {

				examCreator.setFirstName("beta");
				examCreator.setLastName("pi");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.04.02 04:03:45 EDT"));
				examCreator.getUser().setUserName("Malissa28651");
				examCreator.getUser().setPassword("gamma");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("Lavendar");
				examCreator.getContactDetails().setCity("epsilon");
				examCreator.getContactDetails().setState("Mark");
				examCreator.getContactDetails().setCountry("beta");
				examCreator.getContactDetails().setZip("delta");
				examCreator.getContactDetails().setPhone("epsilon");
				examCreator.getContactDetails().setEmail("Wilson24988");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			examCreatorService.save(examCreator);
			assertNotNull(examCreator.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			ExamCreator examCreator = (ExamCreator) examCreatorTestDataFactory
					.loadOneRecord();

			examCreator.setFirstName("pi");
			examCreator.setLastName("epsilon");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.10 16:00:25 EDT"));
			examCreator.getUser().setUserName("zeta13542");
			examCreator.getUser().setPassword("zeta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("zeta");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("Wilson");
			examCreator.getContactDetails().setCountry("gamma");
			examCreator.getContactDetails().setZip("beta");
			examCreator.getContactDetails().setPhone("Lavendar");
			examCreator.getContactDetails().setEmail("alpha46278");

			examCreatorService.save(examCreator);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(examCreatorService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	public void testDelete() {

		try {
			long count, newCount, diff = 0;
			count = examCreatorService.getCount();
			ExamCreator examCreator = (ExamCreator) examCreatorTestDataFactory
					.loadOneRecord();
			examCreatorService.delete(examCreator);
			newCount = examCreatorService.getCount();
			diff = newCount - count;
			assertEquals(diff, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testLoad() {

		try {
			ExamCreator examCreator = examCreatorService
					.load(examCreatorInstance.getId());
			assertNotNull(examCreator.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByUserName() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a ExamCreator with userName ",
				examCreatorService.findByUserName(examCreatorInstance.getUser()
						.getUserName()));
		//assertNull("Found a ExamCreator with userName YYY", examCreatorService.findByUserName("YYY"));			

	}

	public void testFindByEmail() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a ExamCreator with email ",
				examCreatorService.findByEmail(examCreatorInstance
						.getContactDetails().getEmail()));
		//assertNull("Found a ExamCreator with email YYY", examCreatorService.findByEmail("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<ExamCreator> examCreators = examCreatorService
					.searchByExample(examCreatorInstance);
			assertTrue(!examCreators.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
