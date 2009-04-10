package org.cerebrum.domain.billing.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.billing.DxCode;

public class DxCodeTest extends org.witchcraft.action.test.BaseTest<DxCode> {

	DxCodeAction dxCodeAction = new DxCodeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DxCode> getAction() {
		return dxCodeAction;
	}
	
	
}
