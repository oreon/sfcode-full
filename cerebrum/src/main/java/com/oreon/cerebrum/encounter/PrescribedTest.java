package com.oreon.cerebrum.encounter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
@Table(name = "prescribedtest")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
//@Name("prescribedTest")   
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class PrescribedTest extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1220063154L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dxTest_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.ddx.DxTest dxTest

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "encounter_id", nullable = false, updatable = true)
	protected Encounter encounter

	;

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String remarks

	;

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "results", column = @Column(name = "testResults_results")),

			@AttributeOverride(name = "document", column = @Column(name = "testResults_document"))

	})
	protected TestResults testResults = new TestResults();

	public void setDxTest(com.oreon.cerebrum.ddx.DxTest dxTest) {
		this.dxTest = dxTest;
	}

	public com.oreon.cerebrum.ddx.DxTest getDxTest() {

		return dxTest;

	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Encounter getEncounter() {

		return encounter;

	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;

	}

	public void setTestResults(TestResults testResults) {
		this.testResults = testResults;
	}

	public TestResults getTestResults() {

		if (testResults == null)
			testResults = new com.oreon.cerebrum.encounter.TestResults();
		return testResults;

	}

	@Transient
	public String getDisplayName() {
		try {
			return remarks;
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

		listSearchableFields.add("remarks");

		listSearchableFields.add("testResults.results");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getRemarks() + " ");

		if (getDxTest() != null)
			builder.append("dxTest:" + getDxTest().getDisplayName() + " ");

		if (getEncounter() != null)
			builder
					.append("encounter:" + getEncounter().getDisplayName()
							+ " ");

		return builder.toString();
	}

}
