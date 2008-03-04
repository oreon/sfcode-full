package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class Category extends CategoryBase {

	private static final Logger log = Logger.getLogger(Category.class);

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
