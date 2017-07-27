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
	var checkcode="no";
	$(function(){
		
		$("#span_viewamt1").html("￥" + formatNum($("#money1").val(),2));
		$("#span_viewamt2").html("￥" + formatNum($("#money2").val(),2));
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

			 $('#CardValidM').blur(function(n) {
				 var validM = $('#CardValidM').val();
				 if(validM < 10 && "0" != validM.substring(0,1) && "" != validM.substring(0,1)){
					 $('#CardValidM').val("0" + validM);
				 }
				 var reg = /^((0?[1-9])|(1[0-2]))$/;
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
			hiddenSomePhoneNo();
			reloadTokenImg();
			if (!isIE())
				//如果是非IE浏览器，则调用此函数，为控件添加事件处理函数。
				doAdd();
		}

		function doAdd()
		{	
		    //获取对象
		    var powerpass = document.getElementById("powerpass");
		    //添加PayerCardPwd控件的回车事件，如果收到此事件，则触发OnPassEventReturn()函数
		    addEvent(powerpass, "EventReturn",OnPassEventReturn);
		  	//添加PayerCardPwd控件的Tab事件，如果收到此事件，则触发OnPassEventTab()函数
		    addEvent(powerpass, "EventTab",OnPassEventTab);
		  	//添加PayerCardPwd控件的密码强弱度事件，如果收到此事件，则触发OnEventDegree(arg1)函数
		    addEvent(powerpass, "EventDegree",OnEventDegree);
		}
		function addEvent(obj, name, func)
		{
		    obj.addEventListener(name, func, false); 
		}

		function OnPassEventReturn()
		{
			//在收到PayerCardPwd控件上的回车事件时，将焦点放在id为login的标签上。
			document.getElementById("login").focus();
		}

		function OnPassEventTab()
		{
			//在收到PayerCardPwd控件上的Tab事件时，将焦点放在id为login的标签上。
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


		function checkLength(nodeName, length) {
		    var node = document.getElementById(nodeName).value;
		    if (length != node.length) {
		        return false;
		    }
		    return true;
		}
		function checkCode(){
		   if(document.getElementById("SMSbutton").disabled==false){
		    	$('#MessageCodeInfo').hide().html("请获取验证码").show(500);
		    	$("#doItButton").attr("disabled","disabled");
		   }       
		}		
		//
		//获取服务器时间戳
		var ts="<%=System.currentTimeMillis()%>";
		function doIt(obj){
			//获取密码密文
			 
			var payerCardPwd =getIBSPayerCardPwd("powerpass", ts ,"EEE", "");
			if(payerCardPwd==null){
				return;
			}else{
				$('#EEE').text("*");
			}
			if(false == checkLength("SmsCode",6)) {
				 $('#MessageCodeInfo').text("验证码格式错误");
             return ;
              }else{
           	   $('#MessageCodeInfo').text("*");
              }
			
			if ((document.forms[0].MsgFlag.value) == "000001") {
				if(true == checkLength("_vTokenName",0)){
					$('#checkTokeninfo').text("验证码为空");
					return;
				}
				if (checkcode != "yes") {
					$("#doItButton").attr("disabled", "disabled");
					return;
				}
			}
			$("#doItButton").attr("disabled", true);//禁用按钮
			document.forms[0].PayerCardPwd.value = payerCardPwd;
			document.forms[0].action="cardPwdPay.do";
			document.forms[0].submit();
			
		}
		function doItForCredit(obj){
			
         	 if(true == checkLength("CardValidM",0) || true == checkLength("CardValidY",0)) {
			 	$('#ValidTip').text(" 信用卡有效期年月不能为空！");
             }
             if(true == checkLength("CVV2",0)) {
				$('#CVV2Tip').text(" 卡背面末三位数不能为空！");
             }
             if(false == checkLength("SmsCode",6)) {
            	 $('#MessageCodeInfo').hide().html("验证码格式错误").show(500);
			 	return;
          	 }
             if($('#ValidTip').text()!="*" || $('#CVV2Tip').text()!="*"){
				return;
			}
             
			 if ((document.forms[0].MsgFlag.value) == "000001") {
				 if(true == checkLength("_vTokenName",0)){
		 				$('#checkTokeninfo').text("验证码为空");
		 				return;
		 			}
				 if (checkcode != "yes") {
						$("#doItButton").attr("disabled", "disabled");
						return;
					}
				}
			$("#doItButton").attr("disabled", true);//禁用按钮
			document.forms[0].action="cardPwdPay.do";
			document.forms[0].submit();
		}

		var InterValObj; //timer变量,控制时间
		var count = 120; //间隔函数,1秒执行
		var curCount; //当前剩余秒数
		function getSMS(){
			curCount=count;
			$('#MessageCodeInfo').hide().html(" *").show(500);
			$("#doItButton").attr("disabled",false);
			$("#SMSbutton").attr("disabled","disabled");//禁用按钮
			$("#SMSbutton").css("background-image","url(images/images/fanhui.png)").css("color","#004595");
			var phone = document.forms[0].PayerPhoneNo.value;
			var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
			var transAmt = document.forms[0].TransAmt.value;
			var payerAcctDeptNbr = document.forms[0].PayerAcctDeptNbr.value;
			
			var oparams = new Array(
					new Pair("PayerPhoneNo", phone),
					new Pair("PayerAcctDeptNbr", payerAcctDeptNbr),
					new Pair("PayerAcctNbr", PayerAcctNbr),
					new Pair("TransAmt", transAmt),
					new Pair("OperationType", "0"),
					new Pair("TransTypCd", "UPP10003")
			);
			postData2SRVWithCallback("SMS.do",PEGetPostData(oparams),function(success, message){
				if(!success){
					$("#SMSbutton").removeAttr("disabled");//启用按钮
					$("#SMSbutton").css("background-image","url(images/images/xiayi.png)");
					document.getElementById("SMSbutton").value="重新获取";

					$('#MessageCodeInfo').hide().html("短信发送失败").show(500);
				}
				else {
					var obj = eval('('+message+')');
					if(obj.SmsSqenbr != null){
						$("#SmsSqenbr").val(obj.SmsSqenbr);
					}
					if("PAY0100"==obj.RespCode){
						curCount = 30;
						$("#MessageCodeInfo").text(obj.RespMessage);
						document.getElementById("SMSbutton").value=curCount+"秒";
						InterValObj=window.setInterval(SetRemainTime,1000);
					} else {
						document.getElementById("SMSbutton").value=curCount+"秒";
						InterValObj=window.setInterval(SetRemainTime,1000);
					}
				}
		 	});
		 	//每秒调用
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
			var heddenPhone = phone.substring(0,3)+"****"+phone.substring(phone.length-4,phone.length);
			document.getElementById("PhoneSpan").innerHTML=heddenPhone;
		}
		function reloadTokenImg(clickObj) {
			if ($('#_vTokenName').val() && clickObj)
				$('#_vTokenName').trigger("blur");
			var img=document.getElementById('_tokenImg');
			if(img){ img.src = "GenTokenImg.do"
					+ "?random=" + Math.random();
			}
		}

		function checkTokenImgOnKeyUp() {
			var userInputToken = document.getElementById("_vTokenName").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage").style.display = "none";
			} else if (userInputToken.length == 4) {
				checkTokenImg();
			}
		}
		function checkTokenImg() {
			var userInputToken = document.getElementById("_vTokenName").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage").style.display = "none";
				checkcode="no";
				return;
			}
			var oparams = new Array(new Pair("_vTokenName", userInputToken));
			postData2SRVWithCallback(
					"ImageTokenVerify.do",
					PEGetPostData(oparams),
					function(flag, answer) {
						if (flag && !answer) {
							checkcode = "yes";
							document.getElementById("rightImage").src = "images/lug.jpg";
							document.getElementById("rightImage").style.display = "inline";
							$("#doItButton").attr("disabled", false);
							$('#checkTokeninfo').text("*");
						} else {
							document.getElementById("rightImage").src = "images/hongch.jpg";
							document.getElementById("rightImage").style.display = "inline";
							$("#doItButton").attr("disabled", true);
							$('#checkTokeninfo').text("*");
						}
					});
		}

	</script>
</head>

<body onload="load();" oncontextmenu="return false"
	ondragstart="return false">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/csii1.png" />
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/images/phone.png" /><span class="net"> <a
						href="http://www.csii.com/">www.csii.com </a></span>
				</div>
				<div class="time">
					<div class="time1"></div>${MerDate }
				</div>
			</div>
		</div>
		<div class="content" style="height: 600px;">
			<form action="#" method="post">
				<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
				<pe:hidden
					fieldList="PayTypCd,PayerCardTypCd,PayerAcctNbr,CustCifNbr,PayerPhoneNo,OrderId,MobileNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,PayerAcctDeptNbr,PayerAcctName,Signature,MsgFlag"
					skipNULL="false" />
				<div class="con_l">
					<div class="con_l_t">
						<img src="images/images/wddd.png" style="margin-top: -2px;" />
					</div>
					<div class="con_l_m">
						<table width="280" align="center" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td class="right hui">商 户：</td>
								<td class="blue">${MerchantName }</td>
							</tr>
							<tr>
								<td class="right hui">金 额：</td>
								<td class="cheng"><span id="span_viewamt1"></span><input  maxlength="15" type="hidden" id="money1" value="${TransAmt}"></input></td>
							</tr>
							<tr>
								<td class="right hui">日 期：</td>
								<td>${MerDate }</td>
							</tr>
							<tr>
								<td class="right hui">订单号：</td>
								<td class="blue">${OrderId }</td>
							</tr>
							<tr>
								<td class="right hui">币 种：</td>
								<td>人民币</td>
							</tr>
						</table>

					</div>
					<!--图片轮播开始-->
					<div id="container">
						<div id="example"></div>
					</div>
					<!--图片轮播结束-->
				</div>

				<div class="con_r">
					<div class="con_r_t">丰收卡支付</div>
					<div class="con_r_main" style="height: 512px;">
						<div class="dingw2 dingw">您的位置：丰收卡支付 > 确认支付</div>
						<div class="buz2a buz">
							<div class="buz1">1.填写信息</div>
							<div class="white buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div class="sr_main">
							<table width="540" border="0">
								<tr>
									<td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
									<td width="75%"><span style="font:bold 15px '微软雅黑';">${ PayerAcctNbr}</span> </td>
								</tr>
								<tr>
									<td class="right">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
									<td width="75%"><span style="font:bold 15px '微软雅黑';" id="span_viewamt2"></span><input  maxlength="15"  type="hidden" id="money2" value="${TransAmt}"></input></td>
								</tr>

								<c:if test="${ PayerCardTypCd=='1'}">
									<tr>
										<td class="right">银行卡密码：</td>
										<input type="hidden" name="PayerCardPwd" value="" />
										<td width="75%"><script type="text/javascript">writePassObject("powerpass",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>
											<span id='EEE' name='EEE' style="font-size: 12px;" class="cheng"> </span></td>
									</tr>
								</c:if>

								<c:if test="${ PayerCardTypCd=='2'}">
									<tr>
										<td class="right">信用卡有效期：</td>
										<td width="75%"><input autocomplete="off" type="text" maxlength="2"
											id="CardValidM" name="CardValidM" class="small_sr" value=""
											style="width: 30px" /><span>&nbsp;月&nbsp;</span> <input
											autocomplete="off" type="text" maxlength="2" id="CardValidY"
											name="CardValidY" class="small_sr" value=""
											style="width: 30px" /><span>&nbsp;年</span> <span
											id="ValidTip" style="font-size: 12px;" class="cheng">*</span></td>
									</tr>
									<tr>
										<td class="right">卡背面末三位数：</td>
										<td width="75%"><input autocomplete="off" type="text" maxlength="3"
											id="CVV2" name="CVV2" class="small_sr" value=""
											onkeypress="mustDigit()" /><span id="CVV2Tip" class="cheng">*</span></td>
									</tr>
								</c:if>

								<tr>
									<td colspan="2">
										<div id="mobile" class="tis">
											请输入您手机（<span id="PhoneSpan"></span>）接收到的短信验证码。
										</div>
									</td>
								</tr>
								<tr>
									<td class="right">短信验证码：</td>
									<td width="75%" height="70px;"><input style="font:bold 15px '微软雅黑';height: 35px;" maxlength="6" onclick="checkCode()" autocomplete="off"
											onkeydown="if(event.keyCode==13){return false;}"
											id="SmsCode" name="SmsCode" type="text"
											class="small_sr" /><span class="btn_big"> <input
										id="SMSbutton" class="chong_btn" type="button"
										onclick="getSMS();" value="点击获取"
										style="background-image: url(images/images/xiayi.png);cursor: pointer;" />
										</span><span id="MessageCodeInfo" style="font-size: 12px;width: 20px;" class="cheng"> *</span>
										<input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
										<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
										</td>
								</tr>
								<c:if test="${ MsgFlag=='000001'}">
								
									<tr>
										<td class="right">附 &nbsp;&nbsp; 加&nbsp;&nbsp; 码：</td>
										<td width="75%"><input onkeyup="checkTokenImgOnKeyUp()"
											onblur="checkTokenImg()" autocomplete="off" id="_vTokenName"
											maxlength="4" name="_vTokenName" type="text" class="small_sr"
											value="" /> <img id="rightImage"
											style="display: none; position: relative; top: 10px;" /> <img
											id="_tokenImg" src="" class="yzm_img" /> <span><a
												href="#" onclick="reloadTokenImg(this);"
												style="font-size: 11px">看不清楚，换一张</a></span>
												<span id="checkTokeninfo" style="font-size: 12px;width: 20px;" class="cheng"> *</span>
												</td>
									</tr>
									
								</c:if>
							</table>
						</div>
						<c:if test="${ PayerCardTypCd=='1'}">
							<div class="btn_big">
								<input
									type="button" id="cancelDebit" value="取消" class="chong_btn"
									style="background-image: url(images/images/xiayi.png); color: #fff;cursor: pointer;" />
								<input type="button" id="doItButton" value="下一步"
									onclick="doIt(this);" class="xia_btn"
									style="background-image: url(images/images/xiayi.png);cursor: pointer;" /> 
							</div>
						</c:if>

						<c:if test="${ PayerCardTypCd=='2'}">
							<div class="btn_big">
								<input
									type="button" id="cancelCredit" value="取消" class="chong_btn"
									style="background-image: url(images/images/xiayi.png); color: #fff;" />
								<input type="button" id="doItButton" value="下一步"
									onclick="doItForCredit(this);" class="xia_btn"
									style="background-image: url(images/images/xiayi.png);" /> 
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
