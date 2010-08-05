package com.nas.recovery.domain.appraisal;

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

@Entity
@Table(name = "appraisal")
@Name("appraisal")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Appraisal extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 54719964L;

	protected Integer appraisalNumber;

	@Lob
	protected String specialInstruction;

	protected ServiceType serviceType;

	protected Status status;

	protected Date ordered;

	protected Date dueDate;

	protected Date received;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty;

	public void setAppraisalNumber(Integer appraisalNumber) {
		this.appraisalNumber = appraisalNumber;
	}

	public Integer getAppraisalNumber() {

		return appraisalNumber;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public String getSpecialInstruction() {

		return specialInstruction;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public ServiceType getServiceType() {

		return serviceType;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {

		return status;
	}

	public void setOrdered(Date ordered) {
		this.ordered = ordered;
	}

	public Date getOrdered() {

		return ordered;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDueDate() {

		return dueDate;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public Date getReceived() {

		return received;
	}

	public void setRealEstateProperty(
			com.nas.recovery.domain.realestate.RealEstateProperty realEstateProperty) {
		this.realEstateProperty = realEstateProperty;
	}

	public com.nas.recovery.domain.realestate.RealEstateProperty getRealEstateProperty() {

		return realEstateProperty;
	}

	@Transient
	public String getDisplayName() {
		return appraisalNumber + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		return listSearchableFields;
	}

}
