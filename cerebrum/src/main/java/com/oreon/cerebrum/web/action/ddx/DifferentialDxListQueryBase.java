package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DifferentialDx;

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

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.ddx.DifferentialDx;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DifferentialDxListQueryBase
		extends
			BaseQuery<DifferentialDx, Long> {

	private static final String EJBQL = "select differentialDx from DifferentialDx differentialDx";

	protected DifferentialDx differentialDx = new DifferentialDx();

	@In(create = true)
	DifferentialDxAction differentialDxAction;

	public DifferentialDxListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DifferentialDx getDifferentialDx() {
		return differentialDx;
	}

	@Override
	public DifferentialDx getInstance() {
		return getDifferentialDx();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('differentialDx', 'view')}")
	public List<DifferentialDx> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DifferentialDx> getEntityClass() {
		return DifferentialDx.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"differentialDx.id = #{differentialDxList.differentialDx.id}",

			"differentialDx.archived = #{differentialDxList.differentialDx.archived}",

			"lower(differentialDx.name) like concat(lower(#{differentialDxList.differentialDx.name}),'%')",

			"differentialDx.dxCategory.id = #{differentialDxList.differentialDx.dxCategory.id}",

			"differentialDx.finding.id = #{differentialDxList.differentialDx.finding.id}",

			"differentialDx.dateCreated <= #{differentialDxList.dateCreatedRange.end}",
			"differentialDx.dateCreated >= #{differentialDxList.dateCreatedRange.begin}",};

	public List<DifferentialDx> getDifferentialDxsByFinding(
			com.oreon.cerebrum.ddx.Finding finding) {
		differentialDx.setFinding(finding);
		return getResultList();
	}

	@Observer("archivedDifferentialDx")
	public void onArchive() {
		refresh();
	}

	public void setDxCategoryId(Long id) {
		if (differentialDx.getDxCategory() == null) {
			differentialDx
					.setDxCategory(new com.oreon.cerebrum.ddx.DxCategory());
		}
		differentialDx.getDxCategory().setId(id);
	}

	public Long getDxCategoryId() {
		return differentialDx.getDxCategory() == null ? null : differentialDx
				.getDxCategory().getId();
	}

	public void setFindingId(Long id) {
		if (differentialDx.getFinding() == null) {
			differentialDx.setFinding(new com.oreon.cerebrum.ddx.Finding());
		}
		differentialDx.getFinding().setId(id);
	}

	public Long getFindingId() {
		return differentialDx.getFinding() == null ? null : differentialDx
				.getFinding().getId();
	}

	//@Restrict("#{s:hasPermission('differentialDx', 'delete')}")
	public void archiveById(Long id) {
		differentialDxAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DifferentialDx e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDxCategory() != null ? e.getDxCategory()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getFinding() != null ? e.getFinding().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("DxCategory" + ",");

		builder.append("Finding" + ",");

		builder.append("\r\n");
	}
}
