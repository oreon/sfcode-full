package com.nas.recovery.web.action.onepack;

import org.wc.trackrite.onepack.Exam;

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

import org.wc.trackrite.onepack.Exam;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ExamListQueryBase extends BaseQuery<Exam, Long> {

	//private static final String EJBQL = "select exam from Exam exam";

	protected Exam exam = new Exam();

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

	public Exam getExam() {
		return exam;
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedExam")
	public void onArchive() {
		refresh();
	}
}
