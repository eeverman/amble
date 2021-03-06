package org.yarnandtail.amble;

import org.yarnandtail.amble.impl.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StepBuilder<S extends ProcessStatus<O>, O extends Outcome> {


	//PathImpl<S, O> path = new PathImpl();

	ArrayDeque<Step<S, O>> steps = new ArrayDeque();

	public StepBuilder<S, O> ifFalse(BooleanCondition<S, O> cond, O outcomeIfFalse) {
		StepToOutcome<S, O> step = new StepToOutcome(cond, null, outcomeIfFalse, null);
		steps.addLast(step);
		return this;
	}

	public StepBuilder<S, O> ifFalse(BooleanCondition<S, O> cond, O outcomeIfFalse, O outcomeIfException) {
		StepToOutcome<S, O> step = new StepToOutcome(cond, null, outcomeIfFalse, outcomeIfException);
		steps.addLast(step);
		return this;
	}

	public StepBuilder<S, O> ifTrue(BooleanCondition<S, O> cond, O outcomeIfTrue) {
		StepToOutcome<S, O> step = new StepToOutcome(cond, outcomeIfTrue, null, null);
		steps.addLast(step);
		return this;
	}

	public StepBuilder<S, O> ifTrue(BooleanCondition<S, O> cond, O outcomeIfTrue, O outcomeIfException) {
		StepToOutcome<S, O> step = new StepToOutcome(cond, outcomeIfTrue, null, outcomeIfException);
		steps.addLast(step);
		return this;
	}

	public StepBuilder<S, O> ifTrue(BooleanCondition<S, O> cond, Step<S, O>... thenDo) {

		//The sub-steps were already added to our internal list - remove
		for (Step<S, O> c : thenDo) {
			steps.removeLastOccurrence(c);
		}

		PathImpl<S, O> subPath = new PathImpl();
		subPath.addSteps(List.of(thenDo));

		StepToPath<S, O> step = new StepToPath(cond, subPath, null, null);
		steps.addLast(step);

		return this;
	}

	public StepBuilder<S, O> ifTrue(BooleanCondition<S, O> cond, StepBuilder<S, O>... thenDo) {

		//The sub-steps were already added to our internal list - remove
		List<Step<S, O>> mySteps = new ArrayList<>();
		for (int i = 0; i < thenDo.length; i++) {
			mySteps.add(steps.removeLast());
		}

		Collections.reverse(mySteps);

		PathImpl<S, O> subPath = new PathImpl();
		subPath.addSteps(mySteps);

		StepToPath<S, O> step = new StepToPath(cond, subPath, null, null);
		steps.addLast(step);

		return this;
	}

	public StepBuilder<S, O> ifException(Action<S, O> action, O outcome) {
		StepToOutcome<S, O> step = new StepToOutcome(action, null, null, outcome);
		steps.addLast(step);
		return this;
	}

	public S run(S processStatus) {
		PathImpl<S, O> path = build();
		path.run(processStatus);
		return processStatus;
	}

	public PathImpl<S, O> build() {
		PathImpl<S, O> path = new PathImpl();
		path.addSteps(steps);
		return path;
	}

	public ExceptionPolicy<S, O> treatAsFalse(Class<? extends Throwable> throwable, O outcome) {
		ExceptionPolicyBoundaryImpl<S, O> callback = new ExceptionPolicyBoundaryImpl(this);

		ExceptionPolicy<S, O> policy = new ExceptionPolicyImpl<S, O>(
				callback, throwable, ExceptionAction.TREAT_AS_FALSE, ExceptionAction.NO_SPEC);

		return policy;
	}

	public static class ExceptionPolicyBoundaryImpl<S extends ProcessStatus<O>, O extends Outcome> implements ExceptionPolicyBoundary<S, O> {

		private final StepBuilder stepBuilder;

		public ExceptionPolicyBoundaryImpl(StepBuilder stepBuilder) {
			this.stepBuilder = stepBuilder;
		}

		@Override
		public void begin(ExceptionPolicy<S, O> policy) {
			stepBuilder.begin(policy);
		}

		@Override
		public void end(ExceptionPolicy<S, O> policy) {
			stepBuilder.end(policy);
		}
	}


	private void begin(ExceptionPolicy<S, O> policy) {
		steps.add(policy);
	}

	private void end(ExceptionPolicy<S, O> policy) {
		PolicyEndStep<S, O> pes = new PolicyEndStep(policy);
		steps.add(pes);
	}
}
