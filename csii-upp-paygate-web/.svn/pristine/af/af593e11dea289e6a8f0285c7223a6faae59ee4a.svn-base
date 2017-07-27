<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>互联网支付系统</title>
	<style>	
html{
overflow-x:hidden;
}
.btn_big1 {
	width: 540px;
	text-align: center;
	/*width:540px;
	text-align:center;*/
}

.astyle {
	text-align: right;
}

.bank_logo {
	background: url(images/bank_logos.png) left top no-repeat;
}

.btn_big1 input {
	width: 124px;
	height: 43px;
	background: url(../images/images/fanhui.png) no-repeat;
	border: 0;
	font-size: 16px;
	color: #fff;
	font-family: "微软雅黑";
	font-weight: bold;
	margin-right: 20px;
}

text {
	font-family: "微软雅黑";
}

.div {
	border: 1px #000000 solid;
}

.janefu {
	position: fixed;
	top: 300px;
	left: 610px;
	height: 10%;
	width: 10%;
	z-index: 10;
}

.formArea {
	font-weight: bold;
	text-align: left;
	padding-top: 10px;
	padding-bottom: 3px;
	border: 1px solid #b7cde4;
	border-top-width: 3px;
}

#btn_big_c {
	padding-bottom: 60px;
}

#btn_big_btn {
	margin-top: 40px;
}

#div_bankcard {
	height: 120px;
}

#payPhoneNbr {
	font: bold 15px '微软雅黑';
}

#ul_payPhoneNbr{
	margin-top: 130px;
}

.box {
	position: absolute;
	width: 400px;
	left: 50%;
	height: 300px;
	z-index: 1001;
	background-color: #fff;
	border: 5px #aaa solid;
	padding: 0px;
}

.box h2 {
	height: 35px;
	font-size: 15px;
	background-color: #4b5b78;
	position: relative;
	padding-left: 10px;
	line-height: 35px;
	color: #fff;
}

.box h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #fff;
}

.box .list {
	padding: 10px;
	margin-left: 0px;
	font: bold 12px '微软雅黑';
}

.list h3 {
	margin-top: 20px;
}

.box .div_paysuccess {
	position: absolute;
	left: 80px;
	top: 90px;
	border: 1px #aaa solid;
	padding: 8px;
	background-color: #eee;
}

.box .div_payfailer {
	position: absolute;
	left: 230px;
	top: 90px;
	border: 1px #aaa solid;
	padding: 8px;
	background-color: #eee;
}

.box div a {
	font: 12px '微软雅黑';
	color: #666;
}

.showbtn {
	font: bold 24px '微软雅黑';
}

#boxhr {
	position: absolute;
	left: 20px;
	top: 150px;
	border: 1px #ddd solid;
	width: 360px;
}

#bg {
	background-color: #ccc;
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

/* 代替alert的弹窗样式start */

.list_tip h1 {
	margin-top: 20px;
	font: bold 15px '微软雅黑';
	text-align: center;
}
#boxhr_tip {
	position: absolute;
	left: 20px;
	top: 150px;
	border: 1px #ddd solid;
	width: 360px;
}

#bg_tip {
	background-color: #eee;
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

.box_tip {
	position: absolute;
	width: 300px;
	left: 50%;
	height: 200px;
	z-index: 1001;
	background-color: #fff;
	border: 5px #aaa solid;
	padding: 0px;
}

.box_tip h2 {
	height: 35px;
	font-size: 15px;
	background-color: #4b5b78;
	position: relative;
	padding-left: 10px;
	line-height: 35px;
	color: #fff;
}

.box_tip h2 a {
	position: absolute;
	right: 10px;
	font-size: 12px;
	color: #fff;
}

.box_tip .list_tip {
	padding: 10px;
	margin-left: 0px;
	font: bold 12px '微软雅黑';
}

.box_tip .div_ok_tip {
	text-align: center;
	position: absolute;
	left: 60px;
	width : 40px;
	top: 120px;
	border: 1px #aaa solid;
	padding: 6px;
	background-color: #eee;
}

.box_tip .div_cancel_tip {
	text-align: center;
	position: absolute;
	left: 200px;
	width: 40px;
	top: 120px;
	border: 1px #aaa solid;
	padding: 6px;
	background-color: #eee;
}

.box_tip div a {
	font: 12px '微软雅黑';
	color: #666;
}

#boxhr_tip {
	position: absolute;
	left: 20px;
	top: 200px;
	border: 1px #ddd solid;
	width: 260px;
}

 #bigdiv1 a{
    position:relative; 
    top:-4px;
    
}
#bigdiv1 .span-2{
    position:relative;
   left:12px; 
}
#bigdiv1 .span-3{
    position:relative;
   left:26px; 
}
#bigdiv1 .span-4{
    position:relative;
    left:39px; 
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
#alipay a:hover,a:active {
	
	color:#58A6E4 !important;
	text-decoration:none;
	
}
.black_a{
	color: #666;
	font-size: 14px;
}
/* 代替alert的弹窗样式end */
-moz-opacit
</style>
<link rel="stylesheet" href="css/global1.css" type="text/css"/>
<link href="css/css1.css" rel="stylesheet" type="text/css" /> 

<link href="css/up.card.css" rel="stylesheet" type="text/css" />
<link href="css/bank_icon.css" rel="stylesheet" type="text/css" />
<link href="css/zhifu.css" rel="stylesheet" type="text/css" />
<link href="css/load_style.css" rel="stylesheet" type="text/css" />
<link href="css/Base.css" rel="stylesheet" type="text/css" />
<link href="css/DesignByXiaoyu.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/common.js"></script><!--公共效果-->

<!-- <script language="javascript" type="text/javascript" src="script.do"></script> -->
<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
<script type="text/javascript">
var codeflag="no";
function getCookie(sName) {
	var sRE = "(?:;)?" + sName + "=([^;]*);?";
	var oRE = new RegExp(sRE);
	if (oRE.test(document.cookie)) {
		return decodeURIComponent(RegExp["$1"]);
	} else {
		return null;
	}
}
function setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) {
	var sCookie = sName + "=" + encodeURIComponent(sValue);
	if (oExpires) {
		sCookie += "; expires=" + oExpires.toGMTString();
	}
	if (sPath) {
		sCookie += "; path=" + sPath;
	}
	if (sDomain) {
		sCookie += "; domain=" + sDomain;
	}
	if (bSecure) {
		sCookie += "; secure";
	}
	document.cookie = sCookie;

}

