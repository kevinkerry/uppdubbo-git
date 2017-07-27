<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/pe.tld" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global.css" type="text/css"/>
	<title>浙江农信银行卡支付</title>
	<script language="javascript" type="text/javascript" src="script.do"></script>
	<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
	<script language="javascript" type="text/javascript" src="js/writeObject.js"></script>
	<script>
		$(function(){
			
			$("#span_viewamt").html("￥" + formatNum($("#money1").val(),2))
			
			function formatNum(s,n){
				n=n>0&&n<20?n:2;
				s=parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
				var l=s.split(".")[0].split("").reverse();
				r=s.split(".")[1];
				t="";
				for(var i = 0;i<l.length;i++){
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

			 $('#CardValidM').blur(function() {
				 var validM = $('#CardValidM').val();
				 var reg = /^(0|1)[0-9]{1}$/;
				 if(!reg.test(validM)){
					 $('#ValidTip').text("有效期月份输入错误");
				 }else{
					 if(validM=="00"){
						 $('#ValidTip').text("有效期月份输入错误");
					 }else{
						 $('#ValidTip').text("*");
					 }
				 }
			});

			 $('#CardValidY').blur(function() {
				 var validM = $('#CardValidY').val();
				 var reg = /^[0-9]{2}$/;
				 if(!reg.test(validM)){
					 $('#ValidTip').text("有效期年份输入错误");
				 }else{
					 $('#ValidTip').text("*");
				 }
			});

			 $('#CVV2').blur(function() {
				 var validM = $('#CVV2').val();
				 var reg = /^[0-9]{3}$/;
				 if(!reg.test(validM)){
					 $('#CVV2Tip').text("CVV2输入错误");
				 }else{
					 $('#CVV2Tip').text("*");
				 }
			});


			
			$('#cancelDebit').click(function() {
				document.forms[0].action="backCardPwdPre.do";
				document.forms[0].submit();
			});
			$('#cancelCredit').click(function() {
				document.forms[0].action="backCardPwdPre.do";
				document.forms[0].submit();
			});
			var smsCodeBackv = document.getElementById("SmsCodeBack").value;
			if(smsCodeBackv!=null&&parseInt(smsCodeBackv)<=120&&parseInt(smsCodeBackv)>0){
				document.getElementById("SMSbutton").value = smsCodeBackv + "秒";
				$("#SMSbutton")
				.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
				curCount = parseInt(smsCodeBackv);
				document.getElementById("SMSbutton").value = curCount
				+ "秒";
				InterValObj = window.setInterval(SetRemainTime,
					1000);
			}
			
		});
		function load()
		{
			//reloadTokenImg();
			//getSMS();
			hiddenSomePhoneNo();
			if (!isIE())
				//如果是非IE浏览器，则调用此函数，为控件添加事件处理函数。
				doAdd();
		}

		function doAdd()
		{	
		    //获取对象
		    var powerpass = document.getElementById("powerpass");
		    //添加Password控件的回车事件，如果收到此事件，则触发OnPassEventReturn()函数
		    addEvent(powerpass, "EventReturn",OnPassEventReturn);
		  	//添加Password控件的Tab事件，如果收到此事件，则触发OnPassEventTab()函数
		    addEvent(powerpass, "EventTab",OnPassEventTab);
		  	//添加Password控件的密码强弱度事件，如果收到此事件，则触发OnEventDegree(arg1)函数
		    addEvent(powerpass, "EventDegree",OnEventDegree);
		}
		function addEvent(obj, name, func)
		{
		    obj.addEventListener(name, func, false); 
		}

		function OnPassEventReturn()
		{
			//在收到Password控件上的回车事件时，将焦点放在id为login的标签上。
			document.getElementById("login").focus();
		}

		function OnPassEventTab()
		{
			//在收到Password控件上的Tab事件时，将焦点放在id为login的标签上。
			document.getElementById("login").focus();
		}

		function OnEventDegree(arg1)
		{
			/* var degree = "";
			if(arg1 == "W")
			{
				degree = "弱";
			}
			else if(arg1 == "M")
			{
				degree = "中";
			}
			else if(arg1 == "S")
			{
				degree = "强";
			}
			window.document.getElementById("EEE").innerHTML = "密码强度:" + degree; */
		}
		//获取服务器时间戳
		var ts="<%=System.currentTimeMillis()%>";
		function doIt(obj){
			//获取密码密文
			var password =getIBSPassword("powerpass", ts ,"EEE", "密码输入错误：");
			if(password==null){
				return;
			}
			$("#doItButton").attr("disabled", true);//禁用按钮
			document.forms[0].Password.value = password;
			document.forms[0].action="cardPwdPay.do";
			document.forms[0].submit();
			//post2SRV('cardPwdPay.do', document.forms[0] , obj,'EEE', null); 
		}
		function doItForCredit(obj){
			$("#doItButton").attr("disabled", true);//禁用按钮
			document.forms[0].action="cardPwdPay.do";
			document.forms[0].submit();
			//post2SRV('cardPwdPay.do', document.forms[0] , obj,'EEE', null); 
		}

		var InterValObj; //timer变量,控制时间
		var count = 120; //间隔函数,1秒执行
		var curCount; //当前剩余秒数
		function getSMS(){
			curCount=count;
			$("#SMSbutton").attr("disabled","disabled");//禁用按钮
			$("#SMSbutton").css("background-image","url(images/images/fanhui.png)").css("color","#004595");
			document.getElementById("SMSbutton").value=curCount+"秒";
			var phone = document.forms[0].PayerPhoneNo.value;
			var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
			var transAmt = document.forms[0].TransAmt.value;
			var payerAcctDeptNbr = document.forms[0].PayerAcctDeptNbr.value;
			
			var oparams = new Array(
					new Pair("PayerPhoneNo", phone),
					new Pair("PayerAcctDeptNbr", payerAcctDeptNbr),
					new Pair("PayerAcctNbr", PayerAcctNbr),
					new Pair("TransAmt", transAmt),
					new Pair("OperateType", "0"),
					new Pair("TransCode","UPP10003")
			);
			postData2SRVWithCallback("SMS.do",PEGetPostData(oparams),function(success, message){
		 		if(!success){}else{
		 			
		 			var obj = eval('(' + message + ')');
		 			if(obj.SmsSqenbr != null){
						$("#SmsSqenbr").val(obj.SmsSqenbr);
					}
		 		}
		 	});
		 	//每秒调用
		 	InterValObj=window.setInterval(SetRemainTime,1000);
		}
		function SetRemainTime() {
			if(curCount == 0){
				window.clearInterval(InterValObj);//停止计时器
				$("#SMSbutton").removeAttr("disabled");//启用按钮
				$("#SMSbutton").css("background-image","url(images/images/xiayi.png)");
				document.getElementById("SMSbutton").value="重新获取";
			} else {
				curCount--;
				document.getElementById("SMSbutton").value=curCount+"秒"
				$("#SMSbutton").css("color","#004595");
				document.getElementById("SmsCodeBack").value = curCount;
			}
		}

		function hiddenSomePhoneNo(){
			var phone = document.forms[0].PayerPhoneNo.value;
			var heddenPhone = phone.substring(0,3)+"****"+phone.substring(phone.length-4,phone.length)
			document.getElementById("PhoneSpan").innerHTML=heddenPhone;
			}

		//function reloadTokenImg(clickObj) {
		//	if($('#_vTokenName').val()&&clickObj)
		//		$('#_vTokenName').trigger("blur");
		//	document.getElementById('_tokenImg').src="GenTokenImg.do"+"?random="+Math.random();
		//} 
	</script>
</head>

<body onload="load();" oncontextmenu="return false" ondragstart="return false">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/images/csii1.png" /></div>
           <div class="top_r">
                <div class="phone"><img src="images/images/phone.png" /><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1"></div>${MerDate }</div>
           </div>
      </div>
      <div class="content" style="height: 600px;">
      <form action="#" method="post">
		<pe:hidden fieldList="PayTypCd,PayerCardTypCd,PayerAcctNbr,CustCifNbr,PayerPhoneNo,OrderId,MobileNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,PayerAcctDeptNbr,PayerAcctName,Signature" skipNULL="false"/>
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
                        <td  class="blue">${OrderId }</td>
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
                       <div class="dingw2 dingw">您的位置：丰收卡支付 > 确认支付</div>
                       <div class="buz2a buz">
                            <div class="buz1">1.填写信息</div>  
                            <div class="white buz2">2.确认支付</div>        
                            <div class="buz3">3.支付成功</div>
                    </div>
                        <div class="sr_main">
                              <table width="540" border="0" >
                                <tr>
                                  <td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td  width="70%"><span style="font:bold 15px '微软雅黑';">${ PayerAcctNbr}</span></td>
                                </tr>
                                <tr>
                                  <td class="right">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
                                  <td  width="70%"><span style="font:bold 15px '微软雅黑';">${ TransAmt}</span></td>
                                </tr>
                                
                                <c:if test="${ PayerCardTypCd=='1'}">
                                <tr>
                                  <td class="right">银行卡密码：</td>
                                  <input type="hidden" name="Password" value="" />
                                  <td width="70%" style="padding-top:18px;">
	                                  <script type="text/javascript">writePassObject("powerpass",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>
	                                  <span id='EEE' name='EEE' class="cheng">*</span>
                                  </td>
                                </tr>
                                </c:if>
                                
                                <c:if test="${ PayerCardTypCd=='2'}">
                                <tr>
			  						<td class="right">信用卡有效期：</td>
		  							<td width="70%">
								  		<input type="text" maxlength="2" id="CardValidM" name="CardValidM" class="small_sr" value="" style="width:30px" /><span>&nbsp;月&nbsp;</span>
								  		<input type="text" maxlength="2" id="CardValidY" name="CardValidY" class="small_sr" value="" style="width:30px"  /><span>&nbsp;年</span>
								  		<span id="ValidTip" class="cheng">*</span>
								  	</td>
								</tr>
								<tr>
								  	<td class="right">信用卡CVV2：</td>
								  	<td width="70%"><input type="text" maxlength="3" id="CVV2" name="CVV2" class="small_sr" value="" onkeypress="mustDigit()" /><span id="CVV2Tip" class="cheng">*</span></td>
								</tr>
	                            </c:if>
	                            
                                <tr>
                                 <td colspan="2"> 
                                     <div id="mobile" class="tis">请输入您手机（<span id="PhoneSpan"></span>）接收到的短信验证码。</div>   
                                 </td>
                               </tr>
                                <tr>
                                  <td class="right">短信验证码： </td>
                                  <td><input style="font:bold 15px '微软雅黑';width: 120px;" maxlength="6" name="MessageCode" type="text" class="small_sr" /> 
                                  <span class="btn_big">
                                   <input id="SMSbutton" class="chongx" type="button"  onclick="getSMS();" value="点击获取" style="background-image:url(images/images/xiayi.png);border:none;color:#fff;font-size:16px;font-weight:bold;" />
                                   </span>
                                  <span class="cheng">*</span>
                                 <input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                                 <input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
                                 </td>
                                </tr>
                                <tr>
                                 <td colspan="2" style="line-height:25px;margin-left:30px; padding-left:26px;"> &nbsp;</td>
                               </tr>
                                
                                <!--
                                
                                 <tr>
                                  <td class="right">附 &nbsp;&nbsp; 加&nbsp;&nbsp;   码： </td>
                                  <td>
	                                  <input id="_vTokenName" maxlength="4" name="_vTokenName" type="text" class="small_sr" value=""/><span class="cheng">*</span>
	                                  <img id="_tokenImg" src="" class="yzm_img"> 
	                                  <span ><a href="#" onclick="reloadTokenImg(this);" style="font-size:11px">看不清楚，换一张</a></span>
                                  </td>
                                </tr>

                              --></table>  	
                        </div>
                        <c:if test="${ PayerCardTypCd=='2'}">
                        <div class="btn_big">
                              <input type="button" id="cancelDebit" value="取消" class="chong_btn" style="background-image:url(images/images/xiayi.png);color:#fff;" />
                              <input type="button"  id="doItButton" value="下一步" onclick="doIt(this);" class="xia_btn" style="background-image:url(images/images/xiayi.png);"/>
                        </div>
                        </c:if>
                        
                        <c:if test="${ PayerCardTypCd=='1'}">
                        <div class="btn_big">
                              <input type="button"  id="cancelCredit" value="取消" class="chong_btn" style="background-image:url(images/images/xiayi.png);color:#fff;" />
                              <input type="button"  id="doItButton" value="下一步" onclick="doItForCredit(this);" class="xia_btn" style="background-image:url(images/images/xiayi.png);" />
                        </div>
                        </c:if>
                        <div class="wxts">
                        	<div class="wxts_main">
	                              <p>&nbsp;</p>
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
