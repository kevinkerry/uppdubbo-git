package com.csii.upp.batch.event.handler;

import com.csii.upp.batch.base.BatchRunner;
import com.csii.upp.event.EventHandler;
/**
 * 运行队列
 * @author 徐锦
 *
 */
public class RunQueEventHandler implements EventHandler<RunQueEvent>{
	private BatchRunner batchRunner;

	public void setBatchRunner(BatchRunner batchRunner) {
		this.batchRunner = batchRunner;
	}
	@Override
	public void handler(RunQueEvent event) {
		batchRunner.run(event.getQueNbr(),event.getRunSeqNbr());
	}

	@Override
	public Class<RunQueEvent> getAcceptedEventType() {
		return RunQueEvent.class;
	}

}
