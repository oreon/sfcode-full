package org.witchcraft.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

import com.pcas.datapkg.users.AppUser;

@Entity
@Name("savedSearch")
// @EntityListeners({EntityTemplateListener.class})
@NamedQueries( {
		@NamedQuery(name = "savedSearch.searchesForEntity", query = "Select c from SavedSearch c where c.entityName = ?1 and c.createdByUser = ?2 order by c.entityName "),
		@NamedQuery(name = "savedSearch.searchByName", query = "Select c from SavedSearch c where c.entityName = ?1 and c.searchName = ?2  and c.createdByUser = ?3") })
public class SavedSearch extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5160168397259950773L;

	@NotNull
	private String searchName;

	@NotNull
	private String entityName;

	@Lob
	@Column(length = 1048576)
	protected String encodedXml;


	public String getEncodedXml() {
		return encodedXml;
	}

	public void setEncodedXml(String encodedXml) {
		this.encodedXml = encodedXml;
	}

	@Transient
	public String getDisplayName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}

}
