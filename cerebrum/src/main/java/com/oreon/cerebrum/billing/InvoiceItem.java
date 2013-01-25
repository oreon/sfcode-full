package com.oreon.cerebrum.billing;

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
@Table(name = "invoiceitem")
@Filters({@Filter(name = "archiveFilterDef"),

})
@Name("invoiceItem")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class InvoiceItem extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -843792017L;

	@Column(unique = false)
	protected Integer units = 1

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_id", nullable = false, updatable = true)
	protected Service service

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", nullable = false, updatable = true)
	protected Invoice invoice

	;

	@Column(name = "appliedPrice", unique = false)
	protected Double appliedPrice

	= service != null ? service.getPrice() : 0;

	@Transient
	protected Double total

	;

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getUnits() {

		return units;

	}

	public void setService(Service service) {
		this.service = service;
	}

	public Service getService() {

		return service;

	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Invoice getInvoice() {

		return invoice;

	}

	public void setAppliedPrice(Double appliedPrice) {
		this.appliedPrice = appliedPrice;
	}

	public Double getAppliedPrice() {

		return appliedPrice;

	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotal() {

		try {
			return units * service.getPrice();
		} catch (Exception e) {

			return 0.0;

		}

	}

	@Transient
	public String getDisplayName() {
		try {
			return units + "";
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getService() != null)
			builder.append("service:" + getService().getDisplayName() + " ");

		if (getInvoice() != null)
			builder.append("invoice:" + getInvoice().getDisplayName() + " ");

		return builder.toString();
	}

}
