package com.hrb.tservices.web.action.faq;

import com.hrb.tservices.domain.faq.Rating;

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

public abstract class RatingActionBase extends BaseAction<Rating>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Rating rating;

	@In(create = true, value = "faqQuestionAction")
	com.hrb.tservices.web.action.faq.FaqQuestionAction faqQuestionAction;

	@DataModel
	private List<Rating> ratingRecordList;

	public void setRatingId(Long id) {
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
	public void setRatingIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setFaqQuestionId(Long id) {

		if (id != null && id > 0)
			getInstance().setFaqQuestion(faqQuestionAction.loadFromId(id));

	}

	public Long getFaqQuestionId() {
		if (getInstance().getFaqQuestion() != null)
			return getInstance().getFaqQuestion().getId();
		return 0L;
	}

	public Long getRatingId() {
		return (Long) getId();
	}

	public Rating getEntity() {
		return rating;
	}

	//@Override
	public void setEntity(Rating t) {
		this.rating = t;
		loadAssociations();
	}

	public Rating getRating() {
		return (Rating) getInstance();
	}

	@Override
	protected Rating createInstance() {
		Rating instance = super.createInstance();

		return instance;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.hrb.tservices.domain.faq.FaqQuestion faqQuestion = faqQuestionAction
				.getDefinedInstance();
		if (faqQuestion != null && isNew()) {
			getInstance().setFaqQuestion(faqQuestion);
		}

	}

	public boolean isWired() {
		return true;
	}

	public Rating getDefinedInstance() {
		return (Rating) (isIdDefined() ? getInstance() : null);
	}

	public void setRating(Rating t) {
		this.rating = t;
		if (rating != null)
			setRatingId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<Rating> getEntityClass() {
		return Rating.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (rating.getFaqQuestion() != null) {
			criteria = criteria.add(Restrictions.eq("faqQuestion.id", rating
					.getFaqQuestion().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (rating.getFaqQuestion() != null) {
			faqQuestionAction.setInstance(getInstance().getFaqQuestion());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewRating() {
		load(currentEntityId);
		return "viewRating";
	}

}
