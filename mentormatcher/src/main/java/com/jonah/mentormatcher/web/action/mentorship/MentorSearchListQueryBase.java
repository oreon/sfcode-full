package com.jonah.mentormatcher.web.action.mentorship;

import com.jonah.mentormatcher.domain.mentorship.MentorSearch;

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

import com.jonah.mentormatcher.domain.mentorship.MentorSearch;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class MentorSearchListQueryBase
		extends
			BaseQuery<MentorSearch, Long> {

	private static final String EJBQL = "select mentorSearch from MentorSearch mentorSearch";

	protected MentorSearch mentorSearch = new MentorSearch();

	public MentorSearch getMentorSearch() {
		return mentorSearch;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<MentorSearch> getEntityClass() {
		return MentorSearch.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"mentorSearch.id = #{mentorSearchList.mentorSearch.id}",

			"mentorSearch.category.id = #{mentorSearchList.mentorSearch.category.id}",

			"lower(mentorSearch.title) like concat(lower(#{mentorSearchList.mentorSearch.title}),'%')",

			"mentorSearch.employee.id = #{mentorSearchList.mentorSearch.employee.id}",

			"mentorSearch.scope = #{mentorSearchList.mentorSearch.scope}",

			"mentorSearch.dateCreated <= #{mentorSearchList.dateCreatedRange.end}",
			"mentorSearch.dateCreated >= #{mentorSearchList.dateCreatedRange.begin}",};

	@Observer("archivedMentorSearch")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, MentorSearch e) {

		builder.append("\""
				+ (e.getCategory() != null ? e.getCategory().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getTitle() != null ? e.getTitle().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getEmployee() != null ? e.getEmployee().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\"" + (e.getScope() != null ? e.getScope() : "")
				+ "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Category" + ",");

		builder.append("Title" + ",");

		builder.append("Employee" + ",");

		builder.append("Scope" + ",");

		builder.append("\r\n");
	}
}
