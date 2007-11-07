package org.witchcraft.model.randomgen.factories;

import java.util.Collection;
import java.util.Random;

import org.witchcraft.model.randomgen.RandomValueCreator;

public class RandomDoubleFactory implements RandomValueCreator<Double>{

	public Double createOne() {
		return Math.round(100 * 100 * new Random().nextDouble()) /100.00;
	}

	public Double createOne(Collection<Double> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double createOne(Double low, Double high) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
