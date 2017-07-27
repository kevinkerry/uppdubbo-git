<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/foison/css.css" rel="stylesheet" type="text/css" />
<link href="css/foison/global.css" rel="stylesheet" type="text/css" />
<title>丰收e支付注册</title>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/slides.min.jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="js/layer-v1.9.3/layer.js"></script>
<script>
	function Cancel() {
		window.location.href = "FS01.do";
	}
	var checkcode = "no";
	function doIt() {
		if (document.getElementById("getSMS").disabled == false) {
			$("#doNext").attr("disabled", "disabled");
			$('#SmsCodeInfo').text(" *请获取短信验证码");
			return;
		}
		if (true == checkLength("SmsCode", 0)) {
			$('#SmsCodeInfo').text("短信验证码为空");
			return;
		}
		/*  if(false == checkLength("_vTokenName",4)) {
		     layer.msg("附加码位数不足，请核实后输入")
		 } */
		if ((document.getElementById('state').value) == "0") {
			if (checkLength("_vTokenName", 0)) {
				/* $("#SMSinfo").text("附加码为空，请输入"); */
				return;
			}
			if (checkcode != "yes") {
				$("#doNext").attr("disabled", "disabled");
				return;
			}
		}
		document.forms[0].submit();
	}
	var InterValObj; //timer变量,控制时间
	var count = 120; //间隔函数,1秒执行
	var curCount; //当前剩余秒数
	function getSMSFunction() {
		$('#SmsCodeInfo').text("");
		$("#SMSinfo").text("");
		$("#doNext").attr("disabled", false);
		curCount = count;
		$("#getSMS").attr("disabled", "disabled");
		$("#getSMS")
		.css("background-image", "url(images/images/fanhui.png)").css(
				"color", "#004595");
		//            $("#SMSbutton").css("background-image","url(images/images/fanhui.png)");
		document.getElementById("getSMS").value = curCount + "秒" ;
		var phone = document.forms[0].PayerPhoneNo.value;
		var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
		var payerAcctDeptNbr = document.forms[0].PayerAcctDeptNbr.value;
		/* var deptId = document.forms[0].PayerAcctDeptNbr.value; */
		var oparams = new Array(new Pair("PayerPhoneNo", phone),
		/* new Pair("PayerAcctDeptNbr", "000000"),  */
		new Pair("PayerAcctNbr", PayerAcctNbr), new Pair("OperateType", "0"),new Pair("PayerAcctDeptNbr", payerAcctDeptNbr),
				new Pair("TransTypCd", "UPP10001")

		);
		postData2SRVWithCallback("SMS.do", PEGetPostData(oparams), function(
				success, message) {
			if (!success) {
				 $("#SMSbutton").removeAttr("disabled");//启用按钮
				$("#SMSbutton").css("background-image","url(images/images/xiayi.png)");
				document.getElementById("SMSbutton").value="重新获取";
				$('#SmsCodeInfo').text("短信发送失败");
			} else {
				var obj = eval('(' + message + ')');
				if(obj.SmsSqenbr != null){
					$("#SmsSqenbr").val(obj.SmsSqenbr);
				}
				if ("PAY0100" == obj.RespCode) {
					curCount = 30;
					$("#SmsCodeInfo").text(obj.RespMessage);
					document.getElementById("getSMS").value = curCount + "秒";
					InterValObj = window.setInterval(SetRemainTime, 1000);
				} else {
					document.getElementById("getSMS").value = curCount + "秒";
					InterValObj = window.setInterval(SetRemainTime, 1000);
				}
			}
		});
	}
	function SetRemainTime() {
		if (curCount == 0) {
			window.clearInterval(InterValObj);//停止计时器
			$("#getSMS").removeAttr("disabled");
			$("#getSMS").css("background-image",
			"url(images/images/xiayi.png)");
			document.getElementById("getSMS").value = "重新获取";
		} else {
			curCount--;
			document.getElementById("getSMS").value = curCount + "秒";
			document.getElementById("SmsCodeBack").value = curCount;
		}
	}
	function checkCode() {
		if (document.getElementById("getSMS").disabled == false) {
			$("#doNext").attr("disabled", "disabled");
			$('#SmsCodeInfo').text(" *请获取短信验证码");
			return;
		}
		if (true == checkLength("SmsCode", 0)) {
			$('#SmsCodeInfo').text("短信验证码为空");
			return;
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
						$("#SMSinfo").text("");
						checkcode = "yes";
						document.getElementById("rightImage").src = "images/lug.jpg";
						document.getElementById("rightImage").style.display = "inline";
						$("#doNext").attr("disabled", false);
					} else {
						document.getElementById("rightImage").src = "images/hongch.jpg";
						document.getElementById("rightImage").style.display = "inline";
						$("#doNext").attr("disabled", true);
					}
				});
	}
	function hiddenSomePhoneNo() {
		var phone = document.forms[0].PayerPhoneNo.value;
		var heddenPhone = phone.substring(0, 3) + "****"
				+ phone.substring(phone.length - 4, phone.length)
		document.getElementById("PhoneSpan").innerHTML = heddenPhone;
	}

	function checkLength(nodeName, length) {
		var node = document.getElementById(nodeName).value;
		if (length != node.length) {
			return false;
		}
		return true;
	}

	function checkTokenImgOnKeyUp() {
		var userInputToken = document.getElementById("_vTokenName").value;
		if (userInputToken.length == 0) {
			document.getElementById("rightImage").style.display = "none";
		} else if (userInputToken.length == 4) {
			checkTokenImg();
		}
	}

	function reloadTokenImg() {
		document.getElementById('_tokenImg').src = "GenTokenImg.do"
				+ "?random=" + Math.random();
	}

	function LoadJs1() {
		hiddenSomePhoneNo();
		if ((document.getElementById('state').value) == "0") {
			reloadTokenImg();
			document.getElementById('tableIMG').style.display = "block";
			//	 $("#doNext").attr("disabled",true);
		}
	}
	$(function(){
		var smsCodeBackv = document.getElementById("SmsCodeBack").value;
		if(smsCodeBackv!=null&&parseInt(smsCodeBackv)<=120&&parseInt(smsCodeBackv)>0){
			document.getElementById("getSMS").value = smsCodeBackv + "秒";
	//		$("#getSMS")
	//		.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
		$("#getSMS")
			.css("background-image", "url(images/images/fanhui.png)").css(
					"color", "#004595");
			curCount = parseInt(smsCodeBackv);
			document.getElementById("getSMS").value = curCount
			+ "秒";
			InterValObj = window.setInterval(SetRemainTime,
				1000);
		}
	});
