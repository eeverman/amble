package org.yarnandtail.amble.impl;

import org.yarnandtail.amble.*;

public abstract class AbstractStep<S extends ProcessStatus<O>, O extends Outcome> implements Step<S, O> {
	final BooleanCondition<S, O> logic;
	final Action<S, O> action;

	protected AbstractStep(BooleanCondition<S, O> logic) {
		this.logic = logic;
		action = null;
	}

	protected AbstractStep(Action<S, O> action) {
		logic = null;
		this.action = action;
	}

	public BooleanCondition<S, O> getLogic() {
		return logic;
	}

	public Action<S, O> getAction() {
		return action;
	}

	/**
	 * Always the opposite of isAction().
	 * @return
	 */
	public boolean isLogic() {
		return logic != null;
	}

	/**
	 * Always the opposite of isLogic().
	 * @return
	 */
	public boolean isAction() {
		return action != null;
	}
}
