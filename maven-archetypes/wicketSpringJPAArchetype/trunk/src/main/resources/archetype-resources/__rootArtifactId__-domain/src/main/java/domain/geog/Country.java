package ${package}.domain.geog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import ${package}.domain.BaseEntity;

/**
 * @author Kamalpreet Singh
 *
 */
@Entity
@Table(name = "COUNTRY")
public class Country extends BaseEntity {

	/** Serialization version UID. */
	private static final long serialVersionUID = 8695800485009228471L;
	
	/** The primary key of the Country. */
	@Id
	@TableGenerator(name = "COUNTRY_ID_GENERATOR", table = "PRIMARY_KEY_GENERATOR", pkColumnName = "GENERATOR_KEY", 
			valueColumnName = "GENERATOR_VALUE", pkColumnValue = "COUNTRY_ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "COUNTRY_ID_GENERATOR")
	@Column(name = "COUNTRY_ID", nullable = false, updatable = false)
	private Long id;

	/** Flag that tells whether a Country is active or not. */
	@Column(name = "ACTIVE", length = 1, nullable = false)
	private boolean active;
	
	/** The code of the Country. */
	@Column(name = "CODE", length = 50, nullable = false, unique = true)
	private String code;
	
	/** The name of the Country. */
	@Column(name = "NAME", length = 100, nullable = false)
	private String name;
	
	/**
	 * Default no-argument constructor.
	 */
	public Country() {
		
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
