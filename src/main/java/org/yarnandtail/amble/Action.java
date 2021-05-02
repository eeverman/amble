package org.yarnandtail.amble;

public interface Action<S extends ProcessStatus<O>, O extends Outcome> {
	void apply(S status);
}
