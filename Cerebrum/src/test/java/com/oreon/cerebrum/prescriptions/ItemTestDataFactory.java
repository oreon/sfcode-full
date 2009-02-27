package com.oreon.cerebrum.prescriptions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.cerebrum.service.ItemService;

@Transactional
public class ItemTestDataFactory extends AbstractTestDataFactory<Item> {

	private List<Item> items = new ArrayList<Item>();

	private static final Logger logger = Logger
			.getLogger(ItemTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void register(Item item) {
		items.add(item);
	}

	public Item createItemOne() {
		Item item = new Item();

		try {

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.IV);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.BID);
			item.setQty("Mark");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			item
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
					.loadOneRecord());

			register(item);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public Item createItemTwo() {
		Item item = new Item();

		try {

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.SL);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.BID);
			item.setQty("delta");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			item
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
					.loadOneRecord());

			register(item);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public Item createItemThree() {
		Item item = new Item();

		try {

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.IV);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.TID);
			item.setQty("Eric");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			item
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
					.loadOneRecord());

			register(item);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public Item createItemFour() {
		Item item = new Item();

		try {

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.SC);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.QXH);
			item.setQty("epsilon");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			item
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
					.loadOneRecord());

			register(item);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public Item createItemFive() {
		Item item = new Item();

		try {

			item.setRoute(com.oreon.cerebrum.prescriptions.Route.RECTAL);
			item.setFrequency(com.oreon.cerebrum.prescriptions.Frequency.BID);
			item.setQty("theta");

			TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("prescriptionTestDataFactory");

			item
					.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
							.loadOneRecord());

			TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("drugTestDataFactory");

			item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
					.loadOneRecord());

			register(item);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return item;
	}

	public Item loadOneRecord() {
		List<Item> items = itemService.loadAll();

		if (items.isEmpty()) {
			persistAll();
			items = itemService.loadAll();
		}

		return items.get(new Random().nextInt(items.size()));
	}

	public List<Item> getAllAsList() {

		if (items.isEmpty()) {

			createItemOne();
			createItemTwo();
			createItemThree();
			createItemFour();
			createItemFive();

		}

		return items;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Item item : items) {
			try {
				itemService.save(item);
			} catch (BusinessException be) {
				logger.warn(" Item " + item.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Item item = createRandomItem();
			itemService.save(item);
		}
	}

	public Item createRandomItem() {
		Item item = new Item();

		item.setRoute((Route) RandomValueGeneratorFactory
				.createInstance("Route"));
		item.setFrequency((Frequency) RandomValueGeneratorFactory
				.createInstance("Frequency"));
		item.setQty((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory prescriptionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("prescriptionTestDataFactory");

		item
				.setPrescription((com.oreon.cerebrum.prescriptions.Prescription) prescriptionTestDataFactory
						.loadOneRecord());

		TestDataFactory drugTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("drugTestDataFactory");

		item.setDrug((com.oreon.cerebrum.drugs.Drug) drugTestDataFactory
				.loadOneRecord());

		return item;
	}

}
