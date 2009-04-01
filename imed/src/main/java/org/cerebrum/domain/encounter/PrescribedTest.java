package org.cerebrum.domain.encounter;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "prescribedtest")
@Name("prescribedTest")
@Filter(name = "archiveFilterDef")
public class PrescribedTest extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "dxTest_id", nullable = false)
	protected org.cerebrum.domain.diagnostics.DxTest dxTest;

	protected String notes;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "encounter_id", nullable = false)
	protected Encounter encounter;

	public void setDxTest(org.cerebrum.domain.diagnostics.DxTest dxTest) {
		this.dxTest = dxTest;
	}

	public org.cerebrum.domain.diagnostics.DxTest getDxTest() {
		return dxTest;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	@Transient
	public String getDisplayName() {
		return dxTest + "";
	}

}