$(function() {
	var dic = {
		payTypeCd : "${PayTypeCdStr}"
	};
	if (getCookie("payTypeCd")) {
		if (!(dic.payTypeCd && (dic.payTypeCd != getCookie("payTypeCd")))) {
			dic.payTypeCd = getCookie("payTypeCd");
		}
	}
	setCookie("payTypeCd", dic.payTypeCd);

	$("#span_viewamt").html("￥" + formatNum($("#money1").val(), 2))

	function formatNum(s, n) {
		n = n > 0 && n < 20 ? n : 2;
		s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		var l = s.split(".")[0].split("").reverse();
		r = s.split(".")[1];
		t = "";
		for (var i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		}
		return t.split("").reverse().join("") + "." + r;
	}

	$('.bank_logo').each(function(index, element) {
		$(this).bind("click", function() {
			$(this).prev().attr("checked", "checked");
		});
	});

	$('image,[src=images/nongxin.png]').each(function(index, element) {
		$(this).bind("click", function() {
			$(this).parent().prev().attr("checked", "checked");
		});
	});

	$('#slides').slides({
		preload : true,
		preloadImage : 'img/loading.gif',
		play : 5000,
		pause : 2500,
		hoverPause : true,
		animationStart : function() {
			$('.caption').animate({
				bottom : -35
			}, 100);
		},
		animationComplete : function(current) {
			$('.caption').animate({
				bottom : 0
			}, 200);
			if (window.console && console.log) {
				// example return of current slide number
				console.log(current);
			}
			;
		}
	});

	$(".span-2").click(function() {
		$(".con_r_main").css({
			"display" : "none"
		});
		$(".con_r_main-1").css({
			"display" : "block"
		});
		$(".con_r_main-2").css({
			"display" : "none"
		});
		 $(".con_r_main-3").css({
			"display" : "none"
		});
		$(".span-4").removeClass("active"); 
		$(".span-1").removeClass("active");
		$(".span-2").addClass("active");
		$(".span-3").removeClass("active");

	});
	$(".span-1").click(function() {
		$(".con_r_main").css({
			"display" : "block"
		});
		$(".con_r_main-1").css({
			"display" : "none"
		});
		$(".con_r_main-2").css({
			"display" : "none"
		});
		 $(".con_r_main-3").css({
			"display" : "none"
		});
		$(".span-4").removeClass("active"); 
		$(".span-1").addClass("active");
		$(".span-2").removeClass("active");
		$(".span-3").removeClass("active");
	});
	$(".span-3").click(function() {
		$("#myAccountContent").show();
		doItPbank();
		$(".con_r_main").css({
			"display" : "none"
		});
		$(".con_r_main-1").css({
			"display" : "none"
		});
		$(".con_r_main-2").css({
			"display" : "block"
		});
		 $(".con_r_main-3").css({
			"display" : "none"
		});

		$(".span-4").removeClass("active"); 
		$(".span-1").removeClass("active");
		$(".span-2").removeClass("active");
		$(".span-3").addClass("active");

		$(".cPayTypeCdOtherBank,.cPayTypeCdBank").hide();
		if (dic.payTypeCd.indexOf("2") > -1) {
			$(".cPayTypeCdBank").show();
		}
		if (dic.payTypeCd.indexOf("7") > -1) {
			$(".cPayTypeCdOtherBank").show();
		}
		});
	 $(".span-4").click(function() {
		$(".con_r_main").css({
			"display" : "none"
		});
		$(".con_r_main-1").css({
			"display" : "none"
		});
		$(".con_r_main-2").css({
			"display" : "none"
		});
		$(".con_r_main-3").css({
			"display" : "block"
		});
		$(".span-1").removeClass("active");
		$(".span-2").removeClass("active");
		$(".span-3").removeClass("active");
		$(".span-4").addClass("active");
		//选择支付方式同时生成二维码
		if($("#ElecPortNotify").val() == "K1"){
			alertTip("涉及跨境商品不支持二维码支付", "");
			return;
		}
		if(codeflag == "no"){
			createQRCode1();
			codeflag="yes";
		}
		 
	}); 

	/* $('#FoisonPay').click(function() {
		var phone = $('#FsPayerPhoneNo').val();
		var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;

		if ("" == $('#FsPayerPhoneNo').val()) {
			$("#FsPayerPhoneNoTip1").hide().html("手机号不能为空").fadeIn(500);
		} else {
			if (!reg.test(phone)) {
				$('#FsPayerPhoneNoTip1').hide().html("手机号输入错误").fadeIn(500);
			} else {
				$('#FsPayerPhoneNoTip1').hide().html("*").fadeIn(500);
				document.forms[0].action = "fsEpayPre.do";
				document.forms[0].submit();
			}
		}

	});
 
	$('#doItFsReset').click(function() {
		$('#FsPayerPhoneNoTip1').hide().html("*").fadeIn(500);
	});*/
	// 丰收卡支付

	// 卡号手机号失去焦点事件
	$('#PayerAcctNbr').blur(function() {
		var acctno = $('#PayerAcctNbr').val();
		var acctnoData19 = /^(6)[0-9]{18}$/;
		var acctnoData16 = /^(6)[0-9]{15}$/;
		if (acctnoData16.test(acctno) || acctnoData19.test(acctno)) {
			$('#PayerAcctNbrTip').hide().html("*").fadeIn(500);
		} else {
			$('#PayerAcctNbrTip').hide().html("卡号输入错误").fadeIn(500);
		}
	});
	$('#PayerPhoneNo').blur(function() {
		var phone = $('#PayerPhoneNo').val();
		var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;
		if (!reg.test(phone)) {
			$('#PayerPhoneNoTip').hide().html("手机号输入错误").fadeIn(500);
		} else {
			$('#PayerPhoneNoTip').hide().html("*").fadeIn(500);
		}
	});

	$('#doItCardPwd').click(
			function() {
				if ($('#PayerPhoneNoTip').text() == "*"
						&& $('#PayerAcctNbrTip').text() == "*") {
					document.forms[0].action = "cardPwdPre.do";
					document.forms[0].submit();
				} else {
					if($('#PayerPhoneNoTip').text() != "*"){
						$('#PayerPhoneNoTip').hide().html("手机号输入错误").fadeIn(500);
						return;
					}if($('#PayerAcctNbrTip').text() != "*"){
						$('#PayerAcctNbrTip').hide().html("卡号输入错误").fadeIn(500);
						return;
					}
				}
			});
	$('#EAccountEpay').click(function() {
		document.forms[0].action = "EAccountEpay.do";
		document.forms[0].submit();
	});
	// 阅读并同意协议内容方可提交付款
	$('#checkbox1').click(
			function() {
				if ($('#checkbox1').attr("checked")) {
					$("#doItCardPwd").removeAttr("disabled");// 启用按钮
					$("#doItCardPwd").css("background-image",
							"url(images/images/xiayi.png)");
				} else {
					$("#doItCardPwd").attr("disabled", "disabled");// 禁用按钮
					$("#doItCardPwd").css("background-image",
							"url(images/images/fanhui.png)");
				}
			});

	$(".span-1")[((dic.payTypeCd.indexOf('1') > -1||dic.payTypeCd.indexOf('3') > -1) ? "show" : "hide")]();
	$(".span-2")[(dic.payTypeCd.indexOf('0') > -1 ? "show" : "hide")]();
	$(".span-3")[((dic.payTypeCd.indexOf('2') > -1 || dic.payTypeCd
			.indexOf('7') > -1) ? "show" : "hide")]();
	 $(".span-4")[(dic.payTypeCd.indexOf('8') > -1 ? "show" : "hide")](); 

	 if (dic.payTypeCd.indexOf('8') > -1) {
		/* $(".span-4").trigger("click"); */
	} 
	if (dic.payTypeCd.indexOf('2') > -1) {
		$(".span-3").trigger("click");
	}
	if (dic.payTypeCd.indexOf('0') > -1) {
		$(".span-2")[0].click();
	}
	if (dic.payTypeCd.indexOf('1') > -1||dic.payTypeCd.indexOf('3')> -1) {
		$(".span-1").trigger("click");
	}
});

