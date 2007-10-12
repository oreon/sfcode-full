package org.witchcraft.model.support.dao;

import java.util.List;

import org.witchcraft.model.support.audit.AuditLog;

public interface GenericDAO<T> {

    T load(Long id);

    List<T> loadAll();

    List<T> searchByExample(T exampleInstance);
    
    T save(T entity);
    
    public void delete(T entity);
    
    
    /** Get the count of records
     * @return - the count
     */
    public int getCount();
    
   

}
