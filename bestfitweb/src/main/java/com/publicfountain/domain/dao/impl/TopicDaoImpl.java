package com.publicfountain.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class TopicDaoImpl extends TopicDaoImplBase {

	private static final Logger log = Logger.getLogger(TopicDaoImpl.class);

	public TopicDaoImpl topicDaoImplInstance() {
		return this;
	}
}