// 2015年7月3日 个人网银支付
function doItPbank() {

	$("#anniudiv").show();
	$("#myAccountContent").show();
	$("#myAccountContent2").hide();
	$("#cancelCredit2")
			.css("background-image", "url(images/images/fanhui.png)");
	$("#cancelCredit1").css("color", "#FFFFFF");
	$("#cancelCredit2").css("color", "#000000");
	$("#cancelCredit1").css("background-image", "url(images/images/xiayi.png)");

}

function morenclick() {
	$(".con_r_main").css({
		"display" : "block"
	});
	$(".con_r_main-1").css({
		"display" : "none"
	});
	$(".con_r_main-2").css({
		"display" : "none"
	});
	 $(".con_r_main-3").css({
		"display" : "none"
	}); 
	$(".span-4").removeClass("active"); 
	$(".span-1").addClass("active");
	$(".span-2").removeClass("active");
	$(".span-3").removeClass("active");

}
function doItEbank() {
	$("#anniudiv").show();
	$("#myAccountContent2").show();
	$("#myAccountContent").hide();
	$("#cancelCredit1").css("background-image",
			"url(images/images/fanhui.png) ");
	$("#cancelCredit2").css("color", "#FFFFFF");
	$("#cancelCredit1").css("color", "#000000");

	$("#cancelCredit2").css("background-image", "url(images/images/xiayi.png)");

}
function isAgree() {
	/* var mobileNo= $('#FsPayerPhoneNo').val();
	if(mobileNo==null){
		$('#FsPayerPhoneNo').val("");
	} */
	if ($('#checkbox1').attr("checked")) {
		$("#doItCardPwd").removeAttr("disabled");// 启用按钮
		$("#doItCardPwd").css("background-image",
				"url(images/images/xiayi.png)");
	} else {
		$("#doItCardPwd").attr("disabled", "disabled");// 禁用按钮
		$("#doItCardPwd").css("background-image",
				"url(images/images/fanhui.png)");
	}
	
}

function getValue() {

	var jane = $("#jane").val();
	var jane1 = jane / 100;
	$("#janemoney").text(jane1);
	var money = $("#money1").val() - jane1;

	$("#jane5").html(money);
}

function useJane() {
	getValue();
	$("#bigdiv").show();

}
function edit() {

	$("#div1").show();
	$("#div3").hide();
}

function hide() {

	$("#fuchen").hide();
}
function submithide() {
	var janemoney = $("#janemoney").text();
	$("#janemoney1").html(janemoney);
	$("#janemoney2").html(janemoney);
	var jane1 = $("#jane").val();
	$("#usejane").html(jane1);
	$("#fuchen").hide();
	$("#div1").hide();
	$("#div2").hide();
	$("#div3").show();
	// $("#div4").show();
	if ($("#jane").val()) {
		var jane = $("#jane").val();

		$("#integral").val(jane);

		var isFlag = "1";
		$("#isFlag").val(isFlag);
		var jane1 = jane / 100;
		$("#deductibleAmt").val(jane1);
		var money = $("#money1").val() - jane1;
		$("#jane1").val($("#jane").val());
		$("#jane3").html(janemoney);
		$("#jane4").html(money);

		$("#realAmt").val(money);

	} else {

	}
}

function goback() {

	$("#bigdiv1").show();
	$("#bigdiv2").hide();
	$(".con_r_main").css({
		"display" : "none"
	});
	$(".con_r_main-1").css({
		"display" : "none"
	});
	$(".con_r_main-2").css({
		"display" : "none"
	});
	$(".con_r_main-4").css({
		"display" : "block"
	});

}
function check1() {
	$(".con_r_main-3").hide(); 

	$("#bigdiv1").hide();
	$("#bigdiv2").show();
	$(".con_r_main").css({
		"display" : "none"
	});
	$(".con_r_main-1").css({
		"display" : "none"
	});
	$(".con_r_main-2").css({
		"display" : "none"
	});
	$(".con_r_main-4").css({
		"display" : "block"
	});

	var jane = $("#jane").val();
	var jane1 = jane / 100;

	var money = $("#money1").val() - jane1;
	$("#amount").html(money);

}
function doIt() {
	var BankCode = "";
	var BankCode1 = $('#myAccountContent input[name="BankCode"]:checked ')
			.val();
	var BankCode2 = $('#myAccountContent2 input[name="BankCode"]:checked ')
			.val();
	if (BankCode1) {
		BankCode = BankCode1;

	} else if (BankCode2) {
		BankCode = BankCode2;
	}

	if (BankCode == "") {
		alertTip("请选择银行", "");
		return;
	}
	$("#bankCode").val(BankCode);
	document.forms[0].action = "othereBankPay.do";
	document.forms[0].submit();

}
function cyberPay() {
	var BankCode1 = $('#myAccountContent input[name="BankCode"]:checked ')
			.val();
	var BankCode2 = $('#myAccountContent2 input[name="BankCode"]:checked ')
			.val();
	if (BankCode1
			&& $("#cancelCredit1").css("background-image").indexOf("xiayi") > 0) {
		if (BankCode1 == "ZJRC") {
			document.forms[0].action = "PbankPay.do";
			document.forms[0].submit();
		} else {
			if($("#ElecPortNotify").val() == "K1"){
				alertTip("涉及跨境商品不支持他行支付", "");
				return;
			}
			document.forms[0].PayerBankNbr.value = BankCode1;
			document.forms[0].CyberTypCd.value = "01";
			document.forms[0].action = "otherPerBankPay.do";
			document.forms[0].target = "_blank";
			hidecyber();
			document.forms[0].submit();
		}
	} else if (BankCode2
			&& $("#cancelCredit2").css("background-image").indexOf("xiayi") > 0) {
		if (BankCode2 == "ZJRC") {
			document.forms[0].action = "EbankPay.do";
			document.forms[0].submit();
		} else {
			if($("#ElecPortNotify").val() == "K1"){
				alertTip("涉及跨境商品不支持他行支付", "");
				return;
			}
			document.forms[0].PayerBankNbr.value = BankCode2;
			document.forms[0].CyberTypCd.value = "02";
			document.forms[0].action = "otherEnterBankPay.do";
			document.forms[0].target = "_blank";
			hidecyber();
			document.forms[0].submit();
		}
	} else {
		alertTip("请选择银行", "");
		return;
	}
}

function hidecyber() {
	$("#myAccountContent").hide();
	$("#myAccountContent2").hide();
	$("#cancelCredit1").hide();
	$("#cancelCredit2").hide();
	$("#btn_big_c").hide();
	$("#myAccountContent3").show();

	$(".con_r_main-2").children(".buz").addClass("buz2a").children(".buz2")
			.addClass("white");

	$(".con_r_main-2").children("div[class=dingw]").html("您的位置：丰收e支付 > 确认支付");

	$("#div_list").children("h3").html("请您在新打开的网上银行页面进行支付，支付完成前请不要关闭该窗口");
	$(".box").children("h2").children("span").html("登录网上银行支付");

	$("#form-box").children("input[name='PayTypCd']").val(7);
	$("#form-box").attr("action", "QueryOrderStatus.do");

	$("#bg").css({
		display : "block",
		height : $(document).height()
	});
	var $box = $('.box');
	$box.css({
		// 设置弹出层距离左边的位置
		left : ($("body").width() - $box.width()) / 2 - 20 + "px",
		// 设置弹出层距离上面的位置
		top : ($(window).height() - $box.height()) / 2 + $(window).scrollTop()
				+ "px",
		display : "block"
	});

	$(".span-1").hide();
	$(".span-2").hide();
	$(".span-4").hide(); 
}

