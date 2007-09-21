package bizobjects.service;

import bizobjects.PlacedOrder;

import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;


public class PlacedOrderDaoTest extends AbstractJpaTests {
    private PlacedOrderService placedOrderService;

    public void setPlacedOrderService(PlacedOrderService placedOrderService) {
        this.placedOrderService = placedOrderService;
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
        PlacedOrder placedOrder = new PlacedOrder();

        //placedOrder.setFirstName("Eri");
        List<PlacedOrder> placedOrders = placedOrderService.searchByExample(placedOrder);
        assertTrue(!placedOrders.isEmpty());
    }
}
