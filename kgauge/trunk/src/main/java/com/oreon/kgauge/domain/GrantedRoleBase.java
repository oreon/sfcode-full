
/**
 * This is generated code - to edit code or override methods use - GrantedRole class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cascade;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.witchcraft.model.jsf.Image;
import java.util.Set;

import java.util.List;
import java.util.ArrayList;

@MappedSuperclass
@Indexed
@Analyzer(impl = org.witchcraft.lucene.analyzers.EnglishAnalyzer.class)
public abstract class GrantedRoleBase
		extends
			org.witchcraft.model.support.security.AbstractAuthority
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	@Field(index = Index.TOKENIZED, store = Store.NO)
	protected String name;

	/* Default Constructor */
	public GrantedRoleBase() {
	}

	/* Constructor with all attributes */
	public GrantedRoleBase(String name) {

		this.name = name;

	}

	@Column(nullable = false, unique = false)
	public String getName() {

		return this.name;

	}

	public void setName(String name) {
		this.name = name;
	}

	private com.oreon.kgauge.domain.User user;

	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false, updatable = true)
	@XmlTransient
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	public abstract GrantedRole grantedRoleInstance();

	@Transient
	public String getDisplayName() {
		return name + "";
	}

	@Transient
	public String getAuthority() {
		return getName();
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public String[] retrieveSearchableFieldsArray() {
		List<String> listSearchableFields = new ArrayList<String>();

		listSearchableFields.add("name");

		String[] arrFields = new String[listSearchableFields.size()];
		return listSearchableFields.toArray(arrFields);
	}

}
