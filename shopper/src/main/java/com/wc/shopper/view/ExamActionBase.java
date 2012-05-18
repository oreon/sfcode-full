package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jboss.seam.faces.context.conversation.Begin;

import com.wc.shopper.domain.Exam;
import com.wc.shopper.domain.Question;



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

	protected List<Question> listQuestions = new ArrayList<Question>();

	void initListQuestions() {

		if (listQuestions.isEmpty())
			listQuestions.addAll(getEntity().getQuestions());

	}

	public List<Question> getListQuestions() {

		prePopulateListQuestions();
		return listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void setListQuestions(
			List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	//@Begin(join = true)
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

}
