package com.oreon.cerebrum.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

public class DrugBackingBean extends DrugBackingBeanBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(DrugBackingBean.class);
	private static final long serialVersionUID = 1L;

}
