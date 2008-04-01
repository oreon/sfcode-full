package org.witchcraft.model.support.dao;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Criteria;
import org.witchcraft.model.support.Range;


public interface GenericDAO<T> {

	@WebMethod
    T load(Long id);

	@WebMethod
    List<T> loadAll();

    
    /** Search by example using the argument
     * @param exampleInstance
     * @return
     */
    public List<T> searchByExample(T exampleInstance);
    
    /** Search by example and additionally use ranges specified in rangeobjects.
     * @param exampleInstance
     * @param rangeObjects
     * @return
     */
    public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects);
    
    /** Create a hibernate criteria object using the given entity as an example 
     * @param exampleInstance
     * @return
     */
    public Criteria createExampleCriteria(T exampleInstance);
    
    T save(T entity);
    
    public void delete(T entity);
    
    
    /** Get the count of records
     * @return - the count
     */
    @WebMethod
    public long getCount();
    
    /** The number of records created between fromdate and todate -
     * the dates can be null indicating the upper/lower bound is not set
     * @param fromDate - 
     * @param toDate
     * @return The number of records created between fromdate and todate
     */
    public long getCount(Date fromDate, Date toDate);
    
    
    
   

}
