<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/pe.tld" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global.css" type="text/css" />
	<title>浙江农信丰收e支付</title>
	<script language="javascript" type="text/javascript"  src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript"  src="js/slides.min.jquery.js"></script>
</head>

<body onload="countDown();">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/images/csii1.png" /></div>
           <div class="top_r">
                <div class="phone"><img src="images/images/phone.png"/><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1">${MerDate}</div></div>
           </div>
      </div>
      <div class="content" style="height: 600px;">
           <div class="con_l">
                 <div class="con_l_t"><img src="images/images/wddd.png" style="margin-top:-2px;" /></div>
                 <div class="con_l_m">
                     <table width="280" align="center" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="right hui">商   户：</td>
                        <td class="blue">${MerchantName}</td>
                      </tr>
                      <tr>
                        <td class="right hui">金   额：</td>
                        <td class="cheng"><span id="span_viewamt"></span><input  maxlength="15" type="hidden" id="money1" value="${TransAmt}"></input></td>
                      </tr>
                      <tr>
                        <td class="right hui">日   期：</td>
                        <td>${MerDate}</td>
                      </tr>
                      <tr>
                        <td class="right hui">订单号：</td>
                        <td  class="blue">${OrderId}</td>
                      </tr>
                      <tr>
                        <td class="right hui">币   种：</td>
                        <td>人民币</td>
                      </tr>
                    </table> 
                    
                 </div>
                 <div id="container">
		<div id="example"> 
			</div> 
	</div> 
    
           </div>
           <div class="con_r">
                  <div class="con_r_t">丰收e支付</div>
                  <div class="con_r_main">
                       <div class="dingw2 dingw">您的位置：丰收e支付 > 支付结果</div>
                       <div class="buz3a buz">
                            <div class="buz1">1.填写信息</div>  
                            <div class="white buz2">2.确认支付</div>        
                            <div class="white buz3">3.支付成功</div>
                    </div>
                    <form action="#" method="post">
    					<pe:hidden fieldList="AcctType,PayerAcctNbr,CifNo,PayerPhoneNo,PlainContext,Plain,ChannelId" skipNULL="false"/>
                    </form>
                        <div class="sr_main2">
                            <div class="duig"><img src="images/images/duig.png" /></div>   
                            <div class="duig_r">
                                  <h3>订单已受理</h3>
                                  <p>流 水 号：  ${MerSeqNo}</p>
                                  <p>订 单 号：  ${OrderId}</p>
                                  <p>金     额：  ￥${TransAmt}</p>  
                                  <a href="###" id="goback" onclick="goback('${MerURL1}');"> 返回</a>（<span id="time" style="width:5px;font-size:16px;">5</span>秒后自动返回，点击按钮立即返回）
                                  <input id="MerURL1" value="${MerURL1}" type="hidden"/>
                            </div>
                                 	
                        </div> 
                        <div class="wxts">
                              <div class="wxts_main">
                                   <h3>&nbsp;</h3>
                                   <p>&nbsp;</p>
                                   <p>&nbsp;</p>
                                   <p>&nbsp;</p>
                                   <p>&nbsp;</p>
                              </div>
                        </div>
                  </div>
                  
           </div>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
  <script>
		$(function(){
			$("#span_viewamt").html("￥" + formatNum($("#money1").val(), 2));
			
			function formatNum(s, n){
				n = n > 0 && n < 20 ? n : 2;
				s=parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
				var l=s.split(".")[0].split("").reverse();
				r=s.split(".")[1];
				t="";
				for(var i = 0; i < l.length; i++){
					t += l[i] + ((i+1)%3 == 0 && (i+1) != l.length ? ",": "");
				}
				return t.split("").reverse().join("")+"."+r;
			}
		});
		function goback(url){
			document.forms[0].action=url;
			document.forms[0].submit();
		}
		function startup(){
			setTimeout(function(){
				$('#goback').click();
				},5000);
		}
		
		var tip = 5;
		var inter=setInterval("countDown()",1000);
		
		function countDown(){
	       var MerURL1=$("#MerURL1").val();
	       $("#time").html(tip);
	       tip = tip - 1;
	       if(tip <= 0){
		     location.href=MerURL1;
		     clearInterval(inter);
	       }
		}
	</script>
</body>
</html>
