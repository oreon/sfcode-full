package bizobjects.dao;

import bizobjects.PlacedOrder;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public interface PlacedOrderDao {
    public PlacedOrder save(PlacedOrder placedOrder);

    public void delete(PlacedOrder placedOrder);

    public PlacedOrder load(Long id);

    public List<PlacedOrder> loadAll();

    public List<PlacedOrder> searchByExample(PlacedOrder placedOrder);
}
