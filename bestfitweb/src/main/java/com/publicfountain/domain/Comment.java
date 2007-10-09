package com.publicfountain.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class Comment extends CommentBase {

	private static final Logger log = Logger.getLogger(Comment.class);

	public Comment commentInstance() {
		return this;
	}
}
