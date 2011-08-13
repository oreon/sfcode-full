package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.ParentGroup;

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

import com.oreon.smartsis.domain.ParentGroup;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ParentGroupListQueryBase
		extends
			BaseQuery<ParentGroup, Long> {

	private static final String EJBQL = "select parentGroup from ParentGroup parentGroup";

	protected ParentGroup parentGroup = new ParentGroup();

	public ParentGroup getParentGroup() {
		return parentGroup;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ParentGroup> getEntityClass() {
		return ParentGroup.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"parentGroup.id = #{parentGroupList.parentGroup.id}",

			"parentGroup.dateCreated <= #{parentGroupList.dateCreatedRange.end}",
			"parentGroup.dateCreated >= #{parentGroupList.dateCreatedRange.begin}",};

	@Observer("archivedParentGroup")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, ParentGroup e) {

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("\r\n");
	}
}
