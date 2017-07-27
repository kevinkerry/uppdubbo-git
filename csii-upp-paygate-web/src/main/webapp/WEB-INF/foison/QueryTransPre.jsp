<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面  -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付查询交易明细</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="script.do"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/calendar/WdatePicker.js"></script>
<link href="css/hDate.css" rel="stylesheet" />
<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:13px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width:100%;
	height:50%;
}
table.gridtable th {
	border-width: 1px;
	padding: 5px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
	height: 10px;
}
table.gridtable td {
	border-width: 1px;
	padding: 5px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	height: 10px;
}
</style>

<script src="js/style.js"></script>
<script language="javascript">
function nextPage(){
	  document.forms[0].NewPageNum.value=""; 
	var PageNum=document.forms[0].PageNum.value;
	var TotalPage=document.forms[0].TotalPage.value;
	var i=parseInt(PageNum);
	var j=parseInt(TotalPage);
	if(i<j){
     i=i+1;
    document.forms[0].PageNum.value=i;
	post2SRVNoFoward('FS02Confirm.do',form1,null,'AAA',"EEE"); 	 
   }	 
}
function prePage(){
	 document.forms[0].NewPageNum.value=""; 
	var PageNum=document.forms[0].PageNum.value;
	var i=parseInt(PageNum);
	if(i>1){
     i=i-1;
    document.forms[0].PageNum.value=i;
	post2SRVNoFoward('FS02Confirm.do',form1,null,'AAA',"EEE"); 	 
   }	 
}
function goPage(){
	 document.forms[0].NewPageNum.value=""; 
	var gopage=parseInt(document.forms[0].gopage.value);
	var TotalPage=parseInt(document.forms[0].TotalPage.value);
	if(gopage>=1&&gopage<=TotalPage){
    document.forms[0].PageNum.value=gopage;
	post2SRVNoFoward('FS02Confirm.do',form1,null,'AAA',"EEE"); 	 
   }	 
}
function throwValidationAtEEE(valiInfo,place)
{
	PEGetElement(place).innerHTML = valiInfo;
}
function doIt(clickObj)
{
	//日期验证
 document.getElementById("EEE").innerHTML = "";
 var date=new Date();
	
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    month=month<10?("0"+month):month;
    var day=date.getDate();
    day=day<10?("0"+day):day;   
    var time=(year-1)+"-"+month+"-"+day;
    var time1=year+"-"+month+"-"+day;  
    perMonthtime=new Date(date.setMonth(date.getMonth()-1));
    var year1=perMonthtime.getFullYear();
    var month1=perMonthtime.getMonth()+1;
    month1=month1<10?("0"+month1):month1;
    var day1=perMonthtime.getDate();
    day1=day1<10?("0"+day1):day1;   
    var perMonthtime=year1+"-"+month1+"-"+day1;
	var TransTypCd=document.forms[0].TransTypCd.value;
	var vBDate=document.forms[0].BeginDate.value;
	var vEDate=document.forms[0].EndDate.value;
    if(TransTypCd=="no"){
    	document.getElementById("AAA").style.display="none";
    	document.getElementById("EEE").innerHTML = "尊敬的客户，请选择交易类型 ";
		 return false;
        }
	if(vBDate==null && vBDate==""){
		document.forms[0].BeginDate.value=perMonthtime;
		document.forms[0].EndDate.value=time1;
	////var r = vBDate.match(/^(\d{4})-(\d{2})-(\d{2})$/);
 //   if(r==null){
		 //  document.getElementById("EEE").innerHTML = "尊敬的客户，请输入正确的开始日期格式，如2009-09-29";
		 //  return false;
	  // }
	}
	if(vEDate!=null && vEDate!=""){
	var v = vEDate.match(/^(\d{4})-(\d{2})-(\d{2})$/); 
    if(v==null){
		   document.getElementById("EEE").innerHTML = "尊敬的客户，请输入正确的结束日期格式，如2009-09-29"; 
		   return false;
	   }
	}
	 //比较开始日期是否大于当前日期
	  var begintime=vBDate.split('-');
	  var nowtime=time.split('-');
	  if(parseInt(begintime[0])<parseInt(nowtime[0])){  
		  document.getElementById("AAA").style.display="none";  
		  document.getElementById("EEE").innerHTML = "抱歉,输入的开始日期需大于"+time;
		  return false;
		 }else if(parseInt(begintime[0])==parseInt(nowtime[0])){
                if(parseInt(begintime[1])<parseInt(nowtime[1])){
                	document.getElementById("AAA").style.display="none";
            		  document.getElementById("EEE").innerHTML = "抱歉,输入的开始日期需大于"+time;             
                    
                    }else if(parseInt(begintime[1])==parseInt(nowtime[1])){
                    	if(parseInt(begintime[2])<parseInt(nowtime[2])){
                    		document.getElementById("AAA").style.display="none";
                    		document.getElementById("EEE").innerHTML = "抱歉,输入的开始日期需大于"+time;
                    	return false;     
                        }
			          }
      			 }
	  if(vBDate>vEDate){
			document.getElementById("AAA").style.display="none";
		  document.getElementById("EEE").innerHTML = "开始日期不能大于结束日期";
		  return false;
	  }
	
		 var endtime=vEDate.split('-');
		 var eday=parseInt(endtime[0])*365+parseInt(endtime[1])*30+parseInt(endtime[2]);
		 var bday=parseInt(begintime[0])*365+parseInt(begintime[1])*30+parseInt(begintime[2]);
		 if(eday-bday>90){
			 document.getElementById("AAA").style.display="none";
			 document.getElementById("EEE").innerHTML = "日期跨度不能超过3个月！";
			 return false;
			 }

 	   document.getElementById("EEE").innerHTML = "";
	   //开始查询并返回到AAA
	   document.getElementById("AAA").style.display="block";
	   document.getElementById("BBB").style.display="none";
	   document.forms[0].NewPageNum.value="1"; 
	post2SRVNoFoward('FS02Confirm.do',form1,null,'AAA',"EEE"); 	 
}

