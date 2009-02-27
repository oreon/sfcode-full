package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Item;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import java.util.Date;

public class ItemDaoTest extends AbstractJpaTests {

	protected Item itemInstance = new Item();

	protected ItemService itemService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	protected TestDataFactory itemTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("itemTestDataFactory");

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

			itemInstance
					.setRoute(com.oreon.cerebrum.prescriptions.Route.RECTAL);
			itemInstance
					.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.BID);
			itemInstance.setQty("alpha");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			itemInstance
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			itemInstance
					.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
							.loadOneRecord());

			itemService.save(itemInstance);
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
			Item item = new Item();

			try {

				item.setRoute(com.oreon.cerebrum.prescriptions.Route.IM);
				item
						.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.QID);
				item.setQty("John");

				TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("prescriptionTestDataFactory");

				item
						.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
								.loadOneRecord());

				TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("drugTestDataFactory");

				item
						.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			itemService.save(item);
			assertNotNull(item.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Item item = (Item) itemTestDataFactory.loadOneRecord();

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.ORAL);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.TID);
			item.setQty("Mark");

			itemService.save(item);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testCount() {
		assertTrue(itemService.getCount() > 0);
	}

	//count the number of records - add one delete it - check count is same after delete
	/*
	public void testDelete() {
									
		try{
			long count,newCount,diff=0;			
			count=itemService.getCount();
			Item item = (Item)itemTestDataFactory.loadOneRecord();					
			itemService.delete(item);
			newCount=itemService.getCount();
			diff=count - newCount;
			assertEquals(diff, 1);
		}catch(Exception e){
			fail(e.getMessage());
		}
	}*/

	public void testLoad() {

		try {
			Item item = itemService.load(itemInstance.getId());
			assertNotNull(item.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Item> items = itemService.searchByExample(itemInstance);
			assertTrue(!items.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/////////////////// Queries //////////////////////////////////

}
