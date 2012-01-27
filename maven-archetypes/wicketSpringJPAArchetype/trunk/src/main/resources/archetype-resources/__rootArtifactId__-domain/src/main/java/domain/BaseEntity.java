package ${package}.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	
	/** The entity create date. */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_CREATED")
    private Date dateCreated;
	
	/** The entity update date. */
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_UPDATED")
    private Date dateUpdated;

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

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * The entity create date.
	 */
	@PrePersist
	public void setDateCreated() {
		this.dateCreated = new Date();
	}

	/**
	 * @return the dateUpdated
	 */
	public Date getDateUpdated() {
		return dateUpdated;
	}

	/**
	 * The entity update date.
	 */
	@PreUpdate
	public void setDateUpdated() {
		this.dateUpdated = new Date();
	}
}
