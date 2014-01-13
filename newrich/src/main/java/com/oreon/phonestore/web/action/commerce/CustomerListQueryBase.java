package com.oreon.phonestore.web.action.commerce;

import java.util.List;

import javax.faces.model.DataModel;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.BaseQuery;

import com.oreon.phonestore.domain.commerce.Customer;
import com.oreon.phonestore.web.action.datamodel.EntityLazyDataModel;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class CustomerListQueryBase extends BaseQuery<Customer, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4361808984422458154L;

	private static final String EJBQL = "select customer from Customer customer";

	protected Customer customer = new Customer();

	@In(create = true)
	CustomerAction customerAction;

//	@In(create = true)
//	private CustomerListLazyDataModel dataModel;
	
	/**
	 * The static class acts as the bridge between backing bean and lazyDataModel. 
	 * The alternative approach than dependency injection.
	 * 
	 * @author Shadeven
	 *
	 */
	private static final class CustomerListLazyDataModel extends EntityLazyDataModel<Customer> {
		private CustomerListLazyDataModel(EntityManager entityManager) {
			super(entityManager, Customer.class, EJBQL);
		}

		/* (non-Javadoc)
		 * @see com.oreon.phonestore.web.action.datamodel.EntityLazyDataModel#getId(java.lang.Object)
		 */
		@Override
		protected Object getId(Customer t) {
			return t.getId();
		}
	}
	
	public CustomerListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DataModel<Customer> getDataModel() {
		return new CustomerListLazyDataModel(entityManager);
	}
	
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public Customer getInstance() {
		return getCustomer();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	//@Restrict("#{s:hasPermission('customer', 'view')}")
	public List<Customer> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"customer.id = #{customerList.customer.id}",

			"customer.archived = #{customerList.customer.archived}",

			"lower(customer.firstName) like concat(lower(#{customerList.customer.firstName}),'%')",

			"lower(customer.lastName) like concat(lower(#{customerList.customer.lastName}),'%')",

			"lower(customer.contactDetails.phone) like concat(lower(#{customerList.customer.contactDetails.phone}),'%')",

			"lower(customer.contactDetails.secondaryPhone) like concat(lower(#{customerList.customer.contactDetails.secondaryPhone}),'%')",

			"lower(customer.contactDetails.city) like concat(lower(#{customerList.customer.contactDetails.city}),'%')",

			"customer.type = #{customerList.customer.type}",

			"customer.dateCreated <= #{customerList.dateCreatedRange.end}",
			"customer.dateCreated >= #{customerList.dateCreatedRange.begin}",};

	@Observer("archivedCustomer")
	public void onArchive() {
		refresh();
	}

	//@Restrict("#{s:hasPermission('customer', 'delete')}")
	public void archiveById(Long id) {
		customerAction.archiveById(id);
		refresh();
	}

	/** create comma delimited row 
	 * @param builder
	 */
	//@Override
	public void createCsvString(StringBuilder builder, Customer e) {

		builder.append("\"" + (e.getType() != null ? e.getType() : "") + "\",");

		builder.append("\r\n");
	}

	/** create the headings 
	 * @param builder
	 */
	//@Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Type" + ",");

		builder.append("\r\n");
	}
}
