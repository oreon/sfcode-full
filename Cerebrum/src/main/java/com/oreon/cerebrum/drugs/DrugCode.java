package com.oreon.cerebrum.drugs;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
import javax.jws.WebService;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Indexed;

import java.util.Date;

@Entity
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public class DrugCode extends DrugCodeBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(DrugCode.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public DrugCode() {
	}

	/* Constructor with all attributes */
	public DrugCode(String code) {
		super(code);
	}

	public DrugCode drugCodeInstance() {
		return this;
	}

}
