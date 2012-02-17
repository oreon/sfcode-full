package com.pcas.datapkg.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class DeltaStockDto {

	private java.util.List<com.pcas.datapkg.services.MachineDto> machineDtos = new ArrayList<com.pcas.datapkg.services.MachineDto>();

	public void setMachineDtos(List<MachineDto> machineDtos) {
		this.machineDtos = machineDtos;
	}

	public List<MachineDto> getMachineDtos() {
		return machineDtos;
	}

}
