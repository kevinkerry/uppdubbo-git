package com.csii.upp.batch.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class QueStatusHolder {
	
	public static final String WaitingStatus = "Waiting";
	public static final String GroupingStatus = "Grouping";
	public static final String RunningStatus = "Running";
	public static final String CompletedStatus = "Completed";
	public static final String ErrorStatus = "Error";

	private static boolean working=true;
	private static QueStatusHolder instance;
	private Map<Object ,QueStatusItem> Ques = new ConcurrentHashMap<Object ,QueStatusItem>();
	public  Map<Object, QueStatusItem> getQues() {
		return Ques;
	}
	public static QueStatusHolder getInstance(){
		if(instance==null)instance= new QueStatusHolder();
		return instance;
	}
	public List<QueStatusItem> getQuesList(){
		if(getInstance().getQues().size()>0){
			List<QueStatusItem> list = new ArrayList<QueStatusItem>();
			for(Entry<Object,QueStatusItem> entry : Ques.entrySet()){
				list.add(entry.getValue());
			}
			return list;
		}
		else
		{
			return null;
		}
	}
	public void setQues(Map<Object, QueStatusItem> ques) {
		Ques = ques;
	}
	/**
	 * 队列创建了
	 * @param seqNbr
	 * @param queNbr
	 * @param queName
	 */
	public void QueCreated(Long seqNbr,Long queNbr,String queName){
		AddQue(seqNbr,queNbr,queName);
	}
	/**
	 * Appl读取完毕
	 * @param appl
	 */
	public void ApplLoaded(ApplConfiguration appl){
		QueStatusItem que=Ques.get(appl.getRunSeqNbr()+"+"+appl.getParentQue().getQuenbr());
		if(que==null)AddQue(appl.getRunSeqNbr(),appl.getParentQue().getQuenbr(),appl.getParentQue().getQuedesc());
		AddAppl(appl.getRunSeqNbr(),appl.getParentQue().getQuenbr(),appl.getWorkerAppl().getApplnbr(),appl.getWorkerAppl().getAppldesc());
	}
	/**
	 * 线程创建完毕，这里面machineNbr如果为Long.MinValue说明没有任务机，也就是单机环境
	 * @param machineNbr
	 * @param threadNbr
	 * @param threadName
	 * @param runMsg
	 * @param totalcount
	 */
	public void ThreadCreated(Long machineNbr,Long threadNbr,String threadName,RunningMessage runMsg,int totalcount){
		QueStatusItem que=Ques.get(runMsg.getQueApplHist().getRunseqnbr()+"+"+runMsg.getQueApplHist().getQuenbr());
		if(que==null)AddQue(runMsg.getQueApplHist().getRunseqnbr(),
				runMsg.getQueApplHist().getQuenbr(),"No Name");
		que=Ques.get(runMsg.getQueApplHist().getRunseqnbr()+"+"+runMsg.getQueApplHist().getQuenbr()).getSubItems().get(runMsg.getQueApplHist().getApplnbr());
		if(que==null)AddAppl(runMsg.getQueApplHist().getRunseqnbr(),runMsg.getQueApplHist().getQuenbr()
				,runMsg.getQueApplHist().getApplnbr(),"NoName");
		AddThread(runMsg.getQueApplHist().getRunseqnbr(),runMsg.getQueApplHist().getQuenbr()
				,runMsg.getQueApplHist().getApplnbr(),machineNbr,threadNbr,threadName,totalcount);
	}
	private void AddQue(Long seqNbr,Long quenbr,String  queName){
		if(!working)return;
		//先清空已经完成的Que
		ArrayList  keys= new ArrayList ();
	 for(Object key :getQues().keySet()){
		 if(getQues().get(key).getStatus()=="Completed") keys.add(key);
	 }
	 if(keys.size()>0){
		 for(Object key :keys)getQues().remove(key);
	 }
		
		//添加Que
		   QueStatusItem item = new  QueStatusItem();
		   item.setID(quenbr);
		   item.setName(queName);
		   item.setItemType("QUE");
		   Ques.put(seqNbr+"+"+quenbr,item);//key是两个加起来
	}
	private void AddAppl(Long seqNbr,Long quenbr,Long applnbr,String applname){
		if(!working)return;  
		String seqandque = seqNbr+"+"+quenbr;
		QueStatusItem item = new  QueStatusItem();
		   item.setID(applnbr);
		   item.setName(applname);
		   item.setItemType("APPL");
		Ques.get(seqandque).getSubItems().put(applnbr,item );
	}
	private void AddThread(Long seqNbr,Long quenbr,Long applnbr,Long machineNbr,Long threadnbr,String threadname,Integer totalcount){
		if(!working)return;
		String seqandque = seqNbr+"+"+quenbr;
		if(machineNbr==null)machineNbr=Long.MIN_VALUE;
		QueStatusItem item = new  QueStatusItem();
		   item.setID(threadnbr);
		   item.setName(threadname);
		   item.setTotalCount(totalcount);
		   item.setItemType("THREAD");
		   if(machineNbr==Long.MIN_VALUE){
		Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().put(threadnbr, item);
		   }else{
			   Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).getSubItems().put(threadnbr, item);
		   }
	}
	public void updateStep(Long seqNbr,Long quenbr,Long applnbr,Long machineNbr,Long threadnbr,Integer step,String currentobject){
		if(!working)return;
		String seqandque = seqNbr+"+"+quenbr;
		if(machineNbr==null)machineNbr=Long.MIN_VALUE;
		if(applnbr==null){
			Ques.get(seqandque).setCurrCount(step);
			Ques.get(seqandque).setCurrObject(currentobject);
		}
		else if(threadnbr==null){
			if(machineNbr==Long.MIN_VALUE){
				Ques.get(seqandque).getSubItems().get(applnbr).setCurrCount(step);
				Ques.get(seqandque).getSubItems().get(applnbr).setCurrObject(currentobject);
			}else{
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).setCurrCount(step);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).setCurrObject(currentobject);
			}
		}else{
			if(machineNbr==Long.MIN_VALUE){
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(threadnbr).setCurrCount(step);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(threadnbr).setCurrObject(currentobject);
			}else{
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).getSubItems().get(threadnbr).setCurrCount(step);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).getSubItems().get(threadnbr).setCurrObject(currentobject);
			}	
		}
	}
	public void updateStatus(Long seqNbr,Long quenbr,Long applnbr,Long machineNbr,Long threadnbr,String statusString,String currentobject){
		if(!working)return;
		String seqandque = seqNbr+"+"+quenbr;
		if(machineNbr==null)machineNbr=Long.MIN_VALUE;
		if(applnbr==null){
			Ques.get(seqandque).setStatus(statusString);
			Ques.get(seqandque).setCurrObject(currentobject);
		}
		else if(threadnbr==null){
			if(machineNbr==Long.MIN_VALUE){
				Ques.get(seqandque).getSubItems().get(applnbr).setStatus(statusString);
				Ques.get(seqandque).getSubItems().get(applnbr).setCurrObject(currentobject);
			}else{
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).setStatus(statusString);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).setCurrObject(currentobject);
			}
		}else{
			if(machineNbr==Long.MIN_VALUE){
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(threadnbr).setStatus(statusString);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(threadnbr).setCurrObject(currentobject);
			}else{
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).getSubItems().get(threadnbr).setStatus(statusString);
				Ques.get(seqandque).getSubItems().get(applnbr).getSubItems().get(machineNbr).getSubItems().get(threadnbr).setCurrObject(currentobject);
			}	
		}}
}

