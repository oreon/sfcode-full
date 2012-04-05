package com.pcas.datapkg.managedsecurity;

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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;

import org.witchcraft.utils.*;

import com.pcas.datapkg.ProjectUtils;

@Entity
@Table(name = "rolefieldprivilege")
@Filter(name = "archiveFilterDef")
@Name("roleFieldPrivilege")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class RoleFieldPrivilege extends BusinessEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 489533214L;

	@Column(unique = false)
	protected Boolean readAccess

	;

	@Column(unique = false)
	protected Boolean writeAccess

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appRole_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.users.AppRole appRole

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "metaField_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.customReports.MetaField metaField

	;

	public void setReadAccess(Boolean readAccess) {
		this.readAccess = readAccess;
	}

	public Boolean getReadAccess() {

		return readAccess;

	}

	public void setWriteAccess(Boolean writeAccess) {
		this.writeAccess = writeAccess;
	}

	public Boolean getWriteAccess() {

		return writeAccess;

	}

	public void setAppRole(com.pcas.datapkg.users.AppRole appRole) {
		this.appRole = appRole;
	}

	public com.pcas.datapkg.users.AppRole getAppRole() {

		return appRole;

	}

	public void setMetaField(com.pcas.datapkg.customReports.MetaField metaField) {
		this.metaField = metaField;
	}

	public com.pcas.datapkg.customReports.MetaField getMetaField() {

		return metaField;

	}

	@Transient
	public String getDisplayName() {
		try {
			return readAccess + "";
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
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

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getAppRole() != null)
			builder.append("appRole:" + getAppRole().getDisplayName() + " ");

		if (getMetaField() != null)
			builder
					.append("metaField:" + getMetaField().getDisplayName()
							+ " ");

		return builder.toString();
	}

}
