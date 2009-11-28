package com.shan.customermgt.domain.action;

import java.util.Arrays;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import com.shan.customermgt.domain.Account;

@Name("accountList")
@Scope(ScopeType.CONVERSATION)
public class AccountList extends EntityQuery<Account> {

	/**
     * 
     */
    private static final long serialVersionUID = -1969725967850006227L;

    private static final String EJBQL = "select account from Account account";

	private static final String[] RESTRICTIONS = {
		"account.customer.id == #{accountList.account.customer.id}",
		"account.accountType == #{accountList.account.accountType}"
	};

	private Account account = new Account();

	public AccountList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Account getAccount() {
		return account;
	}
}
