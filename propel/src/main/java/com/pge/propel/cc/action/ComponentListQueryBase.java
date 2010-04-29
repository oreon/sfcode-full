package com.pge.propel.cc.action;

import com.pge.propel.cc.Component;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.Component;

public abstract class ComponentListQueryBase extends EntityQuery<Component> {

	private static final String EJBQL = "select component from Component component";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Component component = new Component();

	private static final String[] RESTRICTIONS = {

			"lower(component.versions) like concat(lower(#{componentListQuery.component.versions}),'%')",

			"lower(component.project) like concat(lower(#{componentListQuery.component.project}),'%')",

			"component.dateCreated <= #{componentListQuery.dateCreatedRange.end}",
			"component.dateCreated >= #{componentListQuery.dateCreatedRange.begin}",};

	public ComponentListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Component getComponent() {
		return component;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
