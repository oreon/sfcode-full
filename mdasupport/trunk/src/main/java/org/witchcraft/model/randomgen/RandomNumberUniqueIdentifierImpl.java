package org.witchcraft.model.randomgen;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomNumberUniqueIdentifierImpl implements
		UniqueIdentifierCreator {
	
	Set<Integer> returnedNumbers = new HashSet<Integer>();
 
	public String createUniqueId() {
		Integer numberToReturn = new Random().nextInt();
		while(returnedNumbers.contains(numberToReturn)){
			numberToReturn = new Random().nextInt();
		}
		
		return numberToReturn.toString();
	}

}
