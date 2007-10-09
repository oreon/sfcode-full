package com.publicfountain.domain.service.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TopicServiceImpl extends TopicServiceImplBase {

	private static final Logger log = Logger.getLogger(TopicServiceImpl.class);

	public TopicServiceImpl topicServiceImplInstance() {
		return this;
	}
}
