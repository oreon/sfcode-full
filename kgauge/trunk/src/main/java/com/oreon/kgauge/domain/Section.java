package com.oreon.kgauge.domain;

import java.util.Iterator;

import javax.faces.event.ActionEvent;
import javax.persistence.Entity;

import org.apache.log4j.Logger;



@Entity
public class Section extends SectionBase {

	private static final Logger log = Logger.getLogger(Section.class);

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
