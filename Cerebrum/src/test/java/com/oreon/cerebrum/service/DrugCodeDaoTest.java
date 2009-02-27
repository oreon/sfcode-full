package com.oreon.cerebrum.service;

import com.oreon.cerebrum.drugs.DrugCode;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class DrugCodeDaoTest extends AbstractJpaTests {

	protected DrugCode drugCodeInstance = new DrugCode();

	protected DrugCodeService drugCodeService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setDrugCodeService(DrugCodeService drugCodeService) {
		this.drugCodeService = drugCodeService;
	}

	protected TestDataFactory drugCodeTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("drugCodeTestDataFactory");

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

			drugCodeInstance.setCode("Lavendar48825");

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			drugCodeInstance
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			drugCodeInstance
					.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
							.loadOneRecord());

			drugCodeService.save(drugCodeInstance);
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
			DrugCode drugCode = new DrugCode();

			try {

				drugCode.setCode("Wilson50441");

				TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("drugTestDataFactory");

				drugCode
						.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
								.loadOneRecord());

				TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("categoryTestDataFactory");

				drugCode
						.setCategory((com.oreon.cerebrum.drugs.Category) categoryTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			drugCodeService.save(drugCode);
			assertNotNull(drugCode.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			DrugCode drugCode = (DrugCode) drugCodeTestDataFactory
					.loadOneRecord();

			drugCode.setCode("theta67489");

			drugCodeService.save(drugCode);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(drugCodeService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=drugCodeService.getCount();
			DrugCode drugCode = (DrugCode)drugCodeTestDataFactory.loadOneRecord();					
			drugCodeService.delete(drugCode);
			newCount=drugCodeService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			DrugCode drugCode = drugCodeService.load(drugCodeInstance.getId());
			assertNotNull(drugCode.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByCode() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a DrugCode with code ", drugCodeService
				.findByCode(drugCodeInstance.getCode()));
		//assertNull("Found a DrugCode with code YYY", drugCodeService.findByCode("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<DrugCode> drugCodes = drugCodeService
					.searchByExample(drugCodeInstance);
			assertTrue(!drugCodes.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
