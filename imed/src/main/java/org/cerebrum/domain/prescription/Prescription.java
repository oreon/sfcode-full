package org.cerebrum.domain.prescription;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "prescription")
@Name("prescription")
public class Prescription extends BusinessEntity {

	@OneToMany(mappedBy = "prescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "Prescription_ID", nullable = false)
	private Set<Item> items = new HashSet<Item>();

	protected Date startDate;

	@Lob
	protected String notes;

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	@Transient
	public String getDisplayName() {
		return items + "";
	}

}
