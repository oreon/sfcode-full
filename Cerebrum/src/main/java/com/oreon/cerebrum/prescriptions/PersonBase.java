
/**
 * This is generated code - to edit code or override methods use - Person class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.prescriptions;

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

	protected Gender gender;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String phone;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String address;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String email;

	/* Default Constructor */
	public PersonBase() {
	}

	/* Constructor with all attributes */
	public PersonBase(String firstName, String lastName, Date dateOfBirth,
			Gender gender, String phone, String address, String email) {

		this.firstName = firstName;

		this.lastName = lastName;

		this.dateOfBirth = dateOfBirth;

		this.gender = gender;

		this.phone = phone;

		this.address = address;

		this.email = email;

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

	public Gender getGender() {
		return this.gender;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
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

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public String getDisplayName() {
		return firstName + "";
	}

}
