package com.pwc.insuranceclaims.web.action.quickclaim;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.pwc.insuranceclaims.quickclaim.ClaimDocument;

public class ClaimDocumentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ClaimDocument> {

	ClaimDocumentAction claimDocumentAction = new ClaimDocumentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ClaimDocument> getAction() {
		return claimDocumentAction;
	}

}
