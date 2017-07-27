package com.csii.upp.batch.base;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;

import com.csii.upp.dto.generate.Queappl;


public abstract class IJavaBatchWorker {
	private final static Log log = LogFactory.getLog(IJavaBatchWorker.class);
	public abstract void run(Object entry, Map<String, Object> argMaps);

	public boolean needToRun(Queappl queApp) {
		try {
			if ((queApp.getCornexperess() != null)
					&& (!queApp.getCornexperess().equals(""))) {
				CronExpression c = new CronExpression(queApp.getCornexperess());
				java.util.Calendar today = java.util.Calendar.getInstance();
				java.util.Calendar tmpday = java.util.Calendar.getInstance();
				tmpday.clear();
				tmpday.set(today.get(java.util.Calendar.YEAR),
						today.get(java.util.Calendar.MONTH),
						today.get(java.util.Calendar.DATE));

				Date timeAfter = c.getTimeAfter(tmpday.getTime());
				if (timeAfter.getYear() == today.get(java.util.Calendar.YEAR)
						&& timeAfter.getMonth() == today
								.get(java.util.Calendar.MONTH)
						&& timeAfter.getDate() == today
								.get(java.util.Calendar.DATE)) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} catch (ParseException e) {
			log.error("error",e);
			return true;
		}
	}
}
