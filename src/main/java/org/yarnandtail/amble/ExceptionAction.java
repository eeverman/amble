package org.yarnandtail.amble;

public enum ExceptionAction {
	TREAT_AS_FALSE,
	TREAT_AS_CHECKED,
	TREAT_AS_UNCHECKED,
	NO_SPEC;	//Fall thru to other rules
}
