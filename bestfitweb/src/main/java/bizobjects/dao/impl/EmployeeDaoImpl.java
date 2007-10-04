package bizobjects.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class EmployeeDaoImpl extends EmployeeDaoImplBase {

	private static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);

	public EmployeeDaoImpl employeeDaoImplInstance() {
		return this;
	}
}
