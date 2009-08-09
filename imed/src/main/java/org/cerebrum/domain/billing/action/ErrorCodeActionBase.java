package org.cerebrum.domain.billing.action;

import org.cerebrum.domain.billing.ErrorCode;
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

public class ErrorCodeActionBase extends BaseAction<ErrorCode>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private ErrorCode errorCode;

	@DataModel
	private List<ErrorCode> errorCodeList;

	@Factory("errorCodeList")
	@Observer("archivedErrorCode")
	public void findRecords() {
		search();
	}

	public ErrorCode getEntity() {
		return errorCode;
	}

	@Override
	public void setEntity(ErrorCode t) {
		this.errorCode = t;
	}

	@Override
	public void setEntityList(List<ErrorCode> list) {
		this.errorCodeList = list;
	}

	public void updateAssociations() {

	}

	public List<ErrorCode> getEntityList() {
		if (errorCodeList == null) {
			findRecords();
		}
		return errorCodeList;
	}

}
