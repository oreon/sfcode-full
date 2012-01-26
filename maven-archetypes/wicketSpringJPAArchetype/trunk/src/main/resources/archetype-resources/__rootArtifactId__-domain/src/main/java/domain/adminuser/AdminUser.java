package ${package}.domain.adminuser;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "ADMIN_USER")
public class AdminUser extends AbstractAdminUser {

	/** Serialization version UID. */
	private static final long serialVersionUID = -5793548369623842848L;

	/**
	 * Default no-argument constructor.
	 */
	public AdminUser() {
		
	}
}
