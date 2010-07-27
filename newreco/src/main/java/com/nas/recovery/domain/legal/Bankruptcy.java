package com.nas.recovery.domain.legal;

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
@Table(name = "bankruptcy")
@Name("bankruptcy")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Bankruptcy extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = -93994108L;

	protected Integer legalNumber;

	@Field(index = Index.TOKENIZED)
	protected String trustee;

	@Field(index = Index.TOKENIZED)
	protected String name;

	protected Date dateFiled;

	protected Date dischargedDate;

	protected Boolean proofOfClaim;

	public void setLegalNumber(Integer legalNumber) {
		this.legalNumber = legalNumber;
	}

	public Integer getLegalNumber() {

		return legalNumber;
	}

	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}

	public String getTrustee() {

		return trustee;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setDateFiled(Date dateFiled) {
		this.dateFiled = dateFiled;
	}

	public Date getDateFiled() {

		return dateFiled;
	}

	public void setDischargedDate(Date dischargedDate) {
		this.dischargedDate = dischargedDate;
	}

	public Date getDischargedDate() {

		return dischargedDate;
	}

	public void setProofOfClaim(Boolean proofOfClaim) {
		this.proofOfClaim = proofOfClaim;
	}

	public Boolean getProofOfClaim() {

		return proofOfClaim;
	}

	@Transient
	public String getDisplayName() {
		return name;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("trustee");

		listSearchableFields.add("name");

		return listSearchableFields;
	}

}
