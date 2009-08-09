package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.billing.ErrorCode;

public class ErrorCodeTestBase
		extends
			org.witchcraft.action.test.BaseTest<ErrorCode> {

	ErrorCodeAction errorCodeAction = new ErrorCodeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ErrorCode> getAction() {
		return errorCodeAction;
	}

}
