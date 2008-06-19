
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
//@Analyzer(impl = example.EnglishAnalyzer.class)
public abstract class CandidateBase extends Person
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public CandidateBase() {
	}

	private com.oreon.kgauge.domain.User user = new com.oreon.kgauge.domain.User();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	public abstract Candidate candidateInstance();

	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
