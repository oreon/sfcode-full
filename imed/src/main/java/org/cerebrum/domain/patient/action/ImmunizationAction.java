package org.cerebrum.domain.patient.action;

import org.cerebrum.domain.patient.Immunization;
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
@Name("immunizationAction")
public class ImmunizationAction extends BaseAction<Immunization>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Immunization immunization;

	@DataModel
	private List<Immunization> immunizationList;

	@Factory("immunizationList")
	public void findRecords() {
		immunizationList = entityManager
				.createQuery(
						"select immunization from Immunization immunization order by immunization.id desc")
				.getResultList();
	}

	public Immunization getEntity() {
		return immunization;
	}

	@Override
	public void setEntity(Immunization t) {
		this.immunization = t;
	}

	@Override
	public void setEntityList(List<Immunization> list) {
		this.immunizationList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (immunization.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", immunization
					.getPatient().getId()));
		}

		if (immunization.getImmunizationType() != null) {
			criteria = criteria.add(Restrictions.eq("immunizationType.id",
					immunization.getImmunizationType().getId()));
		}

	}

}
