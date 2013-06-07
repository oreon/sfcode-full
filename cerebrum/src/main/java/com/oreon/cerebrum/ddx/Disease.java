package com.oreon.cerebrum.ddx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
@Table(name = "disease")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("disease")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Disease extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 714241030L;

	@Column(unique = false)
	protected com.oreon.cerebrum.patient.Gender gender

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@OneToMany(mappedBy = "relatedDisease", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "relatedDisease_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Disease> differentialDiagnoses = new HashSet<Disease>();

	public void addDifferentialDiagnose(Disease differentialDiagnose) {

		differentialDiagnose.setRelatedDisease(this);

		this.differentialDiagnoses.add(differentialDiagnose);
	}

	@Transient
	public List<com.oreon.cerebrum.ddx.Disease> getListDifferentialDiagnoses() {
		return new ArrayList<com.oreon.cerebrum.ddx.Disease>(
				differentialDiagnoses);
	}

	//JSF Friendly function to get count of collections
	public int getDifferentialDiagnosesCount() {
		return differentialDiagnoses.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "relatedDisease_id", nullable = false, updatable = true)
	protected Disease relatedDisease

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "conditionCategory_id", nullable = true, updatable = true)
	protected ConditionCategory conditionCategory

	;

	public void setGender(com.oreon.cerebrum.patient.Gender gender) {
		this.gender = gender;
	}

	public com.oreon.cerebrum.patient.Gender getGender() {

		return gender;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setDifferentialDiagnoses(Set<Disease> differentialDiagnoses) {
		this.differentialDiagnoses = differentialDiagnoses;
	}

	public Set<Disease> getDifferentialDiagnoses() {
		return differentialDiagnoses;
	}

	public void setRelatedDisease(Disease relatedDisease) {
		this.relatedDisease = relatedDisease;
	}

	public Disease getRelatedDisease() {

		return relatedDisease;

	}

	public void setConditionCategory(ConditionCategory conditionCategory) {
		this.conditionCategory = conditionCategory;
	}

	public ConditionCategory getConditionCategory() {

		return conditionCategory;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getRelatedDisease() != null)
			builder.append("relatedDisease:"
					+ getRelatedDisease().getDisplayName() + " ");

		if (getConditionCategory() != null)
			builder.append("conditionCategory:"
					+ getConditionCategory().getDisplayName() + " ");

		for (BaseEntity e : differentialDiagnoses) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
