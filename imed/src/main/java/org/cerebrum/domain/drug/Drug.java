package org.cerebrum.domain.drug;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "drugs")
@Name("drug")
@Filter(name = "archiveFilterDef")
public class Drug extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	@Column(name = "drugname", unique = false)
	protected String name;

	protected String dosage;

	protected String form;

	protected String activeIngred;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDosage() {
		return dosage;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getForm() {
		return form;
	}

	public void setActiveIngred(String activeIngred) {
		this.activeIngred = activeIngred;
	}

	public String getActiveIngred() {
		return activeIngred;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
