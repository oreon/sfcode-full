package com.nas.recovery.web.action.drugs;

import com.oreon.callosum.drugs.AtcDrug;

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

import com.oreon.callosum.drugs.AtcDrug;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class AtcDrugListQueryBase extends BaseQuery<AtcDrug, Long> {

	//private static final String EJBQL = "select atcDrug from AtcDrug atcDrug";

	private AtcDrug atcDrug = new AtcDrug();

	private static final String[] RESTRICTIONS = {
			"atcDrug.id = #{atcDrugList.atcDrug.id}",

			"lower(atcDrug.code) like concat(lower(#{atcDrugList.atcDrug.code}),'%')",

			"lower(atcDrug.name) like concat(lower(#{atcDrugList.atcDrug.name}),'%')",

			"atcDrug.drug = #{atcDrugList.atcDrug.drug}",

			"atcDrug.parent = #{atcDrugList.atcDrug.parent}",

			"atcDrug.dateCreated <= #{atcDrugList.dateCreatedRange.end}",
			"atcDrug.dateCreated >= #{atcDrugList.dateCreatedRange.begin}",};

	public AtcDrug getAtcDrug() {
		return atcDrug;
	}

	@Override
	public Class<AtcDrug> getEntityClass() {
		return AtcDrug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedAtcDrug")
	public void onArchive() {
		refresh();
	}
}
