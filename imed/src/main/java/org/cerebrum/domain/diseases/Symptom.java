package org.cerebrum.domain.diseases;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "symptom")
@Name("symptom")
@Filter(name = "archiveFilterDef")
public class Symptom extends BusinessEntity {

	//causes->symptom ->Symptom->Cause->Cause

	@OneToMany(mappedBy = "symptom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Symptom_ID", nullable = true)
	private Set<Cause> causes = new HashSet<Cause>();

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	protected String descripton;

	public void setCauses(Set<Cause> causes) {
		this.causes = causes;
	}

	public Set<Cause> getCauses() {
		return causes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public String getDescripton() {
		return descripton;
	}

	@Transient
	public String getDisplayName() {
		return causes + "";
	}

}
