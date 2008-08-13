package tpack;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerAspect {

	Logger log = Logger.getLogger(LoggerAspect.class);
	
	@Pointcut("within( tpack.**)")
	public void callingMethodUsingPC(){}
		
	@Before("tpack.LoggerAspect.callingMethodUsingPC()")
	public void logMethod(JoinPoint joinPoint){
		Object args[] = joinPoint.getArgs();
		StringBuffer buffer = new StringBuffer();
		int cnt = 0;
		for (Object object : args) {
			cnt++;
			buffer.append(object == null ?"null":object.toString() + (cnt < args.length?";":"") );
		}
		System.out.println("Executing " + joinPoint.getSignature().toLongString() + "  with '" + buffer + "'");
	}	
	
	/*
	@AfterReturning(value="tpack.LoggerAspect.callingMethodUsingPC()", returning = "retArg")
	public void logMethodReturn(Object retArg){
		System.out.println(  "  returned " + retArg + "'");
	}	
	
	
	@AfterThrowing(value="tpack.LoggerAspect.callingMethodUsingPC()", throwing = "thrownException")
	public void logException(Throwable thrownException){
		System.out.println(  "  threw " + thrownException.getMessage());
	}*/	
	
	
}
