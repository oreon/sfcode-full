package bizobjects.dao.impl;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@org.springframework.stereotype.Repository
public class PlacedOrderDaoImpl extends PlacedOrderDaoImplBase {

	private static final Logger log = Logger
			.getLogger(PlacedOrderDaoImpl.class);

	public PlacedOrderDaoImpl placedOrderDaoImplInstance() {
		return this;
	}
}
