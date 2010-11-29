package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Vaccine;

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

import com.oreon.callosum.patient.Vaccine;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class VaccineListQueryBase extends BaseQuery<Vaccine, Long> {

	private static final String EJBQL = "select vaccine from Vaccine vaccine";

	protected Vaccine vaccine = new Vaccine();

	public Vaccine getVaccine() {
		return vaccine;
	}

	@Override
	protected String getql() {
		return EJBQL;
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

			"lower(vaccine.name) like concat(lower(#{vaccineList.vaccine.name}),'%')",

			"vaccine.dateCreated <= #{vaccineList.dateCreatedRange.end}",
			"vaccine.dateCreated >= #{vaccineList.dateCreatedRange.begin}",};

	@Observer("archivedVaccine")
	public void onArchive() {
		refresh();
	}

}
