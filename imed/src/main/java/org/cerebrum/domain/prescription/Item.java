package org.cerebrum.domain.prescription;

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
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "item")
@Name("item")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Item extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id", nullable = false, updatable = true)
	@ContainedIn
	protected Prescription prescription;

	@Column(name = "qty", unique = false)
	protected Integer qty;

	@Field(index = Index.TOKENIZED)
	protected String measurement;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "drug_id", nullable = false, updatable = true)
	@ContainedIn
	protected org.cerebrum.domain.drug.Drug drug;

	protected Route route;

	@Field(index = Index.TOKENIZED)
	protected String instructions;

	protected Boolean prn;

	@Column(name = "days", unique = false)
	protected Integer days;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "frequency_id", nullable = false, updatable = true)
	@ContainedIn
	protected Frequency frequency;

	protected Boolean subs;

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getQty() {
		return qty;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setDrug(org.cerebrum.domain.drug.Drug drug) {
		this.drug = drug;
	}

	public org.cerebrum.domain.drug.Drug getDrug() {
		return drug;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Route getRoute() {
		return route;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setPrn(Boolean prn) {
		this.prn = prn;
	}

	public Boolean getPrn() {
		return prn;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getDays() {
		return days;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setSubs(Boolean subs) {
		this.subs = subs;
	}

	public Boolean getSubs() {
		return subs;
	}

	@Transient
	public String getDisplayName() {
		return measurement;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("measurement");

		listSearchableFields.add("instructions");

		return listSearchableFields;
	}

}
