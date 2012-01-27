package ${package}.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import ${package}.domain.BaseEntity;

/**
 * @author Kamalpreet Singh
 *
 */
@MappedSuperclass
public abstract class AbstractRole extends BaseEntity {

	/** Serialization version UID. */
	private static final long serialVersionUID = -3196483070323730735L;

	/** The primary key of the Role. */
	@Id
	@TableGenerator(name = "ROLE_ID_GENERATOR", table = "PRIMARY_KEY_GENERATOR", pkColumnName = "GENERATOR_KEY", 
			valueColumnName = "GENERATOR_VALUE", pkColumnValue = "ROLE_ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "ROLE_ID_GENERATOR")
	@Column(name = "ROLE_ID", nullable = false, updatable = false)
	private Long id;
	
	/** Flag that tells whether a Role is active or not. */
	@Column(name = "ACTIVE", length = 1, nullable = false)
	private boolean active;
	
	/** The name of the Role. */
    @Column(name = "NAME", length = 50)
	private String name;
    
    /** The display name of the Role. */
    @Column(name = "DISPLAY_NAME", length = 50)
	private String displayName;

	/** The users of this Role. */
    @ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<User>();
	
	/**
	 * Default no-argument constructor.
	 */
	public AbstractRole() {
		
	}
}
