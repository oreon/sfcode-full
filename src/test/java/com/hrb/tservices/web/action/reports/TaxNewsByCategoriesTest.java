package com.hrb.tservices.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.hrb.tservices.web.action.reports.TaxNewsByCategoriesAction;

public class TaxNewsByCategoriesTest extends BaseReportsTest {

	@Test
	public void testTaxNewsByCategoriesReport() {
		try {
			runReportTest("TaxNewsByCategories");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testTaxNewsByCategoriesReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				TaxNewsByCategoriesAction taxNewsByCategoriesAction = (TaxNewsByCategoriesAction) Component
						.getInstance("taxNewsByCategoriesAction");
				taxNewsByCategoriesAction.runReport("TaxNewsByCategories",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
