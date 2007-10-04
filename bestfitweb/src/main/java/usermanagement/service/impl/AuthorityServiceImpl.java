package usermanagement.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class AuthorityServiceImpl extends AuthorityServiceImplBase {

	private static final Logger log = Logger
			.getLogger(AuthorityServiceImpl.class);

	public AuthorityServiceImpl authorityServiceImplInstance() {
		return this;
	}
}