/*---代替alert弹窗js代码start---*/

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
/*---代替alert弹窗js代码start---*/
 /* function startup()
{	
	var merSeqNo="20161215005882";
	 
	 
	 var url="http://60.190.244.46:34880/paygate/FS09.do?";
	var inputName =url+"rand="+Math.random();
	var imgName = document.getElementById("QRcode");
	var imgParam = encodeURI(encodeURI(inputName));
	if(inputName == null || imgName == null){
		return;
	}
	imgName.src="CQCD.do?content="+imgParam;
}
 */
 function createQRCode(CodeType){
	  
		if(CodeType==$("#weixin_a").attr('id')){
			$("input[name='CodeTypCd']").val('02');
		}
		if(CodeType==$("#alipay_a").attr('id')){
			$("input[name='CodeTypCd']").val('03');
		}
		$.ajax({
			type: "POST",
			async: true,
			url: "QrCodePay.do",
			data:$("#payform").serializeArray(),
			success: function(msg){
				if("0000000" == msg["RespCode"]){
					var inputName=msg["CodeUrl"];
					var imgName = document.getElementById("QRcode");
					var imgParam = encodeURI(encodeURI(inputName));
					if(inputName == null || imgName == null){
						return;
					}
					var wechatproxystatus=msg["Wechatproxystatus"]
					var alipayproxystatus=msg["Alipayproxystatus"]
					if('0'==wechatproxystatus){
						$("#Wechatproxystatus").val('0');
					}
					if('0'==alipayproxystatus){
						$("#Alipayproxystatus").val('0');
					}
					if(""==$("input[name='CodeTypCd']").val()){
						if('0'==wechatproxystatus&&'0'!=alipayproxystatus){
							$("input[name='CodeTypCd']").val('02');	
						}else{
							$("input[name='CodeTypCd']").val('03');
						}
						
					}
					imgName.src="CreatCode.do?"+"content="+imgParam+"&CodeTypCd="+$("input[name='CodeTypCd']").val();
					
				}else if("PAY0210" == msg["RespCode"]){
					alertTip("商户不支持二维码支付方式", "");
					return;
				}else{
					alertTip("二维码订单信息有误", "");
					return;
				}
					
			}
		});
 }
 
 function createQRCode1(){
	  
		$.ajax({
			type: "POST",
			async: true,
			url: "QrCodePay.do",
			data:$("#payform").serializeArray(),
			success: function(msg){
				
				if("0000000" == msg["RespCode"]){
					var inputName=msg["CodeUrl"];
					var imgName = document.getElementById("QRcode");
					imgName.src="CreatCode.do?"+"content="+encodeURI(inputName);
					//imgName.src="http://pan.baidu.com/share/qrcode?w=150&h=150&url="+inputName;
				}else if("PAY0210" == msg["RespCode"]){
					alertTip("商户不支持二维码支付方式", "");
					return;
				}else{
					alertTip("二维码订单信息有误", "");
					return;
				}
					
			}
		});
}
 
 function selectcode(obj){
	 var obj_id=obj.id;
	 if($("#weixin_a").attr('id')==obj_id){
		 if('0'==$("#Wechatproxystatus").val()){
			 createQRCode(obj_id)
		 }else{
			 alertTip("该商户不支持", "");
		 }
		 return;
	 }else if($("#alipay_a").attr('id')==obj_id){
		 if('0'==$("#Alipayproxystatus").val()){
			 createQRCode(obj_id)
		 }else{
			 alertTip("该商户不支持", "");
		 }
		 return;
	 }
 }
	
	//控制网页打印的页眉页脚为空
	function setPageNull(){
		var HKEY_Root,HKEY_Path,HKEY_Key;
		HKEY_Root="HKEY_CURRENT_USER";
	  	HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
		try{
			var Wsh=new ActiveXObject("WScript.Shell");
	      	HKEY_Key="header";
	      	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
	      	HKEY_Key="footer";
	      	Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		}catch(e){
		}
	}

	function print(){
	
		  setPageNull();
			var  Bdhtml=window.document.body.innerHTML;
			var  sprnstr="startpint";
			var  eprnstr="endprint";
			var prnhtml=Bdhtml.substring(Bdhtml.indexOf(sprnstr)+17) ;
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			var printFrame = window.parent.frames["businessfrm"];
			printFrame.document.body.innerHTML=prnhtml;
			printFrame.document.execCommand('print');
			printFrame.document.body.innerHTML = Bdhtml;
	}
	function gobackhistory() {
		if(navigator.userAgent.indexOf("MSIE")>0){
			if(navigator.userAgent.indexOf("MSIE6.0")>0){
				window.opener=null;
				window.close();
			}else{
				window.open('', '_top');
				window.top.close();
			}
		}else if(navigator.userAgent.indexOf("Firefox")>0){
			window.location.href='about:blank';
			window.close();
		}else{
			window.opener=null;
			window.open('', '_self','')
			window.close();
		} 
		
	}
 
</script>
</head>

