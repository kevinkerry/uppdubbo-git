<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>
<%
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("ddHHmmss");
String orderId = sdf.format(new java.util.Date());

java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("yyyyMMdd HH:mm:ss");
String timestamp = timeFormat.format(new java.util.Date());
java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
String merdatetime = sdf1.format(new java.util.Date());
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link  href="css/css.css" rel="stylesheet" type="text/css" />
	<link  rel="stylesheet" href="css/global.css" type="text/css" />
	<title>浙江农信银行卡支付</title>
	<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
	<script>
		$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'img/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true,
				animationStart: function(){
					$('.caption').animate({
						bottom:-35
					},100);
				},
				animationComplete: function(current){
					$('.caption').animate({
						bottom:0
					},200);
					if (window.console && console.log) {
						// example return of current slide number
						console.log(current);
					};
				}
			});
			$('#goback').click(function() {
				document.forms[0].action="backCardPwdPre.do";
				document.forms[0].submit();
			});
		});
	</script>
</head>

<body>
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/images/csii1.png" /></div>
           <div class="top_r">
                <div class="phone"><img src="images/images/phone.png" /><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1"><%=timestamp%></div></div>
           </div>
      </div>
      <div class="content">
       		<form name="form1" action="#" method="post">
     	    <pe:hidden fieldList="AcctType,PayerAcctNbr,CifNo,PayerPhoneNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,DeptId,AcctName,Signature,SmsSqenbr" skipNULL="true"/>
           <div class="con_l">
                 <div class="con_l_t"><img src="images/images/wddd.png" style="margin-top:-2px;" /></div>
                 <div class="con_l_m">
                     <table width="280" align="center" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="right hui">商   户：</td>
                        <td class="blue">${MerchantName }</td>
                      </tr>
                      <tr>
                        <td class="right hui">金   额：</td>
                        <td class="cheng">￥${TransAmt }</td>
                      </tr>
                      <tr>
                        <td class="right hui">日   期：</td>
                        <td>${MerDate }</td>
                      </tr>
                      <tr>
                        <td class="right hui">订单号：</td>
                        <td  class="blue">${ OrderId}</td>
                      </tr>
                      <tr>
                        <td class="right hui">币   种：</td>
                        <td>人民币</td>
                      </tr>
                    </table> 
                    
                 </div>
<!--图片轮播开始-->
                 <div id="container">
		<div id="example"> 
			<!--<div id="slides">
				<div class="slides_container">
					<div>
						<a href="###" title="145.365 - Happy Bokeh Thursday! | Flickr - Photo Sharing!" target="_blank"><img src="images/images/guangao.png" width="262" height="178" alt="Slide 1" /></a> 
					</div>
					<div>
						<a href="###" title="Taxi | Flickr - Photo Sharing!" target="_blank"><img src="images/images/guangao.png" width="262" height="178" alt="Slide 2" /></a> 
					</div> 
					 
					 
				</div>
				<a href="#" class="prev"><img src="images/images/arrow-prev.png" width="19" height="43" alt="Arrow Prev" /></a>
				<a href="#" class="next"><img src="images/images/arrow-next.png" width="19" height="43" alt="Arrow Next" /></a>
			</div> 
		--></div> 
	</div> 
<!--图片轮播结束-->
    
    
           </div>
           <div class="con_r">
                  <div class="con_r_t">丰收卡支付</div>
                  <div class="con_r_main">
                       <div class="dingw2 dingw">您的位置：丰收卡支付 > 信息填写</div> 
                        <div class="sr_main3 sr_main2" >
                            <div class="duig"><img src="images/images/cuo.png" style="margin-top:30px;" /></div>   
                            <div class="duig_r">
                                  <h3>错误提示</h3>
                                  <p>错误代码：  ${ RespCode}</p>
                                  <p>错误信息：  ${ RespMessage}</p>
                            </div>
                                 	
                        </div> 
                         
                  </div>
                  
           </div>
           </form>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
</body>
</html>


    