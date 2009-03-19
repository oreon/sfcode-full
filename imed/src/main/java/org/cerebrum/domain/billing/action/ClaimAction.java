package org.cerebrum.domain.billing.action;

import org.cerebrum.domain.billing.Claim;
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

import org.cerebrum.domain.billing.Service;

@Scope(ScopeType.CONVERSATION)
@Name("claimAction")
public class ClaimAction extends BaseAction<Claim>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Claim claim;

	@DataModel
	private List<Claim> claimList;

	@Factory("claimList")
	public void findRecords() {
		claimList = entityManager.createQuery(
				"select claim from Claim claim order by claim.id desc")
				.getResultList();
	}

	public Claim getEntity() {
		return claim;
	}

	@Override
	public void setEntity(Claim t) {
		this.claim = t;
	}

	@Override
	public void setEntityList(List<Claim> list) {
		this.claimList = list;
	}

	private List<Service> listServices;

	void initListServices() {
		listServices = new ArrayList<Service>();
		if (claim.getServices().isEmpty()) {

			addServices();

		} else
			listServices.addAll(claim.getServices());
	}

	public List<Service> getListServices() {
		if (listServices == null) {
			initListServices();
		}
		return listServices;
	}

	public void setListServices(List<Service> listServices) {
		this.listServices = listServices;
	}

	public void deleteServices(Service services) {
		listServices.remove(services);
	}

	@Begin(join = true)
	public void addServices() {
		Service services = new Service();

		services.setClaim(claim);

		listServices.add(services);
	}

	public void updateComposedAssociations() {

		claim.getServices().clear();
		claim.getServices().addAll(listServices);

	}

}
