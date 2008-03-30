
/**
 * This is generated code - to edit code or override methods use - Candidate class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import org.witchcraft.model.jsf.Image;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

@MappedSuperclass
public abstract class CandidateBase extends Person
		implements
			java.io.Serializable,
			org.witchcraft.model.support.audit.Auditable {

	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public CandidateBase() {
	}

	private com.oreon.kgauge.domain.User user = new com.oreon.kgauge.domain.User();

	public void setUser(com.oreon.kgauge.domain.User user) {
		this.user = user;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public com.oreon.kgauge.domain.User getUser() {
		return this.user;
	}

	public abstract Candidate candidateInstance();

}
