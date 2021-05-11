package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.*;

public class ExceptionPolicyBoundaryImpl<S extends ProcessStatus<O>, O extends Outcome> implements ExceptionPolicyBoundary<S, O> {

	private final StepBuilder stepBuilder;

	public ExceptionPolicyBoundaryImpl(StepBuilder stepBuilder) {
		this.stepBuilder = stepBuilder;
	}

	@Override
	public void begin(ExceptionPolicy<S, O> policy) {

	}

	@Override
	public void end(ExceptionPolicy<S, O> policy) {

	}
}
