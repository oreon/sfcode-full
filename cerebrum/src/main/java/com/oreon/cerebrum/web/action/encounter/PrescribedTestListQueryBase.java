package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.PrescribedTest;

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

import com.oreon.cerebrum.encounter.PrescribedTest;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescribedTestListQueryBase
		extends
			BaseQuery<PrescribedTest, Long> {

	private static final String EJBQL = "select prescribedTest from PrescribedTest prescribedTest";

	protected PrescribedTest prescribedTest = new PrescribedTest();

	public PrescribedTest getPrescribedTest() {
		return prescribedTest;
	}

	@Override
	public PrescribedTest getInstance() {
		return getPrescribedTest();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('prescribedTest', 'view')}")
	public List<PrescribedTest> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PrescribedTest> getEntityClass() {
		return PrescribedTest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"prescribedTest.id = #{prescribedTestList.prescribedTest.id}",

			"prescribedTest.archived = #{prescribedTestList.prescribedTest.archived}",

			"prescribedTest.dxTest.id = #{prescribedTestList.prescribedTest.dxTest.id}",

			"prescribedTest.encounter.id = #{prescribedTestList.prescribedTest.encounter.id}",

			"lower(prescribedTest.remarks) like concat(lower(#{prescribedTestList.prescribedTest.remarks}),'%')",

			"lower(prescribedTest.testResults.results) like concat(lower(#{prescribedTestList.prescribedTest.testResults.results}),'%')",

			"prescribedTest.dateCreated <= #{prescribedTestList.dateCreatedRange.end}",
			"prescribedTest.dateCreated >= #{prescribedTestList.dateCreatedRange.begin}",};

	public List<PrescribedTest> getPrescribedTestsByEncounter(
			com.oreon.cerebrum.encounter.Encounter encounter) {
		//setMaxResults(10000);
		prescribedTest.setEncounter(encounter);
		return getResultList();
	}

	@Observer("archivedPrescribedTest")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PrescribedTest e) {

		builder.append("\""
				+ (e.getDxTest() != null ? e.getDxTest().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getEncounter() != null ? e.getEncounter().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getTestResults() != null ? e.getTestResults() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("DxTest" + ",");

		builder.append("Encounter" + ",");

		builder.append("Remarks" + ",");

		builder.append("TestResults" + ",");

		builder.append("\r\n");
	}
}
