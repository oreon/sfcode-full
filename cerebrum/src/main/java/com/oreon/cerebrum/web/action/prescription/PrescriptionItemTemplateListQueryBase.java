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

import org.jboss.seam.annotations.security.Restrict;

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
	public PrescriptionItemTemplate getInstance() {
		return getPrescriptionItemTemplate();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('prescriptionItemTemplate', 'view')}")
	public List<PrescriptionItemTemplate> getResultList() {
		return super.getResultList();
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

			"prescriptionItemTemplate.archived = #{prescriptionItemTemplateList.prescriptionItemTemplate.archived}",

			"prescriptionItemTemplate.drug.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.drug.id}",

			"prescriptionItemTemplate.qty >= #{prescriptionItemTemplateList.qtyRange.begin}",
			"prescriptionItemTemplate.qty <= #{prescriptionItemTemplateList.qtyRange.end}",

			"prescriptionItemTemplate.frequency.id = #{prescriptionItemTemplateList.prescriptionItemTemplate.frequency.id}",

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
		prescriptionItemTemplate.setPrescriptionTemplate(prescriptionTemplate);
		return getResultList();
	}

	@Observer("archivedPrescriptionItemTemplate")
	public void onArchive() {
		refresh();
	}

	public void setDrugId(Long id) {
		if (prescriptionItemTemplate.getDrug() == null) {
			prescriptionItemTemplate
					.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		prescriptionItemTemplate.getDrug().setId(id);
	}

	public Long getDrugId() {
		return prescriptionItemTemplate.getDrug() == null
				? null
				: prescriptionItemTemplate.getDrug().getId();
	}

	public void setFrequencyId(Long id) {
		if (prescriptionItemTemplate.getFrequency() == null) {
			prescriptionItemTemplate
					.setFrequency(new com.oreon.cerebrum.prescription.Frequency());
		}
		prescriptionItemTemplate.getFrequency().setId(id);
	}

	public Long getFrequencyId() {
		return prescriptionItemTemplate.getFrequency() == null
				? null
				: prescriptionItemTemplate.getFrequency().getId();
	}

	public void setPrescriptionTemplateId(Long id) {
		if (prescriptionItemTemplate.getPrescriptionTemplate() == null) {
			prescriptionItemTemplate
					.setPrescriptionTemplate(new com.oreon.cerebrum.prescription.PrescriptionTemplate());
		}
		prescriptionItemTemplate.getPrescriptionTemplate().setId(id);
	}

	public Long getPrescriptionTemplateId() {
		return prescriptionItemTemplate.getPrescriptionTemplate() == null
				? null
				: prescriptionItemTemplate.getPrescriptionTemplate().getId();
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
				+ (e.getFrequency() != null ? e.getFrequency().getDisplayName()
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

		builder.append("Frequency" + ",");

		builder.append("Strength" + ",");

		builder.append("Route" + ",");

		builder.append("Duration" + ",");

		builder.append("Remarks" + ",");

		builder.append("BrandName" + ",");

		builder.append("PrescriptionTemplate" + ",");

		builder.append("\r\n");
	}
}
