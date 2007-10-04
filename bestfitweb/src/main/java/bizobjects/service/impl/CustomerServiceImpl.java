package bizobjects.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CustomerServiceImpl extends CustomerServiceImplBase {

	private static final Logger log = Logger
			.getLogger(CustomerServiceImpl.class);

	public CustomerServiceImpl customerServiceImplInstance() {
		return this;
	}
}
