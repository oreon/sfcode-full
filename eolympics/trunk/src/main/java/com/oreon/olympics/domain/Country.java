package com.oreon.olympics.domain;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

public class Country extends CountryBase {

	private static final Logger log = Logger.getLogger(Country.class);

	public Country countryInstance() {
		return this;
	}
}
