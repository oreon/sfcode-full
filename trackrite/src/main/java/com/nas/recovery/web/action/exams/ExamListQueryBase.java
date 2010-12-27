package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Exam;

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

import org.wc.trackrite.exams.Exam;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ExamListQueryBase extends BaseQuery<Exam, Long> {

	private static final String EJBQL = "select exam from Exam exam";

	protected Exam exam = new Exam();

	public Exam getExam() {
		return exam;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> durationRange = new Range<Integer>();
	public Range<Integer> getDurationRange() {
		return durationRange;
	}
	public void setDuration(Range<Integer> durationRange) {
		this.durationRange = durationRange;
	}

	private static final String[] RESTRICTIONS = {
			"exam.id = #{examList.exam.id}",

			"lower(exam.name) like concat(lower(#{examList.exam.name}),'%')",

			"exam.duration >= #{examList.durationRange.begin}",
			"exam.duration <= #{examList.durationRange.end}",

			"exam.dateCreated <= #{examList.dateCreatedRange.end}",
			"exam.dateCreated >= #{examList.dateCreatedRange.begin}",};

	@Observer("archivedExam")
	public void onArchive() {
		refresh();
	}

}
