package com.oreon.cerebrum.patient;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Embeddable
public class Address implements java.io.Serializable {
	private static final long serialVersionUID = -855511539L;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String streetAddress

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String city

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String State

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String phone

	;

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress() {

		return streetAddress;

	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;

	}

	public void setState(String State) {
		this.State = State;
	}

	public String getState() {

		return State;

	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {

		return phone;

	}

	@Transient
	public String getDisplayName() {
		try {
			return streetAddress;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}