package org.cerebrum.domain.users.action;

import org.cerebrum.domain.users.Role;
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
@Name("roleAction")
public class RoleAction extends BaseAction<Role>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Role role;

	@DataModel
	private List<Role> roleList;

	@Factory("roleList")
	public void findRecords() {
		roleList = entityManager.createQuery(
				"select role from Role role order by role.id desc")
				.getResultList();
	}

	public Role getEntity() {
		return role;
	}

	@Override
	public void setEntity(Role t) {
		this.role = t;
	}

	@Override
	public void setEntityList(List<Role> list) {
		this.roleList = list;
	}

}
