package org.witchcraft.dbc;

import org.witchcraft.exceptions.ContractViolationException;

public class AssertContractCondition {

	public static void enforce(boolean condition, String message){
		if(!condition){
			throw new ContractViolationException(message);
		}
	}
	
	public static void enforce(boolean condition){
		if(!condition){
			throw new ContractViolationException("Method contract not fullfilled");
		}
	}
}
