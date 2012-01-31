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
@Table(name = "STATE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class State extends AbstractState {

	/** Serialization version UID. */
	private static final long serialVersionUID = -2428114531596883522L;

	/**
	 * Default no-argument constructor.
	 */
	public State() {
		
	}
}
