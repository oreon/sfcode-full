package com.oreon.kgauge.service;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.witchcraft.model.support.errorhandling.BusinessException;

@Aspect
public class ServiceExceptionAspect {

	private static final Logger logger = Logger.getLogger(ServiceExceptionAspect.class);
	
	
	@Before("com.oreon.kgauge.service.SystemArchitecture.businessService()")
	public void sayHello() {
		System.out.println("AOP: Servicemethod called ");
	}

	
	@AfterThrowing(pointcut = "com.oreon.kgauge.service.SystemArchitecture.businessService()", throwing = "ex")
	public void reportException(Throwable ex) {
		if (!(ex instanceof BusinessException)) {
			logger.error("A serious service level error has occured ", ex);
			//write code for mailing the exception
		}
	}

}
