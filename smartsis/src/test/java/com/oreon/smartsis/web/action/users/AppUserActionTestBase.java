package com.oreon.smartsis.web.action.users;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.smartsis.users.AppUser;

public class AppUserActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppUser> {

	AppUserAction appUserAction = new AppUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		return appUserAction;
	}

	@Test
	public void testEnableAccount() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AppUserAction appUserAction = (AppUserAction) org.jboss.seam.Component
						.getInstance("appUserAction");

				// assert(appUserAction.enableAccount()).equals("");
			}

		}.run();
	}

	@Test
	public void testDisableAccount() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AppUserAction appUserAction = (AppUserAction) org.jboss.seam.Component
						.getInstance("appUserAction");

				// assert(appUserAction.disableAccount()).equals("");
			}

		}.run();
	}

	@Test
	public void testLogin() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AppUserAction appUserAction = (AppUserAction) org.jboss.seam.Component
						.getInstance("appUserAction");

				// assert(appUserAction.login()).equals("");
			}

		}.run();
	}

	@Test
	public void testRetrieveCredentials() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AppUserAction appUserAction = (AppUserAction) org.jboss.seam.Component
						.getInstance("appUserAction");

				// assert(appUserAction.retrieveCredentials()).equals("");
			}

		}.run();
	}

}
