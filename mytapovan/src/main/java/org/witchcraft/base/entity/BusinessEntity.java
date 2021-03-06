package org.witchcraft.base.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
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

import org.hibernate.search.annotations.DocumentId;
import org.witchcraft.users.IUser;


@MappedSuperclass
@EntityListeners({EntityListener.class})
public class BusinessEntity implements Serializable{
	private static final long serialVersionUID = -2225862673125944610L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    @DocumentId
    private Long id;
    
    private boolean archived = false;
    
    @Version
    @Column(name="version")
    private Long version;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated;
    
     
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="created_by_user_id", nullable=true)
    private IUser createdByUser;
   
    
    public Boolean isArchived() {
		return archived;
	}
    
    public Boolean getArchived(){
    	return archived;
    }

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_updated")
    private Date dateUpdated;
    
    public BusinessEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
    
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    
    public IUser getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(IUser createdByUser) {
        this.createdByUser = createdByUser;
    }

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
    public String getDisplayName(){
    	return toString();
    }
    
    
	public List<String> listSearchableFields() {
    	return new ArrayList<String>();
    }
	
	
	@Override
    public boolean equals(Object o) {
		if(o == null || !(o instanceof BusinessEntity))
			return false;
		BusinessEntity entity = ((BusinessEntity)o);
		if(getId() == null || getId() == 0 || entity.getId() == null || entity.getId() == 0)
			return false;
    	return this.getId() == entity.getId();
    }
}
