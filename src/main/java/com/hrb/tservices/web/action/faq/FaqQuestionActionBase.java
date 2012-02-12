package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.FaqQuestion;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.hrb.tservices.domain.faq.QuestionTranslation;
import com.hrb.tservices.domain.faq.Rating;

public abstract class FaqQuestionActionBase extends BaseAction<FaqQuestion>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FaqQuestion faqQuestion;

	@In(create = true, value = "faqCategoryAction")
	com.hrb.tservices.web.action.faq.FaqCategoryAction faqCategoryAction;

	@DataModel
	private List<FaqQuestion> faqQuestionRecordList;

	public void setFaqQuestionId(Long id) {
		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		setId(id);
		if (!isPostBack())
			loadAssociations();
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFaqQuestionIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setFaqCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setFaqCategory(faqCategoryAction.loadFromId(id));

	}

	public Long getFaqCategoryId() {
		if (getInstance().getFaqCategory() != null)
			return getInstance().getFaqCategory().getId();
		return 0L;
	}

	public Long getFaqQuestionId() {
		return (Long) getId();
	}

	public FaqQuestion getEntity() {
		return faqQuestion;
	}

	//@Override
	public void setEntity(FaqQuestion t) {
		this.faqQuestion = t;
		loadAssociations();
	}

	public FaqQuestion getFaqQuestion() {
		return (FaqQuestion) getInstance();
	}

	@Override
	protected FaqQuestion createInstance() {
		FaqQuestion instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.faq.FaqCategory faqCategory = faqCategoryAction
				.getDefinedInstance();
		if (faqCategory != null && isNew()) {
			getInstance().setFaqCategory(faqCategory);
		}

	}

	public boolean isWired() {
		return true;
	}

	public FaqQuestion getDefinedInstance() {
		return (FaqQuestion) (isIdDefined() ? getInstance() : null);
	}

	public void setFaqQuestion(FaqQuestion t) {
		this.faqQuestion = t;
		if (faqQuestion != null)
			setFaqQuestionId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<FaqQuestion> getEntityClass() {
		return FaqQuestion.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (faqQuestion.getFaqCategory() != null) {
			criteria = criteria.add(Restrictions.eq("faqCategory.id",
					faqQuestion.getFaqCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (faqQuestion.getFaqCategory() != null) {
			faqCategoryAction.setInstance(getInstance().getFaqCategory());
		}

		initListQuestionTranslations();

		initListRatings();

	}

	public void updateAssociations() {

	}

	protected List<com.hrb.tservices.domain.faq.QuestionTranslation> listQuestionTranslations = new ArrayList<com.hrb.tservices.domain.faq.QuestionTranslation>();

	void initListQuestionTranslations() {

		if (listQuestionTranslations.isEmpty())
			listQuestionTranslations.addAll(getInstance()
					.getQuestionTranslations());

	}

	public List<com.hrb.tservices.domain.faq.QuestionTranslation> getListQuestionTranslations() {

		prePopulateListQuestionTranslations();
		return listQuestionTranslations;
	}

	public void prePopulateListQuestionTranslations() {
	}

	public void setListQuestionTranslations(
			List<com.hrb.tservices.domain.faq.QuestionTranslation> listQuestionTranslations) {
		this.listQuestionTranslations = listQuestionTranslations;
	}

	public void deleteQuestionTranslations(int index) {
		listQuestionTranslations.remove(index);
	}

	@Begin(join = true)
	public void addQuestionTranslations() {
		initListQuestionTranslations();
		QuestionTranslation questionTranslations = new QuestionTranslation();

		questionTranslations.setFaqQuestion(getInstance());

		getListQuestionTranslations().add(questionTranslations);
	}

	protected List<com.hrb.tservices.domain.faq.Rating> listRatings = new ArrayList<com.hrb.tservices.domain.faq.Rating>();

	void initListRatings() {

		if (listRatings.isEmpty())
			listRatings.addAll(getInstance().getRatings());

	}

	public List<com.hrb.tservices.domain.faq.Rating> getListRatings() {

		prePopulateListRatings();
		return listRatings;
	}

	public void prePopulateListRatings() {
	}

	public void setListRatings(
			List<com.hrb.tservices.domain.faq.Rating> listRatings) {
		this.listRatings = listRatings;
	}

	public void deleteRatings(int index) {
		listRatings.remove(index);
	}

	@Begin(join = true)
	public void addRatings() {
		initListRatings();
		Rating ratings = new Rating();

		ratings.setFaqQuestion(getInstance());

		getListRatings().add(ratings);
	}

	public void updateComposedAssociations() {

		if (listQuestionTranslations != null) {
			getInstance().getQuestionTranslations().clear();
			getInstance().getQuestionTranslations().addAll(
					listQuestionTranslations);
		}

		if (listRatings != null) {
			getInstance().getRatings().clear();
			getInstance().getRatings().addAll(listRatings);
		}
	}

	public void clearLists() {
		listQuestionTranslations.clear();
		listRatings.clear();

	}

	public String viewFaqQuestion() {
		load(currentEntityId);
		return "viewFaqQuestion";
	}

}
