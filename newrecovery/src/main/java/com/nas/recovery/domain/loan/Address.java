package com.nas.recovery.domain.loan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

@Embeddable
@Indexed
public class Address implements java.io.Serializable {
	private static final long serialVersionUID = 382203720L;

	@Field(index = Index.TOKENIZED)
	protected String streetDirection;

	protected Integer streetNumber;

	@Field(index = Index.TOKENIZED)
	protected String streetName;

	@Field(index = Index.TOKENIZED)
	protected String province;

	@Field(index = Index.TOKENIZED)
	protected String streetType;

	@Field(index = Index.TOKENIZED)
	protected String postalCode;

	protected Integer unitNumber;

	@Field(index = Index.TOKENIZED)
	protected String city;

	public void setStreetDirection(String streetDirection) {
		this.streetDirection = streetDirection;
	}

	public String getStreetDirection() {

		return streetDirection;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public Integer getStreetNumber() {

		return streetNumber;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetName() {

		return streetName;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {

		return province;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreetType() {

		return streetType;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCode() {

		return postalCode;
	}

	public void setUnitNumber(Integer unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Integer getUnitNumber() {

		return unitNumber;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;
	}

}
