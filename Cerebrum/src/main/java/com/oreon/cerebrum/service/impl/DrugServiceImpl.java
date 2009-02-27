package com.oreon.cerebrum.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
@WebService(endpointInterface = "com.oreon.cerebrum.service.DrugService", serviceName = "DrugService")
public class DrugServiceImpl extends DrugServiceImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(DrugServiceImpl.class);
	private static final long serialVersionUID = 1L;

}
