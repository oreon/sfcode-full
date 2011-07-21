package com.oreon.smartsis.web.action.fees;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.fees.StudentPaidFee;

public class StudentPaidFeeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<StudentPaidFee> {

	StudentPaidFeeAction studentPaidFeeAction = new StudentPaidFeeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<StudentPaidFee> getAction() {
		return studentPaidFeeAction;
	}

}
