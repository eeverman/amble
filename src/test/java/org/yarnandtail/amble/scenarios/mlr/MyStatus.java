package org.yarnandtail.amble.scenarios.mlr;

import org.yarnandtail.amble.ProcessStatus;

public class MyStatus implements org.yarnandtail.amble.ProcessStatus<MyOutcome> {

	private MyOutcome outcome;
	public SqsMessage sqsMessage;
	public Message message;
	public Domain domain;
	public DaoResult daoResult;

	//
	public Double version;
	public Long newTimestamp;
	public Long currentTimestamp;

	@Override
	public void setOutcome(MyOutcome outcome) {
		this.outcome = outcome;
	}

	@Override
	public MyOutcome getOutcome() {
		return outcome;
	}


	public static class SqsMessage {
		public String version;
		public String timestamp;
		public Message message;
	}

	public static class Message {
		public Type type;
		public Domain domain;
	}

	public static class Domain {
		public Long upstreamId;
		public String agencyCode;
		public String siteNumber;
		public Long lastTimestamp;
	}

	public static class DaoResult {
		public Long id;
		public Long upstreamId;
		public Long lastTimestamp;
		public Type type;
	}

	public static enum Type {
		CREATE,
		MODIFY,
		NOOP,
		UNKNOWN;
	}
}
