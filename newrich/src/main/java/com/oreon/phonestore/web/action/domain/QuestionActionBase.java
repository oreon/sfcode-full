package com.oreon.phonestore.web.action.domain;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Question;

public abstract class QuestionActionBase extends BaseAction<Question>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Question question;

	@In(create = true, value = "examAction")
	com.oreon.phonestore.web.action.domain.ExamAction examAction;

	public void setQuestionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		question = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setQuestionIdForModalDlg(Long id) {
		setId(id);
		question = loadInstance();
		clearLists();
		loadAssociations();
	}

	public void setExamId(Long id) {

		if (id != null && id > 0)
			getInstance().setExam(examAction.loadFromId(id));

	}

	public Long getExamId() {
		if (getInstance().getExam() != null)
			return getInstance().getExam().getId();
		return 0L;
	}

	public Long getQuestionId() {
		return (Long) getId();
	}

	public Question getEntity() {
		return question;
	}

	//@Override
	public void setEntity(Question t) {
		this.question = t;
		loadAssociations();
	}

	public Question getQuestion() {
		return (Question) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('question', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('question', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Question createInstance() {
		Question instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.phonestore.domain.Exam exam = examAction.getDefinedInstance();
		if (exam != null && isNew()) {
			getInstance().setExam(exam);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Question getDefinedInstance() {
		return (Question) (isIdDefined() ? getInstance() : null);
	}

	public void setQuestion(Question t) {
		this.question = t;
		if (question != null)
			setQuestionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (question.getExam() != null) {
			criteria = criteria.add(Restrictions.eq("exam.id", question
					.getExam().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (question.getExam() != null) {
			examAction.setInstance(getInstance().getExam());
			examAction.loadAssociations();
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewQuestion() {
		load(currentEntityId);
		return "viewQuestion";
	}

}
