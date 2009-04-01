package org.cerebrum.domain.diagnostics;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "dxtest")
@Name("dxTest")
@Filter(name = "archiveFilterDef")
public class DxTest extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
