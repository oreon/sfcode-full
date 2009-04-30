package org.caisi.persistence.base.prfl;

import org.aspectj.lang.JoinPoint.StaticPart;

public interface ProfilingStrategy {
	public abstract Object start(StaticPart jpStaticPart);

	public abstract void stop(Object token, StaticPart jpStaticPart);
}
