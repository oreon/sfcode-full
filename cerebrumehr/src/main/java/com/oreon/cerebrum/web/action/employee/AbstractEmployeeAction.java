package com.oreon.cerebrum.web.action.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.UserUtilAction;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.users.AppRole;
import com.oreon.cerebrum.web.action.users.AppRoleAction;

public abstract class AbstractEmployeeAction<T extends com.oreon.cerebrum.employee.Employee>
		extends
			BaseAction<T> implements java.io.Serializable {
	
	
	@In(create = true)
	AppRoleAction appRoleAction;
	
	@In(create = true)
	UserUtilAction userUtilAction;
	
	abstract String getDefaultRoleName();

	
	//add role and employee number 
	@Override
	public void preSave() {
		if(!isNew())
			return;
		AppRole role = appRoleAction.findByUnqName(getDefaultRoleName());
		getInstance().getAppUser().addAppRole(role);
		//getInstance().setEmployeeNumber(createEmployeeNumber(getInstance()));
	}

	@Override
	//add current user's facility to the newly created employee
	protected T createInstance() {
		T result = super.createInstance();
		result.setFacility(userUtilAction.getCurrentFacility());
		
		return result;
	}

	private String createEmployeeNumber(T result) {
		// TODO Auto-generated method stub
		return getDefaultRoleName().substring(0,1) +  result.getFacility().getId() + "-" + new Date().getTime();
	}
	
	

}
