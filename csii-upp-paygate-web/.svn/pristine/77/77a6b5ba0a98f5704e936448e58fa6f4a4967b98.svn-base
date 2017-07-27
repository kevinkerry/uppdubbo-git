<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link  href="css/css.css" rel="stylesheet" type="text/css" />
	<link  rel="stylesheet" href="css/global.css" type="text/css" />
	<title>浙江农信丰收e支付</title>
	<!-- 浙江农信网银支付错误页面    2015.07.03  ZGB -->
	<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
	<script src="js/common.js" language="javascript" type="text/javascript"></script>
	<script>
	    
		$(function(){
			
			$("#span_viewamt").html("￥" + formatNum($("#money1").val(),2))
			
			function formatNum(s,n){
				n=n>0&&n<20?n:2;
				s=parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
				var l=s.split(".")[0].split("").reverse();
				r=s.split(".")[1];
				t="";
				for(i = 0;i<l.length;i++){
					t+=l[i]+((i+1)%3==0&&(i+1)!=l.length?",":"");
				}
				return t.split("").reverse().join("")+"."+r;
			}
			
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
				document.forms[0].RespCode.value = "FS";
				document.forms[0].action="backCardPwdPre.do";
				document.forms[0].submit();
			});
		});
	</script>
</head>

<body>
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/csii1.png" /></div>
           <div class="top_r">
                <div class="phone"><img src="images/images/phone.png" /><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1">${MerDate }</div></div>
           </div>
      </div>
      <div class="content" style="height: 600px;">
       <form action="#" method="post">
		<pe:hidden fieldList="AcctType,PayerAcctNbr,CifNo,PayerPhoneNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,DeptId,AcctName,Signature,RespCode,SmsSqenbr" skipNULL="false"/>
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
                        <td class="cheng"><span id="span_viewamt"></span><input  maxlength="15" type="hidden" id="money1" value="${TransAmt}"></input></td>
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
                 <div id="container"><div id="example"></div></div> 
				<!--图片轮播结束-->
           </div>
           
           <div class="con_r">
	           <div class="con_r_t">丰收e支付</div>
	           <div class="con_r_main">
               <div class="dingw2 dingw">您的位置：丰收e支付 > 错误提示</div> 
                 <div class="sr_main3 sr_main2" >
                     <div class="duig"><img src="images/images/cuo.png" style="margin-top:30px;" /></div>   
                     <div class="duig_r">
                           <h3>交易失败</h3>
                           <p>错误代码：  ${ RespCode}</p>
                           <p>错误信息：  ${ RespMessage}</p><!--
                 		   
                 		   <c:if test="${ RespCode=='FS0001'}">
                 		   		如您尚未开通，请点击（<a href="/paygate/FS01.do">www.csii.com</a>）注册丰收e支付或进入（<a href="/paygate/FS01.do">浙江农信官网</a>），点击丰收e支付进行注册
                 		   </c:if>
                 		   <c:if test="${ RespCode!='FS0001'}">
                 		   		<a id="goback" href="###">返回</a>
                 		   </c:if>
                     --></div>
                     <div style="float:left;font-size:11px;">
                     	 <c:if test="${ RespCode=='FS0001'}">
                 		   		如您尚未开通，请点击<a href="/paygate/FS01.do">注册丰收e支付</a>或进入浙江农信官网（<a href="/paygate/FS01.do">www.csii.com</a>）点击丰收e支付进行注册
                 		 </c:if>
                 		 <c:if test="${ RespCode!='FS0001'}">
                 		 	<a id="goback" href="###">返回</a>
                 		 </c:if>
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


    