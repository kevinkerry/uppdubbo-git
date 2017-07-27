<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<%@ taglib prefix="e" uri="http://www.csii.com.cn/tag/pe" %>
<html>
<head>
<title>丰收e支付查询交易明细</title>
<!-- 银行卡支付页面 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/common.js" language="javascript" type="text/javascript"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="js/writeObject.js"></script>
<script language="javascript">

function Canceloo(){
	 document.forms[0].action="QryTrsDetailPre.do";
	 document.forms[0].submit();
	  //window.history.back(-1);
		}
function startup()
{	
	getBigStringAmount();
	document.forms[0].AmountCapital.value=document.getElementById("fff").innerHTML;
	var inputName = document.getElementsByName("ElecReceipt");
	var imgName = document.getElementsByName("QRcode");
	createEWM(inputName, imgName);
}

function createEWM(inputName, imgName){
	if(inputName == null || imgName == null){
		return;
	}
	//对应生成二维码
	for(var i=0, len=inputName.length; i<len; i++){
		var content = inputName[i].value;
		var imgParam = encodeURI(encodeURI(content));
		imgName[i].src="GenerateElectronicReceipt.do?content="+imgParam;
}

	
	//控制网页打印的页眉页脚为空
	function setPageNull(){
		var HKEY_Root,HKEY_Path,HKEY_Key;
		HKEY_Root="HKEY_CURRENT_USER";
	  	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		try{
			var Wsh=new ActiveXObject("WScript.Shell");
	      	HKEY_Key="header";
	      	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	      	HKEY_Key="footer";
	      	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}catch(e){
		}
	}

	function print(){
	
		  setPageNull();
			var  Bdhtml=window.document.body.innerHTML;
			var  sprnstr="startpint";
			var  eprnstr="endprint";
			var prnhtml=Bdhtml.substring(Bdhtml.indexOf(sprnstr)+17) ;
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			var printFrame = window.parent.frames["businessfrm"];
			printFrame.document.body.innerHTML=prnhtml;
			printFrame.document.execCommand('print');
			printFrame.document.body.innerHTML = Bdhtml;
	}
	

}
</script>
<style type="text/css">
#hr{ width: 800px; margin: auto; margin-top: 8px; margin-bottom: 18px;}
#but{width: 80px; margin-top: 5px;}
#sr_main{ font: 32px;}
</style>
</head>
<body  onload="startup()">
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
                   <div class="dingw">您可以在此查看丰收e支付电子回单</div>        
                      <div id="sr_main">
                       <form id="form1" name="form1" method="post" action="#" > 
                        <pe:hidden fieldList="CifNo,DeptId" skipNULL="true"/> 
                       	<input name="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}"/>
                       <input name="vBDate" type="hidden" value="${vBDate}"/>
                       <input name="TransTyp" type="hidden" value="${TransTyp}"/>
                        <input name="vEDate" type="hidden" value="${vEDate}"/>
                        <input name="pageNo" type="hidden" value="${pageNo}"/>                                                
                        <%--  <input name="PayerAcctName" type="hidden" value="${PayerAcctName}" /> --%>
                        <input name="Amount" type="hidden" value="<fmt:formatNumber value="${TransAmt}" pattern="#,###,###,##0.00"/>" />
                        <input type="hidden" name="AmountCapital" value="">
                        <input name="PayeeAcctName" type="hidden" value="${PayeeAcctName}" />
                        <input name="TransAcctNo" type="hidden" value="${TransAcctNo}" />
                        <input name="RCVACCTNO" type="hidden" value="${RCVACCTNO}" />
                        <input name="AmtAmount" type="hidden" value="${TransDateTime}" />
                        <input name="AmtAmount" type="hidden" value="${AmtAmount}" />

                       <input name="vBDate_return" type="hidden" value="${vBDate}"/>
                       <input name="TransTypCd_return" type="hidden" value="${TransTypCd}"/>
                       <input name="vEDate_return" type="hidden" value="${vEDate}"/>
                       <input name="pageNo_return" type="hidden" value="${pageNo}"/>                                                
                       <input name="PayerAcctNbr" type="hidden" value="${PayerAcctNbr}"/>
                       <input name="returnState" type="hidden" value="return"/>
                        
                       <!--startpint-->
					<table width="800" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" style="font-size:14px;">
				            <tr class="lz_bd1_2">
				             
				              <td width="81%" colspan="2" align="left" class="lz_bd1_1" height="35px;"> 浙江农信丰收e支付电子回单</td>
				            
				            </tr>
