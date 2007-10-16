package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

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

			orderItem.setSalePrice(22.77);
			orderItem.setQuantity(1);
			orderItem.setTotal(7.46);

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

			orderItem.setSalePrice(5.87);
			orderItem.setQuantity(1);
			orderItem.setTotal(7.58);

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

			orderItem.setSalePrice(76.68);
			orderItem.setQuantity(1);
			orderItem.setTotal(47.75);

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

			orderItem.setSalePrice(99.3);
			orderItem.setQuantity(1);
			orderItem.setTotal(13.59);

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

			orderItem.setSalePrice(20.68);
			orderItem.setQuantity(1);
			orderItem.setTotal(37.79);

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

}
