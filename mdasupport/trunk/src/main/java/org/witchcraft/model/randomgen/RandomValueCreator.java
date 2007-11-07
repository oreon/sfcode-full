package org.witchcraft.model.randomgen;

import java.util.Collection;

/** This interface is to be implemented by any class that implements 
 *  
 * @author jsingh
 *
 * @param <T> - the type of the object to be created
 */
public interface RandomValueCreator<T> {
	
	
	/** This method should generate and return a random object of type  T
	 * @return
	 */
	T createOne();
	
	/** This method should choose randomly an object of type t from 
	 * the given collection 
	 * @param collection
	 * @return
	 */
	T createOne(Collection<T> collection);
	
	/** Create a random object between the given range 
	 * @param low
	 * @param high
	 * @return
	 */
	T createOne(T low, T high);
}
