package org.witchcraft.model.support.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.search.annotations.DocumentId;
import org.witchcraft.model.support.BusinessEntity;

/**  This class is the base for all dtos - mimics actual business Entity
 * @author jsingh
 *
 */
public class BusinessEntityDto {
	
	private Long id ;
	protected Date dateCreated;
	private Date dateModified = new Date();
	protected int version;
	
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
	
	public String getDisplayName(){
		return toString();
	}
	
	//TODO: Find a better equals strategyD
	@Override
	public boolean equals(Object object) {
		if (this == object){
			return true;
		}
		if ((object == null) || !(object instanceof BusinessEntityDto)){
			return false;
		}
		
		if(getId() == null || ((BusinessEntityDto) object).getId() == null)
			return false;
		
		return ((BusinessEntityDto) object).getId().longValue() == getId().longValue();
	}
	
	/** Derived classes should override this method to return the fields that are to be used 
	 * in full text search
	 * @return
	 */
	public String[] retrieveSearchableFieldsArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
