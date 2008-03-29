package com.oreon.kgauge.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

public class GrantedAuthorityBackingBean
		extends
			GrantedAuthorityBackingBeanBase {

	private static final Logger log = Logger
			.getLogger(GrantedAuthorityBackingBean.class);

}
