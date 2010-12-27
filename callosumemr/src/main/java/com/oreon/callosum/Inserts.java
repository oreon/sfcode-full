package com.oreon.callosum;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.witchcraft.base.entity.BusinessEntity;


@Entity
@Table(name="drugsorg")
public class Inserts extends BusinessEntity{
	
	private String contraIndication;
	private String patientInfo;

	public String getContraIndication() {
		return contraIndication;
	}

	public void setContraIndication(String contraIndication) {
		this.contraIndication = contraIndication;
	}

	public String getPatientInfo() {
		return patientInfo;
	}

	public void setPatientInfo(String patientInfo) {
		this.patientInfo = patientInfo;
	}
}