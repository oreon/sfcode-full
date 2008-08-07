package org.witchcraft.model.randomgen;

/** This interface should be implemented by applications that want to provide their own
 * random value creation
 * @author jsingh
 *
 */
public interface UniqueIdentifierCreator {

	/** Implement this method to create your unique id - it will be used when creating random objects
	 * e.g. if you need email of the inital data set to be unique - you can override this method to provide
	 * an implementation different than default which creates a random number by choosing it from 0 - 10000
	 * @return
	 */
	public String createUniqueId();
}
