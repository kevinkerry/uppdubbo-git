package com.csii.upp.sequence;

import java.util.Date;

import com.csii.pe.service.id.RawSequenceIdFactory;
import com.csii.pe.service.id.sequence.SequenceFormat;
import com.csii.upp.util.StringUtil;

public class UppSequenceIdFactory extends RawSequenceIdFactory{

	private SequenceFormat format;

	public void setFormat(String pattern) {
		this.format = SequenceFormat.getInstance(pattern);
	}
	public Object generate(){
		Object obj=super.generate();
		if(this.format==null){
			return obj;
		}else{
			return this.format.format(this, StringUtil.parseLong(obj).longValue(), new Date(System.currentTimeMillis()));
		}
	}
}
