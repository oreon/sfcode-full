package com.oreon.smartsis.web.action.exam;

import com.oreon.smartsis.exam.Choice;

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

import com.oreon.smartsis.exam.Choice;

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

			"lower(choice.choice) like concat(lower(#{choiceList.choice.choice}),'%')",

			"choice.question.id = #{choiceList.choice.question.id}",

			"choice.dateCreated <= #{choiceList.dateCreatedRange.end}",
			"choice.dateCreated >= #{choiceList.dateCreatedRange.begin}",};

	public List<Choice> getChoicesByQuestion(
			com.oreon.smartsis.exam.Question question) {
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

		builder.append("\""
				+ (e.getChoice() != null ? e.getChoice().replace(",", "") : "")
				+ "\",");

		builder.append("\""
				+ (e.getQuestion() != null ? e.getQuestion().getDisplayName()
						.replace(",", "") : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Choice" + ",");

		builder.append("Question" + ",");

		builder.append("\r\n");
	}
}
