package org.witchcraft.model.support.audit;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.witchcraft.model.support.BusinessEntity;

/** This class represents an audit log entry
 * @author jsingh
 *
 */
@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"dateCreated","username"})})
public class AuditLog extends BusinessEntity{
	private AuditAction action;
	private String record;
	private String entityName;
	private String username;
	/*private AbstractUser user; */ 
	
	public AuditLog(AuditAction action, String record, String entityName,
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
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
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
	
}
