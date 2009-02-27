package com.oreon.cerebrum.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

@org.springframework.stereotype.Repository
public class PrescriptionDaoImpl extends PrescriptionDaoImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger
			.getLogger(PrescriptionDaoImpl.class);
	private static final long serialVersionUID = 1L;

}
