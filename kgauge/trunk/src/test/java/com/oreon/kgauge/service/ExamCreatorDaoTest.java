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

			examCreatorInstance.setFirstName("pi");
			examCreatorInstance.setLastName("pi");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.04.26 23:00:06 EDT"));
			examCreatorInstance.getUser().setUsername("John52176");
			examCreatorInstance.getUser().setPassword("Malissa");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("Mark");
			examCreatorInstance.getContactDetails().setCity("Lavendar");
			examCreatorInstance.getContactDetails().setState("Eric");
			examCreatorInstance.getContactDetails().setCountry("delta");
			examCreatorInstance.getContactDetails().setZip("Lavendar");
			examCreatorInstance.getContactDetails().setPhone("John");
			examCreatorInstance.getContactDetails().setEmail("Eric65111");

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

				examCreator.setFirstName("zeta");
				examCreator.setLastName("theta");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.04.12 02:41:48 EDT"));
				examCreator.getUser().setUsername("John11049");
				examCreator.getUser().setPassword("delta");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("zeta");
				examCreator.getContactDetails().setCity("theta");
				examCreator.getContactDetails().setState("gamma");
				examCreator.getContactDetails().setCountry("pi");
				examCreator.getContactDetails().setZip("Wilson");
				examCreator.getContactDetails().setPhone("theta");
				examCreator.getContactDetails().setEmail("delta51446");

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

			examCreator.setFirstName("delta");
			examCreator.setLastName("Malissa");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.01 10:52:53 EDT"));
			examCreator.getUser().setUsername("Eric26577");
			examCreator.getUser().setPassword("alpha");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("epsilon");
			examCreator.getContactDetails().setCity("Lavendar");
			examCreator.getContactDetails().setState("Malissa");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("Malissa");
			examCreator.getContactDetails().setPhone("Wilson");
			examCreator.getContactDetails().setEmail("Malissa29122");

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
