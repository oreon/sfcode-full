package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.billing.Claim;

public class ClaimTestBase extends org.witchcraft.action.test.BaseTest<Claim> {

	ClaimAction claimAction = new ClaimAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Claim> getAction() {
		return claimAction;
	}

}
