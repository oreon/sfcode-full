package ${package}.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "ROLE")
public class Role extends AbstractRole {

	/** Serialization version UID. */
	private static final long serialVersionUID = 8838777217710536728L;

	/**
	 * Default no-argument constructor.
	 */
	public Role() {
		
	}
}
