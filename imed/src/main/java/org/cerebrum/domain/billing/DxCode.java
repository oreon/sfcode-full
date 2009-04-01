package org.cerebrum.domain.billing;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "dxcode")
@Name("dxCode")
@Filter(name = "archiveFilterDef")
public class DxCode extends BusinessEntity {

	@NotNull
	@Length(min = 2, max = 50)
	protected String code;

	protected String description;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Transient
	public String getDisplayName() {
		return code + "";
	}

}
