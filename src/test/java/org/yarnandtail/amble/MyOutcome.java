package org.yarnandtail.amble;

public enum MyOutcome implements Outcome {
	OK(true),
	ERROR(false),
	DOMAIN_IS_NULL_ERR(false),
	HAPPY_RESULT_DONE(true);

	private boolean okContinue;

	private MyOutcome(boolean okContinue) {
		this.okContinue = okContinue;
	}

}
