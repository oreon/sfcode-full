package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Vaccine;

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

import com.oreon.cerebrum.patient.Vaccine;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class VaccineListQueryBase extends BaseQuery<Vaccine, Long> {

	private static final String EJBQL = "select vaccine from Vaccine vaccine";

	protected Vaccine vaccine = new Vaccine();

	@In(create = true)
	VaccineAction vaccineAction;

	public VaccineListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	@Override
	public Vaccine getInstance() {
		return getVaccine();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('vaccine', 'view')}")
	public List<Vaccine> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Vaccine> getEntityClass() {
		return Vaccine.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"vaccine.id = #{vaccineList.vaccine.id}",

			"vaccine.archived = #{vaccineList.vaccine.archived}",

			"lower(vaccine.name) like concat(lower(#{vaccineList.vaccine.name}),'%')",

			"vaccine.dateCreated <= #{vaccineList.dateCreatedRange.end}",
			"vaccine.dateCreated >= #{vaccineList.dateCreatedRange.begin}",};

	@Observer("archivedVaccine")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('vaccine', 'delete')}")
	public void archiveById(Long id) {
		vaccineAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Vaccine e) {

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
