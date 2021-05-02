package org.yarnandtail.amble;

public interface ProcessStatus<O extends Outcome> {
	void setOutcome(O outcome);

	O getOutcome();
}
