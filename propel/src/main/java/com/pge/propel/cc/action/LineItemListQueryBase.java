package com.pge.propel.cc.action;

import com.pge.propel.cc.LineItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.LineItem;

public abstract class LineItemListQueryBase extends EntityQuery<LineItem> {

	private static final String EJBQL = "select lineItem from LineItem lineItem";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private LineItem lineItem = new LineItem();

	private static final String[] RESTRICTIONS = {

			"lower(lineItem.productionService) like concat(lower(#{lineItemListQuery.lineItem.productionService}),'%')",

			"lower(lineItem.qty) like concat(lower(#{lineItemListQuery.lineItem.qty}),'%')",

			"lower(lineItem.budget) like concat(lower(#{lineItemListQuery.lineItem.budget}),'%')",

			"lineItem.dateCreated <= #{lineItemListQuery.dateCreatedRange.end}",
			"lineItem.dateCreated >= #{lineItemListQuery.dateCreatedRange.begin}",};

	public LineItemListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public LineItem getLineItem() {
		return lineItem;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
