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
}
