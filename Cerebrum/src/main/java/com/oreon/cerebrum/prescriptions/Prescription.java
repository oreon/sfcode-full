package com.oreon.cerebrum.prescriptions;

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
public class Prescription extends PrescriptionBase
		implements
			java.io.Serializable {

	private static final Logger log = Logger.getLogger(Prescription.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public Prescription() {
	}

	/* Constructor with all attributes */
	public Prescription(String Dx) {
		super(Dx);
	}

	public Prescription prescriptionInstance() {
		return this;
	}

}
