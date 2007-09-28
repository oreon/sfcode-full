package org.witchcraft.model.support;
 
/** This class is the base for test data factories for various entities 
 * @author jsingh
 *
 */
public abstract class AbstractTestDataFactory {
	
	private boolean persistable = true;

	/** Flag indicating weather the records should be persisted when the derived classe's persistAll method
	 * is called
	 * @return
	 */
	public boolean isPersistable() {
		return persistable;
	}

	public void setPersistable(boolean persistable) {
		this.persistable = persistable;
	}

}
