package com.cc.civlit.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import java.util.Date;

@org.springframework.stereotype.Repository
public class DivsionDaoImpl extends DivsionDaoImplBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(DivsionDaoImpl.class);
	private static final long serialVersionUID = 1L;

}
