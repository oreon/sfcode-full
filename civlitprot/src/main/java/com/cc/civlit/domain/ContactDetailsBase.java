
/**
 * This is generated code - to edit code or override methods use - ContactDetails class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain;

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
public abstract class ContactDetailsBase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String address1;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String address2;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String city;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String state;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String country;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String postalCode;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String phone;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String fax;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String email;

	/* Default Constructor */
	public ContactDetailsBase() {
	}

	/* Constructor with all attributes */
	public ContactDetailsBase(String address1, String address2, String city,
			String state, String country, String postalCode, String phone,
			String fax, String email) {
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getAddress1() {

		return this.address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getCity() {

		return this.city;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getState() {

		return this.state;
	}

	@Column(nullable = false, unique = false)
	/*
	
	 */
	public String getCountry() {

		return this.country;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getFax() {
		return this.fax;
	}

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getEmail() {

		return this.email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
