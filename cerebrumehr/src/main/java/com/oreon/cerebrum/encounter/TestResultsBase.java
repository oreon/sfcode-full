package com.oreon.cerebrum.encounter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.witchcraft.base.entity.FileAttachment;

//Impl 

/**
 * This file is generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 *
 */

/**
 * 
 *
 */

@MappedSuperclass
public class TestResultsBase {

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String results

	;

	@Column(unique = false)
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "document_name")),
			@AttributeOverride(name = "contentType", column = @Column(name = "document_contentType")),
			@AttributeOverride(name = "data", column = @Column(name = "document_data", length = 4194304))})
	protected FileAttachment document = new FileAttachment();

	public void setResults(String results) {
		this.results = results;
	}

	public String getResults() {

		return results;

	}

	public void setDocument(FileAttachment document) {
		this.document = document;
	}

	public FileAttachment getDocument() {

		return document;

	}

	@Transient
	public String getDisplayName() {
		try {
			return results + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getResultsAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(results.trim(),
					100, 200, "...");
		} catch (Exception e) {
			return results != null ? results : "";
		}
	}

}
