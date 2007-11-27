package org.witchcraft.generator;
/**
 * @author jsingh
 *
 */
public class GeneratorEngineException extends RuntimeException {
	
	public GeneratorEngineException(String message){
		super(message);
	}
	
	public GeneratorEngineException(String message, Throwable t){
		super(message, t);
	}

}