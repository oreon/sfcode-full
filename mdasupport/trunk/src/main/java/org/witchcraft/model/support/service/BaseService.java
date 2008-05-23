package org.witchcraft.model.support.service;

import java.util.List;

import org.witchcraft.model.support.audit.AuditLog;
import org.witchcraft.model.support.dao.GenericDAO;


/** This interface is the superinterface of all services 
 * @author jesing
 *
 * @param <T>
 */
public interface BaseService<T> extends GenericDAO<T>{
	
	 /** Get the audit logs for this entity
     * @param <T> 
     * @return
     */
    public List<AuditLog<T>> getAuditLogs();
    
    
    /**
     * @param searchText
     * @return
     */
    public List<T> performTextSearch(String searchText);

}
