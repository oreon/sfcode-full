package com.oreon.cerebrum.drugs;

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

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "atcdrug")
@Filter(name = "archiveFilterDef")
@Name("atcDrug")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class AtcDrug extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1623432457L;

	@NotNull
	@Column(name = "code", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String code

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "parent_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	private Set<AtcDrug> subcategories = new HashSet<AtcDrug>();

	public void addSubcategorie(AtcDrug subcategorie) {
		subcategorie.setParent(this);
		this.subcategories.add(subcategorie);
	}

	@Transient
	public List<com.oreon.cerebrum.drugs.AtcDrug> getListSubcategories() {
		return new ArrayList<com.oreon.cerebrum.drugs.AtcDrug>(subcategories);
	}

	//JSF Friendly function to get count of collections
	public int getSubcategoriesCount() {
		return subcategories.size();
	}

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "drug_id", nullable = true, updatable = true)
	@ContainedIn
	@NotNull
	protected Drug drug

	;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", nullable = true, updatable = true)
	@NotNull
	protected AtcDrug parent

	;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {

		return code;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setSubcategories(Set<AtcDrug> subcategories) {
		this.subcategories = subcategories;
	}

	public Set<AtcDrug> getSubcategories() {
		return subcategories;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Drug getDrug() {

		return drug;

	}

	public void setParent(AtcDrug parent) {
		this.parent = parent;
	}

	public AtcDrug getParent() {

		return parent;

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

		listSearchableFields.add("code");

		listSearchableFields.add("name");

		listSearchableFields.add("subcategories.code");

		listSearchableFields.add("subcategories.name");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getCode() + " ");

		builder.append(getName() + " ");

		if (getDrug() != null)
			builder.append("drug:" + getDrug().getDisplayName() + " ");

		if (getParent() != null)
			builder.append("parent:" + getParent().getDisplayName() + " ");

		for (BaseEntity e : subcategories) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
