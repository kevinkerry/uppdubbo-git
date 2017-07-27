<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 丰收e支付确认页面 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/remodal.css" />
<link rel="stylesheet" type="text/css" href="css/remodal-default-theme.css" />
<link rel="stylesheet" type="text/css" href="css/DesignByXiaoyu.css" />
<link rel="stylesheet" href="css/global.css" type="text/css" />
<link href="css/checkBox.css" rel="stylesheet" type="text/css" />
<link href="css/load_style.css" rel="stylesheet" type="text/css" />
<link href="css/foisonconfirm.css" rel="stylesheet" type="text/css" />
<title>丰收e支付</title>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.9.1.js"></script>
<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/remodal.js"></script>	
<script language="javascript" type="text/javascript" src="script.do"></script>
<script>
    window.onbeforeunload = function(){
	    if("block" == $("#bg").css("display") && "block" == $(".box").css("display")){
			var merSeqNo = $("#form-box").children("input[name='MerSeqNo']").val();
			var merchantId = $("#form-box").children("input[name='MerchantId']").val();
			$.ajax({
				type: "POST",
				async: true,
				url: "QueryOrderStatus.do",
				data: "MerchantId=" + merchantId + "&MerSeqNo=" + merSeqNo,
				success: function(msg){
				}
			});
		}
	};
 var checkcode="no";
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
	$(function(){
		
		$("#span_viewamt1").html("￥" + formatNum($("#money1").val(),2));
		$("#span_viewamt2").html("￥" +formatNum($("#money2").val(),2));
		
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

		$('#cancelDebit').click(function() {
			document.forms[0].RespCode.value="FS";
			document.forms[0].action="backCardPwdPre.do";
			document.forms[0].submit();
		});
		$('#cancelCredit').click(function() {
			document.forms[0].action="backCardPwdPre.do";
			document.forms[0].submit();
		});
		
		$("#PayerAcctNbr").change(function(){
			if("add" == $(this).children("option:selected").val()){
				showInput();
			}else{
				var payercard=$(this).children("option:selected").val();
				if(22==payercard.length||25==payercard.length){
	        		payercard=payercard.substring(0,payercard.length-6);
	        	}
				if(true==checkCard(payercard)){
						$(this).children("option:selected").val(payercard);
					    document.forms[0].action = "otherQuickPayPre.do";
						document.forms[0].submit();
				}
			}
		
		
		});
		function showInput(){
			$("#div1").hide();
			$("#sr_main").addClass("hide");
			$("#confirmpay0").hide();
			$("#confirmpay1").hide();
			$("#confirmpay2").hide();
			$("#confirmpay3").hide();
			$("#confirmpay4").hide();
			$("#confirmpay5").hide();
			$("#div_ConfirmPay").hide();
			
			$("#yanzhengma").show();
			$("#sr_add").removeClass("hide");
			$("#div_OpenPay").removeClass("hide");
			reloadTokenImg();
			
		}
		
		$("#a_back").bind("click", function(){
			showPoint();
			$("#confirmpay0").show();
			$("#confirmpay1").show();
			$("#confirmpay2").show();
			$("#confirmpay3").show();
			$("#confirmpay4").show();
			$("#confirmpay5").show();
			$("#div_ConfirmPay").show();
			$("#sr_main").removeClass("hide");
			
			$("#PayerAcctNbr").children("option:selected").val("add");
			$("#sr_add").addClass("hide");
			$("#div_OpenPay").addClass("hide");
			$("#yanzhengma").hide();
			$("#PayerAcctNbr").children("option[value!='add']").first().prop("selected", true);
			document.getElementById("PayerPhoneNo").value = document.getElementById("PayerPhoneNo_bak").value;
		});
		
		$('#RegisterCheckbox').click(
				function() {
					if ($('#RegisterCheckbox').is(':checked')) {
						$("#btn_OpenPay").removeAttr("disabled");//启用按钮
						$("#btn_OpenPay").css("background-image",
								"url(images/images/xiayi.png)");
					} else {
						$("#btn_OpenPay").attr("disabled", "disabled");//禁用按钮
						$("#btn_OpenPay").css("background-image",
								"url(images/images/fanhui.png)");
					}
				});
		
		$("#btn_OpenPay").bind("click", function(){
			var payerAcctNbr = $("#input_payBankCardNbr").val();
			var payerPhoneNbr = $("input[name='RegisterPayerPhoneNo']").val();
			//设置注册的时候传给paygate的卡号，手机号
			$("#PayerAcctNbr").children("option:selected").val(payerAcctNbr);
			document.getElementById("PayerPhoneNo").value = payerPhoneNbr;
			if(false==checknbr(payerAcctNbr)){
				return;
			}
			if (checkcode != "yes") {
					return;
			}
		 	if(true==checkCard(payerAcctNbr)){
				if($("#ElecPortNotify").val() == "K1"){
					alertTip("涉及跨境商品不支持他行支付", "");
					return;
				}if($("input[name='PayTypeCdStr']").val().indexOf("3") == -1){
					alertTip("商户不支持此银联卡", "");
					return;
				}
				submitForm("otherQuickOpenAndPay.do", payerAcctNbr, payerPhoneNbr, "2");
			}else{
				document.forms[0].action = "fsRegisterAndEpay.do";
				document.forms[0].submit();
			}
		});
		
		function showLoading(){
			$("#load_bg").css({
		         display: "block",
		         height: $(document).height()
		     });
		     var $load_box = $('.load_box');
		     $load_box.css({
		         //设置弹出层距离左边的位置
		         left: ($("body").width() - $load_box.width()) / 2 - 20 + "px",
		         //设置弹出层距离上面的位置
		         top: ($(window).height() - $load_box.height()) / 2 + $(window).scrollTop() + "px",
		         display: "block"
		     });
		}
		
		function hideLoading(){
			$("#load_bg").css({
		         display: "none"
		     });
		     var $load_box = $('.load_box');
		     $load_box.css({
		         display: "none"
		     });
		}
		function submitForm(strAction, payerAcctNbr, payerPhoneNbr, isQueryOpenStatus){
			showLoading();
			$.ajax({
				type: "POST",
				async: false,
				url: "qAOSPaygate.do",
				data: "PayerPhoneNo=" + payerPhoneNbr + "&PayerAcctNbr=" + payerAcctNbr
				 + "&IsQueryOpenStatus=" + isQueryOpenStatus,
				success: function(msg){
					if("0000000" == msg["RespCode"] && msg["PayerCardTypCd"]){
						$("input[name='PayerCardTypCd']").val(msg["PayerCardTypCd"]);
						$("input[name='PayerAcctNbr']").val(payerAcctNbr);
						$("input[name='InnerCardFlag']").val(msg["InnerCardFlag"]);
					}else if("PAY0054" == msg["RespCode"]){
						hideLoading();
						alertTip("该卡已注册！", "");
					}
					else {
						hideLoading();
						$("#span_shortcutAcctTip").hide().html(msg["RespMessage"]).fadeIn(500);
					}
				}
			});
			var val = $("input[name='PayerCardTypCd']").val();
			if(val && "" != val && "null" != val){
				$("#form_id").attr("action", strAction);
				if("otherQuickOpenAndPay.do" == strAction){
					hideLoading();
					showBox();
					$("#form_id").attr("target", "_black");
					$("#form_id").submit();
				}else{
					$("#doItButton").attr("disabled", "disabled");
					$("#form_id").submit();
				}
			}else{
				hideLoading();
			}
		}
		
		$("#input_payBankCardNbr").bind("blur", function(){
			checkCardNbr($(this));
		});
		
		function showBox(){
			$("#bg").css({
		         display: "block",
		         height: $(document).height()
		     });
		     $(".box").children("h2").children("span").html("丰收e注册并支付");
		     var $box = $('.box');
		     $box.css({
		         //设置弹出层距离左边的位置
		         left: ($("body").width() - $box.width()) / 2 - 20 + "px",
		         //设置弹出层距离上面的位置
		         top: ($(window).height() - $box.height()) / 2 + $(window).scrollTop() + "px",
		         display: "block"
		     });
		}
		
		$("#div_list").children("div").each(function(index, element){
			$(this).bind("mouseover",function(){
				$(this).css({
					"background-color":"#fff",
					"cursor":"pointer"
				});
			}).bind("mouseout",function(){
				$(this).css("background-color","#eee");
			}).bind("click",function(){
				hideBox();
				showLoading();
				$("#form-box").submit();
			});
	   });
		
		$("#closebox").bind("click",function(){
			   $(".box").hide();
			   $("#form-box").submit();
		});
		
		function hideBox(){
			$(".box").hide();
			$("#bg").hide();
		}
		function checkCardNbr(that){
			if(/^\d{16}$|^\d{19}$/g.test(that.val())){
				$("#span_shortcutAcctTip").html("*");
			}else {
				$("#span_shortcutAcctTip").hide().html("卡号格式有误").fadeIn(500);
				return;
			}
		}

        function checknbr(payercard){
			
			if(/^\d{16}$|^\d{19}$/g.test(payercard)){
				$("#span_shortcutAcctTip").html("*");
				return true;
			}else {
				$("#span_shortcutAcctTip").hide().html("卡号格式有误").fadeIn(500);
				return false;
			}
		}
		
		function checkCard(payerAcctNbr){
			$.ajax({
				type: "POST",
				async: false,
				url: "QCFG.do",
				data:"PayerAcctNbr=" + payerAcctNbr,
				success: function(msg){
					if("0000000" == msg["RespCode"]){
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
	function showPoint(){
 		var dic = {
 					payTypeCd : "${PayTypeCdStr}"
 				};
 			
 				if (getCookie("payTypeCd")) {
 					if (!(dic.payTypeCd && (dic.payTypeCd != getCookie("payTypeCd")))) {
 						dic.payTypeCd = getCookie("payTypeCd");
 					}
 				}
 				setCookie("payTypeCd", dic.payTypeCd);
 				if (dic.payTypeCd.indexOf("5") == -1) {
 					$("#div1").hide();
 					
 				}else{
 					$("#div1").show();
 				}
 	}
		
		
	 
	function load(){
		hiddenSomePhoneNo();
		showPoint();
		if('000001'=="${MsgFlag}"){
			reloadTokenImg();
		}
		if(navigator.userAgent.indexOf("MSIE 8.0")>0){
			$("#remodal").addClass("topmiddle");
			$("#remodal2").addClass("topmiddle");
		}
	}

	//获取服务器时间戳
	var ts="<%=System.currentTimeMillis()%>";

	function checkLength(nodeName, length) {
		var node = document.getElementById(nodeName).value;
		if (length != node.length) {
			return false;
		}
		return true;
	}

	function doIt() {
		if (false == checkLength("SmsCode", 6)) {
			$('#MessageCodeInfo').text("验证码格式不正确！");
			return;
		}else{
			$('#MessageCodeInfo').text("*");
		}

		if ((document.forms[0].MsgFlag.value) == "000001") {
			if (checkcode != "yes") {
				$("#doItButton").attr("disabled", true);
				return;
			}else{
				$("#doItButton").attr("disabled", false);
			}
		}
		$("#doItButton").attr("disabled", true);//禁用按钮
		var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
		if((document.getElementById("checkbox").checked)){
				$('#interalFlag').val('1');
		}else{
			$('#benciamt').val('');
			$('#kehuhao').val('');
			$('#hangshe').val('');
			$('#interalFlag').val('');
		}
		$("#PayerAcctNbr").prop("disabled", false); 
		document.forms[0].action = "foisonEpay.do";
		document.forms[0].submit();             
	}

	var InterValObj; //timer变量,控制时间
	var count = 120; //间隔函数,1秒执行
	var curCount; //当前剩余秒数
	function getSMS() {
		$("#doItButton").removeAttr("disabled");//启用按钮
		$("#doItButton").css("background-image",
				"url(images/images/xiayi.png)");
		
		$("#SMSinfo").text(" ");
		$('#MessageCodeInfo').text("");
		$("#doItButton").attr("disabled", false);
		curCount = count;
		$("#SMSbutton").attr("disabled", true);//禁用按钮
		$("#SMSbutton")
				.css("background-image", "url(images/images/fanhui.png)").css(
						"color", "#004595");
		var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
		var deptId = PayerAcctNbr.substring(PayerAcctNbr.length-6);
		var AcctNbr = PayerAcctNbr.substring(0,PayerAcctNbr.length-6);
		var PayerPhoneNo = document.forms[0].PayerPhoneNo.value;
		var transAmtPoint=(document.getElementById("AmtThisTime")==null)||(!document.getElementById("checkbox").checked)
		?0:document.getElementById("AmtThisTime").value;
		var transAmt = formatNum(document.forms[0].TransAmt.value -transAmtPoint,2);
		var oparams = new Array(
				new Pair("PayerAcctNbr", AcctNbr), 
				new Pair("PayerPhoneNo",PayerPhoneNo),
				new Pair("PayerAcctDeptNbr", deptId), 
				new Pair("TransAmt",transAmt), 
				new Pair("OperateType", "0"),
				new Pair("TransTypCd", "UPP10003"));
		postData2SRVWithCallback(
				"SMS.do",
				PEGetPostData(oparams),
				function(success, message) {

					if (!success) {
						$("#SMSbutton").removeAttr("disabled");//启用按钮
						$("#SMSbutton").css("background-image",
								"url(images/images/xiayi.png)");
						document.getElementById("SMSbutton").value = "重新获取";

						$('#MessageCodeInfo').text("短信发送失败");
					} else {
						$("#checkbox").attr("disabled", "disabled");
						$("#detail").removeAttr("href");
						$('#MessageCodeInfo').text("*");
						var obj = eval('(' + message + ')');
						if(obj.SmsSqenbr != null){
							$("#SmsSqenbr").val(obj.SmsSqenbr);
						}
						if ("PAY0100" == obj.RespCode) {
							curCount = 30;
							$("#MessageCodeInfo").text(obj.RespMessage);
							document.getElementById("SMSbutton").value = curCount
									+ "秒";
							
							InterValObj = window.setInterval(SetRemainTime,
									1000);
						} else {
							document.getElementById("SMSbutton").value = curCount
									+ "秒";
							InterValObj = window.setInterval(SetRemainTime,
									1000);
						}
							$("#PayerAcctNbr").prop("disabled", true); 
						}
				});
		//每秒调用
	}
	
	function SetRemainTime() {
		if (curCount == 0) {
			window.clearInterval(InterValObj);//停止计时器
			$("#SMSbutton").removeAttr("disabled");//启用按钮
			$("#SMSbutton").css("background-image",
					"url(images/images/xiayi.png)");
			document.getElementById("SMSbutton").value = "重新获取";
		} else {
			curCount--;
			document.getElementById("SmsCodeBack").value = curCount;
			document.getElementById("SMSbutton").value = curCount + "秒"
			$("#SMSbutton").css("color", "#004595");
		}
	}

	function checkCode() {
		if (document.getElementById("SMSbutton").disabled == false) {
			$('#MessageCodeInfo').text(" *请获取短信验证码");
			$("#doItButton").attr("disabled", "disabled");
		}
	}

	function hiddenSomePhoneNo() {
		var phone = document.forms[0].PayerPhoneNo.value;
		var heddenPhone = phone.substring(0, 3) + "****"
				+ phone.substring(phone.length - 4, phone.length)
		document.getElementById("PhoneSpan").innerHTML = heddenPhone;
	}

	function reloadTokenImg(clickObj) {
		if("sr_add"==$("#sr_add").attr("class")){
			if ($('#_vTokenName_r').val() && clickObj)
				$('#_vTokenName_r').trigger("blur");
			var img=document.getElementById('_tokenImg_r');
			if(img){ img.src = "GenTokenImg.do"
					+ "?random=" + Math.random();
			}
		}else{
			if ($('#_vTokenName').val() && clickObj)
				$('#_vTokenName').trigger("blur");
			var img=document.getElementById('_tokenImg');
			if(img){ img.src = "GenTokenImg.do"
					+ "?random=" + Math.random();
			}
		}
	}

	function checkTokenImgOnKeyUp() {
		if("sr_add"==$("#sr_add").attr("class")){
			var userInputToken = document.getElementById("_vTokenName_r").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage_r").style.display = "none";
			} else if (userInputToken.length == 4) {
				checkTokenImg();
			}
		}else{
			var userInputToken = document.getElementById("_vTokenName").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage").style.display = "none";
			} else if (userInputToken.length == 4) {
				checkTokenImg();
			}
		}
		
	}
	function checkTokenImg() {
		if("sr_add"==$("#sr_add").attr("class")){
			var userInputToken = document.getElementById("_vTokenName_r").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage_r").style.display = "none";
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
							document.getElementById("rightImage_r").src = "images/lug.jpg";
							document.getElementById("rightImage_r").style.display = "inline";
							$("#btn_OpenPay").attr("disabled", false);
						} else {
							document.getElementById("rightImage_r").src = "images/hongch.jpg";
							document.getElementById("rightImage_r").style.display = "inline";
							$("#btn_OpenPay").attr("disabled", true);
						}
					});
		}else{
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
						} else {
							document.getElementById("rightImage").src = "images/hongch.jpg";
							document.getElementById("rightImage").style.display = "inline";
							$("#doItButton").attr("disabled", true);
						}
					});
		}
		
	}	
	$(function () {
        $('#open-box').click(function () {
            $('#fuc').removeClass('hide');
        });
        $('#close-box').click(function () {
            $('#fuc').addClass('hide');
        });
    });
	
 	function isdedction(obj){ 
			if($(obj).val="on"){
				var deductionAmt = $(obj).parent().parent().find("input[id='AmtThisTime']").val();
				var clientNo = $(obj).parent().parent().find("input[id='ClientNo']").val();
				var branchNo = $(obj).parent().parent().find("input[id='BranchNo']").val();
				$('#benciamt').val(deductionAmt);
				$('#kehuhao').val(clientNo);
				$('#hangshe').val(branchNo);
				document.getElementById("deduction").value = deductionAmt;
				document.getElementById("jane").value = formatNum(deductionAmt,2);
				var transAmt = document.forms[0].TransAmt.value;	
				var transAmtdeduction = transAmt-deductionAmt;
				document.getElementById("jane5").innerHTML=formatNum(transAmtdeduction,2);
				document.getElementById("janemoney").innerHTML=formatNum(deductionAmt,2);				
				if(!$('#checkbox').is(':checked'))
					$('#checkbox').prop("checked",true);
				$("#span_viewamt2").html("￥" +formatNum(transAmtdeduction,2));												
			}	 
	}	
 	
 	function isChecked(obj){
 		var transAmt = document.forms[0].TransAmt.value;
 		if($(obj).is(':checked')){
 			var deductionAmt = $('#jane').val();				
			var transAmtdeduction = transAmt-deductionAmt;
			$("#span_viewamt2").html("￥" +formatNum(transAmtdeduction,2));	
 		}else{
 			$("#span_viewamt2").html("￥" +formatNum(transAmt,2));
 		}
 		
 	}
 	
 	$(function(){
		$("#div_list_tip").children("div").each(function(index, element) {
			$(this).bind("mouseover", function() {
				$(this).css({
					"background-color" : "#fff",
					"cursor" : "pointer"
				});
			}).bind("mouseout", function() {
				$(this).css("background-color", "#eee");
			}).bind("click", function() {
				$(".box_tip").hide();
				$("#bg_tip").hide();
			});
		});

		$("#close_box_tip").bind("click", function() {
			$(".box_tip").hide();
			$("#bg_tip").hide();
		});
	});

 	function alertTip(h3Str, h2Str) {
 		$("#div_list_tip").children("h1").html(h3Str);
 		$(".box_tip").children("h2").children("span").html(h2Str);

 		$("#bg_tip").css({
 			display : "block",
 			height : $(document).height()
 		});
 		var $box = $('.box_tip');
 		$box.css({
 			// 设置弹出层距离左边的位置
 			left : ($("body").width() - $box.width()) / 2 - 20 + "px",
 			// 设置弹出层距离上面的位置
 			top : ($(window).height() - $box.height()) / 2
 					+ $(window).scrollTop() + "px",
 			display : "block"
 		});
 	}
 	
