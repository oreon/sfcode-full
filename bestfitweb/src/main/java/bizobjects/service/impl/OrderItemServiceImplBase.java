package bizobjects.service.impl;

import bizobjects.OrderItem;
import bizobjects.service.OrderItemService;
import bizobjects.dao.OrderItemDao;
import java.util.List;
import bizobjects.service.OrderItemService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.errorhandling.BusinessException;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class OrderItemServiceImplBase implements OrderItemService {

	private static final Logger log = Logger
			.getLogger(OrderItemServiceImplBase.class);

	private OrderItemDao orderItemDao;

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	//// Delegate all crud operations to the Dao ////

	public OrderItem save(OrderItem orderItem) {

		orderItemDao.save(orderItem);

		return orderItem;
	}

	public void delete(OrderItem orderItem) {
		orderItemDao.delete(orderItem);
	}

	public OrderItem load(Long id) {
		return orderItemDao.load(id);
	}

	public List<OrderItem> loadAll() {
		return orderItemDao.loadAll();
	}

	public List<OrderItem> searchByExample(OrderItem orderItem) {
		return orderItemDao.searchByExample(orderItem);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
