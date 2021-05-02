package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.*;

public class StepToPath<S extends ProcessStatus<O>, O extends Outcome> extends AbstractStep<S, O> {
	final Path<S, O> pathIfTrue;
	final Path<S, O> pathIfFalse;
	final Path<S, O> pathIfException;

	public StepToPath(BooleanCondition<S, O> logic, Path<S, O> pathIfTrue, Path<S, O> pathIfFalse, Path<S, O> pathIfException) {
		super(logic);
		this.pathIfTrue = pathIfTrue;
		this.pathIfFalse = pathIfFalse;
		this.pathIfException = pathIfException;
	}

	public StepToPath(Action<S, O> action, Path<S, O> pathIfTrue, Path<S, O> pathIfFalse, Path<S, O> pathIfException) {
		super(action);
		this.pathIfTrue = pathIfTrue;
		this.pathIfFalse = pathIfFalse;
		this.pathIfException = pathIfException;
	}

	public Path<S, O> getPathIfTrue() {
		return pathIfTrue;
	}

	public Path<S, O> getPathIfFalse() {
		return pathIfFalse;
	}

	public Path<S, O> getPathIfException() {
		return pathIfException;
	}
}
