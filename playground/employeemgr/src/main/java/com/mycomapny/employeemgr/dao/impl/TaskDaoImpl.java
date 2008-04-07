package com.mycomapny.employeemgr.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@org.springframework.stereotype.Repository
public class TaskDaoImpl extends TaskDaoImplBase {

	private static final Logger log = Logger.getLogger(TaskDaoImpl.class);

	public TaskDaoImpl taskDaoImplInstance() {
		return this;
	}
}
