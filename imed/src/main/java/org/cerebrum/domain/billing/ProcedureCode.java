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
@Table(name = "procedurecode")
@Name("procedureCode")
@Filter(name = "archiveFilterDef")
public class ProcedureCode extends BusinessEntity {

	protected Double price;

	@NotNull
	@Length(min = 2, max = 50)
	protected String code;

	@Lob
	protected String description;

	protected Boolean referringPhysRequired;

	protected Boolean dxCodeRequired;

	protected Boolean hospitalizaionRequired;

	protected Boolean adminDateRequired;

	protected Boolean IORequired;

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

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

	public void setReferringPhysRequired(Boolean referringPhysRequired) {
		this.referringPhysRequired = referringPhysRequired;
	}

	public Boolean getReferringPhysRequired() {
		return referringPhysRequired;
	}

	public void setDxCodeRequired(Boolean dxCodeRequired) {
		this.dxCodeRequired = dxCodeRequired;
	}

	public Boolean getDxCodeRequired() {
		return dxCodeRequired;
	}

	public void setHospitalizaionRequired(Boolean hospitalizaionRequired) {
		this.hospitalizaionRequired = hospitalizaionRequired;
	}

	public Boolean getHospitalizaionRequired() {
		return hospitalizaionRequired;
	}

	public void setAdminDateRequired(Boolean adminDateRequired) {
		this.adminDateRequired = adminDateRequired;
	}

	public Boolean getAdminDateRequired() {
		return adminDateRequired;
	}

	public void setIORequired(Boolean IORequired) {
		this.IORequired = IORequired;
	}

	public Boolean getIORequired() {
		return IORequired;
	}

	@Transient
	public String getDisplayName() {
		return price + "";
	}

}
