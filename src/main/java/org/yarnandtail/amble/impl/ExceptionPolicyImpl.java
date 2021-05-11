package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.*;

import java.io.IOException;

public class ExceptionPolicyImpl<S extends ProcessStatus<O>, O extends Outcome> implements ExceptionPolicy<S, O> {

	private final ExceptionPolicyBoundary<S, O> boundary;
	private final Class<? extends Throwable> exception;
	private final ExceptionAction logicPolicy;
	private final ExceptionAction actionPolicy;

	public ExceptionPolicyImpl(ExceptionPolicyBoundary<S, O> boundary, Class<? extends Throwable> exception,
							   ExceptionAction logicPolicy, ExceptionAction actionPolicy) {
		this.boundary = boundary;
		this.exception = exception;
		this.logicPolicy = logicPolicy;
		this.actionPolicy = actionPolicy;
	}

	@Override
	public Class<? extends Throwable> getException() {
		return exception;
	}

	@Override
	public ExceptionAction getLogicPolicy() {
		return logicPolicy;
	}

	@Override
	public ExceptionAction getActionPolicy() {
		return actionPolicy;
	}

	@Override
	public void close() throws IOException {

	}
}
