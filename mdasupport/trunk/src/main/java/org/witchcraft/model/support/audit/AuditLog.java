package org.witchcraft.model.support.audit;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.witchcraft.model.support.BusinessEntity;

/** This class represents an audit log entry
 * @author jsingh
 *
 */
@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"dateCreated","username"})})
public class AuditLog<T> extends BusinessEntity{
	private AuditAction action;
	@Basic
	private BusinessEntity record;
	private String entityName;
	private String username;
	/*private AbstractUser user; */ 
	
	public AuditLog(){}
	
	public AuditLog(AuditAction action, BusinessEntity record, String entityName,
			String username) {
		super();
		this.action = action;
		this.record = record;
		this.entityName = entityName;
		this.username = username;
	}
	
	public AuditAction getAction() {
		return action;
	}
	public void setAction(AuditAction action) {
		this.action = action;
	}
	
	
	public BusinessEntity getRecord() {
		return record;
	}
	public void setRecord(BusinessEntity record) {
		this.record = record;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName; 
	}
	/*
	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false)
	public AbstractUser getUser() {
		return user;
	}
	public void setUser(AbstractUser user) {
		this.user = user;
	}*/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Transient
	public  T getEntity(){
		return (T) getRecord();
	}
	
}
