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

			examCreatorInstance.setFirstName("Mark");
			examCreatorInstance.setLastName("epsilon");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.05.23 01:06:29 EDT"));
			examCreatorInstance.getUser().setUsername("delta92620");
			examCreatorInstance.getUser().setPassword("gamma");
			examCreatorInstance.getUser().setEnabled(true);
			examCreatorInstance.getContactDetails().setStreetAddress("John");
			examCreatorInstance.getContactDetails().setCity("zeta");
			examCreatorInstance.getContactDetails().setState("alpha");
			examCreatorInstance.getContactDetails().setCountry("Malissa");
			examCreatorInstance.getContactDetails().setZip("John");
			examCreatorInstance.getContactDetails().setPhone("gamma");
			examCreatorInstance.getContactDetails().setEmail("Wilson29155");

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

				examCreator.setFirstName("Eric");
				examCreator.setLastName("Eric");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.05.21 09:16:29 EDT"));
				examCreator.getUser().setUsername("epsilon73997");
				examCreator.getUser().setPassword("epsilon");
				examCreator.getUser().setEnabled(false);
				examCreator.getContactDetails().setStreetAddress("delta");
				examCreator.getContactDetails().setCity("delta");
				examCreator.getContactDetails().setState("epsilon");
				examCreator.getContactDetails().setCountry("pi");
				examCreator.getContactDetails().setZip("zeta");
				examCreator.getContactDetails().setPhone("gamma");
				examCreator.getContactDetails().setEmail("Malissa87440");

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
			examCreator.setLastName("John");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.04 06:33:42 EDT"));
			examCreator.getUser().setUsername("Mark467");
			examCreator.getUser().setPassword("theta");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("alpha");
			examCreator.getContactDetails().setCity("Malissa");
			examCreator.getContactDetails().setState("Eric");
			examCreator.getContactDetails().setCountry("beta");
			examCreator.getContactDetails().setZip("Mark");
			examCreator.getContactDetails().setPhone("zeta");
			examCreator.getContactDetails().setEmail("Lavendar27985");

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
