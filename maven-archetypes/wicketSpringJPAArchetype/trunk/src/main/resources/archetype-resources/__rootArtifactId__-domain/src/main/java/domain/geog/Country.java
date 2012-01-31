package ${package}.domain.geog;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "COUNTRY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country extends AbstractCountry {

	/** Serialization version UID. */
	private static final long serialVersionUID = 2784605279302998958L;

	/**
	 * Default no-argument constructor.
	 */
	public Country() {
		
	}
}
