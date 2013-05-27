package com.oreon.phonestore.domain.commerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "customerorder")
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
	@Field(index = Index.YES)
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

	@Field(index = Index.YES, name = "searchData")
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
