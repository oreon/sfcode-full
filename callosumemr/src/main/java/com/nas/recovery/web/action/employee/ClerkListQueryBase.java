package com.nas.recovery.web.action.employee;

import com.oreon.callosum.employee.Clerk;

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

import com.oreon.callosum.employee.Clerk;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ClerkListQueryBase extends BaseQuery<Clerk, Long> {

	private static final String EJBQL = "select clerk from Clerk clerk";

	protected Clerk clerk = new Clerk();

	public Clerk getClerk() {
		return clerk;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Clerk> getEntityClass() {
		return Clerk.class;
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
			"clerk.id = #{clerkList.clerk.id}",

			"lower(clerk.employeeNumber) like concat(lower(#{clerkList.clerk.employeeNumber}),'%')",

			"lower(clerk.user.userName) like concat(lower(#{clerkList.clerk.user.userName}),'%')",

			"clerk.user.enabled = #{clerkList.clerk.user.enabled}",

			"lower(clerk.firstName) like concat(lower(#{clerkList.clerk.firstName}),'%')",

			"lower(clerk.lastName) like concat(lower(#{clerkList.clerk.lastName}),'%')",

			"clerk.dateOfBirth >= #{clerkList.dateOfBirthRange.begin}",
			"clerk.dateOfBirth <= #{clerkList.dateOfBirthRange.end}",

			"clerk.gender = #{clerkList.clerk.gender}",

			"lower(clerk.contactDetails.primaryPhone) like concat(lower(#{clerkList.clerk.contactDetails.primaryPhone}),'%')",

			"lower(clerk.contactDetails.secondaryPhone) like concat(lower(#{clerkList.clerk.contactDetails.secondaryPhone}),'%')",

			"lower(clerk.contactDetails.email) like concat(lower(#{clerkList.clerk.contactDetails.email}),'%')",

			"clerk.age >= #{clerkList.ageRange.begin}",
			"clerk.age <= #{clerkList.ageRange.end}",

			"clerk.dateCreated <= #{clerkList.dateCreatedRange.end}",
			"clerk.dateCreated >= #{clerkList.dateCreatedRange.begin}",};

	@Observer("archivedClerk")
	public void onArchive() {
		refresh();
	}

}