</script>
</head>

<body onload="load();" oncontextmenu="return false"
	ondragstart="return false">
<div class="box"></div>
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
					<div class="time1"></div>
					${MerDate }
				</div>
			</div>
		</div>
		<div class="content" style="height: 600px;">
			<form id="form_id" action="#" method="post">
			<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
				<pe:hidden
					fieldList="PayTypCd,CardList,PayerCardTypCd,CustCifNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,PayerAcctName,Signature,RespCode,MsgFlag,UserName,TransId,PayTypeCdStr"
					skipNULL="false" />
					<input  name="PayerPhoneNo" id="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}"/>
					<input  name="PayerPhoneNo_bak" id="PayerPhoneNo_bak" type="hidden" value="${PayerPhoneNo}"/>
					<input id="pointShowFlag" value="${pointShowFlag}" type="hidden"></input>
					<input  name="InnerCardFlag" type="hidden"/>
					<input  type="hidden" id="benciamt" name="AmtThisTime" value=""></input>
					<input  type="hidden" id="kehuhao" name="ClientNo" value=""></input>
					<input  type="hidden" id="hangshe" name="BranchNo" value=""></input>
					<input  type="hidden" id="interalFlag" name="interalFlag" value=""></input>
					<input type="hidden" value="${ElecPortNotify }" id="ElecPortNotify" name="ElecPortNotify"/>
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
								<td class="cheng"><span id="span_viewamt1"></span><input  maxlength="15" type="hidden" id="money1" value="${TransAmt}"></input> </td>
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
					<div class="con_r_t">丰收e支付</div>
					<div class="con_r_main" style="height: 512px;">
						<div class="dingw2 dingw">您的位置：丰收e支付 > 确认支付</div>
						<div class="buz2a buz">
							<div class="buz1">1.填写信息</div>
							<div class="white buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						 <div class="cardDetial" id="div1" style="float: left;margin-top: -18px;border: none;display: none;">
              				<ul style="margin-left: 19px;margin-right: 19px;padding: 0px;">
                  			<li class="jf" style="width: 615px;color: #666">
                  					<div class="tag"  style="float: left;padding-top: 18px;margin-left:0px;">
                  						<input class="mgc" style="width: 16px;" id="checkbox" name="checkbox" type="checkbox" onclick="isChecked(this)"/>
                  					
                  					</div> 
                  					
                 					 	<image style="margin-left:-45px;" src="images/jxIco.png" align="absmiddle"  />&nbsp;&nbsp;您当前积分可抵扣金额为<input class="input1" type="text" name="jane" id="jane"  value="0"  readonly="true" style="width: 40px;line-height:15px;"></input>元&nbsp;<span style="display: none;" id="janemoney">0</span>&nbsp;&nbsp;<span id="jane5" style="display: none;">${TransAmt}</span>
                 						<a href="#modal2" style="right:155px;background: none;color:#5478D0;text-decoration: none;font-size: 16px;">抵扣规则</a>
                 					 	<a href="#modal" style="right:70px;" id="detail">抵扣详请</a>
                 					
                 					 </li>
               					
               				</ul>
          				</div>
          				
						<div class="sr_main" id="sr_main">
							<table width="540" border="0" style="margin-bottom: 20px;">
								<tr id="confirmpay0">
									<td class="left" colspan="2" style="font-size: 13px">1．请仔细核对左侧订单信息和下面支付信息：</td>
								</tr>
								<tr id="confirmpay1">
									<td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
									<td td width="78%"><select id="PayerAcctNbr" name="PayerAcctNbr" > 
										<c:forEach items="${CardList}" var="cardInfo">
											<option value="${cardInfo.PayerAcctNbr}${cardInfo.PayerAcctDeptNbr}">${cardInfo.PayerAcctNo}</option>
										</c:forEach>
										     <option value="add"> 添 加 新 卡  </option>
										</select>
									</td>

								</tr>
								<tr>

									<tr id="confirmpay2">
										<td class="right">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
										<td td width="78%"><span id="span_viewamt2"></span><input  maxlength="15" type="hidden" id="money2" value="${TransAmt}"></input>&nbsp;元</td>
									</tr>

									<tr>
										<td class="left" colspan="2" style="font-size: 13px"></td>
									</tr>	

									<tr id="confirmpay3">
										<td colspan="2">
											<div id="mobile" class="tis">
												请输入您手机（<span id="PhoneSpan"></span>）接收到的短信验证码。
											</div>
										</td>
									</tr>
									<tr id="confirmpay4">
										<td class="right">短信验证码：</td>
										<td td width="78%"><input onclick="checkCode()" autocomplete="off"
											onkeydown="if(event.keyCode==13){return false;}"
											id="SmsCode" name="SmsCode" type="text"
											class="small_sr" /> <input id="SMSbutton" class="chongx"
											type="button" onclick="getSMS();" value="点击获取"
											style="background-image: url(images/images/xiayi.png); width: 124px; height: 43px; border: none; color: #fff; font-size: 16px; font-weight: bold;" />
											<span id="MessageCodeInfo" class="cheng">*</span>
											<input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
											<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
											</td>
									</tr>
									<c:if test="${ MsgFlag=='000001'}">
									
										<tr id="confirmpay5">
											<td class="right">附 &nbsp;&nbsp; 加&nbsp;&nbsp; 码：</td>
											<td td width="78%"><input onkeyup="checkTokenImgOnKeyUp()"
												onblur="checkTokenImg()" autocomplete="off" id="_vTokenName"
												maxlength="4" name="_vTokenName1" type="text"
												class="small_sr" value="" /> <img id="rightImage"
												style="display: none; position: relative; top: 10px;" /> <img
												id="_tokenImg" src="" class="yzm_img" /> <span><a
													href="#" onclick="reloadTokenImg(this);"
													style="font-size: 11px">看不清楚，换一张</a></span></td>
										</tr>
										
									</c:if>
									<tr>
										<td colspan="2" style="font-size: 13px"><span
											id="SMSinfo" class="cheng" style="margin-left: 150px;"></span></td>
									</tr>
							</table>
						</div>
						<div class="sr_add hide" id="sr_add" >
							<table width="540" border="0" style="margin-top: 20px;">
								
								<tr>
									<td class="right" width="25%">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
									<td width="70%"><input autocomplete="off" name="RegisterPayerAcctNbr"
										id="input_payBankCardNbr"  value="" type="text"  style="font:bold 15px '微软雅黑'"/>
										<span id="span_shortcutAcctTip" class="cheng"> *</span></td>
								</tr>
								<tr>
									<td width="25%" class="right" >手机号码：</td>
									<td width="70%"><input autocomplete="off"
										id="RegisterPayerPhoneNo" name="RegisterPayerPhoneNo"
										value="${PayerPhoneNo}" type="text" style="font:bold 15px '微软雅黑'"/> <span
										id="PayerPhoneNoTip" class="cheng"> *</span></td>
								</tr>
								<tr id="yanzhengma">
											<td class="right" style="letter-spacing: 3.76px">附加码：</td>
									<td td width="78%"><input onkeyup="checkTokenImgOnKeyUp()"
										onblur="checkTokenImg()" autocomplete="off" id="_vTokenName_r"
										maxlength="4" name="_vTokenName" type="text"
										class="small_sr" value="" style="width: 120px;"/> <img id="rightImage_r"
										style="display: none; position: relative; top:10px;" /> <img
										id="_tokenImg_r" src="" class="yzm_img" style="top:10px;"/> <span><a
											href="#" onclick="reloadTokenImg(this);"
											style="font-size: 11px">看不清楚，换一张</a></span></td>
											
											
											
								</tr>
								<tr>
									<td colspan="2">
										<div class="tis" style="width: 80%">请输入您在发卡行社预留的手机号码，注:预留或修改手机号码可至发卡行社任一营业网点</div>
									</td>
								</tr>
								<tr>
									<td class="right" style="padding-right: 15px;"><input
										id="RegisterCheckbox" name="RegisterCheckbox" type="checkbox" value=""
										style="width: 20px;" /></td>
									<td class="font14"><a href="###" id="open-box">我已阅读并接受《浙江农信丰收e支付服务协议》</a></td>
								</tr>
							</table>
						</div>


                        <div class="btn_big hide" id="div_OpenPay">
								<input
									style="cursor: pointer; background-image: url(images/images/fanhui.png);"
									type="button" id="btn_OpenPay" value="注册并支付" class="xia_btn"
									disabled="disabled" />&nbsp;&nbsp;&nbsp;
									<a href="##" id="a_back">返回选卡支付</a>
						</div>
						<div class="btn_big" id="div_ConfirmPay" style="margin-left:-30px;">
							<input
								type="button" id="cancelDebit" value="取消" class="chong_btn"
								style="background-image: url(images/images/xiayi.png); color: #fff;" />
							<input type="button" id="doItButton" value="下一步" disabled="disabled"
							
								onclick="doIt();" class="xia_btn"
								style="background-image: url(images/images/fanhui.png);" /> 
						</div>
						<br /> <br /> <br /> <br />
					</div>

				</div>

				<input id="integral" name="integral" type="hidden"
					value="${integral}"> </input> <input id="isFlag" name="isFlag"
					type="hidden" value="${isFlag}"> </input> <input id="deductibleAmt"
					name="deductibleAmt" type="hidden" value="${deductibleAmt} ">
				</input> <input id="realAmt" name="realAmt" type="hidden"
					value="${realAmt }"> </input>


			</form>
		</div> 
