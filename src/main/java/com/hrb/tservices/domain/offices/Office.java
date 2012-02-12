package com.hrb.tservices.domain.offices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.hrb.tservices.ProjectUtils;

@Entity
@Table(name = "office")
@Filter(name = "archiveFilterDef")
@Name("office")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Office extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1142486201L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String officeId

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String headingEN

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String headingFR

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String address

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String city

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String province

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String postalCode

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String latitude

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String longitude

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String phone

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String fax

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String enInfo

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String frInfo

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String enHours

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String frHours

	;

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeId() {

		return officeId;

	}

	public void setHeadingEN(String headingEN) {
		this.headingEN = headingEN;
	}

	public String getHeadingEN() {

		return headingEN;

	}

	public void setHeadingFR(String headingFR) {
		this.headingFR = headingFR;
	}

	public String getHeadingFR() {

		return headingFR;

	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {

		return address;

	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {

		return city;

	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {

		return province;

	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCode() {

		return postalCode;

	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLatitude() {

		return latitude;

	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongitude() {

		return longitude;

	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {

		return phone;

	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {

		return fax;

	}

	public void setEnInfo(String enInfo) {
		this.enInfo = enInfo;
	}

	public String getEnInfo() {

		return enInfo;

	}

	public void setFrInfo(String frInfo) {
		this.frInfo = frInfo;
	}

	public String getFrInfo() {

		return frInfo;

	}

	public void setEnHours(String enHours) {
		this.enHours = enHours;
	}

	public String getEnHours() {

		return enHours;

	}

	public void setFrHours(String frHours) {
		this.frHours = frHours;
	}

	public String getFrHours() {

		return frHours;

	}

	@Transient
	public String getDisplayName() {
		try {
			return officeId;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("officeId");

		listSearchableFields.add("headingEN");

		listSearchableFields.add("headingFR");

		listSearchableFields.add("address");

		listSearchableFields.add("city");

		listSearchableFields.add("province");

		listSearchableFields.add("postalCode");

		listSearchableFields.add("latitude");

		listSearchableFields.add("longitude");

		listSearchableFields.add("phone");

		listSearchableFields.add("fax");

		listSearchableFields.add("enInfo");

		listSearchableFields.add("frInfo");

		listSearchableFields.add("enHours");

		listSearchableFields.add("frHours");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getOfficeId() + " ");

		builder.append(getHeadingEN() + " ");

		builder.append(getHeadingFR() + " ");

		builder.append(getAddress() + " ");

		builder.append(getCity() + " ");

		builder.append(getProvince() + " ");

		builder.append(getPostalCode() + " ");

		builder.append(getLatitude() + " ");

		builder.append(getLongitude() + " ");

		builder.append(getPhone() + " ");

		builder.append(getFax() + " ");

		builder.append(getEnInfo() + " ");

		builder.append(getFrInfo() + " ");

		builder.append(getEnHours() + " ");

		builder.append(getFrHours() + " ");

		return builder.toString();
	}

}
