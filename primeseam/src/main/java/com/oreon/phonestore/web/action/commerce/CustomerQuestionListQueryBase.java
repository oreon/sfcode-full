package com.oreon.phonestore.web.action.commerce;

import com.oreon.phonestore.domain.commerce.CustomerQuestion;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.phonestore.domain.commerce.CustomerQuestion;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerQuestionListQueryBase
		extends
			BaseQuery<CustomerQuestion, Long> {

	private static final String EJBQL = "select customerQuestion from CustomerQuestion customerQuestion";

	protected CustomerQuestion customerQuestion = new CustomerQuestion();

	@In(create = true)
	CustomerQuestionAction customerQuestionAction;

	public CustomerQuestionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public CustomerQuestion getCustomerQuestion() {
		return customerQuestion;
	}

	@Override
	public CustomerQuestion getInstance() {
		return getCustomerQuestion();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('customerQuestion', 'view')}")
	public List<CustomerQuestion> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<CustomerQuestion> getEntityClass() {
		return CustomerQuestion.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customerQuestion.id = #{customerQuestionList.customerQuestion.id}",

			"customerQuestion.archived = #{customerQuestionList.customerQuestion.archived}",

			"lower(customerQuestion.text) like concat(lower(#{customerQuestionList.customerQuestion.text}),'%')",

			"customerQuestion.customer.id = #{customerQuestionList.customerQuestion.customer.id}",

			"customerQuestion.dateCreated <= #{customerQuestionList.dateCreatedRange.end}",
			"customerQuestion.dateCreated >= #{customerQuestionList.dateCreatedRange.begin}",};

	public LazyDataModel<CustomerQuestion> getCustomerQuestionsByCustomer(
			final com.oreon.phonestore.domain.commerce.Customer customer) {

		EntityLazyDataModel<CustomerQuestion, Long> customerQuestionLazyDataModel = new EntityLazyDataModel<CustomerQuestion, Long>(
				this) {

			@Override
			public List<CustomerQuestion> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				customerQuestion.setCustomer(customer);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return customerQuestionLazyDataModel;

	}

	@Observer("archivedCustomerQuestion")
	public void onArchive() {
		refresh();
	}

	public void setCustomerId(Long id) {
		if (customerQuestion.getCustomer() == null) {
			customerQuestion
					.setCustomer(new com.oreon.phonestore.domain.commerce.Customer());
		}
		customerQuestion.getCustomer().setId(id);
	}

	public Long getCustomerId() {
		return customerQuestion.getCustomer() == null ? null : customerQuestion
				.getCustomer().getId();
	}

	//@Restrict("#{s:hasPermission('customerQuestion', 'delete')}")
	public void archiveById(Long id) {
		customerQuestionAction.archiveById(id);
		refresh();
	}

}
