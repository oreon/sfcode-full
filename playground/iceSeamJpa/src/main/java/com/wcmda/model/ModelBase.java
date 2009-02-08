package com.wcmda.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Version;

@MappedSuperclass
//@EntityListeners({ModelListener.class})
public class ModelBase implements Serializable {

    private static final long serialVersionUID = -2225862673125944610L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Version
    @Column(name="version")
    private Long version;
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="created_by_user_id", nullable=false)
    private User createdByUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated;
    
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="updated_by_user_id")
    private User updatedByUser;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_updated")
    private Date dateUpdated;
    
    public ModelBase() {
        super();
    }

    public ModelBase(User createdByUser, Date dateCreated,
            Date dateUpdated, Long id, 
            User updatedByUser, Long version) {
        super();
        this.createdByUser = createdByUser;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.id = id;
        this.updatedByUser = updatedByUser;
        this.version = version;
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

    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
