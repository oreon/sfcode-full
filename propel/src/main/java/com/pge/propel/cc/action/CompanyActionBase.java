package com.pge.propel.cc.action;

import com.pge.propel.cc.Company;

import org.witchcraft.seam.action.BaseAction;

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
import org.jboss.seam.annotations.Observer;

import com.pge.propel.cc.solution;

public class CompanyActionBase extends BaseAction<Company>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Company company;

	@DataModel
	private List<Company> companyList;

	@Factory("companyList")
	@Observer("archivedCompany")
	public void findRecords() {
		search();
	}

	public Company getEntity() {
		return company;
	}

	@Override
	public void setEntity(Company t) {
		this.company = t;
	}

	@Override
	public void setEntityList(List<Company> list) {
		this.companyList = list;
	}

	public void updateAssociations() {

	}

	private List<solution> listSolutions;

	void initListSolutions() {
		listSolutions = new ArrayList<solution>();
		if (company.getSolutions().isEmpty()) {

		} else
			listSolutions.addAll(company.getSolutions());
	}

	public List<solution> getListSolutions() {
		if (listSolutions == null) {
			initListSolutions();
		}
		return listSolutions;
	}

	public void setListSolutions(List<solution> listSolutions) {
		this.listSolutions = listSolutions;
	}

	public void deleteSolutions(solution solutions) {
		listSolutions.remove(solutions);
	}

	@Begin(join = true)
	public void addSolutions() {
		solution solutions = new solution();

		solutions.setCompany(company);

		listSolutions.add(solutions);
	}

	public void updateComposedAssociations() {

		company.getSolutions().clear();
		company.getSolutions().addAll(listSolutions);

	}

	public List<Company> getEntityList() {
		if (companyList == null) {
			findRecords();
		}
		return companyList;
	}

}
