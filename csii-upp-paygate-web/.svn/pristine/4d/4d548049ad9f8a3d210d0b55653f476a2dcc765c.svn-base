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
<title>丰收e支付调整交易限额</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
	<script>
	function Cancel1(){
	      window.location.href="FS03.do";
			}
	function formatamt(){
		var perTransLimit='${PerTransLimit}';
		var perDayLimit='${PerDayLimit}';
		
		if(perTransLimit.indexOf('.')>-1){
			perTransLimit=perTransLimit.substring(0, perTransLimit.indexOf('.'));
		}
		if(perDayLimit.indexOf('.')>-1){
			perDayLimit=perDayLimit.substring(0, perDayLimit.indexOf('.'));
		}
		document.getElementById('PerTransLimit1').innerHTML=perTransLimit+'.00元';
		document.getElementById('PerDayLimit1').innerHTML=perDayLimit+'.00元';
	}
	</script>
</head>

<body onload="formatamt();">
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
              <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;"  href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
         </ul> 
      
      </div>
      <div class="content">
           
           <div class="con_r">
                
                  
             <div class="con_r_main">
                       <div class="dingw">您可以在此调整丰收e支付交易限额</div>
                      
                        <div class="sr_main">
                       <form id="form1" name="form1" method="post" action="FS03ConfirmNext.do"> 
                        <pe:hidden fieldList="AcctType,CifNo,DeptId" skipNULL="true"/> 
                        <input name="PayerAcctNbr" id="textfield" value="${PayerAcctNbr}" type="hidden"/>
                        <input name="PayerPhoneNo" id="textfield" value="${PayerPhoneNo}" type="hidden"/>
                        <input name="PerTransLimit" id="PerTransLimit"  value="${PerTransLimit}" type="hidden" />
                        <input name="PerDayLimit" id="PerDayLimit"  value="${PerDayLimit}" type="hidden" />
                              <table width="550" border="0" style="margin:20px auto;" >
                                <tr>
                                  <td width="30%" class="right" style="letter-spacing: 2.15px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="50%">${PayerAcctNbr}</td>
                                  
                                </tr>
                           <tr>
                                  <td width="30%" class="right" style="letter-spacing: 4.1px">手&nbsp;机&nbsp;号&nbsp;码：</td>
                                  <td width="50%">${PayerPhoneNo}</td>
                                     
                                </tr>
                                 	
                                  <td width="30%" class="right" style="letter-spacing: 2.3px">单笔交易限额：</td>
                                 <td width="50%" id="PerTransLimit1">${PerTransLimit}.00元 </td>
                                 
                                </tr>
                                
                             <tr> 
                                  <td width="30%" class="right">日累计交易限额：</td>
                                 <td width="50%" id="PerDayLimit1">${PerDayLimit}.00元</td>
                                
                                </tr>
                          
                              </table>  	
               </div>
                        <div class="btn_big">
                             <input type="button"  value="取消"  onclick="Cancel1();" class="xia_btn"/>
                              <input type="submit"  value="确认" class="xia_btn"/>
                        </div>
                        </form>
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












