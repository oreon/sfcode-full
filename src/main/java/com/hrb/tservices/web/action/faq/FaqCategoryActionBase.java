package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.FaqCategory;

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

import com.hrb.tservices.domain.faq.FaqQuestion;

public abstract class FaqCategoryActionBase extends BaseAction<FaqCategory>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private FaqCategory faqCategory;

	@DataModel
	private List<FaqCategory> faqCategoryRecordList;

	public void setFaqCategoryId(Long id) {
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
	public void setFaqCategoryIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public Long getFaqCategoryId() {
		return (Long) getId();
	}

	public FaqCategory getEntity() {
		return faqCategory;
	}

	//@Override
	public void setEntity(FaqCategory t) {
		this.faqCategory = t;
		loadAssociations();
	}

	public FaqCategory getFaqCategory() {
		return (FaqCategory) getInstance();
	}

	@Override
	protected FaqCategory createInstance() {
		FaqCategory instance = super.createInstance();

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

	public FaqCategory getDefinedInstance() {
		return (FaqCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setFaqCategory(FaqCategory t) {
		this.faqCategory = t;
		if (faqCategory != null)
			setFaqCategoryId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<FaqCategory> getEntityClass() {
		return FaqCategory.class;
	}

	public com.hrb.tservices.domain.faq.FaqCategory findByUnqName(String name) {
		return executeSingleResultNamedQuery("faqCategory.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListFaqQuestions();

	}

	public void updateAssociations() {

	}

	protected List<com.hrb.tservices.domain.faq.FaqQuestion> listFaqQuestions = new ArrayList<com.hrb.tservices.domain.faq.FaqQuestion>();

	void initListFaqQuestions() {

		if (listFaqQuestions.isEmpty())
			listFaqQuestions.addAll(getInstance().getFaqQuestions());

	}

	public List<com.hrb.tservices.domain.faq.FaqQuestion> getListFaqQuestions() {

		prePopulateListFaqQuestions();
		return listFaqQuestions;
	}

	public void prePopulateListFaqQuestions() {
	}

	public void setListFaqQuestions(
			List<com.hrb.tservices.domain.faq.FaqQuestion> listFaqQuestions) {
		this.listFaqQuestions = listFaqQuestions;
	}

	public void deleteFaqQuestions(int index) {
		listFaqQuestions.remove(index);
	}

	@Begin(join = true)
	public void addFaqQuestions() {
		initListFaqQuestions();
		FaqQuestion faqQuestions = new FaqQuestion();

		faqQuestions.setFaqCategory(getInstance());

		getListFaqQuestions().add(faqQuestions);
	}

	public void updateComposedAssociations() {

		if (listFaqQuestions != null) {
			getInstance().getFaqQuestions().clear();
			getInstance().getFaqQuestions().addAll(listFaqQuestions);
		}
	}

	public void clearLists() {
		listFaqQuestions.clear();

	}

	public String viewFaqCategory() {
		load(currentEntityId);
		return "viewFaqCategory";
	}

}
