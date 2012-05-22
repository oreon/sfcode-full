package com.pcas.datapkg.web.action.domain;

import com.pcas.datapkg.domain.Clerk;

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

import com.pcas.datapkg.domain.Clerk;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClerkListQueryBase extends BaseQuery<Clerk, Long> {

	private static final String EJBQL = "select clerk from Clerk clerk";

	protected Clerk clerk = new Clerk();

	public Clerk getClerk() {
		return clerk;
	}
	
	public Clerk getInstance(){
		return clerk;
	}
	
	public void setInstance(Clerk clerk){
		this.clerk = clerk;
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

	private Range<BigDecimal> salaryRange = new Range<BigDecimal>();

	public Range<BigDecimal> getSalaryRange() {
		return salaryRange;
	}
	public void setSalary(Range<BigDecimal> salaryRange) {
		this.salaryRange = salaryRange;
	}

	private Range<Date> appUser_lastLoginRange = new Range<Date>();

	public Range<Date> getAppUser_lastLoginRange() {
		return appUser_lastLoginRange;
	}
	public void setAppUser_lastLogin(Range<Date> appUser_lastLoginRange) {
		this.appUser_lastLoginRange = appUser_lastLoginRange;
	}

	private static final String[] RESTRICTIONS = {
			"clerk.id = #{clerkList.clerk.id}",

			"clerk.department.id = #{clerkList.clerk.department.id}",

			"lower(clerk.employeeNumber) like concat(lower(#{clerkList.clerk.employeeNumber}),'%')",

			"clerk.employeeType = #{clerkList.clerk.employeeType}",

			"clerk.salary >= #{clerkList.salaryRange.begin}",
			"clerk.salary <= #{clerkList.salaryRange.end}",

			"clerk.customer.id = #{clerkList.clerk.customer.id}",

			"lower(clerk.appUser.userName) like concat(lower(#{clerkList.clerk.appUser.userName}),'%')",

			"clerk.appUser.enabled = #{clerkList.clerk.appUser.enabled}",

			"lower(clerk.appUser.email) like concat(lower(#{clerkList.clerk.appUser.email}),'%')",

			"clerk.appUser.lastLogin >= #{clerkList.appUser_lastLoginRange.begin}",
			"clerk.appUser.lastLogin <= #{clerkList.appUser_lastLoginRange.end}",

			"lower(clerk.firstName) like concat(lower(#{clerkList.clerk.firstName}),'%')",

			"lower(clerk.lastName) like concat(lower(#{clerkList.clerk.lastName}),'%')",

			"lower(clerk.contactDetails.phone) like concat(lower(#{clerkList.clerk.contactDetails.phone}),'%')",

			"lower(clerk.contactDetails.secondaryPhone) like concat(lower(#{clerkList.clerk.contactDetails.secondaryPhone}),'%')",

			"lower(clerk.contactDetails.city) like concat(lower(#{clerkList.clerk.contactDetails.city}),'%')",

			"clerk.typist = #{clerkList.clerk.typist}",

			"clerk.dateCreated <= #{clerkList.dateCreatedRange.end}",
			"clerk.dateCreated >= #{clerkList.dateCreatedRange.begin}",};

	@Observer("archivedClerk")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Clerk e) {

		builder.append("\"" + (e.getTypist() != null ? e.getTypist() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Typist" + ",");

		builder.append("\r\n");
	}
}
