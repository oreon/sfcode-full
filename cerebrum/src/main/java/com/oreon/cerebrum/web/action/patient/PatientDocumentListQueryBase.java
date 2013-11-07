package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.PatientDocument;

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

import com.oreon.cerebrum.patient.PatientDocument;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientDocumentListQueryBase
		extends
			BaseQuery<PatientDocument, Long> {

	private static final String EJBQL = "select patientDocument from PatientDocument patientDocument";

	protected PatientDocument patientDocument = new PatientDocument();

	@In(create = true)
	PatientDocumentAction patientDocumentAction;

	public PatientDocumentListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PatientDocument getPatientDocument() {
		return patientDocument;
	}

	@Override
	public PatientDocument getInstance() {
		return getPatientDocument();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('patientDocument', 'view')}")
	public List<PatientDocument> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientDocument> getEntityClass() {
		return PatientDocument.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientDocument.id = #{patientDocumentList.patientDocument.id}",

			"patientDocument.archived = #{patientDocumentList.patientDocument.archived}",

			"lower(patientDocument.name) like concat(lower(#{patientDocumentList.patientDocument.name}),'%')",

			"lower(patientDocument.notes) like concat(lower(#{patientDocumentList.patientDocument.notes}),'%')",

			"patientDocument.patient.id = #{patientDocumentList.patientDocument.patient.id}",

			"patientDocument.dateCreated <= #{patientDocumentList.dateCreatedRange.end}",
			"patientDocument.dateCreated >= #{patientDocumentList.dateCreatedRange.begin}",};

	public List<PatientDocument> getPatientDocumentsByPatient(
			com.oreon.cerebrum.patient.Patient patient) {
		patientDocument.setPatient(patient);
		return getResultList();
	}

	@Observer("archivedPatientDocument")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (patientDocument.getPatient() == null) {
			patientDocument
					.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		patientDocument.getPatient().setId(id);
	}

	public Long getPatientId() {
		return patientDocument.getPatient() == null ? null : patientDocument
				.getPatient().getId();
	}

	//@Restrict("#{s:hasPermission('patientDocument', 'delete')}")
	public void archiveById(Long id) {
		patientDocumentAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, PatientDocument e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getNotes() != null ? e.getNotes().replace(",", "") : "")
				+ "\",");

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

		builder.append("Name" + ",");

		builder.append("Notes" + ",");

		builder.append("Patient" + ",");

		builder.append("\r\n");
	}
}
