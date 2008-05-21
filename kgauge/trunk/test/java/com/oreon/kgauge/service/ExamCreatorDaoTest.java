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

			examCreatorInstance.setFirstName("Lavendar");
			examCreatorInstance.setLastName("alpha");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.06.12 12:51:54 EDT"));
			examCreatorInstance.getUser().setUsername("Mark41049");
			examCreatorInstance.getUser().setPassword("Wilson");
			examCreatorInstance.getUser().setEnabled(true);
			examCreatorInstance.getContactDetails().setStreetAddress("delta");
			examCreatorInstance.getContactDetails().setCity("delta");
			examCreatorInstance.getContactDetails().setState("Malissa");
			examCreatorInstance.getContactDetails().setCountry("theta");
			examCreatorInstance.getContactDetails().setZip("alpha");
			examCreatorInstance.getContactDetails().setPhone("John");
			examCreatorInstance.getContactDetails().setEmail("alpha5218");

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

				examCreator.setFirstName("epsilon");
				examCreator.setLastName("pi");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.06.01 23:53:36 EDT"));
				examCreator.getUser().setUsername("alpha67055");
				examCreator.getUser().setPassword("theta");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("alpha");
				examCreator.getContactDetails().setCity("theta");
				examCreator.getContactDetails().setState("Mark");
				examCreator.getContactDetails().setCountry("Malissa");
				examCreator.getContactDetails().setZip("Malissa");
				examCreator.getContactDetails().setPhone("Wilson");
				examCreator.getContactDetails().setEmail("zeta96742");

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

			examCreator.setFirstName("Malissa");
			examCreator.setLastName("Lavendar");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.04 01:31:54 EDT"));
			examCreator.getUser().setUsername("alpha42346");
			examCreator.getUser().setPassword("John");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("pi");
			examCreator.getContactDetails().setCity("epsilon");
			examCreator.getContactDetails().setState("John");
			examCreator.getContactDetails().setCountry("theta");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("Wilson");
			examCreator.getContactDetails().setEmail("John56767");

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

	public void testFindByUsername() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a ExamCreator with username ",
				examCreatorService.findByUsername(examCreatorInstance.getUser()
						.getUsername()));
		//assertNull("Found a ExamCreator with username YYY", examCreatorService.findByUsername("YYY"));			

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
