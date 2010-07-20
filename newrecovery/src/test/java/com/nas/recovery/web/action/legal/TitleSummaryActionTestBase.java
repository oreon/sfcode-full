package com.nas.recovery.web.action.legal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.nas.recovery.domain.legal.TitleSummary;

public class TitleSummaryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<TitleSummary> {

	TitleSummaryAction titleSummaryAction = new TitleSummaryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TitleSummary> getAction() {
		return titleSummaryAction;
	}

}
