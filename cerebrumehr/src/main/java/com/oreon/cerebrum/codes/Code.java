package com.oreon.cerebrum.codes;

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

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

@Entity
@Table(name = "code")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Code extends com.oreon.cerebrum.codes.AbstractCode
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -31405977L;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String includes

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String notIncludedHere

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String codeFirst

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "section_id", nullable = false, updatable = true)
	protected Section section

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String notCodedHere

	;

	@Lob
	@Column(name = "codeAlso", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String codeAlso

	;

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public String getIncludes() {

		return includes;

	}

	public void setNotIncludedHere(String notIncludedHere) {
		this.notIncludedHere = notIncludedHere;
	}

	public String getNotIncludedHere() {

		return notIncludedHere;

	}

	public void setCodeFirst(String codeFirst) {
		this.codeFirst = codeFirst;
	}

	public String getCodeFirst() {

		return codeFirst;

	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Section getSection() {

		return section;

	}

	public void setNotCodedHere(String notCodedHere) {
		this.notCodedHere = notCodedHere;
	}

	public String getNotCodedHere() {

		return notCodedHere;

	}

	public void setCodeAlso(String codeAlso) {
		this.codeAlso = codeAlso;
	}

	public String getCodeAlso() {

		return codeAlso;

	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getIncludesAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					includes.trim(), 100, 200, "...");
		} catch (Exception e) {
			return includes != null ? includes : "";
		}
	}

	@Transient
	public String getNotIncludedHereAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(notIncludedHere
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return notIncludedHere != null ? notIncludedHere : "";
		}
	}

	@Transient
	public String getCodeFirstAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(codeFirst
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return codeFirst != null ? codeFirst : "";
		}
	}

	@Transient
	public String getNotCodedHereAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(notCodedHere
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return notCodedHere != null ? notCodedHere : "";
		}
	}

	@Transient
	public String getCodeAlsoAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					codeAlso.trim(), 100, 200, "...");
		} catch (Exception e) {
			return codeAlso != null ? codeAlso : "";
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

		listSearchableFields.add("includes");

		listSearchableFields.add("notIncludedHere");

		listSearchableFields.add("codeFirst");

		listSearchableFields.add("notCodedHere");

		listSearchableFields.add("codeAlso");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getIncludes() + " ");

		builder.append(getNotIncludedHere() + " ");

		builder.append(getCodeFirst() + " ");

		builder.append(getNotCodedHere() + " ");

		builder.append(getCodeAlso() + " ");

		if (getSection() != null)
			builder.append("section:" + getSection().getDisplayName() + " ");

		return builder.toString();
	}

}