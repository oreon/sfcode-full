package org.witchcraft.model.randomgen.factories;

import java.util.Random;

import org.apache.log4j.Logger;
import org.witchcraft.model.randomgen.RandomValueCreator;

public abstract class AbstractRandomValueGenerator<T> implements  RandomValueCreator<T>{
	
	public static final Logger logger = Logger.getLogger(AbstractRandomValueGenerator.class);
	

	public T createOne(T[] array) {
		int randomIndex = new Random().nextInt(array.length);
		return array[randomIndex];
	}
	
	
	public T createUnique() {
		logger.warn("Unique creation has not been implemented for " + 
				AbstractRandomValueGenerator.class.getDeclaredAnnotations()[0].getClass().getSimpleName());
		return null;
	}
}
