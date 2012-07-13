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
@Table(name = "serviceorderitem")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class ServiceOrderItem extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = -2088417182L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "serviceOrder_id", nullable = false, updatable = true)
	@ContainedIn
	protected ServiceOrder serviceOrder

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String additionalInfo

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerServiceSpec_id", nullable = false, updatable = true)
	@ContainedIn
	protected CustomerServiceSpec customerServiceSpec

	;

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public ServiceOrder getServiceOrder() {

		return serviceOrder;

	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {

		return additionalInfo;

	}

	public void setCustomerServiceSpec(CustomerServiceSpec customerServiceSpec) {
		this.customerServiceSpec = customerServiceSpec;
	}

	public CustomerServiceSpec getCustomerServiceSpec() {

		return customerServiceSpec;

	}

	@Transient
	public String getDisplayName() {
		try {
			return additionalInfo;
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

		listSearchableFields.add("additionalInfo");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getAdditionalInfo() + " ");

		if (getServiceOrder() != null)
			builder.append("serviceOrder:" + getServiceOrder().getDisplayName()
					+ " ");

		if (getCustomerServiceSpec() != null)
			builder.append("customerServiceSpec:"
					+ getCustomerServiceSpec().getDisplayName() + " ");

		return builder.toString();
	}

}