<div class="remodal" id="remodal" data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">
  <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
  <div>
    <h2 id="modal1Title">抵扣金额 <input class="input2" style="width: 50px;" type="text" name="deduction" id="deduction"  value="" readonly="true"></input>元</h2>
    <p id="modal1Desc" align="left">
    	您可以手动选择抵扣行社：
    </p>
    <div>
    <table width="100%" border="1" cellpadding="2" cellspacing="0"	class="table-class" align="center">
    	<tr class="trTitle" bgcolor="#c8c8ff">
		<td nowrap class="tdTitle" align="center">行社</td>
	    <td nowrap class="tdTitle" align="center">可抵扣金额（元）</td>
		<td nowrap class="tdTitle" align="center">本次可抵扣金额（元）</td>
		<td nowrap class="tdTitle" align="center">是否抵扣</td>
		</tr>
		<c:forEach var="item" items="${pointRecords}">
		<tr>
		<td class="tdValue" >${item.BranchName}</td>
		<td class="tdValue" >${item.integralTotal}</td>
		<td class="tdValue" >${item.AmtThisTime}</td>	
		<td class="tdValue" ><input type="radio" id="isdedction" name="isdedction" onclick="isdedction(this)"></input></td>	
		<input class="input5" id="AmtThisTime" type="hidden" value="${item.AmtThisTime }"></input>
		<input class="input3" id="ClientNo" type="hidden" value="${item.ClientNo}"></input>
		<input class="input4" id="BranchNo" type="hidden" value="${item.BranchNo}"></input>
		</tr>
		</c:forEach>
    </table>
    </div>
    <c:choose>
	    <c:when test="${PointRespFlag=='1'}">
	    	<p style="color: red;font-size: 15px;">未获取到客户的积分信息！</p>
	    </c:when>
	    <c:when test="${PointRespFlag=='2'}">
	        <p style="color: red;font-size: 15px;">客户名下没有积分！</p>
	    </c:when>
    </c:choose>
    <p id="mark">
    	<p align="left">1.积分最高抵扣额度为订单金额的50%</p>
    	<p align="left">2.一笔支付订单只能选择一家行社的积分进行抵扣</p>
    </p>
  </div>
  <button data-remodal-action="confirm" class="remodal-confirm" >确认</button>
