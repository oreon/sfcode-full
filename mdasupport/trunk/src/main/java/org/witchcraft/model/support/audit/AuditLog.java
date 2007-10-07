package org.witchcraft.model.support.audit;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.witchcraft.model.support.BusinessEntity;
import org.witchcraft.model.support.security.AbstractUser;

/** This class represents an audit log entry
 * @author jsingh
 *
 */
@Entity
public class AuditLog extends BusinessEntity{
	private AuditAction action;
	private String record;
	private String table;
	private String username;
	/*private AbstractUser user; */ 
	
	public AuditLog(AuditAction action, String record, String table,
			String username) {
		super();
		this.action = action;
		this.record = record;
		this.table = table;
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
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table; 
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
