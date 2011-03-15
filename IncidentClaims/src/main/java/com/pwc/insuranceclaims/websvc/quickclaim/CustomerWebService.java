package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.Customer;
import java.util.List;

@WebService
public interface CustomerWebService {

	public Customer loadById(Long id);

	public List<Customer> findByExample(Customer exampleCustomer);

	public void save(Customer customer);

}
