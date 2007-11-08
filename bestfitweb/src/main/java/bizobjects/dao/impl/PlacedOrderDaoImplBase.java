package bizobjects.dao.impl;

import bizobjects.PlacedOrder;
import bizobjects.dao.PlacedOrderDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class PlacedOrderDaoImplBase extends BaseDao<PlacedOrder>
		implements
			PlacedOrderDao {

	//// FINDERS ///// 

}
