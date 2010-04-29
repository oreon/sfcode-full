package com.pge.propel.cc.action;

import com.pge.propel.cc.ProductionService;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.ProductionService;

public abstract class ProductionServiceListQueryBase
		extends
			EntityQuery<ProductionService> {

	private static final String EJBQL = "select productionService from ProductionService productionService";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private ProductionService productionService = new ProductionService();

	private static final String[] RESTRICTIONS = {

			"lower(productionService.name) like concat(lower(#{productionServiceListQuery.productionService.name}),'%')",

			"lower(productionService.price) like concat(lower(#{productionServiceListQuery.productionService.price}),'%')",

			"lower(productionService.code) like concat(lower(#{productionServiceListQuery.productionService.code}),'%')",

			"productionService.dateCreated <= #{productionServiceListQuery.dateCreatedRange.end}",
			"productionService.dateCreated >= #{productionServiceListQuery.dateCreatedRange.begin}",};

	public ProductionServiceListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public ProductionService getProductionService() {
		return productionService;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
