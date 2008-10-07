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
    
    
    /** peform a text search against an index
     * @param searchText
     * @return
     */
    public List<T> performTextSearch(String searchText);
    
    
    /**Filter record is to be used when you want to filter the loadAll method by 
     * a certain criterion e.g. a website that hosts multiple organizations document
     * would want to show records only for the currently logged in user's company
     * @return
     */
    //public T getFilterRecord(); 
}
