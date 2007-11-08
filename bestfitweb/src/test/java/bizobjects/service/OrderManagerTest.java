package bizobjects.service;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import bizobjects.OrderManager;
import junit.framework.TestCase;

public class OrderManagerTest extends TestCase{
	
	public void testSendMail(){
		((OrderManager)BeanHelper.getBean("orderManager")).placeOrder();
	}

}