<body onload="isAgree();" oncontextmenu="return false"
	ondragstart="return false">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/csii1.png" />
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/phone.png" /><span class="net"> <a
						href="http://www.csii.com/">www.csii.com </a></span>
				</div>
				<div class="time">
					<div class="time1">${MerDate}</div>
				</div>
			</div>
		</div>
		
	
		
		
		
		<div class="content" style="height: 561px;">
			<form name="form1" id="payform" action="#" method="post">
					<input id="integral"  name="integral" type="hidden" value="0"> </input>
					<!-- <input id="integral" name="integral" type="hidden"></input> -->
					<input id="isFlag"  name="isFlag" type="hidden"value="0"> </input>
					<input id="deductibleAmt" name="deductibleAmt" type="hidden" value="0"> </input>
					<!--  <input id="realAmt"  name="realAmt" type="hidden" value="${TransAmt}"> </input>-->
					<input id="realAmt" name="realAmt" type="hidden" value="${TransAmt}"></input>
					<input id="PayerAcctDeptNbr"  name="PayerAcctDeptNbr" value="" type="hidden"></input>
					<input id="PayerBankNbr"  name="PayerBankNbr" value="" type="hidden"></input>
					<input id="CyberTypCd"  name="CyberTypCd" value="" type="hidden"></input>
					<input id="PayerCardTypCd"  name="PayerCardTypCd" value="" type="hidden"></input>
					<input type="hidden" value="${ElecPortNotify}" id="ElecPortNotify" name="ElecPortNotify"/>
					<input type="hidden" id="CodeTypCd" name="CodeTypCd" value=""/>
					<input type="hidden" id="Alipayproxystatus" value=""/>
					<input type="hidden" id="Wechatproxystatus" value=""/>
					<pe:hidden fieldList="AcctType,PlainContext,Plain,ChannelId,ChannelNbr,OrderId,PayTypCd,MerDate,MerchantName,Signature,PayTypeCdStr" skipNULL="true" />
				<div class="con_l">
					<div class="con_l_t">
						<img src="images/wddd.png" style="margin-top: -2px;" />
					</div>
					<div class="con_l_m">
						<table width="280" align="center" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td class="right hui">商 户：</td>
								<td class="blue">${ MerchantName}</td>
							</tr>
							<tr>
								<td class="right hui">金 额：</td>
								<td class="cheng" ><span id="span_viewamt"></span><input  maxlength="15" type="hidden" id="money1" value="${TransAmt}"></input> </td>
							</tr>
							<tr>
								<td class="right hui">日 期：</td>
								<td>${MerDate }</td>
							</tr>
							<tr>
								<td class="right hui">订单号：</td>
								<td class="blue">${ OrderId}</td>
							</tr>
							<tr>
								<td class="right hui">币 种：</td>
								<td>人民币</td>
							</tr>
						</table>
					</div>
					<!--图片轮播开始-->
					<div id="container">
						<div id="example">
						</div>
					</div>
					<!--图片轮播结束-->
				</div>
				
			
				
				
				 <!--个人信息-->
		<%-- 		 
          <div class="cardDetial" id="div1" >
              <ul>
                  <li class="jf">
                  <image src="images/jxIco.png"></image>&nbsp;&nbsp;&nbsp;&nbsp;使用  <input class="input1" type="text" name="jane" id="jane"  value="0"  onblur="getValue()"></input>分,总共<span >${bonusamt}</span>分&nbsp;(&nbsp;-<span id="janemoney">0</span> 元&nbsp;)&nbsp;&nbsp;还需支付<span id="jane5">${TransAmt} </span>元<a href="#" onclick="submithide()">&nbsp;&nbsp;&nbsp; 使用</a></li>
                  <li class="acc"><image src="images/accIco.png"></image>&nbsp;&nbsp;&nbsp;&nbsp;浙江农信账户：<b>${eaccoutnbr}</b>余额<span>${eacctamt}</span>元<a href="#" onclick="check1()"> &nbsp;&nbsp;&nbsp;使用</a></li>
              </ul>
          </div>
				 <div class="cardDetial" id="div3" hidden="hidden">
              <ul>
                  <li class="jf">使用浙江农信综合积分<input name="jane1" id="jane1" type="text" value="0"  readonly="readonly"></input>分,<span></span>&nbsp;(&nbsp;抵扣-<span  id="jane3">0</span> 元&nbsp;)&nbsp;&nbsp;还需支付<span id="jane4">${TransAmt} </span>元<a href="#" onclick="edit()"> &nbsp;&nbsp;&nbsp;修改</a></li>
                  <li class="acc">浙江农信账户：<b>${eaccoutnbr} </b>余额<span>${eacctamt}</span>元<a href="#" onclick="check1()"> &nbsp;&nbsp;&nbsp;使用</a></li>
              </ul>
          </div> --%>
				
					<!-- <div id="div1" class="div" style="right: 100px">
					<table>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<image src="images/3.gif">
								</image>浙江农信综合积分 <span style="color: red">${bonusamt} </span>分 ( 可抵扣<span
								style="color: red">${bonusamtToAmt} </span>元)
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td><a href="#" onclick="useJane()"> 使用</a></td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"
								id="check" name="check" onclick="check1(this)"></input>&nbsp;浙江农信账户${eaccoutnbr}
								&nbsp;余额${eacctamt}元
							</td>
						</tr>
					</table>
				</div>
				mi
				<div id="div3" class="div" hidden="hidden">
					<table> 
						<tr>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<image src="images/3.gif">
								</image>使用浙江农信综合积分 <span style="color: red" id="usejane" >0</span>分(
								抵扣<span style="color: red" id="janemoney2"></span>元)
							</td>
							
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;</td>
							<td>抵扣<span style="color: red" id="janemoney1"></span><a
								href="#" onclick="edit()"> 修改</a></td>
						</tr>
						<tr>
							
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;</td>
							<td>支付<span style="color: red">  <input type="text" id ="realAmt" name="realAmt"/></span>元
							</td>
						</tr>
						<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox"
								id="check" name="check" onclick="check1(this)"></input>浙江农信账户${eaccoutnbr}
								&nbsp;余额${eacctamt}元</td>
							<td><td></td></tr>

					</table>
				</div> -->
				
				
				
				<div class="con_r">
					<div id="bigdiv1" class="con_r_t">
					 
						<a href="#" class="span-1"> 丰收e支付</a> 
						<a href="#"	class="span-2"> 丰收卡支付</a> 
					    <a href="#" class="span-3"> 网银支付</a>
					 	<a href="#" class="span-4"> 二维码支付</a> 
					 
					</div>

	<%--      电子账户支付   hidden
					<div id="bigdiv2" hidden="hidden">
						<div class="con_r_main-4">
							<div class="dingw">您的位置：电子账户支付></div>
							<div class="buz">
								<div class="buz1">1.填写信息</div>
								<!-- <div class="buz2">2.确认支付</div> -->
								<div class="buz3">2.支付成功</div>
							</div>
							<div class="sr_main"></div>

  <div class="input_box">
                  <ul>
                      <li class="title">使用余额：</li>
                      <li><span class="red" id="amount"></span> 元</li>
                  </ul>
                   <ul>
                      <li class="title">电子账户：</li>
                      <li><input id="EPayerPhoneNo" name="EPayerPhoneNo"
										type="text" class="input1"  value="${eaccoutnbr}" /></li>
                     
                  </ul>
                  
                  <ul>
                      <li class="title">支付密码：</li>
                      <li><input type="password" class="input1" placeholder="请输入支付密码" value="" id="EPassword" name="EPassword"/></li>
                     
                  </ul>
                  <ul>
                      <li class="title">短信验证码：</li>
                      <li><input type="text" class="input1" placeholder="请输入还款金额" value=""/></li><li><a class="Code">获取验证码</a></li>
                  </ul>
                  <ul>
                      <li class="title">&nbsp;</li>
                      <li class="blue">请输入您的手机号（139 XXXX1234）收到的短信验证码。</li>
                  </ul>
              </div> 

							<table width="540" border="0" >
								
                            
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td width="90px" class="right">电子账户：</td>
									<td width="250px"><input autocomplete="off"
										style="width: 180px;" id="EPayerPhoneNo" name="EPayerPhoneNo"
										value="${eaccoutnbr}" type="text" /></td>

								</tr>
								<tr>
									<td width="90px" class="right">支付密码：</td>
									<td width="250px"><input autocomplete="off"
										style="width: 180px;" id="EPassword" name="EPassword"
										value="" type="password" /></td>

								</tr>
								<tr>

								</tr>
							</table>
							</br> </br> </br> </br>
							<div class="btn_big">
							  
								<input type="button" id="EAccountEpay" value="下一步" class="xia_btn" />  
                                <input type="button" id=""  value="返回" class="xia_btn"
                                  onclick="goback()" /> 
                        
							</div>
						</div>

					</div>
 --%>

					<!-- 积分的浮框 -->

				<!-- 	<div id="fuchen" class="janefu" hidden="hidden"
						style="width: 470px; ">
						<div id="shiyongjane" class="formArea" hight="500px">
							<table style="width: 100%;background: url(images/4.gif) repeat left top;">
								<tr>
									<td style="width: 90%">&nbsp; &nbsp; 使用积分</td>
									<td style="width: 10%; text-align: right;"><a href="#"
										onclick="hide()" class="astyle"><image src="images/2.gif"></image></a>
										&nbsp;</td>
								</tr>
							</table>
							<div id="shiyongjane2" style="background-color: #e7f0fa;">
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<image src="">图片</image>
								&nbsp;使用浙江农信积分后，还可以使用"账户余额"，储蓄卡，信用卡支付剩余余额
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的账户有${bonusamt}积分，此次消费最多可用${bonusamt}分(抵扣${bonusamtToAmt}元)。</br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我要用：<input id="jane" name="jane"
									type="text" class="btn_big1 input" id="jane" value="0"
									style="width: 100px" onblur="getValue()"></input>分，抵扣￥ <span
									id="janemoney"> </span>元
							</p>
							<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;抵扣金额不能超过应付金额：${TransAmt}元</p>
							</br> </br> </br>

							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="确定"
									class="xia_btn" style="width: 100px" onclick="submithide()">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</input> <a href="#" onclick="hide()">不再使用积分</a>
							</p>
						</div>
						</div>
					</div>
 -->

					<!-- 结束 -->
					<div class="con_r_main" style="height:512px;">
						<div class="dingw">您的位置：丰收e支付 > 填写信息</div>
						<div class="buz">
							<div class="buz1">1.填写信息</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>

						<div class="input_box">
							<div id="div_bankcard">
								<ul id="ul_payPhoneNbr">
									<li class="title">手机号码：</li>
									<li><input type="text" id="payPhoneNbr" class="input1" maxlength="11" autocomplete="off" placeholder="请输入手机号" value="${MobileNo}"/></li>
									<li><span id="span_shortcutPhoneTip" style="font-size: 12px;" class="cheng"> *</span></li>
								</ul>
								<ul>
									 <div class="tis" style="margin: 5% 20% 5% 20%;width: 330px;">
									 	<span style="padding-left: 0px;">温馨提示：1.&nbsp;丰收e支付支持丰收卡及它行银联卡。
									 	<br /><span style="padding-left: 60px;">2.&nbsp;手机号码为发卡行预留手机号。</span>
									 	</span>
									 	</div>   
								</ul>
						</div>
							<div class="btn_big" id="div_oBCardP" style="height: 223px;">
							 	<input style="cursor: pointer;background-image:url(images/images/fanhui.png);" type="button" id="oBCardP" value="下一步" class="xia_btn" disabled="disabled"/>
							</div>
						</div>
						<br />
							<div class="wxts">
							<!-- <div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div> -->
						</div>
					</div>
					 
					<div class="con_r_main-1" style="height:512px;">
						<div class="dingw">您的位置：丰收卡支付 > 填写信息</div>
						<div class="buz">
							<div class="buz1">1.填写信息</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div class="sr_main">
						
					 <table width="540" border="0" align="center" style="margin-top: 20px">
							 	
                                <tr>
                                  <td class="right" >卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="72%"><input style="font:bold 15px '微软雅黑';height:39px;" class="input1" autocomplete="off" id="PayerAcctNbr" name="PayerAcctNbr" value="" type="text" /> <span id="PayerAcctNbrTip" style="font-size: 12px;" class="cheng"> *</span></td>
                                </tr>
                                <tr>
                                  <td  class="right">手机号码：</td>
                                  <td width="72%" height="70px;"><input style="font:bold 15px '微软雅黑';height:39px;" class="input1" autocomplete="off" id="PayerPhoneNo" name="PayerPhoneNo" value="" type="text" /> <span id="PayerPhoneNoTip" style="font-size: 12px;" class="cheng">*</span></td>
                                </tr>
                           
                                <tr>
                                  <td colspan="2"> 
                                      <div class="tis" style="margin-left: 20%;width: 53%">请输入您在银行开户或办理业务时留存手机号。</div>   
                                  </td>
                                </tr>
                                 <tr>
                                  <td class="right" style="padding-right:15px;margin-top: 12px;"><input id="checkbox1" name="checkbox1" type="checkbox" value="" style="width:20px;"/></td>
                                  <td class="font14"><a href="###" id="open-box">我已阅读并接受《浙江农信“丰收卡支付”协议》</a></td>
                                </tr>
                              </table>  
              
						
							 <%-- <table width="540" border="0" >
							 	<tr>
                                  <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td class="right" width="25%">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="70%"><input autocomplete="off" id="PayerAcctNbr" name="PayerAcctNbr" value="6226593040583234" type="text" /> <span id="PayerAcctNbrTip" class="cheng"> *</span></td>
                                </tr>
                                <tr>
                                  <td width="25%" class="right">手机号码：</td>
                                  <td width="70%"><input autocomplete="off" id="PayerPhoneNo" name="PayerPhoneNo"  type="text"  value="${PayerPhoneNo }" /> <span id="PayerPhoneNoTip" class="cheng"> *</span></td>
                                </tr>
                           
                                <tr>
                                  <td colspan="2"> 
                                      <div class="tis">请输入您在银行开户或办理业务时留存手机号。</div>   
                                  </td>
                                </tr>
                                 <tr>
                                  <td class="right" style="padding-right:15px;"><input id="checkbox1" name="checkbox1" type="checkbox" value="" style="width:20px;" onclick="check()"/></td>
                                  <td class="font14"><a href="###" id="open-box">我已阅读并接受《浙江农信“丰收卡支付”协议》</a></td>
                                </tr>
                              </table>  --%>
						</div>
						<div class="btn_big" style="height: 493px">
							 <input style="cursor: pointer;" type="button" id="doItCardPwd" value="下一步" class="xia_btn" disabled="disabled" /> 
							<!--   <input type="button"  onclick="location.href='###'" value="重填" class="chong_btn"> -->
						</div>
						<!-- <div class="wxts">
							<div class="tub">
								<img src="images/tishi.png" />
							</div> -->
							<div class="wxts">
						</div>
					</div>
					<div class="con_r_main-2">
						<div class="dingw">您的位置：网银支付 > 选择网银</div>
						<div class="buz">
							<div class="buz1">1.选择网银</div>
							<div class="buz2">2.登录网银</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div class="sr_main">
						
							<div class="btn_big" style="height: 50px;">
								<input style="cursor: pointer;" type="button" id="cancelCredit1" value="个人网银"
									onclick="doItPbank();" class="chong_btn"
									 />
								<input style="cursor: pointer;" type="button" id="cancelCredit2" value="企业网银"
									onclick="doItEbank(this);" class="chong_btn"
									/>
							</div>
						
							<!-- 跳到个人网银div -->
