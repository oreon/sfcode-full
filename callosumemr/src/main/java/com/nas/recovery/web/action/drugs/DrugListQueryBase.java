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

	private static final String EJBQL = "select drug from Drug drug";

	protected Drug drug = new Drug();

	public Drug getDrug() {
		return drug;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"lower(drug.absorption) like concat(lower(#{drugList.drug.absorption}),'%')",

			"lower(drug.biotransformation) like concat(lower(#{drugList.drug.biotransformation}),'%')",

			"lower(drug.atcCodes) like concat(lower(#{drugList.drug.atcCodes}),'%')",

			"lower(drug.contraIndication) like concat(lower(#{drugList.drug.contraIndication}),'%')",

			"lower(drug.description) like concat(lower(#{drugList.drug.description}),'%')",

			"lower(drug.dosageForm) like concat(lower(#{drugList.drug.dosageForm}),'%')",

			"lower(drug.drugCategory) like concat(lower(#{drugList.drug.drugCategory}),'%')",

			"lower(drug.foodInteractions) like concat(lower(#{drugList.drug.foodInteractions}),'%')",

			"lower(drug.halfLife) like concat(lower(#{drugList.drug.halfLife}),'%')",

			"lower(drug.indication) like concat(lower(#{drugList.drug.indication}),'%')",

			"lower(drug.interactions) like concat(lower(#{drugList.drug.interactions}),'%')",

			"lower(drug.mechanism) like concat(lower(#{drugList.drug.mechanism}),'%')",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"lower(drug.patientInfo) like concat(lower(#{drugList.drug.patientInfo}),'%')",

			"lower(drug.pharmacology) like concat(lower(#{drugList.drug.pharmacology}),'%')",

			"lower(drug.toxicity) like concat(lower(#{drugList.drug.toxicity}),'%')",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}",};

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		drug.setName(input);

	}

}
