package org.cerebrum.domain.provider.action;

import org.cerebrum.domain.provider.Physician;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;

@Scope(ScopeType.CONVERSATION)
@Name("physicianAction")
public class PhysicianAction extends BaseAction<Physician>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Physician physician;

	@DataModel
	private List<Physician> physicianList;

	@Factory("physicianList")
	public void findRecords() {
		physicianList = entityManager
				.createQuery(
						"select physician from Physician physician order by physician.id desc")
				.getResultList();
	}

	public Physician getEntity() {
		return physician;
	}

	@Override
	public void setEntity(Physician t) {
		this.physician = t;
	}

	@Override
	public void setEntityList(List<Physician> list) {
		this.physicianList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (physician.getSpecialization() != null) {
			criteria = criteria.add(Restrictions.eq("specialization.id",
					physician.getSpecialization().getId()));
		}

		if (physician.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", physician
					.getUser().getId()));
		}

	}

}
