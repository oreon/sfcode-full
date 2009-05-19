package org.cerebrum.domain.customforms.action;

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

public class CustomFormTest extends
		org.witchcraft.action.test.BaseTest<CustomForm> {

	CustomFormAction customFormAction = new CustomFormAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<CustomForm> getAction() {
		return customFormAction;
	}
	
	@Test
	public void testSave(){
		CustomForm form = new CustomForm();
		form.setName("patientUrinalysis");
		CustomField field = new CustomField();
		field.setName("creatine");
		form.getCustomFields().add(field);
		customFormAction.setEntity(form);
		//customFormAction.set
		customFormAction.save();
	}
}
