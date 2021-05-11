package org.yarnandtail.amble;

import java.util.Collection;
import java.util.List;

public interface Path<S extends ProcessStatus<O>, O extends Outcome> extends Step<S, O> {
	void addStep(Step<S, O> condition);
	void addSteps(Collection<Step<S, O>> steps);

	List<Step<S, O>> getSteps();
/*
	Options for exceptions:
	* Consider an exception an exception, thus do what ever Outcome is configured for an exception
	* Consider an exception 'false' (typ a non-pointer) and just follow the false path/outcome
	* A particular exception has a particular outcome, may be overridden at the step level.
 */



	void run(S processStatus);
}
