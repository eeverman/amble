package org.yarnandtail.amble;

public interface StepStatus<S extends ProcessStatus<O>, O extends Outcome> {
	ProcessStatus<O> getStatus();

	O getFalseOutcome();

	O getTrueOutcome();
}