<div id="myAccountContent" hidden="hidden" style="position:relative; left:-10px;">	<!-- left:-90px; -->
	<div class="main_bzlch" >
		<th>
			<ul class="ebank_logo" style="padding-top: 0px;">
			<li class="cPayTypeCdBank"><input type="radio" name="BankCode"
					id="ZJRC" value="ZJRC"/> <span ><image  width = "120px" hight = "34px" src="images/nongxin.png"></image></span>
				</li>
				
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="ICBC" value="ICBC"/> <span class="bank_logo bank50"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="ABC-UPOP" value="ABC"/> <span class="bank_logo bank02"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CCB" value="CCB"/> <span class="bank_logo bank04"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BOCOMNEW" value="BOCOM"/> <span class="bank_logo bank25"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="BOC" value="BOCSH"/> <span class="bank_logo bank03"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CMB" value="CMB"/> <span class="bank_logo bank11"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="PSBC" value="PSBC"/> <span class="bank_logo bank06"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CNCB" value="CNCB"/> <span class="bank_logo bank07"> </span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="GDB" value="GDB"/> <span class="bank_logo bank18"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="PAB" value="PAB"/> <span class="bank_logo bank30"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CIB" value="CIB"/> <span class="bank_logo bank17"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="SPDB" value="SPDB"/> <span class="bank_logo bank29"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CEB" value="CEB"/> <span class="bank_logo bank28"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="CMBC-YL" value="CMBC"/> <span class="bank_logo bank08"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BOS" value="BOS"/> <span class="bank_logo bank09"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="SRCB" value="SRCB"/> <span class="bank_logo bank20"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="HXB" value="HXB"/> <span class="bank_logo bank809"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BCCB" value="BCCB"/> <span class="bank_logo bank13"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BRCB" value="BRCB"/> <span class="bank_logo bank21"></span>
				</li>
			</ul>
		</th>
		
		
    
