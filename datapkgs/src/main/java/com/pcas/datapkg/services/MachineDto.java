package com.pcas.datapkg.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

@javax.xml.bind.annotation.XmlRootElement
public class MachineDto {

	private java.util.List<com.pcas.datapkg.services.ItemDto> itemDtos = new ArrayList<com.pcas.datapkg.services.ItemDto>();

	private DeltaStockDto deltaStockDto;

	private String name;

	public void setItemDtos(List<ItemDto> itemDtos) {
		this.itemDtos = itemDtos;
	}

	public List<ItemDto> getItemDtos() {
		return itemDtos;
	}

	public void setDeltaStockDto(DeltaStockDto deltaStockDto) {
		this.deltaStockDto = deltaStockDto;
	}

	public DeltaStockDto getDeltaStockDto() {
		return deltaStockDto;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
