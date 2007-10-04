package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

@Entity
public class OrderItem extends OrderItemBase {

	private static final Logger log = Logger.getLogger(OrderItem.class);

	public OrderItem orderItemInstance() {
		return this;
	}
}