</div>
</div>
<!-- 跳到企业网银 -->
		<div id="myAccountContent2" hidden="hidden" style="position:relative; left:-10px;" ><!-- left:-90px; -->
   <table width="90%" border="0" cellspacing="0" cellpadding="0" class="main_bzlch" >
	<tr>
		<th>
			<ul class="ebank_logo" style="padding-top: 0px;">
				<li class="cPayTypeCdBank"><input type="radio" name="BankCode"
					id="ZJRC" value="ZJRC"/> <span ><image  width = "120px" hight = "34px" src="images/nongxin.png"></image></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="ICBC" value="ICBC"/> <span class="bank_logo bank50"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="ABC-UPOP" value="ABC"/> <span class="bank_logo bank02"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CCB" value="CCB"/> <span class="bank_logo bank04"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="CMB" value="CMB"/> <span class="bank_logo bank11"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="HXB" value="HXB"/> <span class="bank_logo bank809"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode" 
					id="BCCB" value="BCCB"/> <span class="bank_logo bank13"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BRCB" value="BRCB"/> <span class="bank_logo bank21"></span>
				</li>
				
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BRCB" value="SRCB"/> <span class="bank_logo bank93"></span>
				</li>
			    <li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="CMBC-YL" value="CMBC"/> <span class="bank_logo bank08"></span>
				</li>
				<li class="cPayTypeCdOtherBank"><input type="radio" name="BankCode"
					id="BOS" value="BOS"/> <span class="bank_logo bank09"></span>
				</li>
			</ul>
		</th>
	</tr>
</table>


</div>

