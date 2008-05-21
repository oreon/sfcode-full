package com.oreon.kgauge.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SystemArchitecture {

	/**
     *  we're in the pet store application if we're within any
     *  of the pet store packages
     */
	@Pointcut("within(com.oreon.kgauge..*)")
        public void inPetStore() {}

	// modules
	// ===========

	@Pointcut("within(com.oreon.kgauge.dao..*)")
        public void inDataAccessLayer() {}

	@Pointcut("within(com.oreon.kgauge.domain.*)")
        public void inDomainModel() {}

	@Pointcut("within(com.oreon.kgauge.service..*)")
        public void inServiceLayer() {}

	@Pointcut("within(com.oreon.kgauge.web..*)")
        public void inWebLayer() {}

	/*
	@Pointcut("within(com.oreon.kgauge.remote..*)")
        public void inRemotingLayer() {}

	@Pointcut("within(com.oreon.kgauge.validation..*)")
        public void inValidationModule() {}
*/
	// module operations
	// ==================

	@Pointcut("execution(* com.oreon.kgauge.dao.*.*(..))")
        public void doaOperation() {}

	@Pointcut("execution(* com.oreon.kgauge.service.*.*(..))")
        public void businessService() {}
	
	
	//@Pointcut("execution(public * com.oreon.kgauge.validation.*.*(..))")
	//public void validation() {}

}