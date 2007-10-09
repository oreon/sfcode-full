package bizobjects;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.audit.Auditable;

import org.apache.log4j.Logger;

@Entity
public class Product extends ProductBase implements Auditable {

	private static final Logger log = Logger.getLogger(Product.class);

	public Product productInstance() {
		return this;
	}
}
