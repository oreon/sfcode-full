package bizobjects.service.impl;

import bizobjects.OrderItem;

import bizobjects.dao.OrderItemDao;

import bizobjects.service.OrderItemService;
import bizobjects.service.OrderItemService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao;

    public void setOrderItemDao(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    //// Delegate all crud operations to the Dao ////
    public OrderItem save(OrderItem orderItem) {
        return orderItemDao.save(orderItem);
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
