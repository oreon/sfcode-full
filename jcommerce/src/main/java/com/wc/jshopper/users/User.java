package com.wc.jshopper.users;

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
import org.witchcraft.model.support.audit.Auditable;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "user")
@Name("user")
//@Filter(name = "archiveFilterDef") 
@Indexed
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class User extends BusinessEntity
		implements
			Auditable,
			java.io.Serializable {

	@Unique(entityName = "com.wc.jshopper.users.User", fieldName = "userName")
	@NotNull
	@Length(min = 2, max = 50)
	@Column(name = "userName", unique = true)
	@Field(index = Index.TOKENIZED)
	protected String userName;

	@NotNull
	@Column(name = "password", unique = false)
	@Field(index = Index.TOKENIZED)
	protected String password;

	protected Boolean enabled;

	//roles->user ->User->Role->Role

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_ID", unique = true), inverseJoinColumns = @JoinColumn(name = "roles_ID", unique = true))
	private Set<Role> roles = new HashSet<Role>();

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

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	@Transient
	public String getDisplayName() {
		return userName;
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("userName");

		listSearchableFields.add("password");

		return listSearchableFields;
	}

}
