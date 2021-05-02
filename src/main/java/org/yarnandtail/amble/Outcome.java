package org.yarnandtail.amble;

public interface Outcome {

	String name();

	default boolean isOk() {
		return name().equals("OK") || name().endsWith("_OK");
	}

	default boolean isSuccessStop() {
		return name().equals("DONE") || name().endsWith("_DONE");
	}

	default boolean isErrorStop() {
		return name().equals("ERROR") || name().endsWith("_ERR");
	}
}
