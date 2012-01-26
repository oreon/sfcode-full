package ${package}.domain.adminuser;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import ${package}.domain.BaseEntity;

/**
 * @author Kamalpreet Singh
 *
 */
@MappedSuperclass
public abstract class AbstractAdminUser extends BaseEntity {

	/** Serialization version UID. */
	private static final long serialVersionUID = -8513466153576550861L;

	/** The primary key of the AdminUser. */
	@Id
	@TableGenerator(name = "ADMIN_USER_ID_GENERATOR", table = "PRIMARY_KEY_GENERATOR", pkColumnName = "GENERATOR_KEY", 
			valueColumnName = "GENERATOR_VALUE", pkColumnValue = "ADMIN_USER_ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "ADMIN_USER_ID_GENERATOR")
	@Column(name = "ADMIN_USER_ID", nullable = false, updatable = false)
	private Long id;

	/** Flag that tells whether a AdminUser is active or not. */
	@Column(name = "ACTIVE", length = 1, nullable = false)
	private boolean active;
	
	/** The code of the AdminUser. */
	@Column(name = "CODE", length = 50, nullable = false, unique = true)
	private String code;

	/** The first name of the AdminUser. */
    @Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	
	/** The last name of the AdminUser. */
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	/** The email of the AdminUser. */
    @Column(name = "EMAIL",  length = 100, nullable = false, unique = true)
	private String email;
    
    /** The password of the AdminUser. */
    @Column(name = "PASSWORD", length = 500, nullable = false)
	private String password;
    
    /**
	 * Default no-argument constructor.
	 */
	public AbstractAdminUser() {
		
	}
}
