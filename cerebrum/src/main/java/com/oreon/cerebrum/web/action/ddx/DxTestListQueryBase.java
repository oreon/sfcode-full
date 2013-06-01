package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DxTest;

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

import com.oreon.cerebrum.ddx.DxTest;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DxTestListQueryBase extends BaseQuery<DxTest, Long> {

	private static final String EJBQL = "select dxTest from DxTest dxTest";

	protected DxTest dxTest = new DxTest();

	public DxTest getDxTest() {
		return dxTest;
	}

	@Override
	public DxTest getInstance() {
		return getDxTest();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('dxTest', 'view')}")
	public List<DxTest> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DxTest> getEntityClass() {
		return DxTest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dxTest.id = #{dxTestList.dxTest.id}",

			"dxTest.archived = #{dxTestList.dxTest.archived}",

			"lower(dxTest.name) like concat(lower(#{dxTestList.dxTest.name}),'%')",

			"lower(dxTest.description) like concat(lower(#{dxTestList.dxTest.description}),'%')",

			"dxTest.dateCreated <= #{dxTestList.dateCreatedRange.end}",
			"dxTest.dateCreated >= #{dxTestList.dateCreatedRange.begin}",};

	@Observer("archivedDxTest")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DxTest e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}
}
