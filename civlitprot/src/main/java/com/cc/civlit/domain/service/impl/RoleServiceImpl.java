package com.cc.civlit.domain.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.cc.civlit.domain.service.RoleService", serviceName = "RoleService")
public class RoleServiceImpl extends RoleServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(RoleServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
