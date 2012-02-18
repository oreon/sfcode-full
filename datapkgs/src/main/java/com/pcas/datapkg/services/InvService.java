package com.pcas.datapkg.services;

import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.exceptions.BusinessException;

import com.pcas.datapkg.domain.inventory.DrugInventory;
import com.pcas.datapkg.domain.inventory.Machine;
import com.pcas.datapkg.web.action.inventory.MachineAction;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Name("invService")
public class InvService extends InvServiceBase {
	
	@In(create=true)
	MachineAction machineAction;

	@Override
	protected DeltaStockDto doGetStockDelta(long customerId, Date date) {
		
		DeltaStockDto deltaStockDto = new DeltaStockDto();
		MachineDto machineDto1 = new MachineDto();
		machineDto1.setName("DummyMachine");
		
		ItemDto itemDto = new ItemDto();
		itemDto.setDrugName("Dicloxacillin");
		itemDto.setDelta(12);
		
		ItemDto metformin = new ItemDto();
		metformin.setDrugName("Metformin");
		metformin.setDelta(15);
		
		
		machineDto1.getItemDtos().add(itemDto);
		machineDto1.getItemDtos().add(metformin);
		
		deltaStockDto.getMachineDtos().add(machineDto1);
		
		
		// TODO Auto-generated method stub
		return deltaStockDto;
	}
	
	@Override
	public DeltaStockDto getInventory(long customerId) {
		List<Machine> machines = machineAction.executeQuery("Select m from Machine m where m.customer.id = ?1", customerId ) ;
		DeltaStockDto deltaStockDto = new DeltaStockDto();
		
		for (Machine machine : machines) {
			MachineDto machineDto = new MachineDto();
			machineDto.setName(machine.getName());
			
			List<DrugInventory> drugInventories = machine.getListDrugInventorys();
			
			for (DrugInventory drugInventory : drugInventories) {
				ItemDto itemDto = new ItemDto();
				itemDto.setDelta(drugInventory.getQty());
				itemDto.setDrugName( drugInventory.getDrugAbstract().getName());
				machineDto.getItemDtos().add(itemDto);
			}
			
			deltaStockDto.getMachineDtos().add(machineDto);
		}
		return deltaStockDto;
	}
	
	@Override
	public GenericReportDto runReportById(String reportName) {
		
		XStream xstream = new XStream(new DomDriver());

		GenericReportDto dto = new GenericReportDto();
		 
		if("items".equals(reportName)){
			List<Machine> machines = machineAction.executeQuery("Select m  from DrugInventory m "); //, Location l where m.machine.id = ");
			dto.setData(xstream.toXML(machines));
		}else if ("machine".equals(reportName)){
			List<Machine> machines = machineAction.executeQuery("Select m from Machine m");
			dto.setData(xstream.toXML(machines));
		}else 
			throw new BusinessException("Unknown report");
		
		return dto;
	}
	
	
	@Override
	protected GenericReportDto doGetCustomers() {
		
		XStream xstream = new XStream(new DomDriver());

		GenericReportDto dto = new GenericReportDto();
		 
		
			List<Machine> machines = machineAction.executeQuery("Select m  from Customer m "); //, Location l where m.machine.id = ");
			dto.setData(xstream.toXML(machines));
		
		
		return dto;
		
		
	
	}

	@Override
	protected DeltaStockDto doGetInventory(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericReportDto doRunReportById(String reportName) {
		// TODO Auto-generated method stub
		return null;
	}

}
