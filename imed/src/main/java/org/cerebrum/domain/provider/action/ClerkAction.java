package org.cerebrum.domain.provider.action;

import org.cerebrum.domain.provider.Clerk;
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
@Name("clerkAction")
public class ClerkAction extends BaseAction<Clerk>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Clerk clerk;

	@DataModel
	private List<Clerk> clerkList;

	@Factory("clerkList")
	public void findRecords() {
		clerkList = entityManager.createQuery(
				"select clerk from Clerk clerk order by clerk.id desc")
				.getResultList();
	}

	public Clerk getEntity() {
		return clerk;
	}

	@Override
	public void setEntity(Clerk t) {
		this.clerk = t;
	}

	@Override
	public void setEntityList(List<Clerk> list) {
		this.clerkList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (clerk.getUser() != null) {
			criteria = criteria.add(Restrictions.eq("user.id", clerk.getUser()
					.getId()));
		}

	}

}
