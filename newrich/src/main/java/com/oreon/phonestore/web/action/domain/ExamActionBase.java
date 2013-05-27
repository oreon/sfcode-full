package com.oreon.phonestore.web.action.domain;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.phonestore.domain.Exam;
import com.oreon.phonestore.domain.Question;

public abstract class ExamActionBase extends BaseAction<Exam>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Exam exam;

	public void setExamId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		exam = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setExamIdForModalDlg(Long id) {
		setId(id);
		exam = loadInstance();
		clearLists();
		loadAssociations();
	}

	public Long getExamId() {
		return (Long) getId();
	}

	public Exam getEntity() {
		return exam;
	}

	//@Override
	public void setEntity(Exam t) {
		this.exam = t;
		loadAssociations();
	}

	public Exam getExam() {
		return (Exam) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('exam', 'edit')}")
	public String doSave() {
		return super.doSave();
	}

	@Override
	@Restrict("#{s:hasPermission('exam', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Exam createInstance() {
		Exam instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

	}

	public boolean isWired() {
		return true;
	}

	public Exam getDefinedInstance() {
		return (Exam) (isIdDefined() ? getInstance() : null);
	}

	public void setExam(Exam t) {
		this.exam = t;
		if (exam != null)
			setExamId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Exam> getEntityClass() {
		return Exam.class;
	}

	public com.oreon.phonestore.domain.Exam findByUnqTitle(String title) {
		return executeSingleResultNamedQuery("exam.findByUnqTitle", title);
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

	protected List<com.oreon.phonestore.domain.Question> listQuestions = new ArrayList<com.oreon.phonestore.domain.Question>();

	void initListQuestions() {

		if (listQuestions.isEmpty())
			listQuestions.addAll(getInstance().getQuestions());

	}

	public List<com.oreon.phonestore.domain.Question> getListQuestions() {

		prePopulateListQuestions();
		return listQuestions;
	}

	public void prePopulateListQuestions() {
	}

	public void setListQuestions(
			List<com.oreon.phonestore.domain.Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	public void deleteQuestions(int index) {
		listQuestions.remove(index);
	}

	@Begin(join = true)
	public void addQuestions() {
		initListQuestions();
		Question questions = new Question();

		questions.setExam(getInstance());

		getListQuestions().add(questions);
	}

	public void updateComposedAssociations() {

		if (listQuestions != null) {
			getInstance().getQuestions().clear();
			getInstance().getQuestions().addAll(listQuestions);
		}
	}

	public void clearLists() {
		listQuestions.clear();

	}

	public String viewExam() {
		load(currentEntityId);
		return "viewExam";
	}

}
