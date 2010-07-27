package com.nas.recovery.web.action.propertymanagement;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.propertymanagement.PropertyManager;

public class PropertyManagerActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PropertyManager> {

	PropertyManagerAction propertyManagerAction = new PropertyManagerAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PropertyManager> getAction() {
		return propertyManagerAction;
	}

}
