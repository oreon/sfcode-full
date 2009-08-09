package org.cerebrum.domain.demographics.action;

import org.cerebrum.domain.demographics.Country;
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

public class CountryActionBase extends BaseAction<Country>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Country country;

	@DataModel
	private List<Country> countryList;

	@Factory("countryList")
	@Observer("archivedCountry")
	public void findRecords() {
		search();
	}

	public Country getEntity() {
		return country;
	}

	@Override
	public void setEntity(Country t) {
		this.country = t;
	}

	@Override
	public void setEntityList(List<Country> list) {
		this.countryList = list;
	}

	public void updateAssociations() {

	}

	public List<Country> getEntityList() {
		if (countryList == null) {
			findRecords();
		}
		return countryList;
	}

}
