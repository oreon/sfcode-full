package com.nas.recovery.web.action.appraisal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.appraisal.Appraisal;

public class AppraisalActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Appraisal> {

	AppraisalAction appraisalAction = new AppraisalAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Appraisal> getAction() {
		return appraisalAction;
	}

}
