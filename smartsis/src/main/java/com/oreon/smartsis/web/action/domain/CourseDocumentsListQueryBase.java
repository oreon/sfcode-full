package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.CourseDocuments;

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

import com.oreon.smartsis.domain.CourseDocuments;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CourseDocumentsListQueryBase
		extends
			BaseQuery<CourseDocuments, Long> {

	private static final String EJBQL = "select courseDocuments from CourseDocuments courseDocuments";

	protected CourseDocuments courseDocuments = new CourseDocuments();

	public CourseDocuments getCourseDocuments() {
		return courseDocuments;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<CourseDocuments> getEntityClass() {
		return CourseDocuments.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"courseDocuments.id = #{courseDocumentsList.courseDocuments.id}",

			"lower(courseDocuments.name) like concat(lower(#{courseDocumentsList.courseDocuments.name}),'%')",

			"courseDocuments.gradeSubject.id = #{courseDocumentsList.courseDocuments.gradeSubject.id}",

			"courseDocuments.dateCreated <= #{courseDocumentsList.dateCreatedRange.end}",
			"courseDocuments.dateCreated >= #{courseDocumentsList.dateCreatedRange.begin}",};

	public List<CourseDocuments> getCourseDocumentsesByGradeSubject(
			com.oreon.smartsis.domain.GradeSubject gradeSubject) {
		//setMaxResults(10000);
		courseDocuments.setGradeSubject(gradeSubject);
		return getResultList();
	}

	@Observer("archivedCourseDocuments")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, CourseDocuments e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getGradeSubject() != null ? e.getGradeSubject()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("GradeSubject" + ",");

		builder.append("\r\n");
	}
}
