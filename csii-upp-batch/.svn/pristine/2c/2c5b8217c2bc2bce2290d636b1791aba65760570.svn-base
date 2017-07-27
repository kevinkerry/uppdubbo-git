package com.csii.upp.batch.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


//基类：队列状态的每一层基本都应该包括ID，名字，数量，下层信息等内容，到最下层的时候sub就没有了
public class QueStatusItem{
private long ID=0;
private String Name="";
private int CurrCount=0;
private int TotalCount=0;
private float percent=0;
private Map<Object,QueStatusItem> SubItems =new HashMap<Object,QueStatusItem>();
///当前正在处理的对象描述
private String CurrObject="";
//item的类型 可以表示是QUE，APPL，THREAD，MACHINE等
private String ItemType = "QUE";
//item的状态，可以表示为：Waiting，Grouping,Running，Completed，Error
private String Status="Waitting";
//Item创建的时间
private Date Date_Create = new Date();
//Item的结束时间
private Date Date_End ;

public boolean hasSubItems(){
		return SubItems.size()>0;
}
public List<QueStatusItem> getSubItemList(){
	if(hasSubItems()){
		List<QueStatusItem> list = new ArrayList<QueStatusItem>();
		for(Entry<Object,QueStatusItem> entry : SubItems.entrySet()){
			list.add(entry.getValue());
		}
		return list;
	}
	else
	{
		return null;
	}
}
public long getID() {
	return ID;
}
public void setID(long iD) {
	ID = iD;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public int getCurrCount() {
	if(hasSubItems()){
		CurrCount=0;
		for(Entry<Object, QueStatusItem> entry : SubItems.entrySet()){
			CurrCount+=entry.getValue().getCurrCount();
		}
		return CurrCount;
	}else{
		
	return CurrCount;
	}
}
public void setCurrCount(int currCount) {
	//System.out.println(this.Name+this.ID+"Add Step "+currCount+"result is "+CurrCount);
	CurrCount += currCount;
}
public int getTotalCount() {
	if(hasSubItems()){
	 TotalCount=0;
	 for(Entry<Object, QueStatusItem> entry : SubItems.entrySet()){
		 TotalCount+=entry.getValue().getTotalCount();
		 
	 }
	
	 return TotalCount;
	}else {
		
	return TotalCount;
	}
}
public void setTotalCount(int totalCount) {
	TotalCount = totalCount;
}
public Map<Object,QueStatusItem> getSubItems() {
	return SubItems;
}
public void setSubItems(Map<Object,QueStatusItem> subItems) {
	SubItems = subItems;
}
public float getPercent(){
	return ((float)getCurrCount())*100F/((float)getTotalCount());
}
public String getCurrObject() {
	return CurrObject;
}
public void setCurrObject(String currObject) {
	CurrObject = currObject;
}
public String getItemType() {
	return ItemType;
}
public void setItemType(String itemType) {
	ItemType = itemType;
}
public String getStatus() {
	if(this.getTotalCount()==0)return QueStatusHolder.CompletedStatus;
	if(hasSubItems()){
		for(Entry<Object, QueStatusItem> entry : SubItems.entrySet()){
			if(entry.getValue().getStatus()!=QueStatusHolder.CompletedStatus){
				return entry.getValue().getStatus();//有任何一个不为结束的就直接返回状态
			}
		}
		return QueStatusHolder.CompletedStatus;
	}else{
		return Status;
	}
}
public void setStatus(String status) {
	if(status == QueStatusHolder.CompletedStatus||status==QueStatusHolder.ErrorStatus){
		this.Date_End = new Date();
	}
	Status = status;
}
public Date getDate_Create() {
	return Date_Create;
}

///执行速度
public long getTps(){
	if(hasSubItems()){//如果有下级计算下级的平均速度
		 long tpses=0;
		 for(Entry<Object, QueStatusItem> entry : SubItems.entrySet()){
			 tpses+=entry.getValue().getTps();
		 }
		 return tpses/SubItems.size();
		}else{
			long dateSpan = getDateSpan();
			if(dateSpan!=0)
	return this.CurrCount/getDateSpan();
			else
				return 0;
		}
}
///单位 秒
public long getDateSpan(){
	if(this.Date_End!=null)return (this.Date_End.getTime()-this.Date_Create.getTime())/1000;
	else return (new Date().getTime()- Date_Create.getTime())/1000;
}
//取分组时间
public float getGroupingDateSpan(){
	if(hasSubItems()){
		for(Entry<Object, QueStatusItem> entry : SubItems.entrySet()){//获取第一个下级的创建时间来计算分组时间
			 return (entry.getValue().getDate_Create().getTime()-this.getDate_Create().getTime())/1000f;
		 }
		return 0;
	}else {
		return 0f;
	}
}
}