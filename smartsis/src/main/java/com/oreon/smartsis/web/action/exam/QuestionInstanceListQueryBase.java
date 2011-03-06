package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.QuestionInstance;

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

import com.oreon.smartsis.exam.QuestionInstance;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class QuestionInstanceListQueryBase
		extends
			BaseQuery<QuestionInstance, Long> {

	private static final String EJBQL = "select questionInstance from QuestionInstance questionInstance";

	protected QuestionInstance questionInstance = new QuestionInstance();

	public QuestionInstance getQuestionInstance() {
		return questionInstance;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<QuestionInstance> getEntityClass() {
		return QuestionInstance.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"questionInstance.id = #{questionInstanceList.questionInstance.id}",

			"questionInstance.electronicExamInstance.id = #{questionInstanceList.questionInstance.electronicExamInstance.id}",

			"questionInstance.question.id = #{questionInstanceList.questionInstance.question.id}",

			"questionInstance.selectedChoice.id = #{questionInstanceList.questionInstance.selectedChoice.id}",

			"questionInstance.dateCreated <= #{questionInstanceList.dateCreatedRange.end}",
			"questionInstance.dateCreated >= #{questionInstanceList.dateCreatedRange.begin}",};

	public List<QuestionInstance> getQuestionInstancesByElectronicExamInstance(
			com.oreon.smartsis.exam.ElectronicExamInstance electronicExamInstance) {
		//setMaxResults(10000);
		questionInstance.setElectronicExamInstance(electronicExamInstance);
		return getResultList();
	}

	@Observer("archivedQuestionInstance")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, QuestionInstance e) {

		builder.append("\""
				+ (e.getElectronicExamInstance() != null ? e
						.getElectronicExamInstance().getDisplayName().replace(
								",", "") : "") + "\",");

		builder.append("\""
				+ (e.getQuestion() != null ? e.getQuestion().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\""
				+ (e.getSelectedChoice() != null ? e.getSelectedChoice()
						.getDisplayName().replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("ElectronicExamInstance" + ",");

		builder.append("Question" + ",");

		builder.append("SelectedChoice" + ",");

		builder.append("\r\n");
	}
}
