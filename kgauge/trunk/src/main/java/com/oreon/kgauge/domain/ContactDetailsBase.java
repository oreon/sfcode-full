
/**
 * This is generated code - to edit code or override methods use - ContactDetails class
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
public abstract class ContactDetailsBase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String streetAddress;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String city;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String state;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String country;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String zip;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String phone;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String email;

	/* Default Constructor */
	public ContactDetailsBase() {
	}

	/* Constructor with all attributes */
	public ContactDetailsBase(String streetAddress, String city, String state,
			String country, String zip, String phone, String email) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

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

	public String getZip() {
		return this.zip;
	}

	public String getPhone() {
		return this.phone;
	}

	@Column(nullable = false, unique = true)
	/*
	
	 */
	public String getEmail() {

		return this.email;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
