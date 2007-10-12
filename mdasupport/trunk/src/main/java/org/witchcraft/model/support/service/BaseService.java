package org.witchcraft.model.support.service;

import java.util.List;

import org.witchcraft.model.support.audit.AuditLog;

public interface BaseService<T> {
	
	 /** Get the audit logs for this entity
     * @param <T>
     * @return
     */
    public List<AuditLog<T>> getAuditLogs();

}
