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
			examCreatorInstance.setLastName("delta");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.03.11 06:32:24 GMT"));
			examCreatorInstance.getUser().setUserName("pi40928");
			examCreatorInstance.getUser().setPassword("delta");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("theta");
			examCreatorInstance.getContactDetails().setCity("epsilon");
			examCreatorInstance.getContactDetails().setState("pi");
			examCreatorInstance.getContactDetails().setCountry("theta");
			examCreatorInstance.getContactDetails().setZip("Mark");
			examCreatorInstance.getContactDetails().setPhone("Malissa");
			examCreatorInstance.getContactDetails().setEmail("delta89396");

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

				examCreator.setFirstName("Malissa");
				examCreator.setLastName("zeta");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.02.25 22:15:44 GMT"));
				examCreator.getUser().setUserName("Malissa37981");
				examCreator.getUser().setPassword("John");
				examCreator.getUser().setEnabled(false);
				examCreator.getContactDetails().setStreetAddress("Mark");
				examCreator.getContactDetails().setCity("Mark");
				examCreator.getContactDetails().setState("beta");
				examCreator.getContactDetails().setCountry("pi");
				examCreator.getContactDetails().setZip("epsilon");
				examCreator.getContactDetails().setPhone("Wilson");
				examCreator.getContactDetails().setEmail("zeta16650");

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

			examCreator.setFirstName("Mark");
			examCreator.setLastName("epsilon");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.03.22 05:32:57 GMT"));
			examCreator.getUser().setUserName("alpha13227");
			examCreator.getUser().setPassword("gamma");
			examCreator.getUser().setEnabled(true);
			examCreator.getContactDetails().setStreetAddress("Mark");
			examCreator.getContactDetails().setCity("delta");
			examCreator.getContactDetails().setState("zeta");
			examCreator.getContactDetails().setCountry("epsilon");
			examCreator.getContactDetails().setZip("zeta");
			examCreator.getContactDetails().setPhone("Malissa");
			examCreator.getContactDetails().setEmail("Lavendar46350");

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
