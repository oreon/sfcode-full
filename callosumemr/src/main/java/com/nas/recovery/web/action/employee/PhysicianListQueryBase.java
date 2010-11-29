package com.nas.recovery.web.action.employee;

import com.oreon.callosum.employee.Physician;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.callosum.employee.Physician;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class PhysicianListQueryBase extends BaseQuery<Physician, Long> {

	private static final String EJBQL = "select physician from Physician physician";

	protected Physician physician = new Physician();

	public Physician getPhysician() {
		return physician;
	}

	@Override
	protected String getql() {
		return EJBQL;
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

	private Range<Integer> ageRange = new Range<Integer>();
	public Range<Integer> getAgeRange() {
		return ageRange;
	}
	public void setAge(Range<Integer> ageRange) {
		this.ageRange = ageRange;
	}

	private static final String[] RESTRICTIONS = {
			"physician.id = #{physicianList.physician.id}",

			"lower(physician.employeeNumber) like concat(lower(#{physicianList.physician.employeeNumber}),'%')",

			"lower(physician.user.userName) like concat(lower(#{physicianList.physician.user.userName}),'%')",

			"physician.user.enabled = #{physicianList.physician.user.enabled}",

			"lower(physician.firstName) like concat(lower(#{physicianList.physician.firstName}),'%')",

			"lower(physician.lastName) like concat(lower(#{physicianList.physician.lastName}),'%')",

			"physician.dateOfBirth >= #{physicianList.dateOfBirthRange.begin}",
			"physician.dateOfBirth <= #{physicianList.dateOfBirthRange.end}",

			"physician.gender = #{physicianList.physician.gender}",

			"lower(physician.contactDetails.primaryPhone) like concat(lower(#{physicianList.physician.contactDetails.primaryPhone}),'%')",

			"lower(physician.contactDetails.secondaryPhone) like concat(lower(#{physicianList.physician.contactDetails.secondaryPhone}),'%')",

			"lower(physician.contactDetails.email) like concat(lower(#{physicianList.physician.contactDetails.email}),'%')",

			"physician.age >= #{physicianList.ageRange.begin}",
			"physician.age <= #{physicianList.ageRange.end}",

			"physician.dateCreated <= #{physicianList.dateCreatedRange.end}",
			"physician.dateCreated >= #{physicianList.dateCreatedRange.begin}",};

	@Observer("archivedPhysician")
	public void onArchive() {
		refresh();
	}

}
