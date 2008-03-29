package com.oreon.kgauge.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@org.springframework.stereotype.Repository
public class GrantedAuthorityDaoImpl extends GrantedAuthorityDaoImplBase {

	private static final Logger log = Logger
			.getLogger(GrantedAuthorityDaoImpl.class);

}
