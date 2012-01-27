package ${package}.domain.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "USER")
public class User extends AbstractUser {

	/** Serialization version UID. */
	private static final long serialVersionUID = -5793548369623842848L;

	/**
	 * Default no-argument constructor.
	 */
	public User() {
		
	}
}
