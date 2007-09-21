package bizobjects.service.impl;

import bizobjects.PlacedOrder;

import bizobjects.dao.PlacedOrderDao;

import bizobjects.service.PlacedOrderService;
import bizobjects.service.PlacedOrderService;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class PlacedOrderServiceImpl implements PlacedOrderService {
    private PlacedOrderDao placedOrderDao;

    public void setPlacedOrderDao(PlacedOrderDao placedOrderDao) {
        this.placedOrderDao = placedOrderDao;
    }

    //// Delegate all crud operations to the Dao ////
    public PlacedOrder save(PlacedOrder placedOrder) {
        return placedOrderDao.save(placedOrder);
    }

    public void delete(PlacedOrder placedOrder) {
        placedOrderDao.delete(placedOrder);
    }

    public PlacedOrder load(Long id) {
        return placedOrderDao.load(id);
    }

    public List<PlacedOrder> loadAll() {
        return placedOrderDao.loadAll();
    }

    public List<PlacedOrder> searchByExample(PlacedOrder placedOrder) {
        return placedOrderDao.searchByExample(placedOrder);
    }

    /*
    public List query(String queryString, Object... params) {
            return basicDAO.query(queryString, params);
    }*/
}
