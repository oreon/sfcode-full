package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

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

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionItemTemplateListQueryBase
		extends
			BaseQuery<PrescriptionItemTemplate, Long> {

	private static final String EJBQL = "select prescriptionItemTemplate from PrescriptionItemTemplate prescriptionItemTemplate";

	protected PrescriptionItemTemplate prescriptionItemTemplate = new PrescriptionItemTemplate();

	public PrescriptionItemTemplate getPrescriptionItemTemplate() {
		return prescriptionItemTemplate;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<PrescriptionItemTemplate> getEntityClass() {
		return PrescriptionItemTemplate.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> qtyRange = new Range<Double>();

	public Range<Double> getQtyRange() {
		return qtyRange;
	}
	public void setQty(Range<Double> qtyRange) {
		this.qtyRange = qtyRange;
	}

	private Range<Integer> durationRange = new Range<Integer>();

	public Range<Integer> getDurationRange() {
		return durationRange;
	}
	public void setDuration(Range<Integer> durationRange) {
		this.durationRange = durationRange;
	}

	private static final String[] RESTRICTIONS = {
			"prescriptionItemTemplate.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.id}",

			"prescriptionItemTemplate.drug.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.drug.id}",

			"prescriptionItemTemplate.qty >= #{prescriptionItemTemplateList.qtyRange.begin}",
			"prescriptionItemTemplate.qty <= #{prescriptionItemTemplateList.qtyRange.end}",

			"prescriptionItemTemplate.frequecy.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.frequecy.id}",

			"lower(prescriptionItemTemplate.strength) like concat(lower(#{prescriptionItemTemplateList.prescriptionItemTemplate.strength}),'%')",

			"prescriptionItemTemplate.route = #{prescriptionItemTemplateList.prescriptionItemTemplate.route}",

			"prescriptionItemTemplate.duration >= #{prescriptionItemTemplateList.durationRange.begin}",
			"prescriptionItemTemplate.duration <= #{prescriptionItemTemplateList.durationRange.end}",

			"lower(prescriptionItemTemplate.remarks) like concat(lower(#{prescriptionItemTemplateList.prescriptionItemTemplate.remarks}),'%')",

			"lower(prescriptionItemTemplate.brandName) like concat(lower(#{prescriptionItemTemplateList.prescriptionItemTemplate.brandName}),'%')",

			"prescriptionItemTemplate.prescriptionTemplate.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.prescriptionTemplate.id}",

			"prescriptionItemTemplate.dateCreated <= #{prescriptionItemTemplateList.dateCreatedRange.end}",
			"prescriptionItemTemplate.dateCreated >= #{prescriptionItemTemplateList.dateCreatedRange.begin}",};

	public List<PrescriptionItemTemplate> getPrescriptionItemTemplatesByPrescriptionTemplate(
			com.oreon.cerebrum.prescription.PrescriptionTemplate prescriptionTemplate) {
		//setMaxResults(10000);
		prescriptionItemTemplate.setPrescriptionTemplate(prescriptionTemplate);
		return getResultList();
	}

	@Observer("archivedPrescriptionItemTemplate")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder,
			PrescriptionItemTemplate e) {

		builder.append("\""
				+ (e.getDrug() != null ? e.getDrug().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\"" + (e.getQty() != null ? e.getQty() : "") + "\",");

		builder.append("\""
				+ (e.getFrequecy() != null ? e.getFrequecy().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getStrength() != null
						? e.getStrength().replace(",", "")
						: "") + "\",");

		builder.append("\"" + (e.getRoute() != null ? e.getRoute() : "")
				+ "\",");

		builder.append("\"" + (e.getDuration() != null ? e.getDuration() : "")
				+ "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getBrandName() != null
						? e.getBrandName().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getPrescriptionTemplate() != null ? e
						.getPrescriptionTemplate().getDisplayName().replace(
								",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Drug" + ",");

		builder.append("Qty" + ",");

		builder.append("Frequecy" + ",");

		builder.append("Strength" + ",");

		builder.append("Route" + ",");

		builder.append("Duration" + ",");

		builder.append("Remarks" + ",");

		builder.append("BrandName" + ",");

		builder.append("PrescriptionTemplate" + ",");

		builder.append("\r\n");
	}
}
