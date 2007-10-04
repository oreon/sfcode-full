package managers;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class CustomerManagerImpl extends CustomerManagerImplBase {

	private static final Logger log = Logger
			.getLogger(CustomerManagerImpl.class);

	public CustomerManagerImpl customerManagerImplInstance() {
		return this;
	}
}
