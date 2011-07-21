package com.oreon.smartsis.web.action.fees;

import com.oreon.smartsis.fees.GradeFeeItem;

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

public abstract class GradeFeeItemActionBase extends BaseAction<GradeFeeItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private GradeFeeItem gradeFeeItem;

	@In(create = true, value = "monthlyFeeAction")
	com.oreon.smartsis.web.action.fees.MonthlyFeeAction monthlyFeeAction;

	@In(create = true, value = "feeItemAction")
	com.oreon.smartsis.web.action.fees.FeeItemAction feeItemAction;

	@DataModel
	private List<GradeFeeItem> gradeFeeItemRecordList;

	public void setGradeFeeItemId(Long id) {
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
	public void setGradeFeeItemIdForModalDlg(Long id) {
		setId(id);
		clearLists();
		loadAssociations();
	}

	public void setMonthlyFeeId(Long id) {

		if (id != null && id > 0)
			getInstance().setMonthlyFee(monthlyFeeAction.loadFromId(id));

	}

	public Long getMonthlyFeeId() {
		if (getInstance().getMonthlyFee() != null)
			return getInstance().getMonthlyFee().getId();
		return 0L;
	}

	public void setFeeItemId(Long id) {

		if (id != null && id > 0)
			getInstance().setFeeItem(feeItemAction.loadFromId(id));

	}

	public Long getFeeItemId() {
		if (getInstance().getFeeItem() != null)
			return getInstance().getFeeItem().getId();
		return 0L;
	}

	public Long getGradeFeeItemId() {
		return (Long) getId();
	}

	public GradeFeeItem getEntity() {
		return gradeFeeItem;
	}

	//@Override
	public void setEntity(GradeFeeItem t) {
		this.gradeFeeItem = t;
		loadAssociations();
	}

	public GradeFeeItem getGradeFeeItem() {
		return (GradeFeeItem) getInstance();
	}

	@Override
	protected GradeFeeItem createInstance() {
		return new GradeFeeItem();
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();

		com.oreon.smartsis.fees.MonthlyFee monthlyFee = monthlyFeeAction
				.getDefinedInstance();
		if (monthlyFee != null && isNew()) {
			getInstance().setMonthlyFee(monthlyFee);
		}

		com.oreon.smartsis.fees.FeeItem feeItem = feeItemAction
				.getDefinedInstance();
		if (feeItem != null && isNew()) {
			getInstance().setFeeItem(feeItem);
		}

	}

	public boolean isWired() {
		return true;
	}

	public GradeFeeItem getDefinedInstance() {
		return (GradeFeeItem) (isIdDefined() ? getInstance() : null);
	}

	public void setGradeFeeItem(GradeFeeItem t) {
		this.gradeFeeItem = t;
		if (gradeFeeItem != null)
			setGradeFeeItemId(t.getId());
		loadAssociations();
	}

	@Override
	public Class<GradeFeeItem> getEntityClass() {
		return GradeFeeItem.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (gradeFeeItem.getMonthlyFee() != null) {
			criteria = criteria.add(Restrictions.eq("monthlyFee.id",
					gradeFeeItem.getMonthlyFee().getId()));
		}

		if (gradeFeeItem.getFeeItem() != null) {
			criteria = criteria.add(Restrictions.eq("feeItem.id", gradeFeeItem
					.getFeeItem().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (gradeFeeItem.getMonthlyFee() != null) {
			monthlyFeeAction.setInstance(getInstance().getMonthlyFee());
		}

		if (gradeFeeItem.getFeeItem() != null) {
			feeItemAction.setInstance(getInstance().getFeeItem());
		}

	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

}
