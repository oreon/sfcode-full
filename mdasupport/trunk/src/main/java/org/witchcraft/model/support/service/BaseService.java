package org.witchcraft.model.support.service;

import java.util.List;

import org.witchcraft.model.support.audit.AuditLog;

public interface BaseService {
	
	 /** Get the audit logs for this entity
     * @param <T>
     * @return
     */
    public <T> List<AuditLog<T>> getAuditLogs();

}
