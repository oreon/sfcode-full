package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Nurse;

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

import com.oreon.cerebrum.employee.Nurse;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class NurseListQueryBase extends BaseQuery<Nurse, Long> {

	private static final String EJBQL = "select nurse from Nurse nurse";

	protected Nurse nurse = new Nurse();

	public Nurse getNurse() {
		return nurse;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Nurse> getEntityClass() {
		return Nurse.class;
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
			"nurse.id = #{nurseList.nurse.id}",

			"lower(nurse.employeeNumber) like concat(lower(#{nurseList.nurse.employeeNumber}),'%')",

			"lower(nurse.appUser.userName) like concat(lower(#{nurseList.nurse.appUser.userName}),'%')",

			"nurse.appUser.enabled = #{nurseList.nurse.appUser.enabled}",

			"lower(nurse.appUser.email) like concat(lower(#{nurseList.nurse.appUser.email}),'%')",

			"nurse.facility.id = #{nurseList.nurse.facility.id}",

			"lower(nurse.firstName) like concat(lower(#{nurseList.nurse.firstName}),'%')",

			"lower(nurse.lastName) like concat(lower(#{nurseList.nurse.lastName}),'%')",

			"nurse.dateOfBirth >= #{nurseList.dateOfBirthRange.begin}",
			"nurse.dateOfBirth <= #{nurseList.dateOfBirthRange.end}",

			"nurse.gender = #{nurseList.nurse.gender}",

			"lower(nurse.contactDetails.primaryPhone) like concat(lower(#{nurseList.nurse.contactDetails.primaryPhone}),'%')",

			"lower(nurse.contactDetails.secondaryPhone) like concat(lower(#{nurseList.nurse.contactDetails.secondaryPhone}),'%')",

			"lower(nurse.contactDetails.email) like concat(lower(#{nurseList.nurse.contactDetails.email}),'%')",

			"nurse.title = #{nurseList.nurse.title}",

			"nurse.nurseSpecialty.id = #{nurseList.nurse.nurseSpecialty.id}",

			"nurse.dateCreated <= #{nurseList.dateCreatedRange.end}",
			"nurse.dateCreated >= #{nurseList.dateCreatedRange.begin}",};

	@Observer("archivedNurse")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Nurse e) {

		builder.append("\""
				+ (e.getNurseSpecialty() != null ? e.getNurseSpecialty()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("NurseSpecialty" + ",");

		builder.append("\r\n");
	}
}
