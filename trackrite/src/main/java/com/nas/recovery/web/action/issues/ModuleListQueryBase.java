package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Module;

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

import org.wc.trackrite.issues.Module;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ModuleListQueryBase extends BaseQuery<Module, Long> {

	private static final String EJBQL = "select module from Module module";

	protected Module module = new Module();

	public Module getModule() {
		return module;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Module> getEntityClass() {
		return Module.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"module.id = #{moduleList.module.id}",

			"lower(module.name) like concat(lower(#{moduleList.module.name}),'%')",

			"module.dateCreated <= #{moduleList.dateCreatedRange.end}",
			"module.dateCreated >= #{moduleList.dateCreatedRange.begin}",};

	@Observer("archivedModule")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Module e) {

		builder.append("\"" + (e.getName() != null ? e.getName() : "") + "\",");

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
