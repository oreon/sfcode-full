package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Parent;

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

import com.oreon.smartsis.domain.Parent;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ParentListQueryBase extends BaseQuery<Parent, Long> {

	private static final String EJBQL = "select parent from Parent parent";

	protected Parent parent = new Parent();

	public Parent getParent() {
		return parent;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Parent> getEntityClass() {
		return Parent.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"parent.id = #{parentList.parent.id}",

			"lower(parent.firstName) like concat(lower(#{parentList.parent.firstName}),'%')",

			"lower(parent.lastName) like concat(lower(#{parentList.parent.lastName}),'%')",

			"lower(parent.contactDetails.primaryPhone) like concat(lower(#{parentList.parent.contactDetails.primaryPhone}),'%')",

			"lower(parent.contactDetails.secondaryPhone) like concat(lower(#{parentList.parent.contactDetails.secondaryPhone}),'%')",

			"lower(parent.contactDetails.email) like concat(lower(#{parentList.parent.contactDetails.email}),'%')",

			"lower(parent.user.userName) like concat(lower(#{parentList.parent.user.userName}),'%')",

			"lower(parent.user.email) like concat(lower(#{parentList.parent.user.email}),'%')",

			"parent.user.enabled = #{parentList.parent.user.enabled}",

			"parent.parentGroup.id = #{parentList.parent.parentGroup.id}",

			"parent.dateCreated <= #{parentList.dateCreatedRange.end}",
			"parent.dateCreated >= #{parentList.dateCreatedRange.begin}",};

	public List<Parent> getParentsByParentGroup(
			com.oreon.smartsis.domain.ParentGroup parentGroup) {
		//setMaxResults(10000);
		parent.setParentGroup(parentGroup);
		return getResultList();
	}

	@Observer("archivedParent")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Parent e) {

		builder.append("\""
				+ (e.getUser() != null ? e.getUser().getDisplayName().replace(
						",", "") : "") + "\",");

		builder.append("\""
				+ (e.getParentGroup() != null ? e.getParentGroup()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("User" + ",");

		builder.append("ParentGroup" + ",");

		builder.append("\r\n");
	}
}
