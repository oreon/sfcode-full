package com.oreon.cerebrum.service;

import com.oreon.cerebrum.drugs.Drug;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class DrugDaoTest extends AbstractJpaTests {

	protected Drug drugInstance = new Drug();

	protected DrugService drugService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setDrugService(DrugService drugService) {
		this.drugService = drugService;
	}

	protected TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("drugTestDataFactory");

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

			drugInstance.setName("epsilon91157");
			drugInstance.setBioavalability(3373);
			drugInstance.setHalfLife(9350);
			drugInstance.setExcretion(com.oreon.cerebrum.drugs.Excretion.Bile);
			drugInstance
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.X);
			drugInstance.setText("alpha");

			drugService.save(drugInstance);
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
			Drug drug = new Drug();

			try {

				drug.setName("zeta85367");
				drug.setBioavalability(8792);
				drug.setHalfLife(5448);
				drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Bile);
				drug
						.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.D);
				drug.setText("theta");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			drugService.save(drug);
			assertNotNull(drug.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Drug drug = (Drug) drugTestDataFactory.loadOneRecord();

			drug.setName("delta69139");
			drug.setBioavalability(5201);
			drug.setHalfLife(1225);
			drug.setExcretion(com.oreon.cerebrum.drugs.Excretion.Renal);
			drug
					.setPregnancyCategory(com.oreon.cerebrum.drugs.PregnancyCategory.X);
			drug.setText("Wilson");

			drugService.save(drug);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(drugService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=drugService.getCount();
			Drug drug = (Drug)drugTestDataFactory.loadOneRecord();					
			drugService.delete(drug);
			newCount=drugService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			Drug drug = drugService.load(drugInstance.getId());
			assertNotNull(drug.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testFindByName() {
		if (!bTest)
			return;

		assertNotNull("Couldn't find a Drug with name ", drugService
				.findByName(drugInstance.getName()));
		//assertNull("Found a Drug with name YYY", drugService.findByName("YYY"));			

	}

	public void testSearchByExample() {
		try {
			List<Drug> drugs = drugService.searchByExample(drugInstance);
			assertTrue(!drugs.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
