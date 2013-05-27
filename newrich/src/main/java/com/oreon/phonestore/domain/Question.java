package com.oreon.phonestore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class Question extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -891114390L;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String text

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id", nullable = false, updatable = true)
	protected Exam exam

	;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {

		return text;

	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Exam getExam() {

		return exam;

	}

	@Transient
	public String getDisplayName() {
		try {
			return text + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getTextAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(text.trim(),
					100, 200, "...");
		} catch (Exception e) {
			return text != null ? text : "";
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

		listSearchableFields.add("text");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getText() + " ");

		if (getExam() != null)
			builder.append("exam:" + getExam().getDisplayName() + " ");

		return builder.toString();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
