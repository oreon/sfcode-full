package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.Customer;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.CustomerWebService", serviceName = "CustomerWebService")
@Name("customerWebService")
public class CustomerWebServiceImpl implements CustomerWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.CustomerAction customerAction;

	public Customer loadById(Long id) {
		return customerAction.loadFromId(id);
	}

	public List<Customer> findByExample(Customer exampleCustomer) {
		return customerAction.search(exampleCustomer);
	}

	public void save(Customer customer) {
		customerAction.persist(customer);
	}

}
