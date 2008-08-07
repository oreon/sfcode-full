package org.witchcraft.model.randomgen.factories;

import java.util.Collection;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomValueCreator;

public class RandomIntFactory extends AbstractRandomValueGenerator<Integer>{

	public Integer createOne() {
		return new Random().nextInt(10000);
	}

	public Integer createOne(Collection<Integer> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer createOne(Integer low, Integer high) {
		// TODO Auto-generated method stub
		return null;
	}


}
