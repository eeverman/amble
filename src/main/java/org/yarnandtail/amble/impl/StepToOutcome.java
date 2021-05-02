package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.*;

public class StepToOutcome<S extends ProcessStatus<O>, O extends Outcome> extends AbstractStep<S, O> {

	final O outcomeIfTrue;
	final O outcomeIfFalse;
	final O outcomeIfException;

	public StepToOutcome(BooleanCondition<S, O> logic, O outcomeIfTrue, O outcomeIfFalse, O outcomeIfException) {
		super(logic);
		this.outcomeIfTrue = outcomeIfTrue;
		this.outcomeIfFalse = outcomeIfFalse;
		this.outcomeIfException = outcomeIfException;
	}

	public StepToOutcome(Action<S, O> action, O outcomeIfTrue, O outcomeIfFalse, O outcomeIfException) {
		super(action);
		this.outcomeIfTrue = outcomeIfTrue;
		this.outcomeIfFalse = outcomeIfFalse;
		this.outcomeIfException = outcomeIfException;
	}

	public O getOutcomeIfTrue() {
		return outcomeIfTrue;
	}

	public O getOutcomeIfFalse() {
		return outcomeIfFalse;
	}

	public O getOutcomeIfException() {
		return outcomeIfException;
	}
}
