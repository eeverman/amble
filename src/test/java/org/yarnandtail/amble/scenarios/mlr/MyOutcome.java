package org.yarnandtail.amble.scenarios.mlr;

import org.yarnandtail.amble.Outcome;

public enum MyOutcome implements Outcome {
	OK,
	ERROR,
	MESSAGE_INVALID,
	UPSTREAM_TIMESTAMP_INVALID,
	MESSAGE_VERSION_INVALID,
	OLD_TIMESTAMP_INVALID,
	SAME_TIMESTAMP_INVALID,
	CREATION_WOULD_OVERWRITE_EXISTING_ERR,
	//These are good outcome - how to say stop??
	CREATION_DONE,
	MODIFICATION_DONE,
	MODIFICATION_WAS_CREATION_DONE;
}
