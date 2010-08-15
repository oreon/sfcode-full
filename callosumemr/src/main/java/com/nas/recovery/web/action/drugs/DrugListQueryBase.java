package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.Drug;

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

import com.oreon.callosum.drugs.Drug;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class DrugListQueryBase extends BaseQuery<Drug, Long> {

	//private static final String EJBQL = "select drug from Drug drug";

	private Drug drug = new Drug();

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"drug.absorption = #{drugList.drug.absorption}",

			"drug.biotransformation = #{drugList.drug.biotransformation}",

			"lower(drug.atcCodes) like concat(lower(#{drugList.drug.atcCodes}),'%')",

			"drug.contraIndication = #{drugList.drug.contraIndication}",

			"drug.description = #{drugList.drug.description}",

			"lower(drug.dosageForm) like concat(lower(#{drugList.drug.dosageForm}),'%')",

			"lower(drug.drugCategory) like concat(lower(#{drugList.drug.drugCategory}),'%')",

			"drug.foodInteractions = #{drugList.drug.foodInteractions}",

			"drug.halfLife = #{drugList.drug.halfLife}",

			"drug.indication = #{drugList.drug.indication}",

			"drug.interactions = #{drugList.drug.interactions}",

			"drug.mechanism = #{drugList.drug.mechanism}",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"drug.patientInfo = #{drugList.drug.patientInfo}",

			"drug.pharmacology = #{drugList.drug.pharmacology}",

			"drug.toxicity = #{drugList.drug.toxicity}",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}",};

	public Drug getDrug() {
		return drug;
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}
}
