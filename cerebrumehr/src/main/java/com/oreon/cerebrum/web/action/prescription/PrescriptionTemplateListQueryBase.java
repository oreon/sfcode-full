package com.oreon.cerebrum.web.action.prescription;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.prescription.PrescriptionTemplate;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionTemplateListQueryBase
		extends
			BaseQuery<PrescriptionTemplate, Long> {

	private static final String EJBQL = "select prescriptionTemplate from PrescriptionTemplate prescriptionTemplate";

	protected PrescriptionTemplate prescriptionTemplate = new PrescriptionTemplate();

	public PrescriptionTemplateListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PrescriptionTemplate getPrescriptionTemplate() {
		return prescriptionTemplate;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PrescriptionTemplate getInstance() {
		return getPrescriptionTemplate();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescriptionTemplate', 'view')}")
	public List<PrescriptionTemplate> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PrescriptionTemplate> getEntityClass() {
		return PrescriptionTemplate.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"prescriptionTemplate.id = #{prescriptionTemplateList.prescriptionTemplate.id}",

			"prescriptionTemplate.archived = #{prescriptionTemplateList.prescriptionTemplate.archived}",

			"lower(prescriptionTemplate.name) like concat(lower(#{prescriptionTemplateList.prescriptionTemplate.name}),'%')",

			"lower(prescriptionTemplate.directivesForPatient) like concat(lower(#{prescriptionTemplateList.prescriptionTemplate.directivesForPatient}),'%')",

			"prescriptionTemplate.dateCreated <= #{prescriptionTemplateList.dateCreatedRange.end}",
			"prescriptionTemplate.dateCreated >= #{prescriptionTemplateList.dateCreatedRange.begin}",};

	@Observer("archivedPrescriptionTemplate")
	public void onArchive() {
		refresh();
	}

}
