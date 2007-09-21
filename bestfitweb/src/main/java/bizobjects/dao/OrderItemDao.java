package bizobjects.dao;

import bizobjects.OrderItem;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public interface OrderItemDao {
    public OrderItem save(OrderItem orderItem);

    public void delete(OrderItem orderItem);

    public OrderItem load(Long id);

    public List<OrderItem> loadAll();

    public List<OrderItem> searchByExample(OrderItem orderItem);
}
