package org.witchcraft.model.randomgen.factories;

import java.util.Collection;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomValueCreator;

public class RandomStringFactory implements RandomValueCreator<String>{
	
	static String randStrings[] = {"alpha", "beta", "gamma", "delta",  "theta", "zeta", "epsilon", "pi" , 
		"John" , "Wilson", "Mark", "Eric", "Malissa", "Lavendar"};
	
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

}
