package com.cc.civlit.domain.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

public class DivsionBackingBean extends DivsionBackingBeanBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(DivsionBackingBean.class);
	private static final long serialVersionUID = 1L;

}
