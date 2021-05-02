package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.Outcome;
import org.yarnandtail.amble.Path;
import org.yarnandtail.amble.ProcessStatus;
import org.yarnandtail.amble.Step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PathImpl<S extends ProcessStatus<O>, O extends Outcome> implements Path<S, O> {

	ArrayList<Step<S, O>> steps = new ArrayList();

	@Override
	public void addStep(Step<S, O> step) {
		steps.add(step);
	}

	@Override
	public List<Step<S, O>> getSteps() {
		return steps;
	}

	@Override
	public void addSteps(Collection<Step<S, O>> addTheseSteps) {
		steps.addAll(addTheseSteps);
	}

	@Override
	public void run(S processStatus) {
		for (Step<S, O> step : steps) {

			if (!processStatus.getOutcome().isOk()) break;

			if (step instanceof StepToOutcome) {
				StepToOutcome<S, O> sto = (StepToOutcome) step;

				try {

					if (sto.isLogic()) {
						boolean result = sto.getLogic().apply(processStatus);

						if (result) {
							assignOutcomeIfNotNull(processStatus, sto.getOutcomeIfTrue());
						} else {
							assignOutcomeIfNotNull(processStatus, sto.getOutcomeIfFalse());
						}
					} else {
						sto.getAction().apply(processStatus);
					}

				} catch (Exception e) {
					assignOutcomeIfNotNull(processStatus, sto.getOutcomeIfException());
				}
			} else if (step instanceof StepToPath) {
				StepToPath<S, O> stp = (StepToPath) step;

				try {

					if (stp.isLogic()) {
						boolean result = stp.getLogic().apply(processStatus);

						if (result) {
							runPathIfNotNull(processStatus, stp.getPathIfTrue());
						} else {
							runPathIfNotNull(processStatus, stp.getPathIfFalse());
						}
					} else {
						stp.getAction().apply(processStatus);
					}

				} catch (Exception e) {
					runPathIfNotNull(processStatus, stp.getPathIfException());
				}
			}
		}
	}

	protected void assignOutcomeIfNotNull(S processStatus, O outcome) {
		if (outcome != null) {
			processStatus.setOutcome(outcome);
		}
	}

	protected void runPathIfNotNull(S processStatus, Path<S, O> path) {
		if (path != null) {
			path.run(processStatus);
		}
	}
}
