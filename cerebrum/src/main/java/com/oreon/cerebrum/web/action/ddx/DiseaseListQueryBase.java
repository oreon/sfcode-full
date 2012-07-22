package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.Disease;

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

import com.oreon.cerebrum.ddx.Disease;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DiseaseListQueryBase extends BaseQuery<Disease, Long> {

	private static final String EJBQL = "select disease from Disease disease";

	protected Disease disease = new Disease();

	public Disease getDisease() {
		return disease;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Disease> getEntityClass() {
		return Disease.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"disease.id = #{diseaseList.disease.id}",

			"disease.gender = #{diseaseList.disease.gender}",

			"lower(disease.name) like concat(lower(#{diseaseList.disease.name}),'%')",

			"disease.relatedDisease.id = #{diseaseList.disease.relatedDisease.id}",

			"disease.conditionCategory.id = #{diseaseList.disease.conditionCategory.id}",

			"disease.dateCreated <= #{diseaseList.dateCreatedRange.end}",
			"disease.dateCreated >= #{diseaseList.dateCreatedRange.begin}",};

	public List<Disease> getDifferentialDiagnosesByRelatedDisease(
			com.oreon.cerebrum.ddx.Disease disease) {
		//setMaxResults(10000);
		disease.setRelatedDisease(disease);
		return getResultList();
	}

	@Observer("archivedDisease")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Disease e) {

		builder.append("\"" + (e.getGender() != null ? e.getGender() : "")
				+ "\",");

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getRelatedDisease() != null ? e.getRelatedDisease()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getConditionCategory() != null ? e.getConditionCategory()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Gender" + ",");

		builder.append("Name" + ",");

		builder.append("RelatedDisease" + ",");

		builder.append("ConditionCategory" + ",");

		builder.append("\r\n");
	}
}
