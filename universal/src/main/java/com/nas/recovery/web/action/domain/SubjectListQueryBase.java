package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.Subject;

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

import com.oreon.tapovan.domain.Subject;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
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

}