</table>
				  <hr id="hr"/>
				  <div id="header">
				  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"  style="font-size:12px;">
                    <c:if test="${ TransTypCd=='00'}" >	
                    <tr>
                      <td width="90" align="left" valign="middle" class="lz_bd1_yw2">&nbsp;&nbsp;付款人户名：</td>
                      <td width="310" align="left" valign="middle" class="lz_bd1_yw">
                      ${PayerAcctName}</td>
						<td  align="right">付款人卡号：</td>
						<td> ${PayerAcctNbr}</td>
                    </tr>
                    </c:if>
                    <c:if test="${ TransTypCd=='01'}" >
                    <tr>
                      <td width="80" align="left" valign="middle" class="lz_bd1_yw2">&nbsp;&nbsp;</td>
                      <td width="310" align="left" valign="middle" class="lz_bd1_yw">
                      </td>
						<td  align="right"></td>
						<td> </td>
                    </tr>
                    </c:if>	
                    <tr>
                      <td  height="24" align="right" valign="middle" class="lz_bd1_yw2">&nbsp;&nbsp;金额：</td>
                      <td  align="left" valign="middle" class="lz_bd1_yw"><fmt:formatNumber value="${TransAmt}" pattern="#,###,###,##0.00"></fmt:formatNumber></td>
				      <td  align="right" valign="middle" class="lz_bd1_yw">&nbsp;&nbsp;&nbsp;手续费：</td>
				      <td  align="left" valign="middle" class="lz_bd1_yw"><fmt:formatNumber value="${FeeAmt}" pattern="#,###,###,##0.00"/> </td>
                    </tr>
                    <tr class="lz_bd1_4">
                      <td height="40"  align="right" valign="middle" class="lz_bd1_yw2">金额：</td>
                      <td align="left">(大写)<span id="fff"></span></td>
                      <td  align="right"><span style="width: 300px;">&nbsp;&nbsp;&nbsp;</span>币种：</td>
                      <td>人民币</td>
                    </tr>
                    <tr>
                      <td height="140" colspan="2" align="left" valign="top" class="lz_bd1_yw"><br />
                        &nbsp;&nbsp;交易时间：
                        <c:out value="${TransTime}"/>
                        <br />
                        &nbsp;&nbsp;钞汇标志：
      钞<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间戳：&nbsp;<% 
									java.text.SimpleDateFormat formatte = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
									java.util.Date currentTim = new java.util.Date();//得到当前系统时间 

									String str_date = formatte.format(currentTim); //将日期时间格式化 
									out.print(str_date);
								%>&nbsp;</td>
                      <td colspan="2" align="right" valign="middle" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td  height="134">&nbsp;</td>
                          <td width="110"><input name="ElecReceipt" type="hidden" value="
       浙江农信丰收e电子回单付款人户名： <c:out value='${PayerAcctName}'/>,
				              				付款人卡号：<c:out value='${PayerAcctNbr}'/>,
				              				手续费：<c:out value='${FeeAmt}'/>,
											币种：人民币,金额：<c:out value='${AmtAmount}'/>">
                   <img alt="扫一扫" name="QRcode" width="110" height="110" id="QRcode" /></td>
                        </tr>
                      </table></td>
                    </tr>
                  </table>
                  <!--endprint-->
				  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="lz_bd1_5 lz_bd2">
				            <tr>
				              <td width="60%" height="30" align="left" valign="middle" bgcolor="#f7f7f7">&nbsp;&nbsp;重要提示：&nbsp;&nbsp;
				              		本回单不作为收款方发货依据，请勿重复记账<br />
				              </td>
				              <td align="right" valign="middle" bgcolor="#f7f7f7">
				              	&nbsp;&nbsp;打印日期：&nbsp;&nbsp;
				              	<% 
									java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
									java.util.Date currentTime = new java.util.Date();//得到当前系统时间 

									String str_date1 = formatter.format(currentTime); //将日期时间格式化 
									out.print(str_date1);
								%>&nbsp;&nbsp;
				              </td>
				            </tr>
				            <tr>
				            <td>&nbsp;</td>
				            <td align="right">
				            <input onclick="print()" type="button" value="打印" id="but"  /> <input type="button" id="but" value="关闭" onclick="Canceloo();"/>
				            
				            </td>
				            </tr>
</table>
</div>
                        <div class="btn_big">
                        </div>

                     </form>
       
     </div>
        <div class="wxts">
           <div class="tub"></div>
             <div class="wxts_main">
       </div>
                        </div>
             </div>
           </div>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
 
 <div class="fuc hide" id="fuc">
       <div class="fuc_main">
            <div class="fuc_main_b"> 浙江农村信用社银行"卡密支付"服务协议 <a href="###" id="close-box"><img src="images/cha.png"></a></div>

       </div>
 </div>
</body>
</html>
