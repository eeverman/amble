package org.yarnandtail.amble;

public interface BooleanCondition<S extends ProcessStatus<O>, O extends Outcome> {
	boolean apply(S status);
}
