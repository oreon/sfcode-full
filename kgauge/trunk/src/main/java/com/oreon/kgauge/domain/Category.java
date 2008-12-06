package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class Category extends CategoryBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Category.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Category() {
	}

	/* Constructor with all attributes */
	public Category(String name) {
		super(name);
	}

	public Category categoryInstance() {
		return this;
	}

}
