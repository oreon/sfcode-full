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
@Table(name = "cma")
@Name("cma")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Cma extends BusinessEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1768162529L;

	protected Date cmaOrdered;

	protected Date cmaDueDate;

	protected Date cmaReceived;

	protected Double cmaAsIsValue;

	protected Double cmaImprovedValue;

	protected Boolean lockBox;

	public void setCmaOrdered(Date cmaOrdered) {
		this.cmaOrdered = cmaOrdered;
	}

	public Date getCmaOrdered() {

		return cmaOrdered;
	}

	public void setCmaDueDate(Date cmaDueDate) {
		this.cmaDueDate = cmaDueDate;
	}

	public Date getCmaDueDate() {

		return cmaDueDate;
	}

	public void setCmaReceived(Date cmaReceived) {
		this.cmaReceived = cmaReceived;
	}

	public Date getCmaReceived() {

		return cmaReceived;
	}

	public void setCmaAsIsValue(Double cmaAsIsValue) {
		this.cmaAsIsValue = cmaAsIsValue;
	}

	public Double getCmaAsIsValue() {

		return cmaAsIsValue;
	}

	public void setCmaImprovedValue(Double cmaImprovedValue) {
		this.cmaImprovedValue = cmaImprovedValue;
	}

	public Double getCmaImprovedValue() {

		return cmaImprovedValue;
	}

	public void setLockBox(Boolean lockBox) {
		this.lockBox = lockBox;
	}

	public Boolean getLockBox() {

		return lockBox;
	}

	@Transient
	public String getDisplayName() {
		return cmaOrdered + "";
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
