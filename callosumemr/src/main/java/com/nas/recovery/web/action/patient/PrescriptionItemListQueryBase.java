package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.PrescriptionItem;

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

import com.oreon.callosum.patient.PrescriptionItem;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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

			"prescriptionItem.route = #{prescriptionItemList.prescriptionItem.route}",

			"prescriptionItem.prescription.id = #{prescriptionItemList.prescriptionItem.prescription.id}",

			"prescriptionItem.duration >= #{prescriptionItemList.durationRange.begin}",
			"prescriptionItem.duration <= #{prescriptionItemList.durationRange.end}",

			"lower(prescriptionItem.remarks) like concat(lower(#{prescriptionItemList.prescriptionItem.remarks}),'%')",

			"prescriptionItem.frequecy.id = #{prescriptionItemList.prescriptionItem.frequecy.id}",

			"prescriptionItem.dateCreated <= #{prescriptionItemList.dateCreatedRange.end}",
			"prescriptionItem.dateCreated >= #{prescriptionItemList.dateCreatedRange.begin}",};

	public List<PrescriptionItem> getPrescriptionItemsByPrescription(
			com.oreon.callosum.patient.Prescription prescription) {
		//setMaxResults(10000);
		prescriptionItem.setPrescription(prescription);
		return getResultList();
	}

	@Observer("archivedPrescriptionItem")
	public void onArchive() {
		refresh();
	}

}
