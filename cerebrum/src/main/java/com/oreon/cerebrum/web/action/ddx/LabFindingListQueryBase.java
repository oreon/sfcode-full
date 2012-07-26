package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.LabFinding;

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

import com.oreon.cerebrum.ddx.LabFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class LabFindingListQueryBase
		extends
			BaseQuery<LabFinding, Long> {

	private static final String EJBQL = "select labFinding from LabFinding labFinding";

	protected LabFinding labFinding = new LabFinding();

	public LabFinding getLabFinding() {
		return labFinding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<LabFinding> getEntityClass() {
		return LabFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"labFinding.id = #{labFindingList.labFinding.id}",

			"lower(labFinding.name) like concat(lower(#{labFindingList.labFinding.name}),'%')",

			"lower(labFinding.testName) like concat(lower(#{labFindingList.labFinding.testName}),'%')",

			"labFinding.dateCreated <= #{labFindingList.dateCreatedRange.end}",
			"labFinding.dateCreated >= #{labFindingList.dateCreatedRange.begin}",};

	@Observer("archivedLabFinding")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, LabFinding e) {

		builder.append("\""
				+ (e.getTestName() != null
						? e.getTestName().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("TestName" + ",");

		builder.append("\r\n");
	}
}