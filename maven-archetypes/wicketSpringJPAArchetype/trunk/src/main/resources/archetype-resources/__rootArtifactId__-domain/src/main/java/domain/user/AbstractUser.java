package ${package}.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import ${package}.domain.BaseEntity;
import ${package}.domain.role.Role;

/**
 * @author Kamalpreet Singh
 *
 */
@MappedSuperclass
public abstract class AbstractUser extends BaseEntity {

	/** Serialization version UID. */
	private static final long serialVersionUID = -8513466153576550861L;

	/** The primary key of the User. */
	@Id
	@TableGenerator(name = "USER_ID_GENERATOR", table = "PRIMARY_KEY_GENERATOR", pkColumnName = "GENERATOR_KEY", 
			valueColumnName = "GENERATOR_VALUE", pkColumnValue = "USER_ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "USER_ID_GENERATOR")
	@Column(name = "USER_ID", nullable = false, updatable = false)
	private Long id;

	/** Flag that tells whether a User is active or not. */
	@Column(name = "ACTIVE", length = 1, nullable = false)
	private boolean active;
	
	/** The code of the User. */
	@Column(name = "CODE", length = 50, nullable = false, unique = true)
	private String code;

	/** The first name of the User. */
    @Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	
	/** The middle name of the User. */
    @Column(name = "MIDDLE_NAME", length = 50)
	private String middleName;

	/** The last name of the User. */
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	/** The user name of the User. */
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	private String userName;

	/** The email of the User. */
    @Column(name = "EMAIL",  length = 100, nullable = false, unique = true)
	private String email;
    
    /** The password of the User. */
    @Column(name = "PASSWORD", length = 500, nullable = false)
	private String password;

	/** The roles of the User. */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "USER_ROLE_RELATION", 
		joinColumns = @JoinColumn(name = "USER_ID"),  
		inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles = new HashSet<Role>();
    
    /**
	 * Default no-argument constructor.
	 */
	public AbstractUser() {
		
	}
}
