package com.pcas.datapkg.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class ItemDto {

	private String drugName;

	private Integer delta;

	private MachineDto machineDto;

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDelta(Integer delta) {
		this.delta = delta;
	}

	public Integer getDelta() {
		return delta;
	}

	public void setMachineDto(MachineDto machineDto) {
		this.machineDto = machineDto;
	}

	public MachineDto getMachineDto() {
		return machineDto;
	}

}
