package com.oreon.olympics.domain.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class CountryDaoImpl extends CountryDaoImplBase {

	private static final Logger log = Logger.getLogger(CountryDaoImpl.class);

	public CountryDaoImpl countryDaoImplInstance() {
		return this;
	}
}
