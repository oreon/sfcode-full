package com.ganz.wonders;

import com.ganz.wonders.model.Pet;

public class PetHibernateRepository implements PetRepository {

	public Pet createPetWithDefaults() {
		Pet pet = new Pet();
		pet.setHappiness(10);
		pet.setHealth(10);
		pet.setHunger(0);
		return pet;
	}

}
