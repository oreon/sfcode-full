
/**
 * This is generated code - to edit code or override methods use - Person class
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
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public abstract class PersonBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String firstName;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String lastName;

	protected Date dateOfBirth;

	/* Default Constructor */
	public PersonBase() {
	}

	/* Constructor with all attributes */
	public PersonBase(String firstName, String lastName, Date dateOfBirth) {

		this.firstName = firstName;

		this.lastName = lastName;

		this.dateOfBirth = dateOfBirth;

	}

	@Column(nullable = false, unique = false)
	public String getFirstName() {

		return this.firstName;

	}

	@Column(nullable = false, unique = false)
	public String getLastName() {

		return this.lastName;

	}

	@Column(nullable = false, unique = false)
	public Date getDateOfBirth() {

		return this.dateOfBirth;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	private com.oreon.kgauge.domain.ContactDetails contactDetails = new com.oreon.kgauge.domain.ContactDetails();

	@XmlTransient
	public com.oreon.kgauge.domain.ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(
			com.oreon.kgauge.domain.ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	@Transient
	public String getDisplayName() {
		return lastName + ", " + firstName + "";
	}

}
