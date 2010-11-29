package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.DrugInteraction;

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

import com.oreon.callosum.drugs.DrugInteraction;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DrugInteractionListQueryBase
		extends
			BaseQuery<DrugInteraction, Long> {

	private static final String EJBQL = "select drugInteraction from DrugInteraction drugInteraction";

	protected DrugInteraction drugInteraction = new DrugInteraction();

	public DrugInteraction getDrugInteraction() {
		return drugInteraction;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<DrugInteraction> getEntityClass() {
		return DrugInteraction.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drugInteraction.id = #{drugInteractionList.drugInteraction.id}",

			"lower(drugInteraction.description) like concat(lower(#{drugInteractionList.drugInteraction.description}),'%')",

			"drugInteraction.drug.id = #{drugInteractionList.drugInteraction.drug.id}",

			"drugInteraction.interactingDrug.id = #{drugInteractionList.drugInteraction.interactingDrug.id}",

			"drugInteraction.dateCreated <= #{drugInteractionList.dateCreatedRange.end}",
			"drugInteraction.dateCreated >= #{drugInteractionList.dateCreatedRange.begin}",};

	@Observer("archivedDrugInteraction")
	public void onArchive() {
		refresh();
	}

}
