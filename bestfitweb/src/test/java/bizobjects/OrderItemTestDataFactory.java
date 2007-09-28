package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import bizobjects.service.OrderItemService;

public class OrderItemTestDataFactory extends AbstractTestDataFactory {

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

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct(productTestDataFactory.loadProduct());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemTwo() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct(productTestDataFactory.loadProduct());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemThree() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct(productTestDataFactory.loadProduct());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemFour() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct(productTestDataFactory.loadProduct());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem createOrderItemFive() {
		OrderItem orderItem = new OrderItem();

		try {

			orderItem.setSalePrice(2.454532713340274);
			orderItem.setQuantity(1);

			ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItem.setProduct(productTestDataFactory.loadProduct());

			register(orderItem);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return orderItem;
	}

	public OrderItem loadOrderItem() {
		List<OrderItem> orderItems = orderItemService.loadAll();

		if (orderItems.isEmpty()) {
			persistAll();
			orderItems = orderItemService.loadAll();
		}

		return orderItems.get(new Random().nextInt(orderItems.size()));
	}

	List<OrderItem> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (OrderItem orderItem : orderItems) {
			orderItemService.save(orderItem);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<OrderItem> getFew() {
		List<OrderItem> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<OrderItem>();
		List returnList = new ArrayList<OrderItem>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
