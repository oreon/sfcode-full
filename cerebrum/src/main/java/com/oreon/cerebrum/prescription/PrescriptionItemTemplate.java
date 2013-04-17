package com.oreon.cerebrum.prescription;

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
@Table(name = "prescriptionitemtemplate")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("prescriptionItemTemplate")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class PrescriptionItemTemplate extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 629235233L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.drugs.Drug drug

	;

	@Column(unique = false)
	protected Double qty

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "frequency_id", nullable = false, updatable = true)
	protected Frequency frequency

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String strength

	;

	@Column(unique = false)
	protected Route route = com.oreon.cerebrum.prescription.Route.PO

	;

	@Column(unique = false)
	protected Integer duration

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

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "prescriptionTemplate_id", nullable = false, updatable = true)
	protected PrescriptionTemplate prescriptionTemplate

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

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public Frequency getFrequency() {

		return frequency;

	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getStrength() {

		return strength;

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

	public void setPrescriptionTemplate(
			PrescriptionTemplate prescriptionTemplate) {
		this.prescriptionTemplate = prescriptionTemplate;
	}

	public PrescriptionTemplate getPrescriptionTemplate() {

		return prescriptionTemplate;

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

		if (getFrequency() != null)
			builder
					.append("frequency:" + getFrequency().getDisplayName()
							+ " ");

		if (getPrescriptionTemplate() != null)
			builder.append("prescriptionTemplate:"
					+ getPrescriptionTemplate().getDisplayName() + " ");

		return builder.toString();
	}

}
