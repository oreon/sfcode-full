package com.shan.customermgt.domain.action;

import com.shan.customermgt.domain.Account;
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
@Name("accountAction")
public class AccountAction extends BaseAction<Account>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Account account;

	@DataModel
	private List<Account> accountList;

	@Factory("accountList")
	@Observer("archivedAccount")
	public void findRecords() {
		search();
	}

	public Account getEntity() {
		return account;
	}

	@Override
	public void setEntity(Account t) {
		this.account = t;
	}

	@Override
	public void setEntityList(List<Account> list) {
		this.accountList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (account.getCustomer() != null) {
			criteria = criteria.add(Restrictions.eq("customer.id", account
					.getCustomer().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<Account> getEntityList() {
		if (accountList == null) {
			findRecords();
		}
		return accountList;
	}

}