</div>
	<div class="btn_big" id="btn_big_c" type="hidden" style="height: 432px;">
	<input style="cursor: pointer;" type="button" id="btn_big_btn" value="确  认" class="xia_btn" onclick="cyberPay();" />
	</div>
						</div>
						
						<!-- <div class="con_r_main-3">
						<div class="dingw">您的位置：跨行智能卡支付 > 填写信息</div>
						<div class="buz">
							<div class="buz1">1.填写信息</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">2.支付成功</div>
						</div>

						<div class="input_box">
							<div id="div_bankcard">
								<ul id="ul_payPhoneNbr">
									<li class="title">手机号码：</li>
									<li><input type="text" id="payPhoneNbr" class="input1" maxlength="11" autocomplete="off" placeholder="请输入手机号"/></li>
									<li><span id="span_shortcutPhoneTip" style="font-size: 12px;" class="cheng"> *</span></li>
								</ul>
							</div>
							<div class="btn_big" id="div_oBCardP" style="height: 223px;">
							 	<input style="cursor: pointer;background-image:url(images/images/fanhui.png);" type="button" id="oBCardP" value="下一步" class="xia_btn" disabled="disabled"/>
							</div>
						</div>
						<br />
							<div class="wxts">
							<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div>
						</div>
					
					</div> -->
				<div class="con_r_main-3" style="height:512px;">
						<div class="dingw">您的位置：二维码支付</div>
						<div class="buz">
							<div class="buz1">1.选择二维码</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div align="center" style="margin-top:140px">
							<!-- <div>
									<img alt="扫一扫" name="QRcode" width="150" height="150" id="QRcode" /></td>
							</div> -->
							
							<div style="text-align:center;"><img width="288" id="QRcode" height="288"/></div>
							<br><br>
							
							<!-- <div style="float: left;width: 663px;height: 20px;margin-top: 15px;" >
								<table width="180px;">
                          			<tr>
                          			<td id="alipay"><a id="alipay_a" class="black_a" onclick="selectcode(this)"><b>支付宝二维码</b></a></td>
                          		
                          			<td id="weixin"><a id="weixin_a" class="black_a"  onclick="selectcode(this)" ><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信二维码</b></a></td>
                          			
                          			</tr>
                          		</table>
                          	</div>	 -->	
							<div>
								<ul>
									 <div class="tis" style="margin: 3% 20% 5% 26%;width: 330px;padding-left: 15px;">
									 	<span style="padding-left: 0px;">温馨提示：&nbsp;二维码在5分钟内有效超时请重新获取。
									 	</span>
									 	</span>
									 	</div>   
								</ul>
							</div>
						
						</div>
				</div>
						 <div class="wxts">
						<!-- 	<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div> -->
						</div>
					</div>
		
					</form>
				</div>

		 <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号 </div>
	      
		</div> 
	<script type="text/javascript">
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
		}
		
		$(function() {
			
			$("#div_list").children("div").each(function(index, element) {
				$(this).bind("mouseover", function() {
					$(this).css({
						"background-color" : "#fff",
						"cursor" : "pointer"
					});
				}).bind("mouseout", function() {
					$(this).css("background-color", "#eee");
				}).bind("click", function() {
					$(".box").hide();
					$("#bg").hide();
					showLoading();
					$("#form-box").submit();
				});
			});

			$("#closebox").bind("click", function() {
				$(".box").hide();
				$("#bg").hide();
				showLoading();
				$("#form-box").submit();
			});
			
			function hideLoading(){
				 $('.load_box').hide();
				 $("#load_bg").hide();
			}
			
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
			
			$('#open-box').click(function() {
				$('#fuc').removeClass('hide');
			});
			$('#close-box').click(function() {
				$('#fuc').addClass('hide');
			});
			
			$("#payPhoneNbr").bind("keyup",function(){
				checkPhone($(this));
			}).bind("blur",function(){
				checkPhone($(this));
			});
			function checkPhone(that){
				var phoneNbr = that.val();
				if(/^1[3|4|5|6|7|8|9]\d{9}$/.test(phoneNbr)){
					$("#span_shortcutPhoneTip").hide().html(" *").fadeIn(500);
					$("#oBCardP").css("background-image", "url(images/images/xiayi.png)").attr("disabled", false);
				}else {
					$("#oBCardP").css("background-image", "url(images/images/fanhui.png)").attr("disabled", "disabled");
				}
			}
			
			$("#oBCardP").bind("click", function(){
				/* if($("#ElecPortNotify").val() == "K1"){
					alertTip("涉及跨境商品不支持他行支付", "");
					return;
				} */
				var phoneNbr = $("#payPhoneNbr").val();
				showLoading();
				document.forms[0].PayerPhoneNo.value = phoneNbr;
				document.forms[0].action = "otherQuickPayPre.do";
				document.forms[0].submit();
			});
		});
		$(function() {
			var phoneNbr = $("#payPhoneNbr").val();
			if(/^1[3|4|5|6|7|8|9]\d{9}$/.test(phoneNbr)){
				$("#span_shortcutPhoneTip").hide().html(" *").fadeIn(500);
				$("#oBCardP").css("background-image", "url(images/images/xiayi.png)").attr("disabled", false);
			}else {
				$("#oBCardP").css("background-image", "url(images/images/fanhui.png)").attr("disabled", "disabled");
			}
		});
	</script>
	<div class="fuc hide" id="fuc" style="overflow: scroll">
		<div class="fuc_main"
			style="width: 980px; height: 600px; overflow: auto; border: 1px">
			<div class="fuc_main_b">
				浙江省农村信用社（合作银行、商业银行）“丰收卡支付”协议 <a href="###" id="close-box"><img
					src="images/images/cha.png" /></a>
			</div>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议是由浙江省农村信用社（合作银行、商业银行）（以下简称“我行”）与我行持卡人（以下简称“您”）就“丰收卡支付”服务（以下简称“本服务”）的使用等相关事项所订立的有效合约。“丰收卡支付”是浙江省农村信用社（合作银行、商业银行）向浙江省农村信用社（合作银行、商业银行）持卡人提供的凭银行卡号和密码支付的电子支付结算方式。您通过互联网（Internet）点击确认或以其他方式选择接受本协议，即表示您同意接受本协议的全部约定内容，确认承担由此产生的一切责任。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在接受本协议之前，请您仔细阅读本协议的全部内容（特别是以粗体下划线标注的内容）。如果您不同意本协议的任何内容，或者无法准确理解相关条款的解释，请不要进行后续操作。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）您应确保您在使用本服务时所使用的银行卡为您本人所有，并确保您用其支付的行为合法、有效，未侵犯任何第三方合法权益；否则因此造成我行、持卡人损失的，您应负责赔偿并承担全部法律责任。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）我行仅为您提供安全可靠的网上支付服务，支付服务所关联的基础交易中的商品质量、送货服务等引起的争议和纠纷均由您和提供商品或服务的商户自行协商解决，我行对此不承担任何责任</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）您应妥善保管该卡开户户名、开户类型、与之相关的证件类型及证件号码、手机号码、固定电话、通信地址等与该卡有关的一切信息（以下简称“身份信息及卡信息”），不得将身份信息及卡信息转告他人，并在安全网络环境下使用，如您遗失银行卡、泄露身份信息及卡信息，您应及时通知我行，以减少可能发生的损失。因您泄露密码、数字证书、U盾、丢失银行卡等所致的风险及损失需由您自行承担。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）使用“丰收卡支付”服务，丰收借记卡单笔网上消费交易额最高为人民币20000元，每日网上消费交易额最高为人民币50000元；丰收信用卡单笔网上消费交易额最高为人民币20000元，每日网上消费交易额最高为人民币50000元，我行可根据业务发展需要及风险控制需要设置或修改支付限额，无须对客户另行通知，请您留意我行有关公告及网页说明。如您对我行修改限额存在异议的，可申请终止使用本服务。如我行修改限额后，您仍然使用本功能的，视为您同意我行对限额所进行的修改和调整。在任何情况下，支付金额不应超过我行设置的支付限额。如实际支付金额大于支付限额，我行将拒绝执行交易指令。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）在支付过程中，我行将向您在我行预留的手机号码发送动态验证码的方式追加验证，以确保您的交易安全。请确保并确认您在我行预留的手机号码为您本人手机号码并在本人实际控制之下。您应妥善保管移动电话所涉相关信息，并保持移动电话通讯和使用功能通畅。因您遗失移动电话、将移动电话转借他人使用、移动电话自身故障、或其他因您自身原因而导致其无法收取我行发送的动态验证码所产生的一切后果及损失，由您自行承担。如因网络通讯、黑名单等非我行原因造成您无法接收动态验证码的，我行不承担任何责任。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（6）您同意我行对您在使用本服务过程中所填写的包括但不限于姓名、银行卡卡号、手机号码、身份证号码等进行校对核验，同时我行保留随时变更上述确认要素作为校验标准的权利。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（7）您不得利用银行卡进行套现、虚假交易、洗钱等行为，且有义务配合我行进行相关调查，一旦您拒绝配合进行相关调查或我行认为您存在或涉嫌虚假交易、洗钱、套现或任何其他非法活动、欺诈或违反诚信原则的行为、或违反本协议约定的，我行有权采取以下一种、多种或全部措施：一、暂停或终止提供本协议项下“丰收卡支付”服务；二、终止本协议；三、取消您的用卡资格。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（8）您同意，我行有权变更、暂停本协议项下相关服务，有权修改、终止本协议，并于执行前通过我行网站进行公告。相关公告经网站发布视为您已收到。您在公告执行后继续办理相关业务的，视同接受有关本协议修改、变更的内容。本协议终止后，您在协议终止前发送我行进行处理的交易指令仍有效，您应承担其后果。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议与本行银行卡章程或协议相冲突部分，以本协议为准。</p>
		</div>
	</div>
	
	<div id="bg"></div>
	<div class="box" style="display:none">
	    <h2><span></span><a id="closebox" href="#" class="close">关闭</a></h2>
	    <div id="div_list" class="list">
	    	<h3></h3>
	    	<div class="div_paysuccess"><a id="paysuccess" href="#">已经完成支付</a></div>
	    	
	    	<div class="div_payfailer"><a id="payfailer" href="#">支付遇到问题</a></div>
	    	
	    	<form id="form-box" action="" method="post">
				<input name="MerchantId" type="hidden" value="${ MerchantId}"/>
				<input name="MerSeqNo" type="hidden" value="${ MerSeqNo}"/>
				<input name="MerchantName" type="hidden" value="${ MerchantName}"/>
				<input name="TransAmt" type="hidden" value="${ TransAmt}"/>
				<input name="OrderId" type="hidden" value="${ OrderId}"/>
				<input name="MerDate" type="hidden" value="${ MerDate}"/>
				<input name="MerURL1" type="hidden" value="${ MerURL1}"/>
			</form>
	    	<hr id="boxhr" />
	    </div>
	</div>
	
	<div id="bg_tip"></div>
	<div id="load_bg"></div>
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
<!-- 		<div class='loader loader--audioWave'></div> -->
<!-- 		<div class='loader loader--snake'></div> -->
<!-- 		<div class='loader loader--spinningDisc'></div> -->
<!-- 		<div class='loader loader--circularSquare'></div> -->
<!-- 		<span style="font: 16px '微软雅黑';">处理中&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</span> -->
	</div>
<!--[if lte IE 7]>  
<script type="text/javascript">
	$("#div_bankcard").css("width","620px")
	$("#div_bankcard > #ul_payPhoneNbr").css("margin-top","0px");
</script>
<![endif]-->
</body>
</html>
