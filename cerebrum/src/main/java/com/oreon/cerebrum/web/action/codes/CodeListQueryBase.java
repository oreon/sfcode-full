package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.Code;

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

import com.oreon.cerebrum.codes.Code;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CodeListQueryBase extends BaseQuery<Code, Long> {

	private static final String EJBQL = "select code from Code code";

	protected Code code = new Code();

	public Code getCode() {
		return code;
	}

	@Override
	public Code getInstance() {
		return getCode();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('code', 'view')}")
	public List<Code> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Code> getEntityClass() {
		return Code.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"code.id = #{codeList.code.id}",

			"code.archived = #{codeList.code.archived}",

			"lower(code.name) like concat(lower(#{codeList.code.name}),'%')",

			"lower(code.description) like concat(lower(#{codeList.code.description}),'%')",

			"lower(code.includes) like concat(lower(#{codeList.code.includes}),'%')",

			"lower(code.excludes) like concat(lower(#{codeList.code.excludes}),'%')",

			"lower(code.codeFirst) like concat(lower(#{codeList.code.codeFirst}),'%')",

			"code.section.id = #{codeList.code.section.id}",

			"code.dateCreated <= #{codeList.dateCreatedRange.end}",
			"code.dateCreated >= #{codeList.dateCreatedRange.begin}",};

	public List<Code> getCodesBySection(com.oreon.cerebrum.codes.Section section) {
		//setMaxResults(10000);
		code.setSection(section);
		return getResultList();
	}

	@Observer("archivedCode")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Code e) {

		builder.append("\""
				+ (e.getIncludes() != null
						? e.getIncludes().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getExcludes() != null
						? e.getExcludes().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getCodeFirst() != null
						? e.getCodeFirst().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getSection() != null ? e.getSection().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Includes" + ",");

		builder.append("Excludes" + ",");

		builder.append("CodeFirst" + ",");

		builder.append("Section" + ",");

		builder.append("\r\n");
	}
}
