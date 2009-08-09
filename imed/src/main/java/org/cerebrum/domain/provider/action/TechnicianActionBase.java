package org.cerebrum.domain.provider.action;

import org.cerebrum.domain.provider.Technician;
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

public class TechnicianActionBase extends BaseAction<Technician>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Technician technician;

	@DataModel
	private List<Technician> technicianList;

	@Factory("technicianList")
	@Observer("archivedTechnician")
	public void findRecords() {
		search();
	}

	public Technician getEntity() {
		return technician;
	}

	@Override
	public void setEntity(Technician t) {
		this.technician = t;
	}

	@Override
	public void setEntityList(List<Technician> list) {
		this.technicianList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (technician.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", technician
					.getUser().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Technician> getEntityList() {
		if (technicianList == null) {
			findRecords();
		}
		return technicianList;
	}

}
