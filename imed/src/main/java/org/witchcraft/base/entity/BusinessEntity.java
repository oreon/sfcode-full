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
import org.jboss.seam.servlet.SeamListener;
import org.witchcraft.users.User;


@MappedSuperclass
@EntityListeners({EntityListener.class})
public class BusinessEntity implements Serializable{
	private static final long serialVersionUID = -2225862673125944610L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    @DocumentId
    private Long id;
    
    private boolean archived;
    
    @Version
    @Column(name="version")
    private Long version;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated;
    
     
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="created_by_user_id", nullable=true)
    private User createdByUser;
   
    
    public Boolean isArchived() {
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

    /*
    public BusinessEntity(User createdByUser, Date dateCreated,
            Date dateUpdated, Long id, 
            User updatedByUser, Long version) {
        super();
        //this.createdByUser = createdByUser;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.id = id;
        //this.updatedByUser = updatedByUser;
        this.version = version;
    }*/

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
    	SeamListener listner;
        this.version = version;
    }

    
    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /*
    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }*/

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
}
