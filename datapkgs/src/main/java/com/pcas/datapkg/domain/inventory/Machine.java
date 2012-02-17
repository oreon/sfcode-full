package com.pcas.datapkg.domain.inventory;

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

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.pcas.datapkg.ProjectUtils;

@Entity
@Table(name = "machine")
@Filter(name = "archiveFilterDef")
@Name("machine")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Machine extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1776872649L;

	@OneToMany(mappedBy = "machine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "machine_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<DrugInventory> drugInventorys = new HashSet<DrugInventory>();

	public void addDrugInventory(DrugInventory drugInventory) {
		drugInventory.setMachine(this);
		this.drugInventorys.add(drugInventory);
	}

	@Transient
	public List<com.pcas.datapkg.domain.inventory.DrugInventory> getListDrugInventorys() {
		return new ArrayList<com.pcas.datapkg.domain.inventory.DrugInventory>(
				drugInventorys);
	}

	//JSF Friendly function to get count of collections
	public int getDrugInventorysCount() {
		return drugInventorys.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true)
	@ContainedIn
	protected Customer customer

	;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", nullable = false, updatable = true)
	@ContainedIn
	protected Location location

	;

	public void setDrugInventorys(Set<DrugInventory> drugInventorys) {
		this.drugInventorys = drugInventorys;
	}

	public Set<DrugInventory> getDrugInventorys() {
		return drugInventorys;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {

		return customer;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {

		return location;

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
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
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

		if (getCustomer() != null)
			builder.append("customer:" + getCustomer().getDisplayName() + " ");

		if (getLocation() != null)
			builder.append("location:" + getLocation().getDisplayName() + " ");

		for (BusinessEntity e : drugInventorys) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
