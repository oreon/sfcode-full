package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PhysicalFinding;

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

import org.jboss.seam.annotations.security.Restrict;

import com.oreon.cerebrum.ddx.PhysicalFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PhysicalFindingListQueryBase
		extends
			BaseQuery<PhysicalFinding, Long> {

	private static final String EJBQL = "select physicalFinding from PhysicalFinding physicalFinding";

	protected PhysicalFinding physicalFinding = new PhysicalFinding();

	public PhysicalFinding getPhysicalFinding() {
		return physicalFinding;
	}

	@Override
	public PhysicalFinding getInstance() {
		return getPhysicalFinding();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('physicalFinding', 'view')}")
	public List<PhysicalFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PhysicalFinding> getEntityClass() {
		return PhysicalFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"physicalFinding.id = #{physicalFindingList.physicalFinding.id}",

			"physicalFinding.archived = #{physicalFindingList.physicalFinding.archived}",

			"lower(physicalFinding.name) like concat(lower(#{physicalFindingList.physicalFinding.name}),'%')",

			"lower(physicalFinding.icdCode) like concat(lower(#{physicalFindingList.physicalFinding.icdCode}),'%')",

			"physicalFinding.dateCreated <= #{physicalFindingList.dateCreatedRange.end}",
			"physicalFinding.dateCreated >= #{physicalFindingList.dateCreatedRange.begin}",};

	@Observer("archivedPhysicalFinding")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PhysicalFinding e) {

		builder.append("\""
				+ (e.getIcdCode() != null
						? e.getIcdCode().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("IcdCode" + ",");

		builder.append("\r\n");
	}
}
