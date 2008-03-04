package com.oreon.kgauge.dao.impl;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class SectionDaoImpl extends SectionDaoImplBase {

	private static final Logger log = Logger.getLogger(SectionDaoImpl.class);

	public SectionDaoImpl sectionDaoImplInstance() {
		return this;
	}
}
