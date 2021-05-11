package org.yarnandtail.amble;

import java.io.Closeable;

public interface ExceptionPolicy<S extends ProcessStatus<O>, O extends Outcome> extends Closeable, Step<S, O> {
	Class<? extends Throwable> getException();
	ExceptionAction getLogicPolicy();
	ExceptionAction getActionPolicy();
}
