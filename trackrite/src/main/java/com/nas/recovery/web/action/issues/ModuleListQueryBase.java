package com.nas.recovery.web.action.issues;

import org.wc.trackrite.issues.Module;

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

import org.wc.trackrite.issues.Module;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ModuleListQueryBase extends BaseQuery<Module, Long> {

	//private static final String EJBQL = "select module from Module module";

	protected Module module = new Module();

	private static final String[] RESTRICTIONS = {
			"module.id = #{moduleList.module.id}",

			"lower(module.name) like concat(lower(#{moduleList.module.name}),'%')",

			"module.dateCreated <= #{moduleList.dateCreatedRange.end}",
			"module.dateCreated >= #{moduleList.dateCreatedRange.begin}",};

	public Module getModule() {
		return module;
	}

	@Override
	public Class<Module> getEntityClass() {
		return Module.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedModule")
	public void onArchive() {
		refresh();
	}
}
