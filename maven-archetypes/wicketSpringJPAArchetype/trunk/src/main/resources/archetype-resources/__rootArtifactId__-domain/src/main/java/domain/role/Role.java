package ${package}.domain.role;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "ROLE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends AbstractRole {

	/** Serialization version UID. */
	private static final long serialVersionUID = 8838777217710536728L;

	/**
	 * Default no-argument constructor.
	 */
	public Role() {
		
	}
}
