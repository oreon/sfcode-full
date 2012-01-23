package com.oreon.talent.web.action.candidates;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.talent.candidates.Client;

public class ClientActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Client> {

	ClientAction clientAction = new ClientAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Client> getAction() {
		return clientAction;
	}

}
