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
@Table(name = "service")
@Name("service")
@Filter(name = "archiveFilterDef")
public class Service extends BusinessEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_id", nullable = false)
	protected Claim claim;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "dxCode_id", nullable = false)
	protected DxCode dxCode;

	protected Integer units;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "procedureCode_id", nullable = false)
	protected ProcedureCode procedureCode;

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setDxCode(DxCode dxCode) {
		this.dxCode = dxCode;
	}

	public DxCode getDxCode() {
		return dxCode;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getUnits() {
		return units;
	}

	public void setProcedureCode(ProcedureCode procedureCode) {
		this.procedureCode = procedureCode;
	}

	public ProcedureCode getProcedureCode() {
		return procedureCode;
	}

	@Transient
	public String getDisplayName() {
		return claim + "";
	}

}
