<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>浙江农信手机支付注册</title>
<link href="css/cellphone.css" rel="stylesheet" type="text/css" />
<style>
#tr_inputbankcard, #tr_selectbankcard, #tr_payerphoneno, #tr_smscode,
	#btn_ConfirmPay, #btn_OpenPay, #btn_back, #tr_tip, #btn_addNewCard {
	display: none;
}

#select_payBankCardNbr, #input_payBankCardNbr {
	/* font: bold 16px "微软雅黑";
	height: 30px; */
}

#span_tip {
	width: 100%;
	font-size: 10px;
	color: #5478D0;
}

.load_box {
	position: absolute;
	width: 300px;
	display: none;
	left: 50%;
	height: 200px;
	z-index: 1001;
	padding: 0px;
	text-align: center;
}

#load_bg {
	background-color: #ddd;
	position: absolute;
	z-index: 1000;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.5;
	filter: alpha(opacity = 50);
}
</style>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/slides.min.jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="js/layer-v1.9.3/layer.js"></script>
<script src="js/style.js"></script>
<script language="javascript" type="text/javascript" src="js/mobile.js"></script>
<script>
	$(function() {
		var orderId = $("#OrderSafeId").val();
		orderId = orderId.substring(0, 7) + "****"
				+ orderId.substring(orderId.length - 6, orderId.length);
		$("#OrderSafeId").val(orderId);
		var cardList = $("#cardList").val();
		if (cardList && "" != cardList) {
			cardList = eval("("
					+ cardList.replace(/=/g, ":").replace(/ /g, "").replace(
							/([^\:{\}\[\]\,]+)\:([^\:\,\{\}\[\]]*)/g,
							"\"$1\":\"$2\"") + ")");
			for (var i = 0; i < cardList.length; i++) {
				$("#select_payBankCardNbr").append(
						"<option value='" + cardList[i]["PayerAcctNbr"] + "'>"
								+ cardList[i]["PayerAcctNo"] + "</option>");
			}
			showSelect();
	
		}else {
			showInput();
			$("#btn_OpenPay").removeClass("khzf");
		}
		//选本行卡切换到丰收e页面
		$("#select_payBankCardNbr").change(function(){
			if(false==checkCard($(this).children("option:selected").val())){
				$("#input_payBankCardNbr").val($(this).children("option:selected").val())
				document.forms[0].action = "CheckFoisonAcct.do";
				document.forms[0].submit();
				}
		});
		
		$("#btn_addNewCard").bind("click", function() {
			
			showInput();
			$("#btn_back").show();
		});
		$("#btn_back").bind("click", function() {
			showSelect();
		});

		function showSelect() {
			//显示让客户选择卡号
			$("#btn_OpenPay").hide();
			$("#tr_inputbankcard").hide();
			$("#tr_tip").hide();
			$("#btn_back").hide();
			$("#yanzhengma").hide();
			
			$("#tr_selectbankcard").show(400);
			$("#tr_payerphoneno").show(400);
			$("#tr_smscode").show(400);
			$("#btn_addNewCard").show(500);
			$("#btn_ConfirmPay").show(500);
			//选卡手机号只读
		
		}
		
		$("#goBack").bind("click", function(){
			 document.forms[0].action = "IPEM.do";
	         document.forms[0].submit();
		});
		//增加同意协议选框
		$(function () {
           		$('#open-box').click(function () {
                	$('#fuc').removeClass('hide');
           	 });
           	 $('#close-box').click(function () {
               		 $('#fuc').addClass('hide');
            		});
        	 });
		
		
		function showInput() {
			//显示让客户输入卡号
			$("#tr_smscode").hide();
			$("#btn_ConfirmPay").hide();
			$("#btn_addNewCard").hide();
			$("#tr_selectbankcard").hide();

			$("#tr_inputbankcard").show(400);
			$("#tr_payerphoneno").show(400);
			$("#tr_tip").show(400);
			$("#btn_OpenPay").show(500);
			$("#yanzhengma").show(500);
			reloadTokenImg();
			
		}

		$("#btn_OpenPay").bind("click", function() {
			if (!(/^\d{16}$|^\d{19}$/g.test($("#input_payBankCardNbr").val()))) {
				layer.msg("卡号长度错误");
				return;
			}
			//添加需同意协议条件
			if(!checkCheckBox("sign")){
				return;
			}
			if (checkcode != "yes") {
				return;
			}
			var payerAcctNbr = $("#input_payBankCardNbr").val().trim();
			var payerPhoneNbr = $("input[name='PayerPhoneNo']").val().trim();
			//本行卡注册并支付它行卡开通并支付
			
			if(false==checkCard(payerAcctNbr)){
				$("#form_id").attr('action','FS01MobileLogin.do');
				$("#form_id").submit();
				return;
			}else{
				if($("#ElecPortNotify").val() == "K1"){
        			layer.msg("涉及跨境商品不支持他行支付");
					return;
				}
				if($("input[name='PayTypeCdStr']").val().indexOf("3") == -1){
					layer.msg("商户不支持此银联卡");
					return;
				}
				submitForm("otherQuickOpenAndPay.do", payerAcctNbr, payerPhoneNbr, "2");
			}
		});
			

		$("#btn_ConfirmPay").bind("click", function() {
			if (/^\d{6}$/g.test($("#SmsCode").val())) {
				if ("" != $("#SendUnionPayTime").val() && "" != $("#SmsInnerFundTransNbr").val()) {
					var payerAcctNbr = $("#select_payBankCardNbr").children("option:selected").val().trim();
					var payerPhoneNbr = $("input[name='PayerPhoneNo']").val().trim();
					if($("#ElecPortNotify").val() == "K1"){
	        			layer.msg("涉及跨境商品不支持他行支付");
						return;
					}
					if($("input[name='PayTypeCdStr']").val().indexOf("3") == -1){
						layer.msg("商户不支持此银联卡");
						return;
					}
					submitForm("otherQuickPay.do", payerAcctNbr, payerPhoneNbr, "1");
				}else{
					layer.msg("验证码获取失败");
				}
			} else {
				layer.msg("验证码格式不正确");
			}
		});

		function submitForm(strAction, payerAcctNbr, payerPhoneNbr, isQueryOpenStatus) {
			showLoading();
			$.ajax({
				type : "POST",
				async : false,
				url : "qAOSPaygate.do",
				data : "PayerPhoneNo=" + payerPhoneNbr + "&PayerAcctNbr="
						+ payerAcctNbr + "&IsQueryOpenStatus=" + isQueryOpenStatus,
				success : function(msg) {
					if ("0000000" == msg["RespCode"] && msg["PayerCardTypCd"]) {
						$("input[name='PayerCardTypCd']").val(
								msg["PayerCardTypCd"]);
						$("input[name='PayerAcctNbr']").val(payerAcctNbr);
					} else if("PAY0054" == msg["RespCode"]){
						hideLoading();
						layer.msg("该卡已注册！");
						$("#input_payBankCardNbr").val('');
						$("#_vTokenName").val('');
						checkcode="no";
	                    document.getElementById("rightImage").src="images/hongch.jpg";
	                    document.getElementById("rightImage").style.display="inline";
					}else {
						hideLoading();
						layer.msg(msg["RespMessage"]);
					}
				}
			});
			var val = $("input[name='PayerCardTypCd']").val();
			if (val && "" != val && "null" != val) {
				$("#form_id").attr("action", strAction);
				if ("otherQuickOpenAndPay.do" == strAction) {
					hideLoading();
					$("#btn_OpenPay").attr("disabled", "disabled");
					$("#form_id").submit();
				} else {
					$("#btn_ConfirmPay").attr("disabled", "disabled");
					$("#form_id").submit();
				}
			}else{
				hideLoading();
			}
		}

		/* $("#SmsCode").bind("keyup", function() {
			checkSmsCode($(this));
		}).bind("blur", function() {
			checkSmsCode($(this));
		});

		$("#input_payBankCardNbr").bind("keyup", function() {
			checkCardNbr($(this));
		}).bind("blur", function() {
			checkCardNbr($(this));
		}); */
		//判断本行它行卡
		function checkCard(payerAcctNbr){
			$.ajax({
				type: "POST",
				async: false,
				url: "QCFG.do",
				data:"PayerAcctNbr=" + payerAcctNbr,
				success: function(msg){
					if("0000000" == msg["RespCode"]){
						//$("input[name='PayerAcctNbr']").val(payerAcctNbr);
						$("input[name='InnerCardFlag']").val(msg["InnerCardFlag"]);
					}
				}
			});
			if('1' == $("input[name='InnerCardFlag']").val()){
				
				return false;
			}
			if('0' == $("input[name='InnerCardFlag']").val()){
				
				return true;
			}
		}
		function checkCardNbr(that) {
			if (/^\d{16}$|^\d{19}$/g.test(that.val())) {
				$("#btn_OpenPay").attr("disabled", false);
			} else {
				$("#btn_OpenPay").attr("disabled", true);
			}
		}

		function checkSmsCode(that) {
			if (/^\d{6}$/g.test(that.val())) {
				if ("" != $("#SendUnionPayTime").val()
						&& "" != $("#SmsInnerFundTransNbr").val()) {
					$("#btn_ConfirmPay").attr("disabled", false);
				}
			} else {
				$("#btn_ConfirmPay").attr("disabled", true);
			}
		}

		//---------------发送短信验证码------------------------
		var InterValObj; //timer变量,控制时间
		var count = 90; //间隔函数,1秒执行
		var curCount; //当前剩余秒数

		$("#SMSbutton").bind(
				"click",
				function() {
					var payerAcctNbr = $("#select_payBankCardNbr").children(
							"option:selected").val().trim();
					var payerPhoneNbr = $("input[name='PayerPhoneNo']").val()
							.trim();
					var amt = $("input[name='TransAmt']").val();

					//发送短信验证码
					$.ajax({
						type : "POST",
						async : true,
						url : "sendMessagePaygate.do",
						data : "PayerPhoneNo=" + payerPhoneNbr
								+ "&PayerAcctNbr=" + payerAcctNbr
								+ "&TransAmt=" + amt,
						success : function(msg) {
							if (msg["SendUnionPayTime"]
									&& msg["SmsInnerFundTransNbr"]) {
								$("#SendUnionPayTime").val(
										msg["SendUnionPayTime"]);
								$("#SmsInnerFundTransNbr").val(
										msg["SmsInnerFundTransNbr"]);
								layer.msg("短信已经发送，注意查收");
							} else {
								layer.msg("获取失败");
							}
						}
					});

					$("#SMSbutton").attr("disabled", "disabled");
					curCount = count;
					InterValObj = window.setInterval(setRemainTime, 1000);
				});

		function setRemainTime() {
			if (curCount == 0) {
				window.clearInterval(InterValObj);//停止计时器
				$("#SMSbutton").removeAttr("disabled");//启用按钮
				$("#SMSbutton").val("重新获取");
			} else {
				$("#SMSbutton").val(curCount + "秒");
				curCount--;
				$("#SMSbutton").css("color", "#004595");
			}
		}
	});
