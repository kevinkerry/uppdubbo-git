package com.csii.upp.batch.base;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import com.csii.upp.dao.generate.QueDAO;
import com.csii.upp.dto.generate.Que;
import com.csii.upp.dto.generate.QueExample;


public class BatchScheduler{
	private final static Log log = LogFactory.getLog(BatchScheduler.class);
	public  List<Que> workerQueList;
	private SchedulerFactory schedFact = null;
	private Scheduler scheduler;
	
	public BatchScheduler(Scheduler scheduler){
		this.scheduler=scheduler;
	}
	
	public BatchScheduler(){
		try {
			schedFact = new StdSchedulerFactory("META-INF/config/quartz.properties");
			scheduler = schedFact.getScheduler();
		} catch (SchedulerException e) {
			throw new RuntimeException("an exception occurred when starting the quartz module.",e);
		}
	}
	
	public void init(){
		workerQueList = loadData();
		try {
			run(workerQueList);
		} catch (SchedulerException e) {
			throw new RuntimeException("an exception occurred when starting the quartz module.",e);
		} catch (ParseException e) {
			throw new RuntimeException("an exception occurred when starting the quartz module.",e);
		}
	}
	
	public void close() throws SchedulerException {
		scheduler.clear();
	}
	
	public void run(List<Que> list) throws SchedulerException, ParseException {
		if(list == null){return;}
		//将job都加入scheduler中
		for(Que que : list){
			schedule( que.getQuenbr().toString(), que.getCornexperess());
		}
		// 启动
		if (!scheduler.isShutdown())
			scheduler.start();
	}

	private List<Que> loadData(){
		QueExample example = new QueExample();
		example.createCriteria().andAutorunEqualTo("Y");
		try {
			return QueDAO.selectByExample(example);
		} catch (SQLException e) {
			log.error("error",e);
			return null;
		}
	}
	
	public void schedule(String queNbr,
			String cronExpression) throws SchedulerException, ParseException {
		JobDetail jobDetail = JobBuilder.newJob(BatchRunner.class)
		        .withIdentity(queNbr, Scheduler.DEFAULT_GROUP).build();// 任务名，任务组，任务执行类
		jobDetail.getJobDataMap().put("queNbr", queNbr);

		// 触发器
		Trigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(queNbr, Scheduler.DEFAULT_GROUP)
                .withSchedule(
                CronScheduleBuilder.cronSchedule(new CronExpression(cronExpression)))
                .build();   
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}
	
	public void refresh() throws SchedulerException, ParseException{
		if(scheduler != null){
			scheduler.clear();
		}
		run(loadData());
	}
	
	public void modifyTrigger(String queNbr, String cronTrigger) throws SchedulerException, ParseException{
		JobKey jobKey = new JobKey(queNbr, Scheduler.DEFAULT_GROUP);
		scheduler.pauseJob(jobKey);
		Trigger oldTrigger = scheduler.getTriggersOfJob(jobKey).get(0);
		Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(queNbr, Scheduler.DEFAULT_GROUP)
                .withSchedule(
                CronScheduleBuilder.cronSchedule(new CronExpression(cronTrigger)))
                .build();    
		scheduler.rescheduleJob(oldTrigger.getKey(), trigger);
		scheduler.resumeJob(jobKey);
	}
	
	public void addJob(String queNbr, String cronTrigger) throws SchedulerException{
		JobDetailImpl job = (JobDetailImpl) JobBuilder.newJob(BatchRunner.class)
				.withIdentity(queNbr, Scheduler.DEFAULT_GROUP).build();
        job.setDurability(true);
        Trigger trigger4 = TriggerBuilder.newTrigger().withIdentity(cronTrigger, Scheduler.DEFAULT_GROUP).forJob(job)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronTrigger)).build();
        scheduler.addJob(job, false);
        scheduler.scheduleJob(trigger4);
	}
	
	public void deleteJob(String queNbr) throws SchedulerException{
		scheduler.deleteJob(new JobKey(queNbr, Scheduler.DEFAULT_GROUP));
	}
	
}
