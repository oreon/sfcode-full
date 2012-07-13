package com.sasktel.om.omdomain;

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

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.sasktel.om.ProjectUtils;

@Entity
@Table(name = "customerservicespec")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class CustomerServiceSpec extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 244086364L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@Column(unique = false)
	protected Double price

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String description

	;

	@OneToMany(mappedBy = "customerServiceSpec", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customerServiceSpec_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<ResourceServiceSpec> resourceServiceSpecs = new HashSet<ResourceServiceSpec>();

	public void addResourceServiceSpec(ResourceServiceSpec resourceServiceSpec) {
		resourceServiceSpec.setCustomerServiceSpec(this);
		this.resourceServiceSpecs.add(resourceServiceSpec);
	}

	@Transient
	public List<com.sasktel.om.omdomain.ResourceServiceSpec> getListResourceServiceSpecs() {
		return new ArrayList<com.sasktel.om.omdomain.ResourceServiceSpec>(
				resourceServiceSpecs);
	}

	//JSF Friendly function to get count of collections
	public int getResourceServiceSpecsCount() {
		return resourceServiceSpecs.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "workflow_id", nullable = false, updatable = true)
	@ContainedIn
	protected Workflow workflow

	;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {

		return price;

	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {

		return description;

	}

	public void setResourceServiceSpecs(
			Set<ResourceServiceSpec> resourceServiceSpecs) {
		this.resourceServiceSpecs = resourceServiceSpecs;
	}

	public Set<ResourceServiceSpec> getResourceServiceSpecs() {
		return resourceServiceSpecs;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Workflow getWorkflow() {

		return workflow;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getDescriptionAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(description
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return description != null ? description : "";
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

		listSearchableFields.add("description");

		listSearchableFields.add("resourceServiceSpecs.name");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		builder.append(getDescription() + " ");

		if (getWorkflow() != null)
			builder.append("workflow:" + getWorkflow().getDisplayName() + " ");

		for (BaseEntity e : resourceServiceSpecs) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
