package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.ExceptionPolicy;
import org.yarnandtail.amble.Outcome;
import org.yarnandtail.amble.ProcessStatus;
import org.yarnandtail.amble.Step;

/**
 * A pseudo step representing the end boundary of a policy step.
 *
 * A policy is added to the list of Steps as a special step.  When that policy ends in the flow of Steps, this type of
 * step is inserted to mark which policy is ended.
 *
 * @param <S>
 * @param <O>
 */
public class PolicyEndStep<S extends ProcessStatus<O>, O extends Outcome> implements Step<S, O> {

	private final ExceptionPolicy<S, O> policyStep;

	public PolicyEndStep(ExceptionPolicy<S, O> policyStep) {
		this.policyStep = policyStep;
	}

	protected ExceptionPolicy<S, O> getPolicyStep() {
		return policyStep;
	}
}
