package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class PlacedOrder extends PlacedOrderBase {

	private static final Logger log = Logger.getLogger(PlacedOrder.class);

	public PlacedOrder placedOrderInstance() {
		return this;
	}
}
