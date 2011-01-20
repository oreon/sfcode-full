package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Choice;

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

import org.wc.trackrite.exams.Choice;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChoiceListQueryBase extends BaseQuery<Choice, Long> {

	private static final String EJBQL = "select choice from Choice choice";

	protected Choice choice = new Choice();

	public Choice getChoice() {
		return choice;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Class<Choice> getEntityClass() {
		return Choice.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"choice.id = #{choiceList.choice.id}",

			"choice.question.id = #{choiceList.choice.question.id}",

			"lower(choice.text) like concat(lower(#{choiceList.choice.text}),'%')",

			"choice.dateCreated <= #{choiceList.dateCreatedRange.end}",
			"choice.dateCreated >= #{choiceList.dateCreatedRange.begin}",};

	public List<Choice> getChoicesByQuestion(
			org.wc.trackrite.exams.Question question) {
		//setMaxResults(10000);
		choice.setQuestion(question);
		return getResultList();
	}

	@Observer("archivedChoice")
	public void onArchive() {
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Choice e) {

		if (e.getQuestion() != null)

			builder.append(e.getQuestion().getDisplayName());

		builder.append(",");

		if (e.getText() != null)

			builder.append(e.getText() + ",");

		builder.append(",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Question" + ",");

		builder.append("Text" + ",");

		builder.append("\r\n");
	}
}
