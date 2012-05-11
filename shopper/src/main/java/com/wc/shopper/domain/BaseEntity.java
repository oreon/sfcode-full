package com.wc.shopper.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;



@MappedSuperclass
public abstract class BaseEntity {

	@Id
	private @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	Long id = null;
	
	@Transient
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	private String searchData;
    
    private boolean archived;
    

	@Version
	private @Column(name = "version")
	int version = 0;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created")
	private Date dateCreated;

	
	/*
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_user_id", nullable = true)
	private AppUser createdByUser; */

	@Transient
	private String highlightedFragment;

	public void setHighlightedFragment(String highlightedFragment) {
		this.highlightedFragment = highlightedFragment;
	}

	public String getHighlightedFragment() {
		return highlightedFragment;
	}

	public Boolean isArchived() {
		return archived;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_updated")
	private Date dateUpdated;

	
	
	/*
	public AppUser getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(AppUser createdByUser) {
		this.createdByUser = createdByUser;
	}*/

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Transient
	public String getDisplayName() {
		return toString();
	}

	public List<String> listSearchableFields() {
		return new ArrayList<String>();
	}

	public String getPopupInfo() {
		return getDisplayName();
	}

	public String getCollectionAsString(Collection<? extends BaseEntity> list) {
		StringBuffer ret = new StringBuffer();
		for (BaseEntity businessEntity : list) {
			ret.append(businessEntity.getDisplayName() + "; ");
		}
		return ret.toString();
	}

	
	public void setSearchData(String searchData) {
		this.searchData = searchData;
	}

	public String getSearchData() {
		return searchData;
	}
	
	
	

	/*
	 * public Object onCycleDetected(Context context) { return null; }
	 */

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((BaseEntity) that).id);
		}
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

}
