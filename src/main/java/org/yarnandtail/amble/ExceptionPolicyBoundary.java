package org.yarnandtail.amble;

public interface ExceptionPolicyBoundary<S extends ProcessStatus<O>, O extends Outcome> {
	void begin(ExceptionPolicy<S, O> policy);
	void end(ExceptionPolicy<S, O> policy);
}
