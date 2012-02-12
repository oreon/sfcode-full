package com.hrb.tservices.web.action.taxnews;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.hrb.tservices.domain.taxnews.TaxNews;

public class TaxNewsActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<TaxNews> {

	TaxNewsAction taxNewsAction = new TaxNewsAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TaxNews> getAction() {
		return taxNewsAction;
	}

}
