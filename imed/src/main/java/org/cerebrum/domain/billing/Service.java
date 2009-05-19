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
@Table(name = "service")
@Name("service")
@Filter(name = "archiveFilterDef")
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Service extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id", nullable = false, updatable = true)
	@ContainedIn
	protected Claim claim;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "dxCode_id", nullable = false, updatable = true)
	@ContainedIn
	protected DxCode dxCode;

	protected Integer units;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "procedureCode_id", nullable = false, updatable = true)
	@ContainedIn
	protected ProcedureCode procedureCode;

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setDxCode(DxCode dxCode) {
		this.dxCode = dxCode;
	}

	public DxCode getDxCode() {
		return dxCode;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getUnits() {
		return units;
	}

	public void setProcedureCode(ProcedureCode procedureCode) {
		this.procedureCode = procedureCode;
	}

	public ProcedureCode getProcedureCode() {
		return procedureCode;
	}

	@Transient
	public String getDisplayName() {
		return claim + "";
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
