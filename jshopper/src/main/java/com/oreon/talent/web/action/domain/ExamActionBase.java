package com.oreon.talent.web.action.domain;

import com.oreon.talent.domain.Exam;

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

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.oreon.talent.domain.Question;

public abstract class ExamActionBase extends BaseAction<Exam>
		implements
			java.io.Serializable {

	protected Predicate[] getSearchPredicates(Root<Exam> root) {

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
	protected Class<Exam> getEntityClass() {
		return Exam.class;
	}

	public Exam createInstance() {
		return new Exam();
	}

	public Exam getExam() {
		if (entity == null)
			entity = createInstance();
		return this.entity;
	}

	public void setExam(Exam exam) {
		this.entity = exam;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListQuestions();

	}

	public void updateAssociations() {
	}

	protected List<com.oreon.talent.domain.Question> listQuestions = new ArrayList<com.oreon.talent.domain.Question>();

	void initListQuestions() {
		prePopulateListQuestions();
		listQuestions.addAll(getEntity().getQuestions());
	}

	public List<com.oreon.talent.domain.Question> getListQuestions() {
		return listQuestions;
	}

	public void setListQuestions(
			List<com.oreon.talent.domain.Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	public void addQuestions() {
		initListQuestions();
		Question questions = new Question();

		questions.setExam(getEntity());

		getListQuestions().add(questions);
	}

	public void updateComposedAssociations() {

		if (listQuestions != null) {
			getEntity().getQuestions().clear();
			getEntity().getQuestions().addAll(listQuestions);
		}
	}

	public void clearLists() {
		listQuestions.clear();

	}

	public com.oreon.talent.domain.Exam findByUnqTitle(String title) {
		return executeSingleResultNamedQuery("exam.findByUnqTitle", title);
	}

}
