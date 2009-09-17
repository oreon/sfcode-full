package com.ganz.wonders;

import com.ganz.wonders.model.Pet;

public class ObjectFactory {
	
	public static Pet createNormalPet(){
		Pet pet = new Pet();
		pet.setAge("23");
		pet.setHappiness(10);
		pet.setHealth(10);
		pet.setHunger(10);
		return pet;
	}

}
