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
@Table(name = "STATE")
public class State extends BaseEntity {

	/** Serialization version UID. */
	private static final long serialVersionUID = 5709144840825704016L;
	
	/** The primary key of the State. */
	@Id
	@TableGenerator(name = "STATE_ID_GENERATOR", table = "PRIMARY_KEY_GENERATOR", pkColumnName = "GENERATOR_KEY", 
			valueColumnName = "GENERATOR_VALUE", pkColumnValue = "STATE_ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.TABLE, generator = "STATE_ID_GENERATOR")
	@Column(name = "STATE_ID", nullable = false, updatable = false)
	private Long id;
	
	/**
	 * Default no-argument constructor.
	 */
	public State() {
		
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
