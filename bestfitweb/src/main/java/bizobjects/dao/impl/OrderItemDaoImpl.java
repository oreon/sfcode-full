package bizobjects.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class OrderItemDaoImpl extends OrderItemDaoImplBase {

	private static final Logger log = Logger.getLogger(OrderItemDaoImpl.class);

	public OrderItemDaoImpl orderItemDaoImplInstance() {
		return this;
	}
}
