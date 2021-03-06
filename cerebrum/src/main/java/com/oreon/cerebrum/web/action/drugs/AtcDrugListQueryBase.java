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

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;

import com.oreon.cerebrum.drugs.AtcDrug;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AtcDrugListQueryBase extends BaseQuery<AtcDrug, Long> {

	private static final String EJBQL = "select atcDrug from AtcDrug atcDrug";

	protected AtcDrug atcDrug = new AtcDrug();

	@In(create = true)
	AtcDrugAction atcDrugAction;

	public AtcDrugListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AtcDrug getAtcDrug() {
		return atcDrug;
	}

	@Override
	public AtcDrug getInstance() {
		return getAtcDrug();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('atcDrug', 'view')}")
	public List<AtcDrug> getResultList() {
		return super.getResultList();
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

			"atcDrug.archived = #{atcDrugList.atcDrug.archived}",

			"lower(atcDrug.code) like concat(lower(#{atcDrugList.atcDrug.code}),'%')",

			"lower(atcDrug.name) like concat(lower(#{atcDrugList.atcDrug.name}),'%')",

			"atcDrug.drug.id = #{atcDrugList.atcDrug.drug.id}",

			"atcDrug.parent.id = #{atcDrugList.atcDrug.parent.id}",

			"atcDrug.dateCreated <= #{atcDrugList.dateCreatedRange.end}",
			"atcDrug.dateCreated >= #{atcDrugList.dateCreatedRange.begin}",};

	public List<AtcDrug> getSubcategoriesByParent(
			com.oreon.cerebrum.drugs.AtcDrug atcDrug) {
		atcDrug.setParent(atcDrug);
		return getResultList();
	}

	@Observer("archivedAtcDrug")
	public void onArchive() {
		refresh();
	}

	public void setDrugId(Long id) {
		if (atcDrug.getDrug() == null) {
			atcDrug.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		atcDrug.getDrug().setId(id);
	}

	public Long getDrugId() {
		return atcDrug.getDrug() == null ? null : atcDrug.getDrug().getId();
	}

	public void setParentId(Long id) {
		if (atcDrug.getParent() == null) {
			atcDrug.setParent(new com.oreon.cerebrum.drugs.AtcDrug());
		}
		atcDrug.getParent().setId(id);
	}

	public Long getParentId() {
		return atcDrug.getParent() == null ? null : atcDrug.getParent().getId();
	}

	//@Restrict("#{s:hasPermission('atcDrug', 'delete')}")
	public void archiveById(Long id) {
		atcDrugAction.archiveById(id);
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
