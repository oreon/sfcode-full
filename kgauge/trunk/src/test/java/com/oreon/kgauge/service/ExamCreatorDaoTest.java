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

			examCreatorInstance.setFirstName("John");
			examCreatorInstance.setLastName("Wilson");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.05.23 16:22:32 EDT"));
			examCreatorInstance.getUser().setUsername("beta55975");
			examCreatorInstance.getUser().setPassword("gamma");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("beta");
			examCreatorInstance.getContactDetails().setCity("Mark");
			examCreatorInstance.getContactDetails().setState("beta");
			examCreatorInstance.getContactDetails().setCountry("gamma");
			examCreatorInstance.getContactDetails().setZip("Malissa");
			examCreatorInstance.getContactDetails().setPhone("pi");
			examCreatorInstance.getContactDetails().setEmail("delta774");

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

				examCreator.setFirstName("gamma");
				examCreator.setLastName("pi");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.06.12 18:49:12 EDT"));
				examCreator.getUser().setUsername("Lavendar37678");
				examCreator.getUser().setPassword("delta");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("John");
				examCreator.getContactDetails().setCity("Malissa");
				examCreator.getContactDetails().setState("John");
				examCreator.getContactDetails().setCountry("John");
				examCreator.getContactDetails().setZip("epsilon");
				examCreator.getContactDetails().setPhone("Eric");
				examCreator.getContactDetails().setEmail("Wilson93519");

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

			examCreator.setFirstName("theta");
			examCreator.setLastName("zeta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.05.16 09:55:19 EDT"));
			examCreator.getUser().setUsername("theta65327");
			examCreator.getUser().setPassword("pi");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Malissa");
			examCreator.getContactDetails().setCity("gamma");
			examCreator.getContactDetails().setState("beta");
			examCreator.getContactDetails().setCountry("theta");
			examCreator.getContactDetails().setZip("gamma");
			examCreator.getContactDetails().setPhone("beta");
			examCreator.getContactDetails().setEmail("zeta33811");

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
