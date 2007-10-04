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
}
