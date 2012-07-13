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
@Table(name = "resourceservice")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ResourceService extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1230156663L;

	@OneToMany(mappedBy = "resourceService", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "resourceService_ID", nullable = false)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<ResourceInstance> resourceInstances = new HashSet<ResourceInstance>();

	public void addResourceInstance(ResourceInstance resourceInstance) {
		resourceInstance.setResourceService(this);
		this.resourceInstances.add(resourceInstance);
	}

	@Transient
	public List<com.sasktel.om.omdomain.ResourceInstance> getListResourceInstances() {
		return new ArrayList<com.sasktel.om.omdomain.ResourceInstance>(
				resourceInstances);
	}

	//JSF Friendly function to get count of collections
	public int getResourceInstancesCount() {
		return resourceInstances.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerService_id", nullable = false, updatable = true)
	@ContainedIn
	protected CustomerService customerService

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "telecomResource_id", nullable = false, updatable = true)
	@ContainedIn
	protected TelecomResource telecomResource

	;

	public void setResourceInstances(Set<ResourceInstance> resourceInstances) {
		this.resourceInstances = resourceInstances;
	}

	public Set<ResourceInstance> getResourceInstances() {
		return resourceInstances;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CustomerService getCustomerService() {

		return customerService;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setTelecomResource(TelecomResource telecomResource) {
		this.telecomResource = telecomResource;
	}

	public TelecomResource getTelecomResource() {

		return telecomResource;

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

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getCustomerService() != null)
			builder.append("customerService:"
					+ getCustomerService().getDisplayName() + " ");

		if (getTelecomResource() != null)
			builder.append("telecomResource:"
					+ getTelecomResource().getDisplayName() + " ");

		for (BaseEntity e : resourceInstances) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
