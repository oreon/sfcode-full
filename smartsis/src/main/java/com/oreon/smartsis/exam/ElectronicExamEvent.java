package com.oreon.smartsis.exam;

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
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
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

import com.oreon.smartsis.ProjectUtils;

@Entity
@Table(name = "electronicexamevent")
@Filter(name = "archiveFilterDef")
@Name("electronicExamEvent")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ElectronicExamEvent extends BusinessEntity
		implements
			java.io.Serializable,
			com.sun.xml.internal.bind.CycleRecoverable {
	private static final long serialVersionUID = -1946424660L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "electronicExam_id", nullable = false, updatable = true)
	@ContainedIn
	protected ElectronicExam electronicExam

	;

	@OneToMany(mappedBy = "electronicExamEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "electronicExamEvent_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<ElectronicExamInstance> electronicExamInstances = new HashSet<ElectronicExamInstance>();

	public void addElectronicExamInstances(
			ElectronicExamInstance electronicExamInstances) {
		electronicExamInstances.setElectronicExamEvent(this);
		this.electronicExamInstances.add(electronicExamInstances);
	}

	@Transient
	public List<com.oreon.smartsis.exam.ElectronicExamInstance> getListElectronicExamInstances() {
		return new ArrayList<com.oreon.smartsis.exam.ElectronicExamInstance>(
				electronicExamInstances);
	}

	//JSF Friendly function to get count of collections
	public int getElectronicExamInstancesCount() {
		return electronicExamInstances.size();
	}

	protected Date dateOfExam

	;

	@Lob
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String remarks

	;

	public void setElectronicExam(ElectronicExam electronicExam) {
		this.electronicExam = electronicExam;
	}

	public ElectronicExam getElectronicExam() {

		return electronicExam;

	}

	public void setElectronicExamInstances(
			Set<ElectronicExamInstance> electronicExamInstances) {
		this.electronicExamInstances = electronicExamInstances;
	}

	public Set<ElectronicExamInstance> getElectronicExamInstances() {
		return electronicExamInstances;
	}

	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public Date getDateOfExam() {

		return dateOfExam;

	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;

	}

	@Transient
	public String getDisplayName() {
		try {
			return electronicExam + "";
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

		listSearchableFields.add("remarks");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getRemarks() + " ");

		if (getElectronicExam() != null)
			builder.append("electronicExam:"
					+ getElectronicExam().getDisplayName() + " ");

		for (BusinessEntity e : electronicExamInstances) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
