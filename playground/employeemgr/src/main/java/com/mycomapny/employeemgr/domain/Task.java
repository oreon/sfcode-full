package com.mycomapny.employeemgr.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

@Entity
public class Task extends TaskBase {

	private static final Logger log = Logger.getLogger(Task.class);

	public Task taskInstance() {
		return this;
	}
}
