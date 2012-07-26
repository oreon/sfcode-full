package com.pcas.datapkg.dashboards;

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
@Table(name = "dashboard")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Name("dashboard")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Analyzer(definition = "entityAnalyzer")
@XmlRootElement
public class Dashboard extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1071479129L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appUser_id", nullable = false, updatable = true)
	@ContainedIn
	protected com.pcas.datapkg.users.AppUser appUser

	;

	@OneToMany(mappedBy = "dashboard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "dashboard_ID", nullable = true)
	@OrderBy("id DESC")
	@IndexedEmbedded
	private Set<DashboardComponent> dashboardComponents = new HashSet<DashboardComponent>();

	public void addDashboardComponent(DashboardComponent dashboardComponent) {
		dashboardComponent.setDashboard(this);
		this.dashboardComponents.add(dashboardComponent);
	}

	@Transient
	public List<com.pcas.datapkg.dashboards.DashboardComponent> getListDashboardComponents() {
		return new ArrayList<com.pcas.datapkg.dashboards.DashboardComponent>(
				dashboardComponents);
	}

	//JSF Friendly function to get count of collections
	public int getDashboardComponentsCount() {
		return dashboardComponents.size();
	}

	@Column(unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	public void setAppUser(com.pcas.datapkg.users.AppUser appUser) {
		this.appUser = appUser;
	}

	public com.pcas.datapkg.users.AppUser getAppUser() {

		return appUser;

	}

	public void setDashboardComponents(
			Set<DashboardComponent> dashboardComponents) {
		this.dashboardComponents = dashboardComponents;
	}

	public Set<DashboardComponent> getDashboardComponents() {
		return dashboardComponents;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
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

		listSearchableFields.add("dashboardComponents.contents");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getAppUser() != null)
			builder.append("appUser:" + getAppUser().getDisplayName() + " ");

		for (BaseEntity e : dashboardComponents) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

}
