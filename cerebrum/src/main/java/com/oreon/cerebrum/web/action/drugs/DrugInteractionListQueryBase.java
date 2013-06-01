package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.DrugInteraction;

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

import com.oreon.cerebrum.drugs.DrugInteraction;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
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
	public DrugInteraction getInstance() {
		return getDrugInteraction();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('drugInteraction', 'view')}")
	public List<DrugInteraction> getResultList() {
		return super.getResultList();
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

			"drugInteraction.archived = #{drugInteractionList.drugInteraction.archived}",

			"lower(drugInteraction.description) like concat(lower(#{drugInteractionList.drugInteraction.description}),'%')",

			"drugInteraction.drug.id = #{drugInteractionList.drugInteraction.drug.id}",

			"drugInteraction.interactingDrug.id = #{drugInteractionList.drugInteraction.interactingDrug.id}",

			"drugInteraction.severity = #{drugInteractionList.drugInteraction.severity}",

			"drugInteraction.dateCreated <= #{drugInteractionList.dateCreatedRange.end}",
			"drugInteraction.dateCreated >= #{drugInteractionList.dateCreatedRange.begin}",};

	public List<DrugInteraction> getDrugInteractionsByDrug(
			com.oreon.cerebrum.drugs.Drug drug) {
		//setMaxResults(10000);
		drugInteraction.setDrug(drug);
		return getResultList();
	}

	@Observer("archivedDrugInteraction")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, DrugInteraction e) {

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\""
				+ (e.getDrug() != null ? e.getDrug().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getInteractingDrug() != null ? e.getInteractingDrug()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getSeverity() != null ? e.getSeverity() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Description" + ",");

		builder.append("Drug" + ",");

		builder.append("InteractingDrug" + ",");

		builder.append("Severity" + ",");

		builder.append("\r\n");
	}
}