</script>
<script>
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
						$("#btn_OpenPay").attr("disabled", false);
					} else {
						document.getElementById("rightImage").src = "images/hongch.jpg";
						document.getElementById("rightImage").style.display = "inline";
						$("#btn_OpenPay").attr("disabled", true);
					}
				});
	}
	function reloadTokenImg(clickObj) {
		if ($('#_vTokenName').val() && clickObj)
			$('#_vTokenName').trigger("blur");
		var img=document.getElementById('_tokenImg');
		if(img){ img.src = "GenTokenImg.do"
				+ "?random=" + Math.random();
		}
		
	}

</script>
</head>
<body>
	<form id="form_id" name="form1" action="#" method="post">
		<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
		<pe:hidden
			fieldList="PayTypCd,PayerCardTypCd,PayerAcctDeptNbr,OrderId,PlainContext,Plain,ChannelNbr,MerchantName,TransAmt,Signature,TransId,PayerAcctName,PayTypeCdStr"
			skipNULL="false" />
		<input type="hidden" id="cardList" value="${CardList }" /> <input
			id="SendUnionPayTime" name="SendUnionPayTime" value="" type="hidden" />
			<input type="hidden" name="InnerCardFlag"/>
		<input id="SmsInnerFundTransNbr" name="SmsInnerFundTransNbr" value=""
			type="hidden" /> <input id="CyberTypCd" name="CyberTypCd" value=""
			type="hidden" />
			<input type="hidden" value="${ElecPortNotify }" id="ElecPortNotify" name="ElecPortNotify"/>
		<div class="logo">
			<div class="">
				丰收e支付<a href="###" class="leftBt"><img
					src="images/phone/left.png" width="16" height="23"
					id="goBack" /></a>
			</div>
		</div>
		<div class="order">
		<table width="100%" border="0" cellpadding="1" cellspacing="0" style="background-color: rgba(158, 202, 241, 0.12);">
            <tr>
                <td class="right" style="width: 28%;text-align: left;">商&nbsp;&nbsp;&nbsp;户：</td>
                <td style="padding-left: 0px;text-align: left;"><c:out value='${MerchantName}'/></td>
            </tr>
            <tr>
                <td class="right" style="width: 28%;text-align: left;">金&nbsp;&nbsp;&nbsp;额：</td>
                <td class="red" style="padding-left: 0px;text-align: left;">￥<fmt:formatNumber type="number" value="${TransAmt}" pattern="#,###,##0.00"/></td>
            </tr>
            <tr>
                <td class="right" style="width: 28%;text-align: left;">订单号：</td>
                <td class="order_id" style="padding-left: 0px;"><input type="text" id="OrderSafeId" value="${OrderId }" readonly="readonly" style="width: 100%"/>
                </td>
            </tr>
   	 	</table>
		</div>
		<div class="main">
			<table width="100%" border="0" cellpadding="1" cellspacing="0">
				 <%-- <tr>
					<td class="right">商&nbsp;&nbsp;&nbsp;户：</td>
					<td><c:out value='${MerchantName}' /></td>
				</tr> 
					<td class="right">金&nbsp;&nbsp;&nbsp;额：</td>
					<td class="red"><fmt:formatNumber type="number"
							value="${TransAmt}" pattern="#,###,##0.00" /></td>
				</tr>
				<tr>
					<td class="right">订单号：</td>
					<td class="order_id"><input type="text" id="OrderSafeId"
						value="${OrderId }" readonly="readonly" style="width: 100%" /></td>
				</tr>  --%>
				<tr id="tr_inputbankcard">
					<td class="right left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
					<td class="srk"><input type="tel" id="input_payBankCardNbr" name="PayerAcctNbr" style="width: 100%"
						placeholder="请输入银行卡号" /></td>
				</tr>
				<tr id="tr_selectbankcard">
					<td class="right left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
					<td class="srk"><select id="select_payBankCardNbr" ></select>
					</td>
				</tr>
				<tr id="tr_payerphoneno">
					<td class="right left">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
					<td class="srk"><input type="text" id="PayerPhoneNo" name="PayerPhoneNo" value="${PayerPhoneNo }"
						readonly="readonly" style="width: 100%" /></td>
				</tr>
				<tr id="yanzhengma">
				<td class="right left">验&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;码：</td>
				<td class="srk"><input onkeyup="checkTokenImgOnKeyUp()"
						onblur="checkTokenImg()" autocomplete="off" id="_vTokenName"
						maxlength="4" name="_vTokenName" type="text"
						style="width: 50px;" value="" /> <img id="rightImage"
						style="display: none; position: relative; width: 27px;height: 27px;" /> <img
						id="_tokenImg" src="" class="yzm_img" style="width: 50px;"/> <span><a
						href="#" onclick="reloadTokenImg(this);"
						style="font-size: 11px">换一张</a></span></td>
			</tr>
				<tr id="tr_tip" >
					<td class="right left" style="padding-right:18px;"><input class="mgc" style="width: 16px;" id="sign" name="sign" type="checkbox" value=""
                                                                /></td>
            <td class="font14"><a href="#" id="open-box">我已阅读并接受《浙江农信丰收e支付服务协议》</a></td>
				</tr>
				<tr id="tr_smscode">
					<td class="right left" nowrap>短信验证码：</td>
					<td class="srk"><input id="SmsCode" name="SmsCode"
						autocomplete="off" placeholder="" type="tel"
						style="width: 45%" maxlength="6" /> <input id="SMSbutton"
						class="hq" value="点击发送" type="button"/> </td>
				</tr>
			</table>
		</div>
		<div class="fuc hide" id="fuc" style="overflow: scroll">
       		 <div class="fuc_main"
            		 style="width: 100%; height: 80%; overflow: auto; border: 1px ; text-align: left">
            <div class="fuc_main_b"> 浙江省农村信用社（合作银行、商业银行）“丰收e支付”服务协议 <a href="###" id="close-box"><img
                    src="images/images/cha.png"/></a></div>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议是由浙江省农村信用社（合作银行、商业银行）（以下简称“我行”）与丰收卡（含丰收借记卡和丰收贷记卡）及他行银联卡持卡人（以下简称“您”）就“丰收e支付”服务（以下简称“本服务”）的使用等相关事项所订立的有效合约。“丰收e支付”是浙江省农村信用社（合作银行、商业银行）向丰收卡及他行银联卡持卡人提供的以“手机号+银行卡号+手机动态密码”作为支付的安全认证方式进行小额购物电子支付的一种支付方式。您通过互联网（Internet）点击确认或以其他方式选择接受本协议，即表示您同意接受本协议的全部约定内容，确认承担由此产生的一切责任。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在接受本协议之前，请您仔细阅读本协议的全部内容（特别是以粗体下划线标注的内容）。如果您不同意本协议的任何内容，或者无法准确理解相关条款的解释，请不要进行后续操作。</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（1）您应确保您在使用本服务时所使用的银行卡为您本人所有，并确保您用其支付的行为合法、有效，未侵犯任何第三方合法权益；否则因此造成我行、持卡人损失的，您应负责赔偿并承担全部法律责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（2）我行仅为您提供安全可靠的网上支付服务，支付服务所关联的基础交易中的商品质量、送货服务等引起的争议和纠纷均由您和提供商品或服务的商户自行协商解决，我行对此不承担任何责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（3）您应妥善保管该卡开户户名、开户类型、与之相关的证件类型及证件号码、手机号码、固定电话、通信地址等与该卡有关的一切信息（以下简称“身份信息及卡信息”），不得将身份信息及卡信息转告他人，并在安全网络环境下使用，如您遗失银行卡、泄露身份信息及卡信息，您应及时通知发卡银行，以减少可能发生的损失。因您遗失手机、泄露银行卡号码、泄露手机验证码、泄露银行卡密码、数字证书、U盾、丢失银行卡等所致的风险及损失需由您自行承担。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（4）使用“丰收e支付”服务，丰收借记卡单笔网上消费交易额最高为人民币2000元，每日网上消费交易额最高为人民币2000元；丰收贷记卡单笔网上消费交易额最高为人民币2000元，每日网上消费交易额最高为人民币2000元；他行卡单笔交易最高额及每日网上消费交易最高额以发卡银行银联在线支付限额为准。使用丰收卡签约丰收e支付您可以根据支付需要和风险考虑，点击浙江农信官网（www.csii.com）上丰收e支付图标，进入丰收e支付业务维护页面，在我行统一设置的丰收e支付交易限额内调整您个人丰收卡的丰收e支付交易限额。我行可根据业务发展需要及风险控制需要设置或修改丰收卡的交易限额，无须对客户另行通知，请您留意我行有关公告及网页说明。如您对我行修改交易限额存在异议的，可申请终止使用本服务。如我行修改限额后，您仍然使用本功能的，视为您同意我行对限额所进行的修改和调整。在任何情况下，交易金额不应超过我行及他行发卡银行银联在线支付设置的交易限额。如实际支付金额大于支付限额，我行将拒绝执行交易指令，由此造成您的损失我行不承担责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（5）请确保并确认“丰收e支付”注册签约手机号码为您在发卡银行预留的本人手机号码并在本人实际控制之下。在支付过程中，我行将向您“丰收e支付”注册签约手机号码发送动态验证码追加验证，以确保您的交易安全。您应妥善保管移动电话所涉相关信息，并保持移动电话通讯和使用功能通畅。因您遗失移动电话、将移动电话转借他人使用、移动电话自身故障、或其他因您自身原因而导致其无法收取我行发送的动态验证码所产生的一切后果及损失，由您自行承担。如因网络通讯、黑名单等非我行原因造成您无法接收动态验证码的，我行不承担任何责任。如您的手机号码在移动运营商处注销，应及时至浙江农信官网（www.csii.com）点击丰收e支付图标，进入丰收e支付业务维护页面，冻结该手机号码注册签约的“丰收e支付”业务。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（6）您同意我行对您在使用本服务过程中所填写的包括但不限于姓名、银行卡卡号、手机号码、身份证号码等进行校对核验，同时我行保留随时变更上述确认要素作为校验标准的权利。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（7）您不得利用银行卡进行套现、虚假交易、洗钱等行为，且有义务配合我行进行相关调查，一旦您拒绝配合进行相关调查或我行认为您存在或涉嫌虚假交易、洗钱、套现或任何其他非法活动、欺诈或违反诚信原则的行为、或违反本协议约定的，我行有权采取以下一种、多种或全部措施：一、暂停或终止提供本协议项下“丰收e支付”服务；二、终止本协议；三、取消您的用卡资格。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（8）您同意我行有权变更、暂停本协议项下相关服务，有权修改、终止本协议，并于执行前通过我行网站进行公告。相关公告经网站发布视为您已收到。您在公告执行后继续办理相关业务的，视同接受有关本协议修改、变更的内容。本协议终止后，您在协议终止前发送我行进行处理的交易指令仍有效，您应承担其后果。</b>
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您使用丰收e支付业务，应遵守我行银行卡章程和我行在网站（www.csii.com）上公布的交易规则，如有冲突，以本协议为准。</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        </div>
    </div>
		<div class="bottom_botton">
			<input id="btn_addNewCard" class="peory khzf" value="开通新卡" type="button" />
			<input id="btn_ConfirmPay" class="peory khzf" value="确认支付" type="button" />
			<input id="btn_back" class="peory khzf" value="返回支付" type="button" />
			<input id="btn_OpenPay" class="peory khzf" value="开通并支付" type="button" />
		</div>
		<div class="bottom_botton"></div>
        	<div class="bottom_botton"></div>
	</form>
	<div id="load_bg"></div>
	
	<div class="load_box">
		<div class='loader loader--glisteningWindow'></div>
		<!-- 		<div class='loader loader--audioWave'></div> -->
		<!-- 		<div class='loader loader--snake'></div> -->
		<!-- 		<div class='loader loader--spinningDisc'></div> -->
		<!-- 		<div class='loader loader--circularSquare'></div> -->
		<!-- 		<span style="font: 16px '微软雅黑';">处理中&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</span> -->
	</div>
	<script>
		function showLoading() {
			$("#load_bg").css({
				display : "block",
				height : $(document).height()
			});
			var $load_box = $('.load_box');
			$load_box.css({
				//设置弹出层距离左边的位置
				left : ($("body").width() - $load_box.width()) / 2 - 20 + "px",
				//设置弹出层距离上面的位置
				top : ($(window).height() - $load_box.height()) / 2
						+ $(window).scrollTop() + "px",
				display : "block"
			});
		}

		function hideLoading() {
			$("#load_bg").css({
				display : "none"
			});
			var $load_box = $('.load_box');
			$load_box.css({
				display : "none"
			});
		}
	</script>
</body>
</html>
