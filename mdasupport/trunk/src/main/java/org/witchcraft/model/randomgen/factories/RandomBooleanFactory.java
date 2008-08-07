package org.witchcraft.model.randomgen.factories;

import java.util.Collection;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomValueCreator;

public class RandomBooleanFactory extends AbstractRandomValueGenerator<Boolean>{

	public Boolean createOne() {
		return new Random().nextBoolean();
	}

	public Boolean createOne(Collection<Boolean> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean createOne(Boolean low, Boolean high) {
		// TODO Auto-generated method stub
		return null;
	}

}
