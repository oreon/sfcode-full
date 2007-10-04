package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Embeddable
public class Address extends AddressBase {

	private static final Logger log = Logger.getLogger(Address.class);

	public Address addressInstance() {
		return this;
	}
}
