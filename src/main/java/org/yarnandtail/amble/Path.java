package org.yarnandtail.amble;

import java.util.Collection;
import java.util.List;

public interface Path<S extends ProcessStatus<O>, O extends Outcome> extends Step<S, O> {
	void addStep(Step<S, O> condition);
	void addSteps(Collection<Step<S, O>> steps);

	List<Step<S, O>> getSteps();

	void run(S processStatus);
}
