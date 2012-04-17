package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.AtcDrug;

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

import com.oreon.cerebrum.drugs.AtcDrug;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AtcDrugListQueryBase extends BaseQuery<AtcDrug, Long> {

	private static final String EJBQL = "select atcDrug from AtcDrug atcDrug";

	protected AtcDrug atcDrug = new AtcDrug();

	public AtcDrug getAtcDrug() {
		return atcDrug;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<AtcDrug> getEntityClass() {
		return AtcDrug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"atcDrug.id = #{atcDrugList.atcDrug.id}",

			"lower(atcDrug.code) like concat(lower(#{atcDrugList.atcDrug.code}),'%')",

			"lower(atcDrug.name) like concat(lower(#{atcDrugList.atcDrug.name}),'%')",

			"atcDrug.drug.id = #{atcDrugList.atcDrug.drug.id}",

			"atcDrug.parent.id = #{atcDrugList.atcDrug.parent.id}",

			"atcDrug.dateCreated <= #{atcDrugList.dateCreatedRange.end}",
			"atcDrug.dateCreated >= #{atcDrugList.dateCreatedRange.begin}",};

	public List<AtcDrug> getSubcategoriesByParent(
			com.oreon.cerebrum.drugs.AtcDrug atcDrug) {
		//setMaxResults(10000);
		atcDrug.setParent(atcDrug);
		return getResultList();
	}

	@Observer("archivedAtcDrug")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AtcDrug e) {

		builder.append("\""
				+ (e.getCode() != null ? e.getCode().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDrug() != null ? e.getDrug().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getParent() != null ? e.getParent().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Code" + ",");

		builder.append("Name" + ",");

		builder.append("Drug" + ",");

		builder.append("Parent" + ",");

		builder.append("\r\n");
	}
}
