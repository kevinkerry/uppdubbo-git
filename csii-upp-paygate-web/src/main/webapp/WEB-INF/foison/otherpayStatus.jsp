<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付注册</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script>
function onload(){
	var AcctNo = $('#PayerAcctNbr').val();
	var acctno= AcctNo.substr(AcctNo.length-4);
	$('#acct').text(acctno);
}


</script>	

</head>

<body onload="onload();">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/csii1.png"></div>
           <div class="top_r">
                <div class="phone"><img src="images/phone.png"><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1"></div><div class="tuic"></div></div>
           </div>
      </div>
      <div class="main-nav">
        <ul class="main-nav-in"> 
           <li><a href="/paygate/FS01.do">注册</a></li>
            <li><a href="/paygate/FS02.do">查询交易明细</a></li>
              <li><a href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
         </ul>   
      </div>
      <div class="content">          
           <div class="con_r">               
             <div class="con_r_main">
                       <div class="dingw">您可以在此注册丰收e支付服务</div>
                      
                        <div class="sr_main">
                     <input name="PayerAcctNbr" id="PayerAcctNbr"  type="hidden" value="${PayerAcctNbr}"/>
                      <form id="form1" name="form1" method="post" action="#"> 
                       <div id="height">&nbsp;</div>
                              <table width="500" border="0" style="margin:20px auto; a:hover{text-decoration:none;} " >
                                <tr>
                                <td width="83"><img src="images/phone/dui.png"/></td>
                                  <td  align="center"><%-- 您尾号为<a id="acct" name="acct" >${acct}</a>的卡丰收e支付 --%>丰收e支付注册成功，欢迎再次使用</td>
                                
                                </tr>
                
                              
                              </table>  
                               <div id="height">&nbsp;</div>
                               <div class="btn_big"></div>	
                           </form>
                        <div class="wxts">
                              
                              <div class="wxts_main">
                     
                              </div>
                              
                        </div>
             </div>
                 

           </div>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
 <script type="text/javascript">
 	$(function(){
		$('#open-box').click(function(){
			$('#fuc').removeClass('hide');
			});
		$('#close-box').click(function(){
			$('#fuc').addClass('hide');
		});
		});
 </script>
 <div class="fuc hide" id="fuc">
       <div class="fuc_main">
            <div class="fuc_main_b"> 浙江农村信用社银行"卡密支付"服务协议 <a href="###" id="close-box"><img src="images/cha.png"></a></div>

       </div>
 </div>
</body>
</html>












