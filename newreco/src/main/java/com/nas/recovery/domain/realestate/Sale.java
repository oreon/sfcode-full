package com.nas.recovery.domain.realestate;

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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "sale")
@Name("sale")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Sale extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 868788113L;

	protected Date soldDate;

	protected Date closingDate;

	protected Double soldPrice;

	protected Double deposit;

	protected Double commission;

	@OneToOne(mappedBy = "sale", optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ContainedIn
	protected RealEstateListing realEstateListing;

	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}

	public Date getSoldDate() {

		return soldDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getClosingDate() {

		return closingDate;
	}

	public void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}

	public Double getSoldPrice() {

		return soldPrice;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getDeposit() {

		return deposit;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommission() {

		return commission;
	}

	public void setRealEstateListing(RealEstateListing realEstateListing) {
		this.realEstateListing = realEstateListing;
	}

	public RealEstateListing getRealEstateListing() {

		return realEstateListing;
	}

	@Transient
	public String getDisplayName() {
		return soldDate + "";
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		return listSearchableFields;
	}

}
