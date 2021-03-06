package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import bizobjects.service.PlacedOrderService;

@Transactional
public class PlacedOrderTestDataFactory
		extends
			AbstractTestDataFactory<PlacedOrder> {

	List<PlacedOrder> placedOrders = new ArrayList<PlacedOrder>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	PlacedOrderService placedOrderService;

	public PlacedOrderService getPlacedOrderService() {
		return placedOrderService;
	}

	public void setPlacedOrderService(PlacedOrderService placedOrderService) {
		this.placedOrderService = placedOrderService;
	}

	public void register(PlacedOrder placedOrder) {
		placedOrders.add(placedOrder);
	}

	public PlacedOrder createPlacedOrderOne() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("Lavendar");
			placedOrder.setPaymentMethod("alpha");
			placedOrder.setStatus(bizobjects.OrderStatus.SHIPPED);
			placedOrder.setTotal(13.19);

			TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder
					.setCustomer((bizobjects.Customer) customerTestDataFactory
							.loadOneRecord());

			TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemsTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemsTestDataFactory.createFewRecords());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderTwo() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("alpha");
			placedOrder.setPaymentMethod("Lavendar");
			placedOrder.setStatus(bizobjects.OrderStatus.NEW);
			placedOrder.setTotal(61.71);

			TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder
					.setCustomer((bizobjects.Customer) customerTestDataFactory
							.loadOneRecord());

			TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemsTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemsTestDataFactory.createFewRecords());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderThree() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("delta");
			placedOrder.setPaymentMethod("epsilon");
			placedOrder.setStatus(bizobjects.OrderStatus.SHIPPED);
			placedOrder.setTotal(28.17);

			TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder
					.setCustomer((bizobjects.Customer) customerTestDataFactory
							.loadOneRecord());

			TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemsTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemsTestDataFactory.createFewRecords());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderFour() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("John");
			placedOrder.setPaymentMethod("zeta");
			placedOrder.setStatus(bizobjects.OrderStatus.SHIPPED);
			placedOrder.setTotal(12.63);

			TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder
					.setCustomer((bizobjects.Customer) customerTestDataFactory
							.loadOneRecord());

			TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemsTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemsTestDataFactory.createFewRecords());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderFive() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("zeta");
			placedOrder.setPaymentMethod("Malissa");
			placedOrder.setStatus(bizobjects.OrderStatus.SHIPPED);
			placedOrder.setTotal(86.24);

			TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder
					.setCustomer((bizobjects.Customer) customerTestDataFactory
							.loadOneRecord());

			TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemsTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemsTestDataFactory.createFewRecords());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder loadOneRecord() {
		List<PlacedOrder> placedOrders = placedOrderService.loadAll();

		if (placedOrders.isEmpty()) {
			persistAll();
			placedOrders = placedOrderService.loadAll();
		}

		return placedOrders.get(new Random().nextInt(placedOrders.size()));
	}

	public List<PlacedOrder> getAllAsList() {

		if (placedOrders.isEmpty()) {
			createPlacedOrderOne();
			createPlacedOrderTwo();
			createPlacedOrderThree();
			createPlacedOrderFour();
			createPlacedOrderFive();

		}

		return placedOrders;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (PlacedOrder placedOrder : placedOrders) {
			placedOrderService.save(placedOrder);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			PlacedOrder placedOrder = createRandomPlacedOrder();
			placedOrderService.save(placedOrder);
		}
	}

	public PlacedOrder createRandomPlacedOrder() {
		PlacedOrder placedOrder = new PlacedOrder();

		placedOrder.setRemarks((String) RandomValueGeneratorFactory
				.createInstance("String"));
		placedOrder.setPaymentMethod((String) RandomValueGeneratorFactory
				.createInstance("String"));
		placedOrder.setStatus((OrderStatus) RandomValueGeneratorFactory
				.createInstance("OrderStatus"));
		placedOrder.setTotal((Double) RandomValueGeneratorFactory
				.createInstance("double"));

		TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("customerTestDataFactory");

		placedOrder.setCustomer((bizobjects.Customer) customerTestDataFactory
				.loadOneRecord());

		TestDataFactory orderItemsTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("orderItemTestDataFactory");

		orderItemsTestDataFactory.setPersistable(false);
		placedOrder.getOrderItems().addAll(
				orderItemsTestDataFactory.createFewRecords());

		return placedOrder;
	}

}
