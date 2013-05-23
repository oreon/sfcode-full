package com.oreon.phonestore.domain.commerce;

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

import com.oreon.phonestore.ProjectUtils;

@Entity
@Table(name = "customerorder")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("customerOrder")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class CustomerOrder extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 702749209L;

	@OneToMany(mappedBy = "customerOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "customerOrder_ID", nullable = false)
	@OrderBy("id DESC")
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public void addOrderItem(OrderItem orderItem) {

		orderItem.setCustomerOrder(this);

		this.orderItems.add(orderItem);
	}

	@Transient
	public List<com.oreon.phonestore.domain.commerce.OrderItem> getListOrderItems() {
		return new ArrayList<com.oreon.phonestore.domain.commerce.OrderItem>(
				orderItems);
	}

	//JSF Friendly function to get count of collections
	public int getOrderItemsCount() {
		return orderItems.size();
	}

	@Lob
	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String remarks

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true)
	protected Customer customer

	;

	@Formula(value = "(select sum(i.salePrice * i.units) from orderItem i where i.customerOrder_id = id)")
	protected BigDecimal total

	;

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;

	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {

		return customer;

	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {

		return total;

	}

	@Transient
	public String getDisplayName() {
		try {
			return orderItems + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getRemarksAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(remarks.trim(),
					100, 200, "...");
		} catch (Exception e) {
			return remarks != null ? remarks : "";
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

		listSearchableFields.add("remarks");

		listSearchableFields.add("orderItems.remarks");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getRemarks() + " ");

		if (getCustomer() != null)
			builder.append("customer:" + getCustomer().getDisplayName() + " ");

		for (BaseEntity e : orderItems) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
