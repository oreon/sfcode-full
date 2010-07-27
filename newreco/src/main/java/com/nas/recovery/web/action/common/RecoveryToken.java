package com.nas.recovery.web.action.common;

import java.io.Serializable;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;

import com.nas.recovery.domain.legal.Lawyer;
import com.nas.recovery.domain.loan.LenderContact;
import com.nas.recovery.domain.propertymanagement.PropertyManager;
import com.nas.recovery.domain.realestate.MasterAgent;
import com.nas.recovery.domain.realestate.RealEstateProperty;

@Name("recoveryTokenSeam")
public class RecoveryToken implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6317183393106475053L;

	private RealEstateProperty realEstateProperty;

	private String lawyer;

	private String propertyManager;

	private String masterAgent;

	private String lenderContact;
	
	@In
	JbpmContext jbpmContext;
	

	
	
	@In 
	RecoveryToken recoveryToken;

	public RealEstateProperty getRealEstateProperty() {
		return realEstateProperty;
	}

	public void setRealEstateProperty(RealEstateProperty realEstateProperty) {
		this.realEstateProperty = realEstateProperty;
	}

	public String getLawyer() {
		return lawyer;
	}

	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}



	public String getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(String propertyManager) {
		this.propertyManager = propertyManager;
	}

	public String getMasterAgent() {
		return masterAgent;
	}

	public void setMasterAgent(String masterAgent) {
		this.masterAgent = masterAgent;
	}

	public String getLenderContact() {
		return lenderContact;
	}

	public void setLenderContact(String lenderContact) {
		this.lenderContact = lenderContact;
	}
	
	public RecoveryToken getCurrentToken(){
		ExecutionContext executionContext = ExecutionContext.currentExecutionContext(); 
		return ((RecoveryToken) executionContext.getVariable("recoveryToken"));
	}
	
	

	public String hasInsurer(){
		//jbpmContext.getProcessInstance
		
		return (getRealEstateProperty().getInsurer() != null ) ?"true":"false";
	}

}
