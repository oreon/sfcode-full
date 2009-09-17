package com.ganz.wonders;

import static org.junit.Assert.assertTrue;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ganz.wonders.model.Pet;

@RunWith(JMock.class)
public class PetServiceTest {

	Mockery context = new JUnit4Mockery();
	PetService petService = new PetService();
	PetRepository petRepository = context.mock(PetRepository.class);
	Pet sickPet = new Pet();

	@Before
	public void setup() {
		
		petService.setPetRepository(petRepository);
		
	}
	
	@Test
	public void createPet(){
		  // expectations
        context.checking(new Expectations() {{
            oneOf (petRepository).createPetWithDefaults();
            will(returnValue( ObjectFactory.createNormalPet()) );
        }});
		
		
		Pet pet = petService.createDefaultPet();
		assertTrue(pet.getHealth() == 5);
	}

}
