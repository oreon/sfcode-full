package com.publicfountain.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CommentDaoImpl extends CommentDaoImplBase {

	private static final Logger log = Logger.getLogger(CommentDaoImpl.class);

	public CommentDaoImpl commentDaoImplInstance() {
		return this;
	}
}
