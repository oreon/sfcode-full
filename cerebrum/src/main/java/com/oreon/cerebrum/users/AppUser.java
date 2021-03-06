package com.oreon.cerebrum.users;

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
@Table(name = "appuser")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
//@Name("appUser")   
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class AppUser extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -1510385050L;

	@NotNull
	@Length(min = 3)
	//@Unique(entityName = "com.oreon.cerebrum.users.AppUser", fieldName = "userName")
	@Column(name = "userName", unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String userName

	;

	@NotNull
	@Column(name = "password", unique = false)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "entityAnalyzer")
	protected String password

	;

	@Column(unique = false)
	protected Boolean enabled = true

	;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "appUsers_appRoles", joinColumns = @JoinColumn(name = "appUsers_ID"), inverseJoinColumns = @JoinColumn(name = "appRoles_ID"))
	private Set<AppRole> appRoles = new HashSet<AppRole>();

	public void addAppRole(AppRole appRole) {

		this.appRoles.add(appRole);
	}

	@Transient
	public List<com.oreon.cerebrum.users.AppRole> getListAppRoles() {
		return new ArrayList<com.oreon.cerebrum.users.AppRole>(appRoles);
	}

	//JSF Friendly function to get count of collections
	public int getAppRolesCount() {
		return appRoles.size();
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {

		return userName;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {

		return password;

	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {

		return enabled;

	}

	public void setAppRoles(Set<AppRole> appRoles) {
		this.appRoles = appRoles;
	}

	public Set<AppRole> getAppRoles() {
		return appRoles;
	}

	@Transient
	public String getDisplayName() {
		try {
			return userName;
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

		listSearchableFields.add("userName");

		listSearchableFields.add("password");

		return listSearchableFields;
	}

	@Field(index = Index.TOKENIZED, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getUserName() + " ");

		builder.append(getPassword() + " ");

		return builder.toString();
	}

}
