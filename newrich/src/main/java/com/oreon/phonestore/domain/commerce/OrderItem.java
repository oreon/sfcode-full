package com.oreon.phonestore.domain.commerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "orderitem")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class OrderItem extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -411833402L;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String remarks

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerOrder_id", nullable = false, updatable = true)
	protected CustomerOrder customerOrder

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false, updatable = true)
	protected Product product

	;

	@Column(unique = false)
	protected Integer units = 1

	;

	@Column(unique = false)
	protected BigDecimal salePrice

	;

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;

	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public CustomerOrder getCustomerOrder() {

		return customerOrder;

	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {

		return product;

	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getUnits() {

		return units;

	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getSalePrice() {

		return salePrice;

	}

	@Transient
	public String getDisplayName() {
		try {
			return remarks;
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

		listSearchableFields.add("remarks");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getRemarks() + " ");

		if (getCustomerOrder() != null)
			builder.append("customerOrder:"
					+ getCustomerOrder().getDisplayName() + " ");

		if (getProduct() != null)
			builder.append("product:" + getProduct().getDisplayName() + " ");

		return builder.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
