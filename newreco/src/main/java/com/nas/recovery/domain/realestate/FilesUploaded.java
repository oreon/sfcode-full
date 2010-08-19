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
@Table(name = "filesuploaded")
@Name("filesUploaded")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class FilesUploaded extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1923420193L;

	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String title;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "file_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "file_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "file_data", length = 4194304))})
	protected FileAttachment file = new FileAttachment();

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "realEstateProperty_id", nullable = false, updatable = true)
	@ContainedIn
	protected RealEstateProperty realEstateProperty;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {

		return title;
	}

	public void setFile(FileAttachment file) {
		this.file = file;
	}

	public FileAttachment getFile() {

		return file;
	}

	public void setRealEstateProperty(RealEstateProperty realEstateProperty) {
		this.realEstateProperty = realEstateProperty;
	}

	public RealEstateProperty getRealEstateProperty() {

		return realEstateProperty;
	}

	@Transient
	public String getDisplayName() {
		return title;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("title");

		return listSearchableFields;
	}

}
