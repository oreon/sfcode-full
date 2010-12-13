package com.nas.recovery.web.action.patient;

import com.oreon.callosum.patient.Allergen;

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

import com.oreon.callosum.patient.Allergen;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AllergenListQueryBase extends BaseQuery<Allergen, Long> {

	private static final String EJBQL = "select allergen from Allergen allergen";

	protected Allergen allergen = new Allergen();

	public Allergen getAllergen() {
		return allergen;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Allergen> getEntityClass() {
		return Allergen.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"allergen.id = #{allergenList.allergen.id}",

			"lower(allergen.name) like concat(lower(#{allergenList.allergen.name}),'%')",

			"allergen.dateCreated <= #{allergenList.dateCreatedRange.end}",
			"allergen.dateCreated >= #{allergenList.dateCreatedRange.begin}",};

	@Observer("archivedAllergen")
	public void onArchive() {
		refresh();
	}

}
