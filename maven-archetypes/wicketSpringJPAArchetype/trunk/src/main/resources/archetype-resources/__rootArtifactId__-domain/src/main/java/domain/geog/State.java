package ${package}.domain.geog;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "STATE")
public class State extends AbstractState {

	/** Serialization version UID. */
	private static final long serialVersionUID = -2428114531596883522L;

	/**
	 * Default no-argument constructor.
	 */
	public State() {
		
	}
}
