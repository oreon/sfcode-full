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
@Table(name = "immunization")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Immunization extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1013563995L;

	@Column(name = "date", unique = false)
	protected Date date

	= new Date();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected Patient patient

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "vaccine_id", nullable = false, updatable = true)
	protected Vaccine vaccine

	;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {

		return date;

	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {

		return patient;

	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}

	public Vaccine getVaccine() {

		return vaccine;

	}

	@Transient
	public String getDisplayName() {
		try {
			return date + "";
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

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getVaccine() != null)
			builder.append("vaccine:" + getVaccine().getDisplayName() + " ");

		return builder.toString();
	}

}
