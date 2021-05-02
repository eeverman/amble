package org.yarnandtail.amble.scenarios.mlr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yarnandtail.amble.StepBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.yarnandtail.amble.scenarios.mlr.MyOutcome.*;
import static org.yarnandtail.amble.scenarios.mlr.MyStatus.*;
//import static org.yarnandtail.amble.scenarios.mlr.MyStatus.Type.*;

class MLRTest {

	StepBuilder<MyStatus, MyOutcome> builder;
	MyDao dao;
	DaoResult daoResult;
	MyStatus status;
	SqsMessage sqsMessage;
	Message message;
	Domain domain;

	@BeforeEach
	public void setup() {
		builder = new StepBuilder();
		status = new MyStatus();

		dao = new MyDao();
		daoResult = new DaoResult();
		domain = new Domain();
		message = new Message();
		sqsMessage = new SqsMessage();

		domain.agencyCode = "ABC";
		domain.siteNumber = "123";
		domain.upstreamId = 111L;

		message.type = Type.CREATE;
		message.domain = domain;

		sqsMessage.message = message;
		sqsMessage.version = "1.1";
		sqsMessage.timestamp = "888888";

		status.setOutcome(CONTINUE);
		status.sqsMessage = sqsMessage;

		daoResult.id = 999999L;
		daoResult.upstreamId = domain.upstreamId;
		daoResult.lastTimestamp = 888888L;
		daoResult.type = Type.CREATE;

		dao.setTimestamp(null);
		dao.setDaoResult(daoResult);


	}

	@Test
	public void happyTest() {

		//Initial checks based on SQS Attributes
		builder.
				ifTrue(s -> (s.sqsMessage == null), MESSAGE_UNREADABLE).
				ifException(s -> s.version = Double.parseDouble(s.sqsMessage.version), UNSUPPORTED_MESSAGE_VERSION).
				ifFalse(s -> s.version < 2.0 && s.version > 1.0, UNSUPPORTED_MESSAGE_VERSION).
				ifException(s -> s.newTimestamp = Long.parseLong(s.sqsMessage.timestamp), UPSTREAM_TIMESTAMP_UNREADABLE);

		//Parse to domain object
		builder.
				ifException(s -> s.message = s.sqsMessage.message, MESSAGE_UNREADABLE).	//parse json message
				ifException(s -> s.message.domain.lastTimestamp = s.newTimestamp, MESSAGE_UNREADABLE).
				ifException(s -> s.domain = s.message.domain, MESSAGE_UNREADABLE).
				ifException(s -> s.currentTimestamp = dao.getLastTimestamp(s.domain.upstreamId), MESSAGE_UNREADABLE);

		//should we run this update?
		builder.
				ifTrue(s -> (s.message.type.equals(Type.CREATE) && s.currentTimestamp != null), CREATION_WOULD_OVERWRITE_EXISTING).
				ifTrue(s -> (s.newTimestamp < ((s.currentTimestamp != null)?s.currentTimestamp:Long.MIN_VALUE)), OLD_TIMESTAMP).
				ifTrue(s -> (s.newTimestamp == ((s.currentTimestamp != null)?s.currentTimestamp:Long.MIN_VALUE)), SAME_TIMESTAMP);

		//Do the Update
		builder.
				ifException(s -> s.daoResult = dao.doUpsert(s.domain), NOT_CONTINUE);

		//Determine Outcome
		builder.
				ifTrue(s -> s.daoResult.type.equals(Type.NOOP), OLD_TIMESTAMP).
				ifTrue(s -> s.message.type.equals(Type.CREATE),
						builder.ifTrue(s -> s.daoResult.type.equals(Type.CREATE), CREATION),
						builder.ifTrue(s -> s.daoResult.type.equals(Type.MODIFY), CREATION_WOULD_OVERWRITE_EXISTING) ).
				ifTrue(s -> s.message.type.equals(Type.MODIFY),
						builder.ifTrue(s -> s.daoResult.type.equals(Type.MODIFY), MODIFICATION),
						builder.ifTrue(s -> s.daoResult.type.equals(Type.MODIFY), MODIFICATION_WAS_CREATION) );

		//And run it...
		builder.run(status);

		assertEquals(CREATION, status.getOutcome());
	}

}

