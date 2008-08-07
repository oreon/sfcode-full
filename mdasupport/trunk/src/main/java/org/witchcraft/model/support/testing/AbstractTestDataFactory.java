package org.witchcraft.model.support.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



 
/** This class is the base for test data factories for various entities 
 * @author jsingh
 *
 */
public abstract class AbstractTestDataFactory<T> implements TestDataFactory{
	
	private boolean persistable = true;
	public boolean alreadyPersisted;

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
	
	/** Will return a random number of records
	 * @return
	 */
	public List<T> createFewRecords() {
		List<T> all = getAllAsList();
		all.retainAll(new ArrayList<T>());
		all = getAllAsList();  //We need to create new objects 
		
		int numToChoose = new Random().nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<T>();
		List returnList = new ArrayList<T>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random().nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(indexToAdd);
		}

		return returnList;
	}
	
	@Override
	public Object loadOneUniqueRecord() {
		// TODO Auto-generated method stub
		return null;
	}

}
