package com.nas.recovery.web.action.propertymanagement;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.propertymanagement.Inspection;

public class InspectionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Inspection> {

	InspectionAction inspectionAction = new InspectionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Inspection> getAction() {
		return inspectionAction;
	}

}
