package com.oreon.cerebrum.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "bedstay")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class BedStay extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 537868947L;

	@Column(unique = false)
	protected Date fromDate

	;

	@Column(unique = false)
	protected Date toDate

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "admission_id", nullable = false, updatable = true)
	protected Admission admission

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bed_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.facility.Bed bed

	;

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getFromDate() {

		return fromDate;

	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getToDate() {

		return toDate;

	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Admission getAdmission() {

		return admission;

	}

	public void setBed(com.oreon.cerebrum.facility.Bed bed) {
		this.bed = bed;
	}

	public com.oreon.cerebrum.facility.Bed getBed() {

		return bed;

	}

	@Transient
	public String getDisplayName() {
		try {
			return fromDate + "";
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

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getAdmission() != null)
			builder
					.append("admission:" + getAdmission().getDisplayName()
							+ " ");

		if (getBed() != null)
			builder.append("bed:" + getBed().getDisplayName() + " ");

		return builder.toString();
	}

}
