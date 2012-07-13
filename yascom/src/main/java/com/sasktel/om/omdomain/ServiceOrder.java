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
@Table(name = "serviceorder")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ServiceOrder extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 838293943L;

	@OneToMany(mappedBy = "serviceOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "serviceOrder_ID", nullable = false)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<ServiceOrderItem> serviceOrderItems = new HashSet<ServiceOrderItem>();

	public void addServiceOrderItem(ServiceOrderItem serviceOrderItem) {
		serviceOrderItem.setServiceOrder(this);
		this.serviceOrderItems.add(serviceOrderItem);
	}

	@Transient
	public List<com.sasktel.om.omdomain.ServiceOrderItem> getListServiceOrderItems() {
		return new ArrayList<com.sasktel.om.omdomain.ServiceOrderItem>(
				serviceOrderItems);
	}

	//JSF Friendly function to get count of collections
	public int getServiceOrderItemsCount() {
		return serviceOrderItems.size();
	}

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subscriber_id", nullable = false, updatable = true)
	@ContainedIn
	protected Subscriber subscriber

	;

	@Column(unique = false)
	protected Date dateRequested

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String location

	;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String comments

	;

	@Column(name = "currentStatus", unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String currentStatus

	;

	@OneToMany(mappedBy = "serviceOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "serviceOrder_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<ServiceOrderTrail> serviceOrderTrails = new HashSet<ServiceOrderTrail>();

	public void addServiceOrderTrail(ServiceOrderTrail serviceOrderTrail) {
		serviceOrderTrail.setServiceOrder(this);
		this.serviceOrderTrails.add(serviceOrderTrail);
	}

	@Transient
	public List<com.sasktel.om.omdomain.ServiceOrderTrail> getListServiceOrderTrails() {
		return new ArrayList<com.sasktel.om.omdomain.ServiceOrderTrail>(
				serviceOrderTrails);
	}

	//JSF Friendly function to get count of collections
	public int getServiceOrderTrailsCount() {
		return serviceOrderTrails.size();
	}

	@Column(unique = false)
	protected Status status = com.sasktel.om.omdomain.Status.ACCEPTED

	;

	public void setServiceOrderItems(Set<ServiceOrderItem> serviceOrderItems) {
		this.serviceOrderItems = serviceOrderItems;
	}

	public Set<ServiceOrderItem> getServiceOrderItems() {
		return serviceOrderItems;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Subscriber getSubscriber() {

		return subscriber;

	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getDateRequested() {

		return dateRequested;

	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {

		return location;

	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {

		return comments;

	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getCurrentStatus() {

		return currentStatus;

	}

	public void setServiceOrderTrails(Set<ServiceOrderTrail> serviceOrderTrails) {
		this.serviceOrderTrails = serviceOrderTrails;
	}

	public Set<ServiceOrderTrail> getServiceOrderTrails() {
		return serviceOrderTrails;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {

		return status;

	}

	@Transient
	public String getDisplayName() {
		try {
			return location;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getCommentsAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(
					comments.trim(), 100, 200, "...");
		} catch (Exception e) {
			return comments != null ? comments : "";
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

		listSearchableFields.add("location");

		listSearchableFields.add("comments");

		listSearchableFields.add("currentStatus");

		listSearchableFields.add("serviceOrderItems.additionalInfo");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getLocation() + " ");

		builder.append(getComments() + " ");

		builder.append(getCurrentStatus() + " ");

		if (getSubscriber() != null)
			builder.append("subscriber:" + getSubscriber().getDisplayName()
					+ " ");

		for (BaseEntity e : serviceOrderItems) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : serviceOrderTrails) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
