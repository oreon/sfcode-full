package bizobjects.web.jsf;

import bizobjects.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerBackingBean {
    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void set(Customer customer) {
        this.customer = customer;
    }

    /**Write values to the database
    * @return - a list of
    */
    public String update() {
        return "success";
    }

    /**Get a list of all customers
    * @return - a list of customers
    */
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        return customers;
    }
}
