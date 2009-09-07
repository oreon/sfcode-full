package org.cerebrum.drugimporter;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(name = "uadrugs")
@Name("uadrug")
@Filter(name = "archiveFilterDef")
public class UADrug extends BusinessEntity{
	
	private String description;
	private String ATCCodes;
	private String brandNames;
	private String drugType;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	public String getATCCodes() {
		return ATCCodes;
	}
	public void setATCCodes(String codes) {
		ATCCodes = codes;
	}
	public String getBrandNames() {
		return brandNames;
	}
	public void setBrandNames(String brandNames) {
		this.brandNames = brandNames;
	}
}
