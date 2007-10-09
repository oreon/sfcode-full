package bizobjects.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class RegisteredUserDaoImpl extends RegisteredUserDaoImplBase {

	private static final Logger log = Logger
			.getLogger(RegisteredUserDaoImpl.class);

	public RegisteredUserDaoImpl registeredUserDaoImplInstance() {
		return this;
	}
}
