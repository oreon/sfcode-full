package com.ganz.wonders;

import com.ganz.wonders.model.Pet;

public class PetService {
	
	PetRepository petRepository = new PetHibernateRepository();
	public int login = 0;
	
	public Pet createDefaultPet(){
		Pet pet = petRepository.createPetWithDefaults();
		if(login == 0 )
			pet.setHealth(pet.getHealth()/2);
		return pet;
	}

	public PetRepository getPetRepository() {
		return petRepository;
	}

	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

}
