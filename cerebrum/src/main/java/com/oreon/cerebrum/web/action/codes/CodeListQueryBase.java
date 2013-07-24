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

			"lower(code.notIncludedHere) like concat(lower(#{codeList.code.notIncludedHere}),'%')",

			"lower(code.codeFirst) like concat(lower(#{codeList.code.codeFirst}),'%')",

			"code.section.id = #{codeList.code.section.id}",

			"lower(code.notCodedHere) like concat(lower(#{codeList.code.notCodedHere}),'%')",

			"lower(code.codeAlso) like concat(lower(#{codeList.code.codeAlso}),'%')",

			"code.dateCreated <= #{codeList.dateCreatedRange.end}",
			"code.dateCreated >= #{codeList.dateCreatedRange.begin}",};

	public List<Code> getCodesBySection(com.oreon.cerebrum.codes.Section section) {
		code.setSection(section);
		return getResultList();
	}

	@Observer("archivedCode")
	public void onArchive() {
		refresh();
	}

	public void setSectionId(Long id) {
		if (code.getSection() == null) {
			code.setSection(new com.oreon.cerebrum.codes.Section());
		}
		code.getSection().setId(id);
	}

	public Long getSectionId() {
		return code.getSection() == null ? null : code.getSection().getId();
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
				+ (e.getNotIncludedHere() != null ? e.getNotIncludedHere()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getCodeFirst() != null
						? e.getCodeFirst().replace(",", "")
						: "") + "\",");

		builder.append("\""
				+ (e.getSection() != null ? e.getSection().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getNotCodedHere() != null ? e.getNotCodedHere().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getCodeAlso() != null
						? e.getCodeAlso().replace(",", "")
						: "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Includes" + ",");

		builder.append("NotIncludedHere" + ",");

		builder.append("CodeFirst" + ",");

		builder.append("Section" + ",");

		builder.append("NotCodedHere" + ",");

		builder.append("CodeAlso" + ",");

		builder.append("\r\n");
	}
}
