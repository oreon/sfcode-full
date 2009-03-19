package org.cerebrum.domain.billing.action;

import org.cerebrum.domain.billing.Service;
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
@Name("serviceAction")
public class ServiceAction extends BaseAction<Service>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Service service;

	@DataModel
	private List<Service> serviceList;

	@Factory("serviceList")
	public void findRecords() {
		serviceList = entityManager.createQuery(
				"select service from Service service order by service.id desc")
				.getResultList();
	}

	public Service getEntity() {
		return service;
	}

	@Override
	public void setEntity(Service t) {
		this.service = t;
	}

	@Override
	public void setEntityList(List<Service> list) {
		this.serviceList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (service.getClaim() != null) {
			criteria = criteria.add(Restrictions.eq("claim.id", service
					.getClaim().getId()));
		}

		if (service.getDxCode() != null) {
			criteria = criteria.add(Restrictions.eq("dxCode.id", service
					.getDxCode().getId()));
		}

		if (service.getProcedureCode() != null) {
			criteria = criteria.add(Restrictions.eq("procedureCode.id", service
					.getProcedureCode().getId()));
		}

	}

}
