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

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.codes.Code;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CodeListQueryBase extends BaseQuery<Code, Long> {

	private static final String EJBQL = "select code from Code code";

	protected Code code = new Code();

	public CodeListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Code getCode() {
		return code;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Code getInstance() {
		return getCode();
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

	public LazyDataModel<Code> getCodesBySection(
			final com.oreon.cerebrum.codes.Section section) {

		EntityLazyDataModel<Code, Long> codeLazyDataModel = new EntityLazyDataModel<Code, Long>(
				this) {

			@Override
			public List<Code> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				code.setSection(section);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return codeLazyDataModel;

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

}
