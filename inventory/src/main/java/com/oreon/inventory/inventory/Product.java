package com.oreon.inventory.inventory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

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

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.oreon.inventory.ProjectUtils;

@Entity
@Table(name = "product")
@Filter(name = "archiveFilterDef")
@Name("product")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
public class Product extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -998315553L;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "product_ID", nullable = true)
	@OrderBy("dateCreated DESC")
	@IndexedEmbedded
	private Set<ProductQuantity> productQuantitys = new HashSet<ProductQuantity>();

	public void addProductQuantitys(ProductQuantity productQuantitys) {
		productQuantitys.setProduct(this);
		this.productQuantitys.add(productQuantitys);
	}

	@Transient
	public List<com.oreon.inventory.inventory.ProductQuantity> getListProductQuantitys() {
		return new ArrayList<com.oreon.inventory.inventory.ProductQuantity>(
				productQuantitys);
	}

	//JSF Friendly function to get count of collections
	public int getProductQuantitysCount() {
		return productQuantitys.size();
	}

	@NotNull
	@Length(min = 2, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name;

	@NotNull
	@Column(name = "barcode", unique = false)
	protected Long barcode;

	@Column(name = "lowStockLevel", unique = false)
	protected Integer lowStockLevel = 20;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "products_ID"), inverseJoinColumns = @JoinColumn(name = "suppliers_ID"))
	private Set<Supplier> suppliers = new HashSet<Supplier>();

	@Transient
	protected Integer currentLevel;

	public void setProductQuantitys(Set<ProductQuantity> productQuantitys) {
		this.productQuantitys = productQuantitys;
	}

	public Set<ProductQuantity> getProductQuantitys() {
		return productQuantitys;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public Long getBarcode() {

		return barcode;

	}

	public void setLowStockLevel(Integer lowStockLevel) {
		this.lowStockLevel = lowStockLevel;
	}

	public Integer getLowStockLevel() {

		return lowStockLevel;

	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Integer getCurrentLevel() {

		try {
			return ProjectUtils.getCurrentTotal(this);
		} catch (Exception e) {

			return 0;

		}

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

		for (BusinessEntity e : productQuantitys) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