</div> 
<div class="remodal" id="remodal2" data-remodal-id="modal2" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">
  <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
  <div align="left" style="color:#666">
    <h2 id="modal1Title" align="center" style="font-size:16px"><b>积 &nbsp;&nbsp;&nbsp;&nbsp;分&nbsp;&nbsp;&nbsp;&nbsp;抵&nbsp;&nbsp;&nbsp;&nbsp;扣&nbsp;&nbsp;&nbsp;&nbsp;规 &nbsp;&nbsp;&nbsp;&nbsp;则</b></h2>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）积分专属浙江省农村信用社（合作银行、商业银行），仅限在丰收购、丰收家平台以浙江农信丰收卡通过丰收E支付时使用。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）买家持有多家浙江农信行社积分时，一笔支付订单只能使用一家行社的积分进行抵扣。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）买家因任何理由对购买的商品或服务发起退款时，退还的金额为买家购买商品或服务时实际支付的现金，积分不予退还。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）具体积分折算金额比例详见各行社积分管理办法。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）积分最小抵扣金额为1元，订单可抵扣金额少于1元时积分不可抵扣，抵扣金额保留整数。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（6）买家在商城使用积分时最高抵用额度为订单金额的50%。在限额范围内，您可选择使用积分进行抵扣或者全额现金支付。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（7）买家在使用积分时，优先消耗旧积分，积分有效期详见各行社积分有效期规定。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（8）积分不能进行任何形式的转让，不可兑换现金。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（9）积分所属各行社独立判断，买家是否存在恶意或伪造非真实交易骗取积分的行为；规定了其他不予积分情形的不得参与积分抵扣。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（10）本积分规则条款及细则中如有任何争议，在法律许可范围内浙江省农村信用社（合作银行、商业银行）保留最终解释权。</p>
  </div>
  <button data-remodal-action="confirm" class="remodal-confirm" >确认</button>
