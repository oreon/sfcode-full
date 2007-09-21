package bizobjects.service;

import bizobjects.Customer;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;

import javax.persistence.EntityExistsException;


public class CustomerDaoTest extends AbstractJpaTests {
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
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
    	Customer customer = new Customer();
    	customer.setFirstName("JH");
    	customer.getPrimaryAddress().setEmail("ms@gmail.com"); 
    	try{
    	customerService.save(customer);
    	}catch(DataIntegrityViolationException dae ){
    		if(dae.getCause() instanceof EntityExistsException){
    			
    			System.out.println(dae.getCause().getMessage());
    		}
    		
    		System.out.println(dae.getMessage());
    	}
    }

    public void testDelete() {
        //return false;
    }

    public void testLoad() {
        //return null;
    }

    public void testFindByfirstName() {
    }

    public void testFindBylastName() {
    }

    public void testSearchByExample() {
        Customer customer = new Customer();

        //customer.setFirstName("Eri");
        List<Customer> customers = customerService.searchByExample(customer);
        assertTrue(!customers.isEmpty());
    }
}
