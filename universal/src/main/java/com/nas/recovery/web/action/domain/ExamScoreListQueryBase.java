package com.nas.recovery.web.action.domain;

import com.oreon.tapovan.domain.ExamScore;

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

import com.oreon.tapovan.domain.ExamScore;

/**
 * D
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ExamScoreListQueryBase extends BaseQuery<ExamScore, Long> {

	private static final String EJBQL = "select examScore from ExamScore examScore";

	protected ExamScore examScore = new ExamScore();

	public ExamScore getExamScore() {
		return examScore;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<ExamScore> getEntityClass() {
		return ExamScore.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> marksRange = new Range<Integer>();
	public Range<Integer> getMarksRange() {
		return marksRange;
	}
	public void setMarks(Range<Integer> marksRange) {
		this.marksRange = marksRange;
	}

	private static final String[] RESTRICTIONS = {
			"examScore.id = #{examScoreList.examScore.id}",

			"examScore.student.id = #{examScoreList.examScore.student.id}",

			"examScore.marks >= #{examScoreList.marksRange.begin}",
			"examScore.marks <= #{examScoreList.marksRange.end}",

			"examScore.examInstance.id = #{examScoreList.examScore.examInstance.id}",

			"examScore.dateCreated <= #{examScoreList.dateCreatedRange.end}",
			"examScore.dateCreated >= #{examScoreList.dateCreatedRange.begin}",};

	public List<ExamScore> getExamScoresByExamInstance(
			com.oreon.tapovan.domain.ExamInstance examInstance) {
		//setMaxResults(10000);
		examScore.setExamInstance(examInstance);
		return getResultList();
	}

	@Observer("archivedExamScore")
	public void onArchive() {
		refresh();
	}

}
