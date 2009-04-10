package org.cerebrum.domain.patient.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.patient.Allergy;
import org.cerebrum.domain.patient.Severity;

public class AllergyTest extends org.witchcraft.action.test.BaseTest<Allergy> {

	AllergyAction allergyAction = new AllergyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Allergy> getAction() {
		return allergyAction;
	}
	
	@Test
	public void testIndex(){
		Allergy allergy = new Allergy();
		allergy.setReactionType(Severity.MILD);
		allergy.setAllergen("Penicillin");
		allergyAction.setEntity(allergy);
		allergyAction.save();
		
		
		allergyAction.reIndex();
	}
}
