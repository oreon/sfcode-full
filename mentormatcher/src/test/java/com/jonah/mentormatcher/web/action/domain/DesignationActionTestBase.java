package com.jonah.mentormatcher.web.action.domain;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.jonah.mentormatcher.domain.Designation;

public class DesignationActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Designation> {

	DesignationAction designationAction = new DesignationAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Designation> getAction() {
		return designationAction;
	}

}
