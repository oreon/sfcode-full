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

			examCreatorInstance.setFirstName("gamma");
			examCreatorInstance.setLastName("beta");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.02.18 09:19:43 EST"));
			examCreatorInstance.getUser().setUserName("beta27139");
			examCreatorInstance.getUser().setPassword("zeta");
			examCreatorInstance.getUser().setEnabled(true);
			examCreatorInstance.getContactDetails()
					.setStreetAddress("Lavendar");
			examCreatorInstance.getContactDetails().setCity("pi");
			examCreatorInstance.getContactDetails().setState("Wilson");
			examCreatorInstance.getContactDetails().setCountry("epsilon");
			examCreatorInstance.getContactDetails().setZip("Lavendar");
			examCreatorInstance.getContactDetails().setPhone("zeta");
			examCreatorInstance.getContactDetails().setEmail("John73416");

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

				examCreator.setFirstName("Mark");
				examCreator.setLastName("Malissa");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.03.11 18:51:57 EDT"));
				examCreator.getUser().setUserName("Malissa92366");
				examCreator.getUser().setPassword("Lavendar");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("gamma");
				examCreator.getContactDetails().setCity("gamma");
				examCreator.getContactDetails().setState("alpha");
				examCreator.getContactDetails().setCountry("theta");
				examCreator.getContactDetails().setZip("Eric");
				examCreator.getContactDetails().setPhone("epsilon");
				examCreator.getContactDetails().setEmail("pi19269");

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
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.02.12 16:23:03 EST"));
			examCreator.getUser().setUserName("theta14615");
			examCreator.getUser().setPassword("alpha");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("alpha");
			examCreator.getContactDetails().setCity("zeta");
			examCreator.getContactDetails().setState("Wilson");
			examCreator.getContactDetails().setCountry("Wilson");
			examCreator.getContactDetails().setZip("gamma");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("Malissa39732");

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
		long count, newCount, diff = 0;
		count = examCreatorService.getCount();
		ExamCreator examCreator = (ExamCreator) examCreatorTestDataFactory
				.loadOneRecord();
		examCreatorService.delete(examCreator);
		newCount = examCreatorService.getCount();
		diff = newCount - count;
		try {
			assertEquals(diff, 0);
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
