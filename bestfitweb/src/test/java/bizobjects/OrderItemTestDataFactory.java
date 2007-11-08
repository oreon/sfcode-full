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

import bizobjects.service.OrderItemService;

@Transactional
public class OrderItemTestDataFactory
		extends
			AbstractTestDataFactory<OrderItem> {

	List<OrderItem> orderItems = new ArrayList<OrderItem>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	OrderItemService orderItemService;

	public OrderItemService getOrderItemService() {
		return orderItemService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public void register(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	public OrderItem createOrderItemOne() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(39.87);
			orderItem.setQuantity(1);
			orderItem.setTotal(58.0);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct((bizobjects.Product) productTestDataFactory
					.loadOneRecord());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemTwo() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(41.12);
			orderItem.setQuantity(1);
			orderItem.setTotal(60.91);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct((bizobjects.Product) productTestDataFactory
					.loadOneRecord());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemThree() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(80.35);
			orderItem.setQuantity(1);
			orderItem.setTotal(34.3);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct((bizobjects.Product) productTestDataFactory
					.loadOneRecord());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemFour() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(77.96);
			orderItem.setQuantity(1);
			orderItem.setTotal(43.61);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct((bizobjects.Product) productTestDataFactory
					.loadOneRecord());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemFive() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(11.83);
			orderItem.setQuantity(1);
			orderItem.setTotal(87.86);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct((bizobjects.Product) productTestDataFactory
					.loadOneRecord());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem loadOneRecord() {
		List<OrderItem> orderItems = orderItemService.loadAll();

		if (orderItems.isEmpty()) {
			persistAll();
			orderItems = orderItemService.loadAll();
		}

		return orderItems.get(new Random().nextInt(orderItems.size()));
	}

	public List<OrderItem> getAllAsList() {

		if (orderItems.isEmpty()) {
			createOrderItemOne();
			createOrderItemTwo();
			createOrderItemThree();
			createOrderItemFour();
			createOrderItemFive();

		}

		return orderItems;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (OrderItem orderItem : orderItems) {
			orderItemService.save(orderItem);
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
			OrderItem orderItem = createRandomOrderItem();
			orderItemService.save(orderItem);
		}
	}

	public OrderItem createRandomOrderItem() {
		OrderItem orderItem = new OrderItem();

		orderItem.setSalePrice((Double) RandomValueGeneratorFactory
				.createInstance("double"));
		orderItem.setQuantity((Integer) RandomValueGeneratorFactory
				.createInstance("int"));
		orderItem.setTotal((Double) RandomValueGeneratorFactory
				.createInstance("double"));

		TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("productTestDataFactory");

		orderItem.setProduct((bizobjects.Product) productTestDataFactory
				.loadOneRecord());

		return orderItem;
	}

}
