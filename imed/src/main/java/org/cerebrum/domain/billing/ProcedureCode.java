package org.cerebrum.domain.billing;

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
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.search.annotations.AnalyzerDef;
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
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "procedurecode")
@Name("procedureCode")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class ProcedureCode extends BusinessEntity {

	protected Double price;

	@NotNull
	@Length(min = 2, max = 50)
	@Field(index = Index.TOKENIZED)
	protected String code;

	@Lob
	protected String description;

	protected Boolean referringPhysRequired;

	protected Boolean dxCodeRequired;

	protected Boolean hospitalizaionRequired;

	protected Boolean adminDateRequired;

	protected Boolean IORequired;

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setReferringPhysRequired(Boolean referringPhysRequired) {
		this.referringPhysRequired = referringPhysRequired;
	}

	public Boolean getReferringPhysRequired() {
		return referringPhysRequired;
	}

	public void setDxCodeRequired(Boolean dxCodeRequired) {
		this.dxCodeRequired = dxCodeRequired;
	}

	public Boolean getDxCodeRequired() {
		return dxCodeRequired;
	}

	public void setHospitalizaionRequired(Boolean hospitalizaionRequired) {
		this.hospitalizaionRequired = hospitalizaionRequired;
	}

	public Boolean getHospitalizaionRequired() {
		return hospitalizaionRequired;
	}

	public void setAdminDateRequired(Boolean adminDateRequired) {
		this.adminDateRequired = adminDateRequired;
	}

	public Boolean getAdminDateRequired() {
		return adminDateRequired;
	}

	public void setIORequired(Boolean IORequired) {
		this.IORequired = IORequired;
	}

	public Boolean getIORequired() {
		return IORequired;
	}

	@Transient
	public String getDisplayName() {
		return code;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("code");

		return listSearchableFields;
	}

}
