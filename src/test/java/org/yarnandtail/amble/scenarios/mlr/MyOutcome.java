package org.yarnandtail.amble.scenarios.mlr;

import org.yarnandtail.amble.Outcome;

public enum MyOutcome implements Outcome {
	CONTINUE(true),
	NOT_CONTINUE(false),
	MESSAGE_UNREADABLE(false),
	UPSTREAM_TIMESTAMP_UNREADABLE(false),
	UNSUPPORTED_MESSAGE_VERSION(false),
	OLD_TIMESTAMP(false),
	SAME_TIMESTAMP(false),
	CREATION_WOULD_OVERWRITE_EXISTING(false),
	//These are good outcome - how to say stop??
	CREATION(false),
	MODIFICATION(false),
	MODIFICATION_WAS_CREATION(false),
	HAPPY_RESULT(true),
	DOMAIN_IS_NULL(false);

	private boolean okContinue;

	private MyOutcome(boolean okContinue) {
		this.okContinue = okContinue;
	}

	@Override
	public boolean isOk() {
		return okContinue;
	}
}
