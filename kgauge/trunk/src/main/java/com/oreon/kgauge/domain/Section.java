package com.oreon.kgauge.domain;

import javax.persistence.Entity;

import org.apache.log4j.Logger;

@Entity
public class Section extends SectionBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Section.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Section() {
	}

	/* Constructor with all attributes */
	public Section(String name) {
		super(name);
	}

	public Section sectionInstance() {
		return this;
	}

}
