package org.witchcraft.model.support;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public class BusinessEntity implements Serializable{
	private Long id ;
	protected Date dateCreated;
	private Date dateModified = new Date();
	
	@Version //For otimistic locking
    protected int version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDateCreated() {
		if(dateCreated == null){
			dateCreated = new Date();
		}
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Transient
	public String getDisplayName(){
		return toString();
	}
	
	//TODO: Find a better equals strategy
	@Override
	public boolean equals(Object object) {
		if (this == object){
			return true;
		}
		if ((object == null) || !(object instanceof BusinessEntity)){
			return false;
		}
		
		if(getId() == null || ((BusinessEntity) object).getId() == null)
			return false;
		
		return ((BusinessEntity) object).getId().longValue() == getId().longValue();
	}
	
}
