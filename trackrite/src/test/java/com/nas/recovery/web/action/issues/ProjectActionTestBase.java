package com.nas.recovery.web.action.issues;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.wc.trackrite.issues.Project;

public class ProjectActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Project> {

	ProjectAction projectAction = new ProjectAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Project> getAction() {
		return projectAction;
	}

}
