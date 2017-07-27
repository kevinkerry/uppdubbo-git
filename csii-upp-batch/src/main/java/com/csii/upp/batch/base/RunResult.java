package com.csii.upp.batch.base;

import java.io.Serializable;

class RunResult implements Serializable {
	private Throwable exception;
	private long totalCount;
	private long unExecutedCount;
	private long successCount;
	private long failureCount;
	private long threadNbr;
	private long queNbr;
	private long applNbr;

	public RunResult(long queNbr, long applNbr, long threadNbr, long totalCount) {
		this.queNbr = queNbr;
		this.applNbr = applNbr;
		this.threadNbr = threadNbr;
		this.totalCount = totalCount;
		this.unExecutedCount = totalCount;
		this.successCount = 0;
		this.failureCount = 0;
	}

	public Throwable getException() {
		return exception;
	}

	public long getUnExecutedCount() {
		return unExecutedCount;
	}

	public long getSuccessCount() {
		return successCount;
	}

	public long getFailureCount() {
		return failureCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getThreadCount() {
		return threadNbr;
	}

	public long getQueNbr() {
		return queNbr;
	}

	public long getApplNbr() {
		return applNbr;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public void plusSuccessCount(long count) {
		this.successCount += count;
		this.unExecutedCount -= count;
	}

	public void plusFailureCount(long count) {
		this.failureCount += count;
		this.unExecutedCount -= count;
	}
}
