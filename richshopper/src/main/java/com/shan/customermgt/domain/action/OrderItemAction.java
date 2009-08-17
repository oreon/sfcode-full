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

import com.shan.customermgt.domain.OrderItem;

@Scope(ScopeType.CONVERSATION)
@Name("orderItemAction")
public class OrderItemAction extends BaseAction<OrderItem>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private OrderItem orderItem;

	@DataModel
	private List<OrderItem> orderItemList;

	@Factory("orderItemList")
	@Observer("archivedOrderItem")
	public void findRecords() {
		search();
	}

	public OrderItem getEntity() {
		return orderItem;
	}

	@Override
	public void setEntity(OrderItem t) {
		this.orderItem = t;
	}

	@Override
	public void setEntityList(List<OrderItem> list) {
		this.orderItemList = list;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	public void addAssoications(Criteria criteria) {

		if (orderItem.getProduct() != null) {
			criteria = criteria.add(Restrictions.eq("product.id", orderItem
					.getProduct().getId()));
		}

		if (orderItem.getCustomerOrder() != null) {
			criteria = criteria.add(Restrictions.eq("customerOrder.id",
					orderItem.getCustomerOrder().getId()));
		}

	}

	public void updateAssociations() {

	}

	public List<OrderItem> getEntityList() {
		if (orderItemList == null) {
			findRecords();
		}
		return orderItemList;
	}

}
