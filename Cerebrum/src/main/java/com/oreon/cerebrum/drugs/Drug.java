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
public class Drug extends DrugBase implements java.io.Serializable {

	private static final Logger log = Logger.getLogger(Drug.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Drug() {
	}

	/* Constructor with all attributes */
	public Drug(String name, Integer bioavalability, Integer halfLife,
			Excretion excretion, PregnancyCategory pregnancyCategory,
			String text) {
		super(name, bioavalability, halfLife, excretion, pregnancyCategory,
				text);
	}

	public Drug drugInstance() {
		return this;
	}

}
