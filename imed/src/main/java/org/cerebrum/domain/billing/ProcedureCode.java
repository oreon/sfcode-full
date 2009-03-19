package org.cerebrum.domain.billing;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "procedurecode")
@Name("procedureCode")
public class ProcedureCode extends BusinessEntity {

	protected Double price;

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	@Transient
	public String getDisplayName() {
		return price + "";
	}

}
