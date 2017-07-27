package com.csii.upp.supportor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.csii.pe.service.id.IdFactory;
import com.csii.upp.sequence.UppSequenceIdFactory;
import com.csii.upp.sequence.UppSnowflakeIdWorker;
import com.csii.upp.util.StringUtil;

/**
 * 序列号生成工厂
 * 
 * @author 徐锦
 *
 */
public class IDGenerateFactory implements ApplicationContextAware {
	private static ApplicationContext appContext;
	private static IdFactory innerFundTransNbrFactory;
	private static IdFactory rtxnNbrFactory;
	private static IdFactory seqIdFactory;
	private static UppSnowflakeIdWorker snowflakeIdWorker;
	private static boolean isReadDataBasesSeq = true;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		// TODO Auto-generated method stub
		appContext = context;
	}

	public static String generateInnerFundTransNbr() {
		if (isReadDataBasesSeq) {
			if (innerFundTransNbrFactory == null) {
				innerFundTransNbrFactory = (UppSequenceIdFactory) appContext.getBean("innerFundTransNbrFactory");
			}
			return StringUtil.parseObjectToString(innerFundTransNbrFactory.generate());
		} else {
			return generateSnowflakeIdWorkerSeqNbr();
		}
	}

	public static String generateRtxnNbr() {
		if (isReadDataBasesSeq) {
			if (rtxnNbrFactory == null) {
				rtxnNbrFactory = (UppSequenceIdFactory) appContext.getBean("rtxnNbrFactory");
			}
			return StringUtil.parseObjectToString(rtxnNbrFactory.generate());
		} else {
			return generateSnowflakeIdWorkerSeqNbr();
		}

	}

	public static String generateSeqId() {
		if (isReadDataBasesSeq) {
			if (seqIdFactory == null) {
				seqIdFactory = (UppSequenceIdFactory) appContext.getBean("seqIdFactory");
			}
			return StringUtil.parseObjectToString(seqIdFactory.generate());
		} else {
			return generateSnowflakeIdWorkerSeqNbr();
		}
	}

	private static String generateSnowflakeIdWorkerSeqNbr() {
		if (snowflakeIdWorker == null) {
			snowflakeIdWorker = (UppSnowflakeIdWorker) appContext.getBean("snowflakeIdWorker");
		}
		return StringUtil.parseObjectToString(snowflakeIdWorker.generate());
	}

}
