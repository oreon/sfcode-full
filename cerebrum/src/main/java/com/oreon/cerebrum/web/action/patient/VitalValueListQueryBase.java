package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.VitalValue;

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

import com.oreon.cerebrum.patient.VitalValue;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class VitalValueListQueryBase
		extends
			BaseQuery<VitalValue, Long> {

	private static final String EJBQL = "select vitalValue from VitalValue vitalValue";

	protected VitalValue vitalValue = new VitalValue();

	public VitalValue getVitalValue() {
		return vitalValue;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	@Restrict("#{s:hasPermission('vitalValue', 'view')}")
	public List<VitalValue> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<VitalValue> getEntityClass() {
		return VitalValue.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> valueRange = new Range<Integer>();

	public Range<Integer> getValueRange() {
		return valueRange;
	}
	public void setValue(Range<Integer> valueRange) {
		this.valueRange = valueRange;
	}

	private static final String[] RESTRICTIONS = {
			"vitalValue.id = #{vitalValueList.vitalValue.id}",

			"lower(vitalValue.remarks) like concat(lower(#{vitalValueList.vitalValue.remarks}),'%')",

			"vitalValue.value >= #{vitalValueList.valueRange.begin}",
			"vitalValue.value <= #{vitalValueList.valueRange.end}",

			"vitalValue.trackedVital.id = #{vitalValueList.vitalValue.trackedVital.id}",

			"vitalValue.patient.id = #{vitalValueList.vitalValue.patient.id}",

			"vitalValue.dateCreated <= #{vitalValueList.dateCreatedRange.end}",
			"vitalValue.dateCreated >= #{vitalValueList.dateCreatedRange.begin}",};

	public List<VitalValue> getVitalValuesByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		//setMaxResults(10000);
		vitalValue.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedVitalValue")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, VitalValue e) {

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\"" + (e.getValue() != null ? e.getValue() : "")
				+ "\",");

		builder.append("\""
				+ (e.getTrackedVital() != null ? e.getTrackedVital()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getPatient() != null ? e.getPatient().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Remarks" + ",");

		builder.append("Value" + ",");

		builder.append("TrackedVital" + ",");

		builder.append("Patient" + ",");

		builder.append("\r\n");
	}
}
