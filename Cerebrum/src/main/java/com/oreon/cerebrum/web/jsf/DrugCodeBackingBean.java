package com.oreon.cerebrum.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

public class DrugCodeBackingBean extends DrugCodeBackingBeanBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(DrugCodeBackingBean.class);
	private static final long serialVersionUID = 1L;

}
