package org.witchcraft.base.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.solr.analysis.EdgeNGramFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.NGramFilterFactory;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.analysis.StopFilterFactory;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.oreon.talent.users.AppUser;

@MappedSuperclass
@AnalyzerDef( name = "entityAnalyzer", tokenizer = @TokenizerDef( factory = StandardTokenizerFactory.class ), filters = {
				@TokenFilterDef( factory = LowerCaseFilterFactory.class ), @TokenFilterDef( factory = StopFilterFactory.class ),
				@TokenFilterDef( factory =  EdgeNGramFilterFactory.class, params = { @Parameter( name = "minGramSize", value = "3" ) , 
						@Parameter( name = "maxGramSize", value = "15" ) , @Parameter( name = "side", value = "front" )   }), 
				@TokenFilterDef( factory = PhoneticFilterFactory.class, params = { @Parameter( name = "encoder", value = "SOUNDEX" ) } ),
				// @TokenFilterDef(factory = SynonymFilterFactory.class),
				@TokenFilterDef( factory = SnowballPorterFilterFactory.class, params = { @Parameter( name = "language", value = "English" ) } ) } )
public abstract class BaseEntity {

	@Id
	private @GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id", updatable = false, nullable = false )
	Long id = null;

	@Transient
	@Field( index = Index.YES )
	@Analyzer( definition = "entityAnalyzer" )
	private String searchData;

	private boolean archived;

	@Version
	private @Column( name = "version" )
	int version = 0;

	private AppUser createdByUser;

	public Long getId() {
		return this.id;
	}

	public void setId( final Long id ) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion( final int version ) {
		this.version = version;
	}

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "date_created" )
	private Date dateCreated;

	/*
	 * @ManyToOne(optional = true, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "created_by_user_id", nullable = true) private AppUser createdByUser;
	 */

	@Transient
	private String highlightedFragment;

	public void setHighlightedFragment( String highlightedFragment ) {
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

	public void setArchived( boolean archived ) {
		this.archived = archived;
	}

	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "date_updated" )
	private Date dateUpdated;

	/*
	 * public AppUser getCreatedByUser() { return createdByUser; }
	 * 
	 * public void setCreatedByUser(AppUser createdByUser) { this.createdByUser = createdByUser; }
	 */

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated( Date dateCreated ) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated( Date dateUpdated ) {
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

	public String getCollectionAsString( Collection<? extends BaseEntity> list ) {
		StringBuffer ret = new StringBuffer();
		for ( BaseEntity businessEntity : list ) {
			ret.append( businessEntity.getDisplayName() + "; " );
		}
		return ret.toString();
	}

	public void setSearchData( String searchData ) {
		this.searchData = searchData;
	}

	public String getSearchData() {
		return searchData;
	}

	public AppUser getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser( AppUser createdByUser ) {
		this.createdByUser = createdByUser;
	}

	/*
	 * public Object onCycleDetected(Context context) { return null; }
	 */

	@Override
	public boolean equals( Object that ) {
		if ( this == that ) {
			return true;
		}
		if ( that == null ) {
			return false;
		}
		if ( getClass() != that.getClass() ) {

			if ( that instanceof HibernateProxy ) {
				Hibernate.initialize( that );
				that = (BaseEntity) ( (HibernateProxy) that ).getHibernateLazyInitializer().getImplementation();
				return equals(that);
			}
			return false;
		}
		if ( id != null ) {
			return id.equals( ( (BaseEntity) that ).id );
		}
		return super.equals( that );
	}

	@Override
	public int hashCode() {
		if ( id != null ) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	@PrePersist
	public void prePersist() {
		updateCreateUser();
		updateCreateDateTime();
	}

	@PreUpdate
	public void preUpdate() {
		updateLastUpdateUser();
		updateLastUpdateDateTime();
	}

	private void updateCreateDateTime() {
		dateCreated = new Date();
	}

	private void updateCreateUser() {
		// TODO Auto-generated method stub

	}

	private void updateLastUpdateUser() {
		// TODO Auto-generated method stub

	}

	private void updateLastUpdateDateTime() {
		dateUpdated = new Date();
	}

}
