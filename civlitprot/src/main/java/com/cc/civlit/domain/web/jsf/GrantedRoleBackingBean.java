package com.cc.civlit.domain.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

public class GrantedRoleBackingBean extends GrantedRoleBackingBeanBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(GrantedRoleBackingBean.class);
	private static final long serialVersionUID = 1L;

}
