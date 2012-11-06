package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.PrescriptionItem;

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

import com.oreon.cerebrum.patient.PrescriptionItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionItemListQueryBase
		extends
			BaseQuery<PrescriptionItem, Long> {

	private static final String EJBQL = "select prescriptionItem from PrescriptionItem prescriptionItem";

	protected PrescriptionItem prescriptionItem = new PrescriptionItem();

	public PrescriptionItem getPrescriptionItem() {
		return prescriptionItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<PrescriptionItem> getEntityClass() {
		return PrescriptionItem.class;
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
			"prescriptionItem.id = #{prescriptionItemList.prescriptionItem.id}",

			"prescriptionItem.drug.id = #{prescriptionItemList.prescriptionItem.drug.id}",

			"prescriptionItem.qty >= #{prescriptionItemList.qtyRange.begin}",
			"prescriptionItem.qty <= #{prescriptionItemList.qtyRange.end}",

			"lower(prescriptionItem.strength) like concat(lower(#{prescriptionItemList.prescriptionItem.strength}),'%')",

			"prescriptionItem.prescription.id = #{prescriptionItemList.prescriptionItem.prescription.id}",

			"prescriptionItem.route = #{prescriptionItemList.prescriptionItem.route}",

			"prescriptionItem.duration >= #{prescriptionItemList.durationRange.begin}",
			"prescriptionItem.duration <= #{prescriptionItemList.durationRange.end}",

			"prescriptionItem.frequecy.id = #{prescriptionItemList.prescriptionItem.frequecy.id}",

			"lower(prescriptionItem.remarks) like concat(lower(#{prescriptionItemList.prescriptionItem.remarks}),'%')",

			"lower(prescriptionItem.brandName) like concat(lower(#{prescriptionItemList.prescriptionItem.brandName}),'%')",

			"prescriptionItem.dateCreated <= #{prescriptionItemList.dateCreatedRange.end}",
			"prescriptionItem.dateCreated >= #{prescriptionItemList.dateCreatedRange.begin}",};

	public List<PrescriptionItem> getPrescriptionItemsByPrescription(
			com.oreon.cerebrum.patient.Prescription prescription) {
		//setMaxResults(10000);
		prescriptionItem.setPrescription(prescription);
		return getResultList();
	}

	@Observer("archivedPrescriptionItem")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PrescriptionItem e) {

		builder.append("\""
				+ (e.getDrug() != null ? e.getDrug().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\"" + (e.getQty() != null ? e.getQty() : "") + "\",");

		builder.append("\""
				+ (e.getStrength() != null
						? e.getStrength().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getPrescription() != null ? e.getPrescription()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getRoute() != null ? e.getRoute() : "")
				+ "\",");

		builder.append("\"" + (e.getDuration() != null ? e.getDuration() : "")
				+ "\",");

		builder.append("\""
				+ (e.getFrequecy() != null ? e.getFrequecy().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getRemarks() != null
						? e.getRemarks().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getBrandName() != null
						? e.getBrandName().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Drug" + ",");

		builder.append("Qty" + ",");

		builder.append("Strength" + ",");

		builder.append("Prescription" + ",");

		builder.append("Route" + ",");

		builder.append("Duration" + ",");

		builder.append("Frequecy" + ",");

		builder.append("Remarks" + ",");

		builder.append("BrandName" + ",");

		builder.append("\r\n");
	}
}
