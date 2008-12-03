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

			examCreatorInstance.setFirstName("Eric");
			examCreatorInstance.setLastName("Wilson");
			examCreatorInstance.setDateOfBirth(dateFormat
					.parse("2008.12.13 16:20:09 EST"));
			examCreatorInstance.getUser().setUsername("epsilon82269");
			examCreatorInstance.getUser().setPassword("zeta");
			examCreatorInstance.getUser().setEnabled(false);
			examCreatorInstance.getContactDetails().setStreetAddress("Malissa");
			examCreatorInstance.getContactDetails().setCity("Lavendar");
			examCreatorInstance.getContactDetails().setState("epsilon");
			examCreatorInstance.getContactDetails().setCountry("delta");
			examCreatorInstance.getContactDetails().setZip("Eric");
			examCreatorInstance.getContactDetails().setPhone("alpha");
			examCreatorInstance.getContactDetails().setEmail("gamma89907");

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

				examCreator.setFirstName("John");
				examCreator.setLastName("gamma");
				examCreator.setDateOfBirth(dateFormat
						.parse("2008.11.21 10:24:34 EST"));
				examCreator.getUser().setUsername("Malissa55182");
				examCreator.getUser().setPassword("theta");
				examCreator.getUser().setEnabled(false);
				examCreator.getContactDetails().setStreetAddress("Malissa");
				examCreator.getContactDetails().setCity("Mark");
				examCreator.getContactDetails().setState("pi");
				examCreator.getContactDetails().setCountry("beta");
				examCreator.getContactDetails().setZip("beta");
				examCreator.getContactDetails().setPhone("beta");
				examCreator.getContactDetails().setEmail("theta50615");

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

			examCreator.setFirstName("John");
			examCreator.setLastName("theta");
			examCreator.setDateOfBirth(dateFormat
					.parse("2008.11.30 13:25:43 EST"));
			examCreator.getUser().setUsername("zeta68857");
			examCreator.getUser().setPassword("Lavendar");
			examCreator.getUser().setEnabled(false);
			examCreator.getContactDetails().setStreetAddress("Lavendar");
			examCreator.getContactDetails().setCity("Mark");
			examCreator.getContactDetails().setState("Eric");
			examCreator.getContactDetails().setCountry("Eric");
			examCreator.getContactDetails().setZip("theta");
			examCreator.getContactDetails().setPhone("gamma");
			examCreator.getContactDetails().setEmail("Malissa34636");

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
