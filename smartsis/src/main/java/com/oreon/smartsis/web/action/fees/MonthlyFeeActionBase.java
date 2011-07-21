package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.MonthlyFee;

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

import com.oreon.smartsis.fees.GradeFeeItem;

public abstract class MonthlyFeeActionBase extends BaseAction<MonthlyFee>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private MonthlyFee monthlyFee;

	@In(create = true, value = "gradeAction")
	com.oreon.smartsis.web.action.domain.GradeAction gradeAction;

	@DataModel
	private List<MonthlyFee> monthlyFeeRecordList;

	public void setMonthlyFeeId(Long id) {
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
	public void setMonthlyFeeIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setGradeId(Long id) {

		if (id != null && id > 0)
			getInstance().setGrade(gradeAction.loadFromId(id));

	}

	public Long getGradeId() {
		if (getInstance().getGrade() != null)
			return getInstance().getGrade().getId();
		return 0L;
	}

	public Long getMonthlyFeeId() {
		return (Long) getId();
	}

	public MonthlyFee getEntity() {
		return monthlyFee;
	}

	//@Override
	public void setEntity(MonthlyFee t) {
		this.monthlyFee = t;
		loadAssociations();
	}

	public MonthlyFee getMonthlyFee() {
		return (MonthlyFee) getInstance();
	}

	@Override
	protected MonthlyFee createInstance() {
		return new MonthlyFee();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.domain.Grade grade = gradeAction
				.getDefinedInstance();
		if (grade != null && isNew()) {
			getInstance().setGrade(grade);
		}

	}

	public boolean isWired() {
		return true;
	}

	public MonthlyFee getDefinedInstance() {
		return (MonthlyFee) (isIdDefined() ? getInstance() : null);
	}

	public void setMonthlyFee(MonthlyFee t) {
		this.monthlyFee = t;
		if (monthlyFee != null)
			setMonthlyFeeId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<MonthlyFee> getEntityClass() {
		return MonthlyFee.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (monthlyFee.getGrade() != null) {
			criteria = criteria.add(Restrictions.eq("grade.id", monthlyFee
					.getGrade().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (monthlyFee.getGrade() != null) {
			gradeAction.setInstance(getInstance().getGrade());
		}

		initListGradeFeeItems();

	}

	public void updateAssociations() {

	}

	protected List<com.oreon.smartsis.fees.GradeFeeItem> listGradeFeeItems = new ArrayList<com.oreon.smartsis.fees.GradeFeeItem>();

	void initListGradeFeeItems() {

		if (listGradeFeeItems.isEmpty())
			listGradeFeeItems.addAll(getInstance().getGradeFeeItems());

	}

	public List<com.oreon.smartsis.fees.GradeFeeItem> getListGradeFeeItems() {

		prePopulateListGradeFeeItems();
		return listGradeFeeItems;
	}

	public void prePopulateListGradeFeeItems() {
	}

	public void setListGradeFeeItems(
			List<com.oreon.smartsis.fees.GradeFeeItem> listGradeFeeItems) {
		this.listGradeFeeItems = listGradeFeeItems;
	}

	public void deleteGradeFeeItems(int index) {
		listGradeFeeItems.remove(index);
	}

	@Begin(join = true)
	public void addGradeFeeItems() {
		initListGradeFeeItems();
		GradeFeeItem gradeFeeItems = new GradeFeeItem();

		gradeFeeItems.setMonthlyFee(getInstance());

		getListGradeFeeItems().add(gradeFeeItems);
	}

	public void updateComposedAssociations() {

		if (listGradeFeeItems != null) {
			getInstance().getGradeFeeItems().clear();
			getInstance().getGradeFeeItems().addAll(listGradeFeeItems);
		}
	}

	public void clearLists() {
		listGradeFeeItems.clear();

	}

}
