package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.ExamInstance;

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

import org.wc.trackrite.exams.ExamInstance;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ExamInstanceListQueryBase
		extends
			BaseQuery<ExamInstance, Long> {

	private static final String EJBQL = "select examInstance from ExamInstance examInstance";

	protected ExamInstance examInstance = new ExamInstance();

	public ExamInstance getExamInstance() {
		return examInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ExamInstance> getEntityClass() {
		return ExamInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"examInstance.id = #{examInstanceList.examInstance.id}",

			"examInstance.exam.id = #{examInstanceList.examInstance.exam.id}",

			"examInstance.candidate.id = #{examInstanceList.examInstance.candidate.id}",

			"examInstance.dateCreated <= #{examInstanceList.dateCreatedRange.end}",
			"examInstance.dateCreated >= #{examInstanceList.dateCreatedRange.begin}",};

	@Observer("archivedExamInstance")
	public void onArchive() {
		refresh();
	}

}
