package org.witchcraft.model.randomgen.factories;

import java.util.Collection;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomNumberUniqueIdentifierImpl;
import org.witchcraft.model.randomgen.RandomValueCreator;
import org.witchcraft.model.randomgen.UniqueIdentifierCreator;

public class RandomStringFactory extends AbstractRandomValueGenerator<String>{
	
	static String randStrings[] = {"Alpha", "Beta", "Gamma", "Delta",  "Theta", "Zeta", "Epsilon", "Pi" , 
		"John" , "Wilson", "Mark", "Eric", "Malissa", "Lavendar", "Mike", "Kate", "Simpson", "Homer"};
	
	//TODO: this should come from spring beans
	UniqueIdentifierCreator uniqueIdentifierCreator = new RandomNumberUniqueIdentifierImpl();
	
	public String createOne(){
		return randStrings[new Random().nextInt(randStrings.length)];
	}

	public String createOne(Collection<String> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public String createOne(String low, String high) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String createUnique() {
		return createOne() + uniqueIdentifierCreator.createUniqueId();
	}

}
