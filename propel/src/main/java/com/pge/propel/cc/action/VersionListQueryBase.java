package com.pge.propel.cc.action;

import com.pge.propel.cc.Version;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.Range;

import com.pge.propel.cc.Version;

public abstract class VersionListQueryBase extends EntityQuery<Version> {

	private static final String EJBQL = "select version from Version version";

	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private Version version = new Version();

	private static final String[] RESTRICTIONS = {

			"lower(version.component) like concat(lower(#{versionListQuery.version.component}),'%')",

			"lower(version.begineDate) like concat(lower(#{versionListQuery.version.begineDate}),'%')",

			"lower(version.endDate) like concat(lower(#{versionListQuery.version.endDate}),'%')",

			"version.dateCreated <= #{versionListQuery.dateCreatedRange.end}",
			"version.dateCreated >= #{versionListQuery.dateCreatedRange.begin}",};

	public VersionListQueryBase() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Version getVersion() {
		return version;
	}

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}

}
