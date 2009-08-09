package org.cerebrum.domain.prescription.action;

import org.cerebrum.domain.prescription.Item;
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

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

public class ItemActionBase extends BaseAction<Item>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Item item;

	@DataModel
	private List<Item> itemList;

	@Factory("itemList")
	@Observer("archivedItem")
	public void findRecords() {
		search();
	}

	public Item getEntity() {
		return item;
	}

	@Override
	public void setEntity(Item t) {
		this.item = t;
	}

	@Override
	public void setEntityList(List<Item> list) {
		this.itemList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (item.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id", item
					.getPrescription().getId()));
		}

		if (item.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", item.getDrug()
					.getId()));
		}

		if (item.getFrequency() != null) {
			criteria = criteria.add(Restrictions.eq("frequency.id", item
					.getFrequency().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Item> getEntityList() {
		if (itemList == null) {
			findRecords();
		}
		return itemList;
	}

}
