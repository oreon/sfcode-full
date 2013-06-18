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
@Table(name = "section")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("section")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Section extends com.oreon.cerebrum.codes.AbstractCode
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -1956883681L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "chapter_id", nullable = false, updatable = true)
	protected Chapter chapter

	;

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "section_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Code> codes = new HashSet<Code>();

	public void addCode(Code code) {

		code.setSection(this);

		this.codes.add(code);
	}

	@Transient
	public List<com.oreon.cerebrum.codes.Code> getListCodes() {
		return new ArrayList<com.oreon.cerebrum.codes.Code>(codes);
	}

	//JSF Friendly function to get count of collections
	public int getCodesCount() {
		return codes.size();
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Chapter getChapter() {

		return chapter;

	}

	public void setCodes(Set<Code> codes) {
		this.codes = codes;
	}

	public Set<Code> getCodes() {
		return codes;
	}

	@Transient
	public String getDisplayName() {
		try {
			return chapter + "";
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

		listSearchableFields.add("codes.includes");

		listSearchableFields.add("codes.excludes");

		listSearchableFields.add("codes.codeFirst");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getChapter() != null)
			builder.append("chapter:" + getChapter().getDisplayName() + " ");

		for (BaseEntity e : codes) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
