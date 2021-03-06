package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Physician;

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

import com.oreon.cerebrum.employee.Physician;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PhysicianListQueryBase extends BaseQuery<Physician, Long> {

	private static final String EJBQL = "select physician from Physician physician";

	protected Physician physician = new Physician();

	@In(create = true)
	PhysicianAction physicianAction;

	public PhysicianListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Physician getPhysician() {
		return physician;
	}

	@Override
	public Physician getInstance() {
		return getPhysician();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('physician', 'view')}")
	public List<Physician> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Physician> getEntityClass() {
		return Physician.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private static final String[] RESTRICTIONS = {
			"physician.id = #{physicianList.physician.id}",

			"physician.archived = #{physicianList.physician.archived}",

			"lower(physician.appUser.userName) like concat(lower(#{physicianList.physician.appUser.userName}),'%')",

			"physician.appUser.enabled = #{physicianList.physician.appUser.enabled}",

			"physician.facility.id = #{physicianList.physician.facility.id}",

			"physician.department.id = #{physicianList.physician.department.id}",

			"lower(physician.firstName) like concat(lower(#{physicianList.physician.firstName}),'%')",

			"lower(physician.lastName) like concat(lower(#{physicianList.physician.lastName}),'%')",

			"physician.dateOfBirth >= #{physicianList.dateOfBirthRange.begin}",
			"physician.dateOfBirth <= #{physicianList.dateOfBirthRange.end}",

			"physician.gender = #{physicianList.physician.gender}",

			"lower(physician.contactDetails.primaryPhone) like concat(lower(#{physicianList.physician.contactDetails.primaryPhone}),'%')",

			"lower(physician.contactDetails.secondaryPhone) like concat(lower(#{physicianList.physician.contactDetails.secondaryPhone}),'%')",

			"lower(physician.contactDetails.email) like concat(lower(#{physicianList.physician.contactDetails.email}),'%')",

			"physician.title = #{physicianList.physician.title}",

			"physician.specialization.id = #{physicianList.physician.specialization.id}",

			"physician.dateCreated <= #{physicianList.dateCreatedRange.end}",
			"physician.dateCreated >= #{physicianList.dateCreatedRange.begin}",};

	@Observer("archivedPhysician")
	public void onArchive() {
		refresh();
	}

	public void setSpecializationId(Long id) {
		if (physician.getSpecialization() == null) {
			physician
					.setSpecialization(new com.oreon.cerebrum.employee.Specialization());
		}
		physician.getSpecialization().setId(id);
	}

	public Long getSpecializationId() {
		return physician.getSpecialization() == null ? null : physician
				.getSpecialization().getId();
	}

	//@Restrict("#{s:hasPermission('physician', 'delete')}")
	public void archiveById(Long id) {
		physicianAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Physician e) {

		builder.append("\""
				+ (e.getSpecialization() != null ? e.getSpecialization()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Specialization" + ",");

		builder.append("\r\n");
	}
}
