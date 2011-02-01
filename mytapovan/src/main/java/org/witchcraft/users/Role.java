package org.witchcraft.users;

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
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
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
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.model.support.audit.Auditable;
import org.witchcraft.base.entity.FileAttachment;
import org.hibernate.annotations.Filter;

import org.witchcraft.utils.*;

@Entity
@Table(name = "role")
@Filter(name = "archiveFilterDef")
@Name("role")
@Indexed
@Cache(usage = CacheConcurrencyStrategy.NONE)
@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})})
public class Role extends BusinessEntity implements java.io.Serializable, IRole {
	private static final long serialVersionUID = -1904783856L;

	//@Unique(entityName = "com.oreon.trkincidents.users.Role", fieldName = "name")

	@NotNull
	@Length(min = 2, max = 250)
	@Column(name = "name", unique = true)
	@Field(index = Index.TOKENIZED)
	@Analyzer(definition = "customanalyzer")
	protected String name;

	@ManyToMany(mappedBy = "roles")
	private Set<IUser> users = new HashSet<IUser>();

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#setUsers(java.util.Set)
	 */
	public void setUsers(Set<IUser> users) {
		this.users = users;
	}

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#getUsers()
	 */
	public Set<IUser> getUsers() {
		return users;
	}

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#getDisplayName()
	 */
	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#setDisplayName(java.lang.String)
	 */
	public void setDisplayName(String name) {
	}

	/* (non-Javadoc)
	 * @see org.witchcraft.users.IRole#listSearchableFields()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		return listSearchableFields;
	}

}
