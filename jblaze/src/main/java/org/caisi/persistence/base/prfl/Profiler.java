package prfl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Profiler {

  private ProfilingStrategy profiler ;//= new NoProfilingStrategy();

  public void setProfilingStrategy(ProfilingStrategy p) {
    this.profiler = p;
  }

  @Pointcut("within( eu.alenislimited.kesoxprocessing.support.services.**) && !within(ProfilingStrategy+)")
  public void profiledOperation(){}
  
  @Around("Profiler.profiledOperation()")
  public Object doProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
    Object token = profiler.start(joinPoint.getStaticPart());
    Object ret = joinPoint.proceed();
    profiler.stop(token, joinPoint.getStaticPart());
    return ret;
  }
}


