package com.oreon.cerebrum.web.action.unusualoccurences;

import com.oreon.cerebrum.unusualoccurences.OccurenceType;

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

import com.oreon.cerebrum.unusualoccurences.OccurenceType;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class OccurenceTypeListQueryBase
		extends
			BaseQuery<OccurenceType, Long> {

	private static final String EJBQL = "select occurenceType from OccurenceType occurenceType";

	protected OccurenceType occurenceType = new OccurenceType();

	public OccurenceType getOccurenceType() {
		return occurenceType;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	@Restrict("#{s:hasPermission('occurenceType', 'view')}")
	public List<OccurenceType> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<OccurenceType> getEntityClass() {
		return OccurenceType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"occurenceType.id = #{occurenceTypeList.occurenceType.id}",

			"lower(occurenceType.name) like concat(lower(#{occurenceTypeList.occurenceType.name}),'%')",

			"occurenceType.dateCreated <= #{occurenceTypeList.dateCreatedRange.end}",
			"occurenceType.dateCreated >= #{occurenceTypeList.dateCreatedRange.begin}",};

	@Observer("archivedOccurenceType")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, OccurenceType e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("\r\n");
	}
}
