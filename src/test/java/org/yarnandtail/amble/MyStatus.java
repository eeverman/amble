package org.yarnandtail.amble;

public class MyStatus implements ProcessStatus<MyOutcome> {

	private MyOutcome outcome;
	private boolean one;
	private boolean two;
	private Object domainObj;

	@Override
	public void setOutcome(MyOutcome outcome) {
		this.outcome = outcome;
	}

	@Override
	public MyOutcome getOutcome() {
		return outcome;
	}

	public boolean isOne() {
		return one;
	}

	public void setOne(boolean one) {
		this.one = one;
	}

	public boolean isTwo() {
		return two;
	}

	public void setTwo(boolean two) {
		this.two = two;
	}

	public Object getDomainObj() {
		return domainObj;
	}

	public void setDomainObj(Object domainObj) {
		this.domainObj = domainObj;
	}
}
