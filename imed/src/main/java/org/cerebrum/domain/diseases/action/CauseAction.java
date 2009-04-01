package org.cerebrum.domain.diseases.action;

import org.cerebrum.domain.diseases.Cause;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
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
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("causeAction")
public class CauseAction extends BaseAction<Cause>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Cause cause;

	@DataModel
	private List<Cause> causeList;

	@Factory("causeList")
	@Observer("archivedCause")
	public void findRecords() {
		search();
	}

	public Cause getEntity() {
		return cause;
	}

	@Override
	public void setEntity(Cause t) {
		this.cause = t;
	}

	@Override
	public void setEntityList(List<Cause> list) {
		this.causeList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (cause.getSymptom() != null) {
			criteria = criteria.add(Restrictions.eq("symptom.id", cause
					.getSymptom().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Cause> getEntityList() {
		if (causeList == null) {
			findRecords();
		}
		return causeList;
	}

}
