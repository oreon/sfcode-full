package com.shan.customermgt.domain.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.witchcraft.seam.action.BaseAction;

import com.shan.customermgt.domain.Account;

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
