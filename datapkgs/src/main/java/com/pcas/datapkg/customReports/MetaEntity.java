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
@Table(name = "metaentity")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("metaEntity")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class MetaEntity extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1562422680L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@OneToMany(mappedBy = "metaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "metaEntity_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<MetaField> metaFields = new HashSet<MetaField>();

	public void addMetaField(MetaField metaField) {
		metaField.setMetaEntity(this);
		this.metaFields.add(metaField);
	}

	@Transient
	public List<com.pcas.datapkg.customReports.MetaField> getListMetaFields() {
		return new ArrayList<com.pcas.datapkg.customReports.MetaField>(
				metaFields);
	}

	//JSF Friendly function to get count of collections
	public int getMetaFieldsCount() {
		return metaFields.size();
	}

	@OneToMany(mappedBy = "metaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "metaEntity_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<EntityFieldPrivilege> entityFieldPrivileges = new HashSet<EntityFieldPrivilege>();

	public void addEntityFieldPrivilege(
			EntityFieldPrivilege entityFieldPrivilege) {
		entityFieldPrivilege.setMetaEntity(this);
		this.entityFieldPrivileges.add(entityFieldPrivilege);
	}

	@Transient
	public List<com.pcas.datapkg.customReports.EntityFieldPrivilege> getListEntityFieldPrivileges() {
		return new ArrayList<com.pcas.datapkg.customReports.EntityFieldPrivilege>(
				entityFieldPrivileges);
	}

	//JSF Friendly function to get count of collections
	public int getEntityFieldPrivilegesCount() {
		return entityFieldPrivileges.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setMetaFields(Set<MetaField> metaFields) {
		this.metaFields = metaFields;
	}

	public Set<MetaField> getMetaFields() {
		return metaFields;
	}

	public void setEntityFieldPrivileges(
			Set<EntityFieldPrivilege> entityFieldPrivileges) {
		this.entityFieldPrivileges = entityFieldPrivileges;
	}

	public Set<EntityFieldPrivilege> getEntityFieldPrivileges() {
		return entityFieldPrivileges;
	}

	@Transient
	public String getDisplayName() {
		try {
			return ProjectUtils.getSimpleName(name);
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

		listSearchableFields.add("name");

		listSearchableFields.add("metaFields.name");

		listSearchableFields.add("metaFields.type");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BaseEntity e : metaFields) {
			builder.append(e.getDisplayName() + " ");
		}

		for (BaseEntity e : entityFieldPrivileges) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
