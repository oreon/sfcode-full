package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import bizobjects.service.PlacedOrderService;

public class PlacedOrderTestDataFactory extends AbstractTestDataFactory {

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

			placedOrder.setRemarks("pi");
			placedOrder.setPaymentMethod("theta");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder.setCustomer(customerTestDataFactory.loadCustomer());

			OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemTestDataFactory.getFew());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderTwo() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("zeta");
			placedOrder.setPaymentMethod("theta");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder.setCustomer(customerTestDataFactory.loadCustomer());

			OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemTestDataFactory.getFew());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderThree() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("theta");
			placedOrder.setPaymentMethod("alpha");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder.setCustomer(customerTestDataFactory.loadCustomer());

			OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemTestDataFactory.getFew());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderFour() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("alpha");
			placedOrder.setPaymentMethod("alpha");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder.setCustomer(customerTestDataFactory.loadCustomer());

			OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemTestDataFactory.getFew());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder createPlacedOrderFive() {
		PlacedOrder placedOrder = new PlacedOrder();

		try {

			placedOrder.setRemarks("gamma");
			placedOrder.setPaymentMethod("alpha");
			placedOrder.setStatus(OrderStatus.SHIPPED);

			CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
					.getBean("customerTestDataFactory");

			placedOrder.setCustomer(customerTestDataFactory.loadCustomer());

			OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
					.getBean("orderItemTestDataFactory");

			orderItemTestDataFactory.setPersistable(false);
			placedOrder.getOrderItems().addAll(
					orderItemTestDataFactory.getFew());

			register(placedOrder);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return placedOrder;
	}

	public PlacedOrder loadPlacedOrder() {
		List<PlacedOrder> placedOrders = placedOrderService.loadAll();

		if (placedOrders.isEmpty()) {
			persistAll();
			placedOrders = placedOrderService.loadAll();
		}

		return placedOrders.get(new Random().nextInt(placedOrders.size()));
	}

	List<PlacedOrder> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (PlacedOrder placedOrder : placedOrders) {
			placedOrderService.save(placedOrder);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<PlacedOrder> getFew() {
		List<PlacedOrder> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<PlacedOrder>();
		List returnList = new ArrayList<PlacedOrder>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
