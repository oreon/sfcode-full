package org.caisi.persistence.base;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;



public interface GenericDao<T> {

	
    /** Load record by given id - returns null if not found
     * @param id
     * @return
     */
    T load(Integer id);

	
    /** Loads all the records for this entity 
     * @return
     */
    List<T> loadAll();

    
    /** Search by example using the argument
     * @param exampleInstance
     * @return
     */
    public List<T> searchByExample(T exampleInstance);
    
    /** Search by example and additionally use ranges specified in range objects.
     * @param exampleInstance
     * @param rangeObjects
     * @return
     */
    public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects);
    
    
    /**
     * @param searchText
     * @return
     */
    public List<T> performTextSearch(String searchText);
    
    /** Create a hibernate criteria object using the given entity as an example 
     * @param exampleInstance
     * @return
     */
    public Criteria createExampleCriteria(T exampleInstance);
    
    /** Saves the entity - update if necessary
     * @param entity
     * @return
     */
    T save(T entity);
    
    /** 
     * @param entity
     */
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
    
    
    /** Returns the count for a given example query  
     * @param exampleInstance
     * @param rangeObjects
     * @return
     */
    public Integer getSearchByExampleCount(T exampleInstance,  List<Range> rangeObjects);


	/**
	 * @param exampleInstance
	 * @param rangeObjects
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> searchByExample(T exampleInstance, List<Range> rangeObjects, int start,
			int pageSize);


	/**
	 * @return the persistent class type
	 */
	Class getPersistentClass();
	
	
}
