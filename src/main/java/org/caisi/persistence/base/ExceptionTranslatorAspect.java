package org.caisi.persistence.base;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.caisi.persistence.base.exceptions.BusinessException;
import org.springframework.security.AccessDeniedException;

/**
 * This aspect would translate known exceptions such as AccessDeniedExcpetions
 * into known exceptions
 * 
 * @author jsingh
 * 
 */
@Aspect
public class ExceptionTranslatorAspect {
	Logger log = Logger.getLogger(ExceptionTranslatorAspect.class);

	//@Pointcut("within( org.caisi.persistence.base.** )")
	@Pointcut("within(org.caisi..*)")
	public void exceptionTranslatorPC() {
	}

	
	@Around("ExceptionTranslatorAspect.exceptionTranslatorPC()")
	public Object translateEx(ProceedingJoinPoint pjp) throws Throwable {
		Object retVal = null;
		try {
			//System.out.println("pjp :" + pjp.getTarget().toString());
			retVal = pjp.proceed();
		} catch (AccessDeniedException ex) {
			throw new BusinessException(
					"You do not have previliges for this action", ex);
		}
		return retVal;
	}

	
	//@AfterThrowing(pointcut = "ExceptionTranslatorAspect.exceptionTranslatorPC()", throwing = "ex")
	public void unknownException(Exception ex) {
		//System.out.println("throwing ade" + ex.getMessage());
		
	}

}
