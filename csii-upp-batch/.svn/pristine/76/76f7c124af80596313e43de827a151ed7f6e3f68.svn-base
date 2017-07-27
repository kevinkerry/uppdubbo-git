package com.csii.upp.batch.appl.eaccount;

import java.util.HashSet;
import java.util.Properties;

import com.csii.upp.constant.AccckTyp;
import com.csii.upp.constant.ProcessMode;

/**
 * 差错分类
 * 
 * @author 颜祎名
 *
 */
public class ErrorCategory {

	// 差错分类，为清算平台提供
	private Properties errorCat;
	// 自动处理最大处理次数
	private Properties maxProTimes;
	// 自动处理类型
	private HashSet<String> autoHand;
	// 默认最大自动处理次数
	private final static long DEFAULT_MAX_PROCESS_TIMES = 1L;
	// 未归类差错类型
	private final static String UNCAT_ERROR_TYPE = AccckTyp.UnCatError;
	// 支付平台处理差错
	private final static String HAND_BY_MAN = AccckTyp.HandByZF;

	public void setMaxProTimes(Properties maxProTimes) {
		this.maxProTimes = maxProTimes;
	}

	public void setAutoHand(HashSet<String> autoHand) {
		this.autoHand = autoHand;
	}

	public boolean belong2AutoHand(String errorTyp) {
		return autoHand.contains(errorTyp);
	}

	/**
	 * 获取最大处理次数，如果是自动处理则返回配置的参数，如果未配置则默认为1，否则返0.
	 * 
	 * @param errorTyp
	 * @return
	 */
	public long getMaxProcessTimes(String errorTyp) {
		if (ProcessMode.PROCESS_BY_AUTO.equals(getProcessMode(errorTyp, true))) {
			String times = maxProTimes.getProperty(errorTyp);
			if (null == times || times.isEmpty())
				return DEFAULT_MAX_PROCESS_TIMES;
			return Long.parseLong(times);
		}

		return 0L;
	}

	public String getErrorCat(String errorTyp) {
		if (null == errorCat)
			return UNCAT_ERROR_TYPE;
		String errorCat = this.errorCat.getProperty(errorTyp);
		return (null == errorCat || errorCat.isEmpty()) ? UNCAT_ERROR_TYPE : errorCat;
	}

	public void setErrorCat(Properties errorCat) {
		this.errorCat = errorCat;
	}

	/**
	 * 获取差错处理模式。如果是初始插入且满足自动处理要求，则返回自动处理模式；如果是非初始插入则判断是否已配置为支付平台
	 * 手工处理，如果是则返回支付手工处理模式，否则返清算平台处理。
	 * 
	 * @param errorTyp
	 *            支付平台差错类型
	 * @param init
	 *            是否初始插入
	 * @return
	 */
	public String getProcessMode(String errorTyp, boolean init) {
		if (init && belong2AutoHand(errorTyp))
			return ProcessMode.PROCESS_BY_AUTO;
		if (HAND_BY_MAN.equals(getErrorCat(errorTyp)))
			return ProcessMode.PROCESS_BY_MAN;
		return ProcessMode.PROCESS_BY_QSPT;
	}

}
