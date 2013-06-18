package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.AbstractCode;

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

import com.oreon.cerebrum.codes.AbstractCode;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AbstractCodeListQueryBase
		extends
			BaseQuery<AbstractCode, Long> {

	private static final String EJBQL = "select abstractCode from AbstractCode abstractCode";

	protected AbstractCode abstractCode = new AbstractCode();

	public AbstractCode getAbstractCode() {
		return abstractCode;
	}

	@Override
	public AbstractCode getInstance() {
		return getAbstractCode();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('abstractCode', 'view')}")
	public List<AbstractCode> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AbstractCode> getEntityClass() {
		return AbstractCode.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"abstractCode.id = #{abstractCodeList.abstractCode.id}",

			"abstractCode.archived = #{abstractCodeList.abstractCode.archived}",

			"lower(abstractCode.name) like concat(lower(#{abstractCodeList.abstractCode.name}),'%')",

			"lower(abstractCode.description) like concat(lower(#{abstractCodeList.abstractCode.description}),'%')",

			"abstractCode.dateCreated <= #{abstractCodeList.dateCreatedRange.end}",
			"abstractCode.dateCreated >= #{abstractCodeList.dateCreatedRange.begin}",};

	@Observer("archivedAbstractCode")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, AbstractCode e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}
}
