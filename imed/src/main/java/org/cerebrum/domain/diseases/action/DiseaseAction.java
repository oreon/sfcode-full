package org.cerebrum.domain.diseases.action;

import org.cerebrum.domain.diseases.Disease;
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
@Name("diseaseAction")
public class DiseaseAction extends BaseAction<Disease>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Disease disease;

	@DataModel
	private List<Disease> diseaseList;

	@Factory("diseaseList")
	public void findRecords() {
		diseaseList = entityManager.createQuery(
				"select disease from Disease disease order by disease.id desc")
				.getResultList();
	}

	public Disease getEntity() {
		return disease;
	}

	@Override
	public void setEntity(Disease t) {
		this.disease = t;
	}

	@Override
	public void setEntityList(List<Disease> list) {
		this.diseaseList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (disease.getCategory() != null) {
			criteria = criteria.add(Restrictions.eq("category.id", disease
					.getCategory().getId()));
		}

	}

}
