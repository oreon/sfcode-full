package com.oreon.smartsis.web.action.domain;

import com.oreon.smartsis.domain.Subject;

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

import com.oreon.smartsis.domain.Subject;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SubjectListQueryBase extends BaseQuery<Subject, Long> {

	private static final String EJBQL = "select subject from Subject subject";

	protected Subject subject = new Subject();

	public Subject getSubject() {
		return subject;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Subject> getEntityClass() {
		return Subject.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"subject.id = #{subjectList.subject.id}",

			"lower(subject.name) like concat(lower(#{subjectList.subject.name}),'%')",

			"subject.dateCreated <= #{subjectList.dateCreatedRange.end}",
			"subject.dateCreated >= #{subjectList.dateCreatedRange.begin}",};

	@Observer("archivedSubject")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Subject e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

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
