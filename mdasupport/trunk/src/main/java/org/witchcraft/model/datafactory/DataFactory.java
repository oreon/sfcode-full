package org.witchcraft.model.datafactory;

import java.util.List;

public interface DataFactory<T> {

	T createObject();
	
	List<T> createList();
}
