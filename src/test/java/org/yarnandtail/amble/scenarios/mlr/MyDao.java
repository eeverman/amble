package org.yarnandtail.amble.scenarios.mlr;

public class MyDao {

	Long timestamp;
	MyStatus.DaoResult daoResult;

	//Set a fake timestamp to return
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setDaoResult(MyStatus.DaoResult daoResult) {
		this.daoResult = daoResult;
	}

	public Long getLastTimestamp(Long upstreamId) {
		return timestamp;
	}

	public MyStatus.DaoResult doUpsert(MyStatus.Domain domain) {
		return daoResult;
	}
}
