package org.cerebrum.domain.provider.action;

import org.cerebrum.domain.provider.Nurse;
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

public class NurseActionBase extends BaseAction<Nurse>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Nurse nurse;

	@DataModel
	private List<Nurse> nurseList;

	@Factory("nurseList")
	@Observer("archivedNurse")
	public void findRecords() {
		search();
	}

	public Nurse getEntity() {
		return nurse;
	}

	@Override
	public void setEntity(Nurse t) {
		this.nurse = t;
	}

	@Override
	public void setEntityList(List<Nurse> list) {
		this.nurseList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (nurse.getSpecialization() != null) {
			criteria = criteria.add(Restrictions.eq("specialization.id", nurse
					.getSpecialization().getId()));
		}

		if (nurse.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", nurse.getUser()
					.getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Nurse> getEntityList() {
		if (nurseList == null) {
			findRecords();
		}
		return nurseList;
	}

}
