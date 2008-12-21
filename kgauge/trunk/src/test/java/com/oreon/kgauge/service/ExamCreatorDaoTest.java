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

			examCreatorInstance.setFirstName("Malissa");
			examCreatorInstance.setLastName("Wilson");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2009.01.05 12:26:18 EST"));
			examCreatorInstance.getUser().setUsername("Malissa74316");
			examCreatorInstance.getUser().setPassword("epsilon");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("gamma");
			examCreatorInstance.getContactDetails().setCity("beta");
			examCreatorInstance.getContactDetails().setState("theta");
			examCreatorInstance.getContactDetails().setCountry("beta");
			examCreatorInstance.getContactDetails().setZip("gamma");
			examCreatorInstance.getContactDetails().setPhone("zeta");
			examCreatorInstance.getContactDetails().setEmail("Lavendar12456");

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
				examCreator.setLastName("Eric");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.12.05 14:52:58 EST"));
				examCreator.getUser().setUsername("Mark12391");
				examCreator.getUser().setPassword("Mark");
				examCreator.getUser().setEnabled(true);
				examCreator.getContactDetails().setStreetAddress("Wilson");
				examCreator.getContactDetails().setCity("delta");
				examCreator.getContactDetails().setState("zeta");
				examCreator.getContactDetails().setCountry("pi");
				examCreator.getContactDetails().setZip("gamma");
				examCreator.getContactDetails().setPhone("delta");
				examCreator.getContactDetails().setEmail("pi43065");

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

			examCreator.setFirstName("Lavendar");
			examCreator.setLastName("gamma");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.12.03 06:04:36 EST"));
			examCreator.getUser().setUsername("Mark37792");
			examCreator.getUser().setPassword("Eric");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("theta");
			examCreator.getContactDetails().setCity("Mark");
			examCreator.getContactDetails().setState("Wilson");
			examCreator.getContactDetails().setCountry("Lavendar");
			examCreator.getContactDetails().setZip("epsilon");
			examCreator.getContactDetails().setPhone("alpha");
			examCreator.getContactDetails().setEmail("theta31447");

			examCreatorService.save(examCreator);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(examCreatorService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=examCreatorService.getCount();
			ExamCreator examCreator = (ExamCreator)examCreatorTestDataFactory.loadOneRecord();					
			examCreatorService.delete(examCreator);
			newCount=examCreatorService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

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

	/////////////////// Queries //////////////////////////////////

}