function downloadTransDetails()
{   
	var TransTypCd=document.forms[0].TransTypCd.value;
	if(TransTypCd=="no"){
	  document.getElementById("AAA").style.display="none";
	  document.getElementById("EEE").innerHTML = "尊敬的客户，请选择交易类型 ";
	  return false;
	}else {
	  document.forms[2].TransTypCd.value = document.forms[0].TransTypCd.value;
	  document.forms[2].BeginDate.value = document.forms[0].BeginDate.value;
	  document.forms[2].EndDate.value = document.forms[0].EndDate.value;
	  document.forms[2].PayerCardTypCd.value = document.forms[0].PayerCardTypCd.value;
	  document.forms[2].PayerAcctNbr.value = document.forms[0].PayerAcctNbr.value;
	  document.forms[2].PayerPhoneNo.value = document.forms[0].PayerPhoneNo.value;
	  document.forms[2].action="DownloadTransDetail.do";
	  document.forms[2].submit();
    } 
}

function Cancel1(){
    window.location.href="FS02.do";
		}


function TrsDetailQryDetail(TransTime,PayerAcctNbr,TransAmt,PayeeAcctNbr,FeeAmt,TransStatus,CurrencyCd,PayeeAcctName,PayerAcctName){
	var TransTypCd=document.forms[0].TransTypCd.value;
	var vBDate=document.forms[0].BeginDate.value;
	var vEDate=document.forms[0].EndDate.value;
	var PageNum=document.forms[0].PageNum.value;
	var PayerAcctNbr=document.forms[0].PayerAcctNbr.value;
	document.forms[1].PageNum.value=PageNum;
	document.forms[1].vEDate.value=vEDate;
	document.forms[1].vBDate.value=vBDate;
	document.forms[1].TransTypCd.value=TransTypCd;
	document.forms[1].TransTime.value=TransTime;
	document.forms[1].PayerAcctNbr.value=PayerAcctNbr;
	document.forms[1].PayeeAcctNbr.value=PayeeAcctNbr;
	document.forms[1].TransAmt.value=TransAmt;
	document.forms[1].CurrencyCd.value=CurrencyCd;
	document.forms[1].TransStatus.value=TransStatus;
	document.forms[1].PayeeAcctName.value=PayeeAcctName;
	document.forms[1].PayerAcctName.value=PayerAcctName;
	document.forms[1].FeeAmt.value=FeeAmt;
	//var oparams =  new Array(            
		      //  new Pair("TransTime",TransTime),
		      //  new Pair("PayerAcctNbr",PayerAcctNbr),
		      //  new Pair("PayeeAcctNbr",PayeeAcctNbr),  
		      //  new Pair("TransAmt",TransAmt),  
		     //   new Pair("CurrenCy",CurrenCy),  		   
		     //   new Pair("TransStatus",TransStatus),  
		      //  new Pair("PayeeAcctName",PayeeAcctName),
		      //  new Pair("FeeAmt",FeeAmt),
		      //  new Pair("PayerAcctName",PayerAcctName),
		     //   new Pair("TransTypCd",TransTypCd),
		    //    new Pair("vBDate",vBDate),   
		    //    new Pair("vEDate",vEDate),
		      //  new Pair("PageNum",PageNum),
		    //    new Pair("AcctNo",AcctNo)           
		   // );
	   // document.getElementById("BBB").style.display="block";
	//postData2SRV("FS02QueryDetail.do", PEGetPostData(oparams),'BBB',"EEE");
	document.forms[1].action="FS02QueryDetail.do";
	document.forms[1].submit();
	
}
function QueryElectronicReceipt(TransTime,PayerAcctNbr,TransAmt,PayeeAcctNbr,FeeAmt,TransStatus,CurrencyCd,PayeeAcctName,PayerAcctName){
	var TransTypCd=document.forms[0].TransTypCd.value;
	var vBDate=document.forms[0].BeginDate.value;
	var vEDate=document.forms[0].EndDate.value;
	var PageNum=document.forms[0].PageNum.value;
	document.forms[1].PageNum.value=PageNum;
	document.forms[1].vEDate.value=vEDate;
	document.forms[1].vBDate.value=vBDate;
	document.forms[1].TransTypCd.value=TransTypCd;
	document.forms[1].TransTime.value=TransTime;
	document.forms[1].PayerAcctNbr.value=PayerAcctNbr;
	document.forms[1].PayeeAcctNbr.value=PayeeAcctNbr;
	document.forms[1].TransAmt.value=TransAmt;
	document.forms[1].CurrencyCd.value=CurrencyCd;
	document.forms[1].TransStatus.value=TransStatus;
	document.forms[1].PayeeAcctName.value=PayeeAcctName;
	document.forms[1].PayerAcctName.value=PayerAcctName;
	document.forms[1].FeeAmt.value=FeeAmt;
	document.forms[1].action="QueryElectronicReceipt.do";
	document.forms[1].submit();
}
function startup(){
	var returnState=document.getElementById('returnState').value;
	if(returnState=="return"){
      document.forms[0].TransTypCd.value=document.getElementById('TransTypCd_return').value;
      document.forms[0].BeginDate.value=document.getElementById('vBDate_return').value;
	  document.forms[0].EndDate.value=document.getElementById('vEDate_return').value;
      document.forms[0].PageNum_return.value=document.getElementById('PageNum_return').value;
	post2SRVNoFoward('FS02Confirm.do',form1,null,'AAA',"EEE"); 	 
}
}
</script>
</head>
<body onload="startup()">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/csii1.png"/></div>
           <div class="top_r">
                <div class="phone"><img src="images/phone.png"/><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1"></div><div class="tuic"></div></div>
           </div>
      </div>
      <div class="main-nav">
        <ul class="main-nav-in"> 
           <li><a href="/paygate/FS01.do">注册</a></li>
            <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS02.do">查询交易明细</a></li>
            <li><a href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
         </ul>     
      </div>
      <div class="content">         
    
           <div class="con_r">             
             <div class="con_r_main">
                   <div class="dingw">您可以在此查询丰收e支付的交易明细</div>        
                      <div class="sr_main">
                      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="mainTable"><tr>
		               <td width="80%">
		              <input id="returnState" name="returnState" type="hidden" value="${returnState}"/>
		              <input id="vBDate_return" name="vBDate_return" type="hidden" value="${vBDate_return}"/>
		              <input id="vEDate_return" name="vEDate_return" type="hidden" value="${vEDate_return}"/>
		              <input id="TransTypCd_return" name="TransTypCd_return" type="hidden" value="${TransTypCd_return}"/>
               
                       <form id="form1" name="form1" method="post" action="#" >                        
                       <pe:hidden fieldList="CustCifNbr,PayerAcctDeptNbr" skipNULL="true"/>
                       <input name="PayerCardTypCd" type="hidden"/> 
                       <input id="TransDate" name="TransDate" type="hidden" value="${TransDate}"/>
                       <input name="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}"/>
                       <input name="PayerAcctNbr" type="hidden" value="${PayerAcctNbr}" />
                       <input id="PageNum_return" name="PageNum_return" type="hidden" value="${PageNum_return}"/>
                       <input id="NewPageNum" name="NewPageNum" type="hidden" value=""/>
                              <table width="650" border="0" style="margin:20px auto;" >
                              <tr align="middle" >
                              <td width="100%" height="23" class="tdTitle" style="margin-right:150px" >
                                                                                  交易类型:     
			                   <select name="TransTypCd" style="width:100px;">
			                   <option value="no">请选择</option>
                               <option value="00" >支付</option>
                               <option value="01" >退货</option>                
                                </select>
                                <span style="width:200px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                 </td>
                                 </tr>
                               <tr  align="middle" >
                               <td width="100%" height="23"  class="tdTitle">起止日期:
				               <input  autocomplete="off"  class="Wdate" style="width:100px;" name="BeginDate" id="BeginDate" type="text" value="${DefaultBeginDate}" onClick="WdatePicker()"/>
                               <input  autocomplete="off"  class="Wdate" style="width:100px;" name="EndDate" id="EndDate" type="text" value="${DefaultEndDate}" onClick="WdatePicker()"/>
                               </td>    		              
		                      </tr>  
		                      <tr  align="middle"><td style="color: red"><span id="EEE"></span><pe:error/></td></tr>                                           
                             </table>  	
               
                        <div class="btn_big">
                          <input type="button"  value="查询" class="xia_btn" id="doitbtn" onclick="doIt()"/>
                          <input type="button"  value="返回" class="xia_btn" onclick="Cancel1()" />
                           <input type="button"  value="下载" class="xia_btn"  onclick="downloadTransDetails()" />
                          </div>
                          <span id="AAA"></span>
                          <span id="BBB" style="display:none;">                       
                          </span>
                     </form>
                     
                      <form id="form2" name="form2" method="post" >     
                          <input id="TransTime" name="TransTime" type="hidden" />
                          <input id="PayeeAcctNbr" name="PayeeAcctNbr" type="hidden" />
                          <input id="CurrencyCd" name="CurrencyCd" type="hidden" />
                          <input id="PayeeAcctName" name="PayeeAcctName" type="hidden" />
                          <input id="PayerAcctName" name="PayerAcctName" type="hidden" />
                          <input id="FeeAmt" name="FeeAmt" type="hidden" />
                          <input id="TransTypCd" name="TransTypCd" type="hidden" /> 
                          <input id="vBDate" name="vBDate" type="hidden" /> 
                          <input id="PageNum" name="PageNum" type="hidden" />
                          <input id="vEDate" name="vEDate" type="hidden" />
                          <input id="TransStatus" name="TransStatus" type="hidden" />
                          <input id="PayerAcctNbr" name="PayerAcctNbr" type="hidden" /> 
                          <input id="TransAmt" name="TransAmt" type="hidden" /> 
                        </form>
                        
                        <form id="downloadForm" name="downloadForm" method="post">
                        	<input id="TransTypCd" name="TransTypCd" type="hidden" />
                        	<input id="BeginDate" name="BeginDate" type="hidden" />
                        	<input id="EndDate" name="EndDate" type="hidden" />
                        	<input id="PayerAcctNbr" name="PayerAcctNbr" type="hidden" />
                        	<input id="PayerPhoneNo" name="PayerPhoneNo" type="hidden" />
                        	<input id="PayerCardTypCd" name="PayerCardTypCd" type="hidden" />
                        </form>
                     </td>
				</tr>
			</table>
     </div>
        <div class="wxts" style="height:8px">
             </div>
           </div>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
      </div>
</body>
</html>