</div>
		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	</div>	
<div class="fuc hide" id="fuc">
		<div class="fuc_main"
			style="width: 980px; height: 600px; overflow: auto; border: 1px">
			<div class="fuc_main_b">
				浙江省农村信用社（合作银行、商业银行）“丰收e支付”服务协议 <a href="###" id="close-box"><img
					src="images/images/cha.png" /></a>
			</div>
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

		</div>
	</div>
	
	<div id="bg"></div>
	<div id="load_bg"></div>
	<div class="box" style="display:none">
	    <h2><span></span><a id="closebox" href="#" class="close">关闭</a></h2>
	    <div id="div_list" class="list">
	    	<h3></h3>
	    	<div class="div_paysuccess"><a id="paysuccess" href="#">已经完成支付</a></div>
	    	
	    	<div class="div_payfailer"><a id="payfailer" href="#">支付遇到问题</a></div>
	    	
	    	<form id="form-box" action="qQPOS.do" method="post">
				<input name="MerchantId" type="hidden" value="${ MerchantId}"/>
				<input name="MerSeqNo" type="hidden" value="${ MerSeqNo}"/>
				<input name="ChannelNbr" type="hidden" value="${ ChannelNbr}"/>
				<input name="MerchantName" type="hidden" value="${ MerchantName}"/>
				<input name="TransAmt" type="hidden" value="${ TransAmt}"/>
				<input name="OrderId" type="hidden" value="${ OrderId}"/>
				<input name="MerDate" type="hidden" value="${ MerDate}"/>
				<input name="MerURL1" type="hidden" value="${ MerURL1}"/>
                <input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
			</form>
	    	<hr id="boxhr" />
	    </div>
	</div>
	<div class="box_tip" style="display:none">
	    <h2><span></span><a id="close_box_tip" href="#" class="close">关闭</a></h2>
	    <div id="div_list_tip" class="list_tip">
	    	<h1></h1>
	    	<div class="div_ok_tip"><a id="ok_tip" href="#">确&nbsp;定</a></div>
	    	
	    	<div class="div_cancel_tip"><a id="cancel_tip" href="#">取&nbsp;消</a></div>
	    	<hr id="boxhr_tip" />
	    </div>
	</div>
	<div class="load_box">
		<div class='loader loader--glisteningWindow'></div>
</body>
</html>
