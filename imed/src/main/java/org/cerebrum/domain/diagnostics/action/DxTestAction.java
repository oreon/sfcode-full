package org.cerebrum.domain.diagnostics.action;

import org.cerebrum.domain.diagnostics.DxTest;
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
@Name("dxTestAction")
public class DxTestAction extends BaseAction<DxTest>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private DxTest dxTest;

	@DataModel
	private List<DxTest> dxTestList;

	@Factory("dxTestList")
	@Observer("archivedDxTest")
	public void findRecords() {
		search();
	}

	public DxTest getEntity() {
		return dxTest;
	}

	@Override
	public void setEntity(DxTest t) {
		this.dxTest = t;
	}

	@Override
	public void setEntityList(List<DxTest> list) {
		this.dxTestList = list;
	}

	public void updateAssociations() {

	}

	public List<DxTest> getEntityList() {
		if (dxTestList == null) {
			findRecords();
		}
		return dxTestList;
	}

}
