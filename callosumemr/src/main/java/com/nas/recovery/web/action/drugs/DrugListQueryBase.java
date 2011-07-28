package com.nas.recovery.web.action.drugs;

import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.oreon.callosum.drugs.Drug;

import edu.emory.mathcs.backport.java.util.TreeMap;

/**
 * D
 *  
 * @author WitchcraftMDA Seam Cartridge
 * 
 */
public abstract class DrugListQueryBase extends BaseQuery<Drug, Long> {

	private static final String EJBQL = "select drug from Drug drug";

	protected Drug drug = new Drug();

	public Drug getDrug() {
		return drug;
	}

	private com.oreon.callosum.drugs.DrugCategory drugCategoryToSearch;

	public void setDrugCategoryToSearch(
			com.oreon.callosum.drugs.DrugCategory drugCategoryToSearch) {
		this.drugCategoryToSearch = drugCategoryToSearch;
	}

	public com.oreon.callosum.drugs.DrugCategory getDrugCategoryToSearch() {
		return drugCategoryToSearch;
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

	private Range<Double> halfLifeNumberOfHoursRange = new Range<Double>();

	public Range<Double> getHalfLifeNumberOfHoursRange() {
		return halfLifeNumberOfHoursRange;
	}

	public void setHalfLifeNumberOfHours(
			Range<Double> halfLifeNumberOfHoursRange) {
		this.halfLifeNumberOfHoursRange = halfLifeNumberOfHoursRange;
	}

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"lower(drug.absorption) like concat(lower(#{drugList.drug.absorption}),'%')",

			"lower(drug.biotransformation) like concat(lower(#{drugList.drug.biotransformation}),'%')",

			"lower(drug.atcCodes) like concat(lower(#{drugList.drug.atcCodes}),'%')",

			"lower(drug.contraIndication) like concat(lower(#{drugList.drug.contraIndication}),'%')",

			"lower(drug.description) like concat(lower(#{drugList.drug.description}),'%')",

			"lower(drug.dosageForm) like concat(lower(#{drugList.drug.dosageForm}),'%')",

			"lower(drug.foodInteractions) like concat(lower(#{drugList.drug.foodInteractions}),'%')",

			"lower(drug.halfLife) like concat(lower(#{drugList.drug.halfLife}),'%')",

			"drug.halfLifeNumberOfHours >= #{drugList.halfLifeNumberOfHoursRange.begin}",
			"drug.halfLifeNumberOfHours <= #{drugList.halfLifeNumberOfHoursRange.end}",

			"lower(drug.indication) like concat(lower(#{drugList.drug.indication}),'%')",

			"lower(drug.mechanismOfAction) like concat(lower(#{drugList.drug.mechanismOfAction}),'%')",

			"lower(drug.patientInfo) like concat(lower(#{drugList.drug.patientInfo}),'%')",

			"lower(drug.pharmacology) like concat(lower(#{drugList.drug.pharmacology}),'%')",

			"#{drugList.drugCategoryToSearch} in elements(drug.drugCategorys)",

			"lower(drug.toxicity) like concat(lower(#{drugList.drug.toxicity}),'%')",

			"lower(drug.routeOfElimination) like concat(lower(#{drugList.drug.routeOfElimination}),'%')",

			"lower(drug.volumeOfDistribution) like concat(lower(#{drugList.drug.volumeOfDistribution}),'%')",

			"lower(drug.drugBankId) like concat(lower(#{drugList.drug.drugBankId}),'%')",

			"lower(drug.categories) like concat(lower(#{drugList.drug.categories}),'%')",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}", };

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		drug.setName(input);

	}


	

}
