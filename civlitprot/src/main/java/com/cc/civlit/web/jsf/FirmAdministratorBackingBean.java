package com.cc.civlit.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

public class FirmAdministratorBackingBean
		extends
			FirmAdministratorBackingBeanBase implements java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(FirmAdministratorBackingBean.class);
	private static final long serialVersionUID = 1L;

}
