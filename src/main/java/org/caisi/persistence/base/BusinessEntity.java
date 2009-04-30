package org.caisi.persistence.base;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanUtils;
import org.caisi.persistence.base.exceptions.BusinessException;

@MappedSuperclass
@EntityListeners({AuditListener.class})
public abstract class BusinessEntity implements Serializable{
	
	
	@Transient
	public abstract Integer getEntityId();  
	
	/*
	protected Date dateCreated;
	private Date dateModified = new Date();
	
	 //For otimistic locking
    protected int version;
	
	@Id
	@DocumentId
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
	
	*/
	
	//TODO: Find a better equals strategy
	@Override
	public boolean equals(Object object) {
		if (this == object){
			return true;
		}
		if ((object == null) || !(object instanceof BusinessEntity)){
			return false;
		}
		
		if(getEntityId() == null || ((BusinessEntity) object).getEntityId() == null)
			return false;
		
		return ((BusinessEntity) object).getEntityId().intValue() == getEntityId().intValue();
	}

	/** Derived classes should override this method to return the fields that are to be used 
	 * in full text search
	 * @return
	 */
	public String[] retrieveSearchableFieldsArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transient
	public String getDisplayName(){
		return toString();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields) {
			String prop;
			try {
				prop = BeanUtils.getProperty(this, field.getName());
			} catch (Exception e) {
				throw new BusinessException("Error accessing property " + field.getName(), e);
			}
			if(prop != null){
				builder.append(field.getName() + ":" + prop + "##");
			}
		}
		
		return builder.toString();
	}
	
	@Transient
	public String getDefaultOrder(){
		return null;
	}
	
}
