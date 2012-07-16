/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/SearchForm.e.vm.java
 */
package com.company.demo.web.domain;

import static com.company.demo.domain.Account_.*;
import static com.company.demo.repository.support.Ranges.RangeDate.newRangeDate;
import static com.google.common.collect.Lists.newArrayList;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import com.company.demo.domain.Account;
import com.company.demo.repository.AccountRepository;
import com.company.demo.repository.support.Range;
import com.company.demo.repository.support.Ranges.RangeDate;
import com.company.demo.web.domain.support.GenericSearchForm;

/**
 * SearchForm for {@link Account}
 */
@Component
@Scope("session")
public class AccountSearchForm extends GenericSearchForm<Account> implements Serializable {
    private static final long serialVersionUID = 1L;
    // make it static to avoid http://jira.springframework.org/browse/SWF-1224
    private static AccountRepository accountRepository;

    private Account account = new Account();
    private RangeDate<Account> birthDateRange = newRangeDate(birthDate);

    @Autowired
    public AccountSearchForm(AccountRepository instance) {
        if (accountRepository == null) {
            accountRepository = instance;
        }
    }

    /**
     * Server side pagination with lazy model.
     * Automatically called by PrimeFaces component (via the GenericSearchForm).
     */
    @Override
    protected List<Account> load(PageRequest pageRequest) {
        List<Range<Account, ?>> ranges = newArrayList();
        ranges.add(birthDateRange);

        Page<Account> page = accountRepository.findWithExample(account, ranges, pageRequest);
        setRowCount((int) page.getTotalElements());
        return page.getContent();
    }

    /**
     * The root account for search by example.
     * Used from the view.
     */
    public Account getAccount() {
        return account;
    }

    // Ranges, used from the view.

    public RangeDate<Account> getBirthDateRange() {
        return birthDateRange;
    }
}