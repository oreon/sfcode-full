package prfl;



import org.aspectj.lang.JoinPoint.StaticPart;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

public class JamonProfilingStrategy implements ProfilingStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.caisi.persistence.base.prfl.Sd#start(org.aspectj.lang.JoinPoint.
	 * StaticPart)
	 */
	public Object start(StaticPart jpStaticPart) {
		
		return MonitorFactory.start(jpStaticPart.toShortString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.caisi.persistence.base.prfl.Sd#stop(java.lang.Object,
	 * org.aspectj.lang.JoinPoint.StaticPart)
	 */
	public void stop(Object token, StaticPart jpStaticPart) {
		if (token instanceof Monitor) {
			Monitor mon = (Monitor) token;
			mon.stop();
		}
	}
}
