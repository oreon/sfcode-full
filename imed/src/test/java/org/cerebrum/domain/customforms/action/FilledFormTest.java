package org.cerebrum.domain.customforms.action;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.customforms.CustomField;
import org.cerebrum.domain.customforms.CustomForm;
import org.cerebrum.domain.customforms.FilledField;
import org.cerebrum.domain.customforms.FilledForm;

public class FilledFormTest extends
		org.witchcraft.action.test.BaseTest<FilledForm> {

	FilledFormAction filledFormAction = new FilledFormAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<FilledForm> getAction() {
		return filledFormAction;
	}
	
	@Test 
	public void testSave(){
		FilledForm filledForm = new FilledForm(); //filledFormAction.getEntity();
		EntityManager em = filledFormAction.getEntityManager();
		em.getTransaction().begin();
		if(filledForm.getCustomForm() == null){
			Query qry = em.createQuery("select c From CustomForm c ");
			CustomForm form = (CustomForm) qry.getResultList().get(0);
			filledForm.setCustomForm(form);
			Set<CustomField> flds  = form.getCustomFields();
			int i = 0;
			for (CustomField customField : flds) {
				FilledField filledField = new FilledField();
				filledField.setValue("As-" + ++i );
				filledField.setCustomField(customField);
				filledField.setFilledForm(filledForm);
				filledForm.getFilledFields().add(filledField);
			}
		}
		em.persist(filledForm);
		em.getTransaction().commit();
		
		
	}
}