</script>
<style>
#tableIMG {
	width: 550px;
	margin: auto;
	margin-left: 250px;
}
</style>
</head>
<body onload="LoadJs1()">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/foison/csii1.png">
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/foison/phone.png"><span class="net">
							<a href="http://www.csii.com/">www.csii.com </a>
					</span>
				</div>
			</div>
		</div>
		<div class="main-nav">
			<ul class="main-nav-in">
				<li><a
					style="background: url(images/foison/top_bg2.png) repeat-x; text-decoration: none; border-radius: 5px; color: #e17b27;"
					href="/paygate/FS01.do">注册</a></li>
				<li><a href="/paygate/FS02.do">查询交易明细</a></li>
				<li><a href="/paygate/FS03.do">调整交易限额</a></li>
				<li><a href="/paygate/FS04.do">冻结</a></li>
				<li><a href="/paygate/FS05.do">解冻</a></li>
				<li><a href="/paygate/FS07.do">注销</a></li>
			</ul>
		</div>
		<div class="content">
			<form id="form1" name="form1" method="post" action="FS01Confirm.do">
				<input type="hidden" name="state" id="state" value="${state}" />
				<input id="TransTypCd" name="TransTypCd" type="hidden" value="UPP10001"/>
				<pe:hidden
					fieldList="PayerAcctNbr,PayerPhoneNo,PayerCardTypCd,PayerAcctDeptNbr,CustCifNbr,PayerIdNbr,PayerCardPwd,PayerIdTypCd,PayerCardExpiredDate,PayerAcctName,TellerNo"
					skipNULL="true" />
				<div class="con_r">
					<div class="con_r_main">
						<div class="dingw">您可以在此注册丰收e支付服务</div>
						<div class="sr_main" style="margin-top: 20px;">
							<table width="620" border="0"
								style="margin: auto; margin-top: 20px;">
								<tr>
									<td class="right" style="letter-spacing: 0.1px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
									<td width="70%">${PayerAcctNbr}</td>
								</tr>
								<tr>
									<td class="right">手机号码：</td>
									<td width="70%">${PayerPhoneNo}</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="tis" style="margin-left: 12%; width: 65%;">
											请输入您手机号码（<span id="PhoneSpan"></span>）接收到的短信验证码
										</div>
									</td width="70%">
								</tr>
								<tr>
									<td class="right" style="width: 30%" >短信验证码：</td>
									<td width="70%"><input autocomplete="off" id="SmsCode"
										style="width: 100px" name="SmsCode" type="text" value=""
										maxlength="6"  /> <input id="getSMS"
										class="chongx" onclick="getSMSFunction()" value="获取验证码"
										type="button"
										style="background-image: url(images/images/xiayi.png); width: 124px; height: 43px; border: none; color: #fff; font-size: 16px; font-weight: bold;" />
										<span id="SmsCodeInfo" class="cheng">*</span>
										<input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
										<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" /></td>
								</tr>
							</table>
							<div style="display: none; margin:auto;" id="tableIMG" >
								<table>
									<tr>
										<td class="right" style="width: 175px;height: 50px;">附&nbsp;&nbsp;加&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
										<td><input autocomplete="off" style="width: 90px"
											id="_vTokenName" name="_vTokenName"
											onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()"
											type="text" value="" size="6" maxlength="4"
											class="_vTokenName" /> <img id="rightImage"
											style="display: none; position: relative; top: 10px;" /> <img
											id="_tokenImg" onclick="reloadTokenImg()" src=""
											style="position: relative; top: 10px;" /> <a
											style="font-size: 12px" onclick="reloadTokenImg()">看不清楚，换一张</a>
										</td>
									</tr>
								</table>
							</div>
							<div id="QQQ">
								<span id="SMSinfo" class="cheng" style="margin-left: 41%;"></span>
							</div>
						</div>
						<div class="btn_big" style="text-align:left;width: 320px;">
							<input type="button" value="取消"
								class="xia_btn" onclick="Cancel()" />
							<input id="doNext" type="button" onclick="doIt()" value="下一步"
								class="xia_btn" /> 
						</div>

					</div>
				</div>
			</form>
		</div>
		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	</div>
</body>
</html>


































