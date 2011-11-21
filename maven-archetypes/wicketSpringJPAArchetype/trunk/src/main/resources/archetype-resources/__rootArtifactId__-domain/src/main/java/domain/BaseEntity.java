package ${package}.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Abstract base entity class for all domain objects/entities.
 *
 * @author Kamalpreet Singh
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/** Serialization version UID. */
	private static final long serialVersionUID = 1831965901933648203L;

	/** Hibernate version. */
	@Version
    @Column(name = "VERSION")
    private Long version;

	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
}
