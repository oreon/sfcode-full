package com.oreon.cerebrum.patient;

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
import org.hibernate.annotations.Filters;
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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "prescriptionitem")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("prescriptionItem")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class PrescriptionItem extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1426410404L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.drugs.Drug drug = new com.oreon.cerebrum.drugs.Drug();

	@Column(unique = false)
	protected Double qty

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String strength

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "prescription_id", nullable = false, updatable = true)
	protected Prescription prescription

	;

	@Column(unique = false)
	protected Route route

	;

	@Column(unique = false)
	protected Integer duration

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "frequecy_id", nullable = false, updatable = true)
	protected Frequecy frequecy

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String remarks

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String brandName

	;

	public void setDrug(com.oreon.cerebrum.drugs.Drug drug) {
		this.drug = drug;
	}

	public com.oreon.cerebrum.drugs.Drug getDrug() {

		return drug;

	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getQty() {

		return qty;

	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getStrength() {

		return strength;

	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Prescription getPrescription() {

		return prescription;

	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Route getRoute() {

		return route;

	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getDuration() {

		return duration;

	}

	public void setFrequecy(Frequecy frequecy) {
		this.frequecy = frequecy;
	}

	public Frequecy getFrequecy() {

		return frequecy;

	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;

	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandName() {

		return brandName;

	}

	@Transient
	public String getDisplayName() {
		try {
			return strength;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("strength");

		listSearchableFields.add("remarks");

		listSearchableFields.add("brandName");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getStrength() + " ");

		builder.append(getRemarks() + " ");

		builder.append(getBrandName() + " ");

		if (getDrug() != null)
			builder.append("drug:" + getDrug().getDisplayName() + " ");

		if (getPrescription() != null)
			builder.append("prescription:" + getPrescription().getDisplayName()
					+ " ");

		if (getFrequecy() != null)
			builder.append("frequecy:" + getFrequecy().getDisplayName() + " ");

		return builder.toString();
	}

}
