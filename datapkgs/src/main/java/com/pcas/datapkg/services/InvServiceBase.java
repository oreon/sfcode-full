package com.pcas.datapkg.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;
import javax.ws.rs.core.Response;

import org.jboss.seam.annotations.security.Restrict;
import org.witchcraft.exceptions.ContractViolationException;

import org.apache.commons.lang.StringUtils;

/** 
 * 
 */
public abstract class InvServiceBase
		extends
			org.witchcraft.seam.action.BaseWebAction {

	/** 
	 * Returns the stock delta , If date is null returns for the current date
	  @param customerId  long   
	 *	  @param date  Date   
	 *	  
	 *
	 * @return DeltaStockDto 
	 */
	@Restrict("#{s:hasPermission('InvService','getStockDelta')}")
	public com.pcas.datapkg.services.DeltaStockDto getStockDelta(
			long customerId, Date date) {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetStockDelta(customerId, date);
	}

	/** 
	 * [Returns the stock delta , If date is null returns for the current date]
	 */

	protected abstract com.pcas.datapkg.services.DeltaStockDto doGetStockDelta(
			long customerId, Date date);

	/** 
	 * 
	  @param customerId  long   
	 *	  
	 *
	 * @return DeltaStockDto 
	 */
	@Restrict("#{s:hasPermission('InvService','getInventory')}")
	public com.pcas.datapkg.services.DeltaStockDto getInventory(long customerId) {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetInventory(customerId);
	}

	/** 
	 * []
	 */

	protected abstract com.pcas.datapkg.services.DeltaStockDto doGetInventory(
			long customerId);

	/** 
	 * run a report by its name , if no such name is found NoReportFound fault is returned.
	  @param reportName  String   
	 *	  
	 *
	 * @return GenericReportDto 
	 */
	@Restrict("#{s:hasPermission('InvService','runReportById')}")
	public com.pcas.datapkg.services.GenericReportDto runReportById(
			String reportName) {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doRunReportById(reportName);
	}

	/** 
	 * [run a report by its name , if no such name is found NoReportFound fault is returned.]
	 */

	protected abstract com.pcas.datapkg.services.GenericReportDto doRunReportById(
			String reportName);

	/** 
	 * 
	  
	 *
	 * @return GenericReportDto 
	 */
	@Restrict("#{s:hasPermission('InvService','getCustomers')}")
	public com.pcas.datapkg.services.GenericReportDto getCustomers() {

		StringBuilder exceptionMessages = new StringBuilder();

		if (!StringUtils.isEmpty(exceptionMessages.toString())) {
			throw new ContractViolationException(exceptionMessages.toString());
		}

		return doGetCustomers();
	}

	/** 
	 * []
	 */

	protected abstract com.pcas.datapkg.services.GenericReportDto doGetCustomers();

}
