package org.witchcraft.model.support.dao;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;


public interface GenericDAO<T> {

    T load(Long id);

    List<T> loadAll();

    List<T> searchByExample(T exampleInstance);
    
    T save(T entity);
    
    public void delete(T entity);
    
    
    /** Get the count of records
     * @return - the count
     */
    public long getCount();
    
    /** The number of records created between fromdate and todate -
     * the dates can be null indicating the upper/lower bound is not set
     * @param fromDate - 
     * @param toDate
     * @return The number of records created between fromdate and todate
     */
    public long getCount(Date fromDate, Date toDate);
    
   

}
