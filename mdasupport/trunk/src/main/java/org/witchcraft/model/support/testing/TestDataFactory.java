package org.witchcraft.model.support.testing;

import java.util.List;

public interface TestDataFactory<T> {
	
	/**
	 * Create a certain number of records and persist them to database 
	 */
	public void persistAll();
	
	/** Loads a random record from the database, if none exists creates 
	 *  a few and returns one of them. 
	 * @return One 
	 */
	public T loadOneRecord();
	
	/** Create a few records of type T - useful for populating lists
	 * @return
	 */
	public List<T> createFewRecords( );
	
	
	/** Create instances and return them as list - no persistence 
	 * @return
	 */
	public List<T> getAllAsList();

	public void setPersistable(boolean b);

	/** Creates the given number or records, typically run on demand
	 * @param recordsTocreate
	 */
	public void createAndSaveRecords(int recordsTocreate);
	
	
}
