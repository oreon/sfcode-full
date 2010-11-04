package com.nas.recovery.web.action.exams;

import org.wc.trackrite.exams.Choice;

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

import org.wc.trackrite.exams.Choice;

/**
 * @author WitchcraftMDA Seam Cartridge
 *
 */
public abstract class ChoiceListQueryBase extends BaseQuery<Choice, Long> {

	//private static final String EJBQL = "select choice from Choice choice";

	protected Choice choice = new Choice();

	private static final String[] RESTRICTIONS = {
			"choice.id = #{choiceList.choice.id}",

			"choice.question.id = #{choiceList.choice.question.id}",

			"lower(choice.text) like concat(lower(#{choiceList.choice.text}),'%')",

			"choice.dateCreated <= #{choiceList.dateCreatedRange.end}",
			"choice.dateCreated >= #{choiceList.dateCreatedRange.begin}",};

	public Choice getChoice() {
		return choice;
	}

	@Override
	public Class<Choice> getEntityClass() {
		return Choice.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		// TODO Auto-generated method stub
		return RESTRICTIONS;
	}

	@Observer("archivedChoice")
	public void onArchive() {
		refresh();
	}
}
