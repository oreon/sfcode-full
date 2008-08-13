package tpack;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PreConditionValidatorAspect {

	Logger log = Logger.getLogger(LoggerAspect.class);

	@Pointcut("within( tpack.**)")
	public void callingMethodUsingPC() {
	}
	
	@Before("tpack.PreConditionValidatorAspect.callingMethodUsingPC()")
	public void logMethod(JoinPoint joinPoint){
		Object args[] = joinPoint.getArgs();
		StringBuffer buffer = new StringBuffer();
		
		for (Object object : args) {
			if(object == null){
				//throw new RuntimeException("arg " + "is null");
			}
			//buffer.append(object == null ?"null":object.toString() + (cnt < args.length?";":"") );
		}
		System.out.println("Executing " + joinPoint.getSignature().toLongString() + "  with '" + buffer + "'");
		

	}	

}
