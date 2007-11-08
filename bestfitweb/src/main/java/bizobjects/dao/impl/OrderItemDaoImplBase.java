package bizobjects.dao.impl;

import bizobjects.OrderItem;
import bizobjects.dao.OrderItemDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class OrderItemDaoImplBase extends BaseDao<OrderItem>
		implements
			OrderItemDao {

	//// FINDERS ///// 

}
