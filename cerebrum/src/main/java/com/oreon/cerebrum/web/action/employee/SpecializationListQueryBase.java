package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Specialization;

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

import com.oreon.cerebrum.employee.Specialization;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SpecializationListQueryBase
		extends
			BaseQuery<Specialization, Long> {

	private static final String EJBQL = "select specialization from Specialization specialization";

	protected Specialization specialization = new Specialization();

	@In(create = true)
	SpecializationAction specializationAction;

	public SpecializationListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	@Override
	public Specialization getInstance() {
		return getSpecialization();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('specialization', 'view')}")
	public List<Specialization> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Specialization> getEntityClass() {
		return Specialization.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"specialization.id = #{specializationList.specialization.id}",

			"specialization.archived = #{specializationList.specialization.archived}",

			"lower(specialization.name) like concat(lower(#{specializationList.specialization.name}),'%')",

			"specialization.dateCreated <= #{specializationList.dateCreatedRange.end}",
			"specialization.dateCreated >= #{specializationList.dateCreatedRange.begin}",};

	@Observer("archivedSpecialization")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('specialization', 'delete')}")
	public void archiveById(Long id) {
		specializationAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Specialization e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
