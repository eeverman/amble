package org.yarnandtail.amble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.yarnandtail.amble.MyOutcome.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StepBuilderTest {

	StepBuilder<MyStatus, MyOutcome> builder;
	MyStatus status;

	@BeforeEach
	public void setup() {
		builder = new StepBuilder();
		status = new MyStatus();
        status.setOutcome(OK);
        status.setOne(true);
        status.setTwo(false);
	}

	@Test
	public void happyTest() {

		builder.
				ifTrue(s -> (s == null), ERROR).
				ifFalse(s -> s.isOne(), ERROR).
				ifFalse(s -> status.isTwo(), HAPPY_RESULT_DONE).
				run(status);

		assertEquals(HAPPY_RESULT_DONE, status.getOutcome());
	}

    @Test
    public void exceptionTest() {

        builder.
                ifFalse(s -> status.isTwo(), OK).
                ifException(s -> status.getDomainObj().equals(new Object()), DOMAIN_IS_NULL_ERR).
				ifException(s -> status.getDomainObj().equals(new Object()), ERROR).
                run(status);

        assertEquals(DOMAIN_IS_NULL_ERR, status.getOutcome());
    }
}

