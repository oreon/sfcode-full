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

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "immunization")
@Filter(name = "archiveFilterDef")
@Name("immunization")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Immunization extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1013563995L;

	@Column(unique = false)
	protected Date date

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	@ContainedIn
	protected Patient patient

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "vaccine_id", nullable = false, updatable = true)
	@ContainedIn
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
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
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