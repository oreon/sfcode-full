package bizobjects.service;

import bizobjects.OrderItem;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class OrderItemDaoTest extends AbstractJpaTests {

	protected OrderItem orderItemInstance = new OrderItem();

	protected OrderItemService orderItemService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	protected TestDataFactory orderItemTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("orderItemTestDataFactory");

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

			orderItemInstance.setSalePrice(21.19);
			orderItemInstance.setQuantity(1);
			orderItemInstance.setTotal(32.77);

			TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("productTestDataFactory");

			orderItemInstance
					.setProduct((bizobjects.Product) productTestDataFactory
							.loadOneRecord());

			orderItemService.save(orderItemInstance);
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
			OrderItem orderItem = new OrderItem();

			try {

				orderItem.setSalePrice(52.63);
				orderItem.setQuantity(1);
				orderItem.setTotal(79.36);

				TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
						.getBean("productTestDataFactory");

				orderItem
						.setProduct((bizobjects.Product) productTestDataFactory
								.loadOneRecord());

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			orderItemService.save(orderItem);
			assertNotNull(orderItem.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			OrderItem orderItem = (OrderItem) orderItemTestDataFactory
					.loadOneRecord();

			orderItem.setSalePrice(96.86);
			orderItem.setQuantity(1);
			orderItem.setTotal(56.6);

			orderItemService.save(orderItem);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			OrderItem orderItem = orderItemService.load(orderItemInstance
					.getId());
			assertNotNull(orderItem.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<OrderItem> orderItems = orderItemService
					.searchByExample(orderItemInstance);
			assertTrue(!orderItems.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
