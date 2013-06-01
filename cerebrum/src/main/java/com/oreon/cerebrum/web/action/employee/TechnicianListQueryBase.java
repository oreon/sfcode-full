package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Technician;

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

import com.oreon.cerebrum.employee.Technician;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class TechnicianListQueryBase
		extends
			BaseQuery<Technician, Long> {

	private static final String EJBQL = "select technician from Technician technician";

	protected Technician technician = new Technician();

	public Technician getTechnician() {
		return technician;
	}

	@Override
	public Technician getInstance() {
		return getTechnician();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('technician', 'view')}")
	public List<Technician> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Technician> getEntityClass() {
		return Technician.class;
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
			"technician.id = #{technicianList.technician.id}",

			"technician.archived = #{technicianList.technician.archived}",

			"lower(technician.appUser.userName) like concat(lower(#{technicianList.technician.appUser.userName}),'%')",

			"technician.appUser.enabled = #{technicianList.technician.appUser.enabled}",

			"technician.facility.id = #{technicianList.technician.facility.id}",

			"technician.department.id = #{technicianList.technician.department.id}",

			"lower(technician.firstName) like concat(lower(#{technicianList.technician.firstName}),'%')",

			"lower(technician.lastName) like concat(lower(#{technicianList.technician.lastName}),'%')",

			"technician.dateOfBirth >= #{technicianList.dateOfBirthRange.begin}",
			"technician.dateOfBirth <= #{technicianList.dateOfBirthRange.end}",

			"technician.gender = #{technicianList.technician.gender}",

			"lower(technician.contactDetails.primaryPhone) like concat(lower(#{technicianList.technician.contactDetails.primaryPhone}),'%')",

			"lower(technician.contactDetails.secondaryPhone) like concat(lower(#{technicianList.technician.contactDetails.secondaryPhone}),'%')",

			"lower(technician.contactDetails.email) like concat(lower(#{technicianList.technician.contactDetails.email}),'%')",

			"technician.title = #{technicianList.technician.title}",

			"technician.dateCreated <= #{technicianList.dateCreatedRange.end}",
			"technician.dateCreated >= #{technicianList.dateCreatedRange.begin}",};

	@Observer("archivedTechnician")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Technician e) {

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("\r\n");
	}
}
