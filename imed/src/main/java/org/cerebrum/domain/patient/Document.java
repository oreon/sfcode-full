package org.cerebrum.domain.patient;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "document")
@Name("document")
@Filter(name = "archiveFilterDef")
public class Document extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	protected byte[] file;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	protected Patient patient;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getFile() {
		return file;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return patient;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
