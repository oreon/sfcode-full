package com.oreon.talent.web.action.domain;

import com.oreon.talent.domain.Question;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DualListModel;

import org.witchcraft.utils.ViewUtils;
import javax.inject.Inject;

public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@Inject
	com.oreon.talent.web.action.domain.ExamAction examAction;

	protected Predicate[] getSearchPredicates(Root<Question> root) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		/*
		String name = search.getName();
		if (name != null && !"".equals(name)) {
			predicatesList.add(builder.like(root.<String> get("name"),
					'%' + name + '%'));
		}
		
		int stock = search.getStock();
		if (stock != 0) {
			predicatesList.add(builder.equal(root.get("stock"), stock));
		}*/

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	@Override
	protected Class<Question> getEntityClass() {
		return Question.class;
	}

	public Question createInstance() {
		return new Question();
	}

	public Question getQuestion() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setQuestion(Question question) {
		this.entity = question;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

	}

	public void updateAssociations() {
	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
