package bizobjects.service;

import bizobjects.OrderItem;

import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;


public class OrderItemDaoTest extends AbstractJpaTests {
    private OrderItemService orderItemService;

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext.xml" };
    }

    /**
    * Do the setup before the test in this method
    **/
    protected void onSetUpInTransaction() throws Exception {
    }

    public void testSave() {
        //test saving a new record and updating an existing record;
    }

    public void testDelete() {
        //return false;
    }

    public void testLoad() {
        //return null;
    }

    public void testSearchByExample() {
        OrderItem orderItem = new OrderItem();

        //orderItem.setFirstName("Eri");
        List<OrderItem> orderItems = orderItemService.searchByExample(orderItem);
        //assertTrue(!orderItems.isEmpty());
    }
}
