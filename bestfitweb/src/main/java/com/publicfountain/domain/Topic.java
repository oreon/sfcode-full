package com.publicfountain.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Topic extends TopicBase {

	private static final Logger log = Logger.getLogger(Topic.class);

	public Topic topicInstance() {
		return this;
	}
}
