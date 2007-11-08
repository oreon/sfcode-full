package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Customer extends CustomerBase {

	private static final Logger log = Logger.getLogger(Customer.class);

	public Customer customerInstance() {
		return this;
	}
	/*
	@Override
	public boolean equals(Object object) {
		if(! (object instanceof Customer))
			return false;
		
		Customer customer = (Customer) object;
		// TODO Auto-generated method stub
		return getPrimaryAddress().getEmail().equals
			(customer.getPrimaryAddress().getEmail());
	}*/
}
