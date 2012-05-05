package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.TrackedVital;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;

import com.oreon.cerebrum.patient.TrackedVital;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TrackedVitalListQueryBase
		extends
			BaseQuery<TrackedVital, Long> {

	private static final String EJBQL = "select trackedVital from TrackedVital trackedVital";

	protected TrackedVital trackedVital = new TrackedVital();

	public TrackedVital getTrackedVital() {
		return trackedVital;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<TrackedVital> getEntityClass() {
		return TrackedVital.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"trackedVital.id = #{trackedVitalList.trackedVital.id}",

			"lower(trackedVital.name) like concat(lower(#{trackedVitalList.trackedVital.name}),'%')",

			"trackedVital.dateCreated <= #{trackedVitalList.dateCreatedRange.end}",
			"trackedVital.dateCreated >= #{trackedVitalList.dateCreatedRange.begin}",};

	@Observer("archivedTrackedVital")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, TrackedVital e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
