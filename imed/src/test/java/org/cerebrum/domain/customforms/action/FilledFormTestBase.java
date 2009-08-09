package org.cerebrum.domain.customforms.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.customforms.FilledForm;

public class FilledFormTestBase
		extends
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

}
