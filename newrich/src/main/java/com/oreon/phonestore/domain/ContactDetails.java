package com.oreon.phonestore.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

@Embeddable
public class ContactDetails implements java.io.Serializable {
	private static final long serialVersionUID = 369764046L;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String phone

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String secondaryPhone

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String city

	;

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {

		return phone;

	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getSecondaryPhone() {

		return secondaryPhone;

	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;

	}

	@Transient
	public String getDisplayName() {
		try {
			return phone;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}
