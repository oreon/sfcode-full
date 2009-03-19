package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "symptom")
@Name("symptom")
public class Symptom extends BusinessEntity {

	@OneToMany(mappedBy = "symptom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Symptom_ID", nullable = true)
	private Set<Cause> causes = new HashSet<Cause>();

	public void setCauses(Set<Cause> causes) {
		this.causes = causes;
	}

	public Set<Cause> getCauses() {
		return causes;
	}

	@Transient
	public String getDisplayName() {
		return causes + "";
	}

}
