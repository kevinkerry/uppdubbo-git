<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付查询交易明细</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="script.do"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script >
function gobackhistory(){
	//  window.history.back(-1);
   document.forms[0].action="QryTrsDetailPre.do";
   document.forms[0].submit();
}
function startup(){
	$("#s1").html(parseFloat($("#s1").html()).toFixed(2));
	$("#s2").html(parseFloat($("#s2").html()).toFixed(2));
		
}
</script>

<script src="js/style.js"></script>
<style>
.table{ width:700; border:1px solid #EBEBEB; margin: auto; margin-top: 40px; margin-bottom: 30px;}
.table td{ border:1px solid #EBEBEB; background-color:#F0FFFF; }

</style>
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
                       <form id="form1" name="form1" method="post" > 
                        <pe:hidden fieldList="CifNo,DeptId" skipNULL="true"/> 
                       <input name="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}"/>
                       <input name="vBDate_return" type="hidden" value="${vBDate}"/>
                       <input name="TransTypCd_return" type="hidden" value="${TransTypCd}"/>
                       <input name="vEDate_return" type="hidden" value="${vEDate}"/>
                       <input name="pageNum_return" type="hidden" value="${pageNum}"/>                                                
                       <input name="PayerAcctNbr" type="hidden" value="${PayerAcctNbr}"/>
                       <input name="returnState" type="hidden" value="return"/>
                             <table width="700" class="table" cellpadding="1" cellspacing="0">
                            <c:if test="${TransTypCd=='00'}" >	
                               <tr>
		                          <td align="right">付款人户名：</td> 
		                          <td align="left">&nbsp;&nbsp;${PayerAcctName}</td>
                                  <td align="right" >付款人卡号：</td>
                                  <td align="left">&nbsp;&nbsp;${PayerAcctNbr} </td> 
                               </tr>
                            </c:if>  
                               <tr>
	                               <td align="right" >付款金额：</td>
	                               <td align="left">&nbsp;&nbsp;<span style="color: red;" id="s1">${TransAmt}</span></td>
	                               <td align="right" >&nbsp;&nbsp;钞汇标志：</td>
	                               <td align="left">&nbsp;&nbsp;钞</td>
	                               
                               </tr>
                              
                               <tr>
	                               <td align="right" >手续费：</td>
	                               <td align="left">&nbsp;&nbsp;<span style="color: red;" id="s2">${FeeAmt}</span></td>
	                               <td align="right"><strong>交易状态：</strong></td>
	                               <td>&nbsp;&nbsp;${TransStatus}</td>
                               </tr>
                               <tr>
	                               <td  align="right" ><strong>交易日期：</strong></td>
	                               <td colspan="3" >&nbsp;&nbsp;${TransTime}	<%-- <%
									java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	                               java.util.Date currentTime=${TransTime};
	                               String str_date1 = formatter.format(currentTime);
	           
									out.print(str_date1);
									%> --%></td>
                               </tr>
                   </table>
                        <div class="btn_big">
                    <input type="button"  value="返回" class="xia_btn" onclick="gobackhistory();" />
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
