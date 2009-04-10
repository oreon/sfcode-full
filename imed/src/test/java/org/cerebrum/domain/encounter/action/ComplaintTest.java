package org.cerebrum.domain.encounter.action;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import org.cerebrum.domain.encounter.Complaint;

public class ComplaintTest
		extends
			org.witchcraft.action.test.BaseTest<Complaint> {

	ComplaintAction complaintAction = new ComplaintAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Complaint> getAction() {
		return complaintAction;
	}
}
