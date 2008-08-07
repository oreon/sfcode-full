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
	
	/** This method should choose randomly an object of type t from 
	 * the given collection 
	 * @param collection
	 * @return
	 */
	T createOne(T[] array);
	
	
	/** Create a random object between the given range 
	 * @param low
	 * @param high
	 * @return
	 */
	T createOne(T low, T high);
	
	/** Create a unique instance of the given class T - usually will do it by appending a 5-6 digit after
	 * the returned value
	 * @return
	 */
	T createUnique(); 
	
}
