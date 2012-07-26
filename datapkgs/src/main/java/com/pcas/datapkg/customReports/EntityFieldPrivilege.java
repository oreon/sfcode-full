package com.pcas.datapkg.customReports;

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

import com.pcas.datapkg.ProjectUtils;

@Entity
@Table(name = "entityfieldprivilege")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("entityFieldPrivilege")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class EntityFieldPrivilege extends BaseEntity
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 2125560378L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "metaEntity_id", nullable = false, updatable = true)
	@ContainedIn
	protected MetaEntity metaEntity

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appRole_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.users.AppRole appRole

	;

	@Column(unique = false)
	protected com.pcas.datapkg.managedsecurity.PrivilegeType privilegeType

	;

	@Column(unique = false)
	protected Boolean readAllowed

	;

	@Column(unique = false)
	protected Boolean writeAllowed

	;

	@Column(unique = false)
	protected Boolean editAllowed

	;

	@Column(unique = false)
	protected Boolean deleteAllowed

	;

	public void setMetaEntity(MetaEntity metaEntity) {
		this.metaEntity = metaEntity;
	}

	public MetaEntity getMetaEntity() {

		return metaEntity;

	}

	public void setAppRole(com.pcas.datapkg.users.AppRole appRole) {
		this.appRole = appRole;
	}

	public com.pcas.datapkg.users.AppRole getAppRole() {

		return appRole;

	}

	public void setPrivilegeType(
			com.pcas.datapkg.managedsecurity.PrivilegeType privilegeType) {
		this.privilegeType = privilegeType;
	}

	public com.pcas.datapkg.managedsecurity.PrivilegeType getPrivilegeType() {

		return privilegeType;

	}

	public void setReadAllowed(Boolean readAllowed) {
		this.readAllowed = readAllowed;
	}

	public Boolean getReadAllowed() {

		return readAllowed;

	}

	public void setWriteAllowed(Boolean writeAllowed) {
		this.writeAllowed = writeAllowed;
	}

	public Boolean getWriteAllowed() {

		return writeAllowed;

	}

	public void setEditAllowed(Boolean editAllowed) {
		this.editAllowed = editAllowed;
	}

	public Boolean getEditAllowed() {

		return editAllowed;

	}

	public void setDeleteAllowed(Boolean deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}

	public Boolean getDeleteAllowed() {

		return deleteAllowed;

	}

	@Transient
	public String getDisplayName() {
		try {
			return metaEntity + "";
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

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		if (getMetaEntity() != null)
			builder.append("metaEntity:" + getMetaEntity().getDisplayName()
					+ " ");

		if (getAppRole() != null)
			builder.append("appRole:" + getAppRole().getDisplayName() + " ");

		return builder.toString();
	}

}
