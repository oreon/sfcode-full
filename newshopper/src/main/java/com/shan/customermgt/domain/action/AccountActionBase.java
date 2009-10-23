package com.shan.customermgt.domain.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.shan.customermgt.domain.Account;

public class AccountActionBase extends BaseAction<Account>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Account account;

	@DataModel
	private List<Account> accountList22;
	
	@RequestParameter("customerId")
	Integer customerId;

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
		this.accountList22 = list;
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
		if (accountList22 == null) {
			findRecords();
		}
		return accountList22;
	}

}
