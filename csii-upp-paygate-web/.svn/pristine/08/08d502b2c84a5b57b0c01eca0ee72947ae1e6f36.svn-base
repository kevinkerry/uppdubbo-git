<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>浙江农信互联网支付系统</title>
	<style>
.btn_big1 {
	width: 540px;
	text-align: center;
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
/* 代替alert的弹窗样式end */
-moz-opacit
</style>
<link rel="stylesheet" href="css/global1.css" type="text/css"/>
<link href="css/css1.css" rel="stylesheet" type="text/css" /> 

<link href="css/up.card.css" rel="stylesheet" type="text/css" />
<link href="css/bank_icon.css" rel="stylesheet" type="text/css" />
<link href="css/zhifu.css" rel="stylesheet" type="text/css" />

<link href="css/Base.css" rel="stylesheet" type="text/css" />
<link href="css/DesignByXiaoyu.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/common.js"></script><!--公共效果-->
<script language="javascript" type="text/javascript" src="script.do"></script>
<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
<script type="text/javascript">
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
			$("#form-box").submit();
		});
	});

	$("#closebox").bind("click", function() {
		$(".box").hide();
		$("#form-box").submit();
	})

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
		/* doItPbank(); */
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
	});

	$('#FoisonPay').click(function() {
		var phone = $('#FsPayerPhoneNo').val();
		var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;

		if ("" == $('#FsPayerPhoneNo').val()) {
			$('#FsPayerPhoneNoTip1').text("手机号不能为空");
		} else {
			if (!reg.test(phone)) {
				$('#FsPayerPhoneNoTip1').text("手机号输入错误");
			} else {
				$('#FsPayerPhoneNoTip1').text("*");
				document.forms[0].action = "otherQuickPayPre.do";
				document.forms[0].submit();
			}
		}

	});

	$('#doItFsReset').click(function() {
		$('#FsPayerPhoneNoTip1').text("*");
	});
	// 丰收卡支付

	// 卡号手机号失去焦点事件
	$('#PayerAcctNbr').blur(function() {
		var acctno = $('#PayerAcctNbr').val();
		var acctnoData19 = /^(6)[0-9]{18}$/;
		var acctnoData16 = /^(6)[0-9]{15}$/;
		if (acctnoData16.test(acctno) || acctnoData19.test(acctno)) {
			$('#PayerAcctNbrTip').text("*");
		} else {
			$('#PayerAcctNbrTip').text("卡号输入错误");
		}
	});
	$('#PayerPhoneNo').blur(function() {
		var phone = $('#PayerPhoneNo').val().trim();
		var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;
		if (!reg.test(phone)) {
			$('#PayerPhoneNoTip').text("号码输入错误");
		} else {
			$('#PayerPhoneNoTip').text("*");
		}
	});

	$('#doItCardPwd').click(
			function() {
				if ($('#PayerPhoneNoTip').text() == "*"
						&& $('#PayerAcctNbrTip').text() == "*") {
					document.forms[0].action = "cardPwdPre.do";
					document.forms[0].submit();
				} else {
					return;
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
});

// 2015年7月3日 个人网银支付
function doItPbank() {

	document.forms[0].action = "PbankPay.do"; // .do http-paygate.xml
	document.forms[0].submit();
}

function morenclick() {

	$(".con_r_main-2").css({
		"display" : "block"
	});
	$(".con_r_main-1").css({
		"display" : "none"
	});
	$(".con_r_main-3").css({
		"display" : "none"
	});
	$(".con_r_main").css({
		"display" : "none"
	});
	$(".span-4").removeClass("active");
	$(".span-1").removeClass("active");
	$(".span-2").removeClass("active");
	$(".span-3").addClass("active");

}

function doItEbank() {
	document.forms[0].action = "EbankPay.do";// bankPay.do?id='e'
	document.forms[0].submit();

}
function isAgree() {

	morenclick();
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
	var BankCode = "";
	var BankCode1 = $('#myAccountContent input[name="BankCode"]:checked ')
			.val();
	var BankCode2 = $('#myAccountContent2 input[name="BankCode"]:checked ')
			.val();

	if (BankCode1) {
		if (BankCode1 == "ZJRC") {
			document.forms[0].action = "PbankPay.do";
			document.forms[0].submit();
		} else {
			document.forms[0].PayerAcctDeptNbr.value = BankCode1;
			document.forms[0].CyberTypCd.value = "01";
			document.forms[0].action = "otherPerBankPay.do";
			document.forms[0].target = "_blank";
			hidecyber()
			document.forms[0].submit();
		}
	} else if (BankCode2) {
		if (BankCode2 == "ZJRC") {
			document.forms[0].action = "EbankPay.do";
			document.forms[0].submit();
		} else {
			document.forms[0].PayerAcctDeptNbr.value = BankCode2;
			document.forms[0].CyberTypCd.value = "02";
			document.forms[0].action = "otherEnterBankPay.do";
			document.forms[0].target = "_blank";
			hidecyber()
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

	$(".con_r_main-2").children("div[class=dingw]").html("您的位置：丰收e支付 > 确认支付")

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

$(function() {
	$("#div_list_tip").children("div").each(function(index, element) {
		$(this).bind("mouseover", function() {
			$(this).css({
				"background-color" : "#fff",
				"cursor" : "pointer"
			});
		}).bind("mouseout", function() {
			$(this).css("background-color", "#eee");
		}).bind("click", function() {
			$(".box_tip").fadeOut(500);
			$("#bg_tip").hide();
		});
	});

	$("#close_box_tip").bind("click", function() {
		$(".box_tip").fadeOut(500);
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
		top : ($(window).height() - $box.height()) / 2 + $(window).scrollTop()
				+ "px",
		display : "block"
	});
}
/*---代替alert弹窗js代码start---*/
</script>
</head>

<body onload="isAgree();">
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
		<div class="content">
			<form name="form1" action="#" method="post">
					<input id="integral"  name="integral" type="hidden" value="0"> </input>
					<input id="isFlag"  name="isFlag" type="hidden" value="0"> </input>
					<input id="deductibleAmt" name="deductibleAmt" type="hidden" value="0"> </input>
					<input id="realAmt"  name="realAmt" type="hidden" value="${TransAmt}"> </input>
					<input id="PayerAcctDeptNbr"  name="PayerAcctDeptNbr" value="" type="hidden"></input>
					<input id="CyberTypCd"  name="CyberTypCd" value="" type="hidden"></input>
					<input id="PayerCardTypCd"  name="PayerCardTypCd" value="" type="hidden"></input>
				<pe:hidden fieldList="AcctType,PlainContext,Plain,ChannelId,OrderId,MerDate,MerchantName,Signature" skipNULL="true" />
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
								<td class="cheng" >￥${ TransAmt}<input type="hidden" id="money1" value="${ TransAmt}"></input> </td>
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
				
				<div class="con_r">
					<div id="bigdiv1" class="con_r_t">
					  	<a href="#" class="span-3"> 网银支付</a>
						<a href="#" class="span-1"> 丰收e支付</a> 
					<!-- 	<a href="#"	class="span-2"> 丰收卡支付</a> 
					    <a href="#" class="span-4"> 提现</a> -->
					 
					</div>



					<div class="con_r_main">
						<div class="dingw">您的位置：丰收e支付 > 填写信息</div>
						<div class="buz">
							<div class="buz1">1.填写信息</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div class="sr_main">
						
						
						 <div class="input_box">
                  <ul>
                      <li class="title">手机号码：</li>
                      <li><input style="font:bold 15px '微软雅黑';" type="text" class="input1"   id="FsPayerPhoneNo" name="FsPayerPhoneNo" value="${MobileNo}"/></li>
                      <li><span id="FsPayerPhoneNoTip1" class="cheng">*</span></li>
                  </ul>
              </div> 
              <!--步骤按钮-->
              <div class="btn_box">
                <input  type="button" value="下一步" id="FoisonPay">
              </div>
						
						
						<%-- 	 <table width="540" border="0" >
                                <!--<tr>
                                  <td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td><input id="FsPayerAcctNbr" name="FsPayerAcctNbr" value="" type="text" /> <span id="FsPayerAcctNbrTip" class="cheng"> *</span></td>
                                </tr>
                                -->
                               	<tr>
                                  <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td width="90px" class="right" >手机号码：</td>
                                  <td width="250px"><input autocomplete="off" style="width:180px;" id="FsPayerPhoneNo" name="FsPayerPhoneNo" value="${MobileNo}" type="text" />
                                   <span id="FsPayerPhoneNoTip1" class="cheng">*</span></td>
                                </tr>                            
                                <tr>
                                  <td colspan="2">
                                      <div class="tis">温馨提示:</div>
                                      <div class="tis2">1.如果您已开通丰收e支付,您可以输入已开通丰收e支付的手机号进行支付;</div> 
                                      <div class="tis2">2.如果您尚未开通丰收e支付,请输入银行预留手机号，点击"下一步"直接进行注册并支付。</div>  
                                  </td>
                                </tr>
                                <tr>
                                  <td colspan="2">&nbsp;</td>
                                </tr>
                             </table> --%>
						</div>
					<!-- 	<div class="btn_big">
							 <input type="button" id="doItFsEpay"    value="下一步" class="xia_btn" /> 
							 <input type="reset" id="doItFsReset" value="重填" />
						</div> -->
						 <div class="wxts">
							<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
							</div>
						</div>
					</div>
					
					<div class="con_r_main-1">
						<div class="dingw">您的位置：丰收卡支付 > 填写信息</div>
						<div class="buz">
							<div class="buz1">1.填写信息</div>
							<div class="buz2">2.确认支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
						<div class="sr_main">
						
					 <table width="540" border="0" align="center" >
							 	<tr>
                                  <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td class="right" >卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="70%"><input style="font:bold 15px '微软雅黑';" autocomplete="off" id="PayerAcctNbr" name="PayerAcctNbr" value="" type="text" /> <span id="PayerAcctNbrTip" class="cheng"> *</span></td>
                                </tr>
                                <tr>
                                  <td  class="right">手机号码：</td>
                                  <td width="70%"><input style="font:bold 15px '微软雅黑';" autocomplete="off" id="PayerPhoneNo" name="PayerPhoneNo" value="" type="text" /> <span id="PayerPhoneNoTip" class="cheng">*</span></td>
                                </tr>
                           
                                <tr>
                                  <td colspan="2"> 
                                      <div class="tis">请输入您在银行开户或办理业务时留存手机号。</div>   
                                  </td>
                                </tr>
                                 <tr>
                                  <td class="right" style="padding-right:15px;margin-top: 12px;"><input id="checkbox1" name="checkbox1" type="checkbox" value="" style="width:20px;" onclick="check()"/></td>
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
						<div class="btn_big">
							 <input style="cursor: pointer;" type="button" id="doItCardPwd" value="下一步" class="xia_btn" disabled="disabled" /> 
							<!--   <input type="button"  onclick="location.href='###'" value="重填" class="chong_btn"> -->
						</div>
						<!-- <div class="wxts">
							<div class="tub">
								<img src="images/tishi.png" />
							</div> -->
							<div class="wxts">
							<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div>
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
								<div class="btn_big">
	                              <p>&nbsp;</p>
								<input type="button" id="cancelCredit" value="个人网银"
									onclick="doItPbank(this);" class="chong_btn"
									style="background-image: url(images/images/xiayi.png); color: #fff;" />
								<input type="button" id="cancelCredit" value="企业网银"
									onclick="doItEbank(this);" class="chong_btn"
									style="background-image: url(images/images/xiayi.png); color: #fff;" />
							</div>
						</div>
						<div class="wxts">
							<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                               <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div>
						</div>
						</div>
						
						<div class="con_r_main-3">
				
					
					</div>
			
						 <div class="wxts">
							<div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
	                              <p>&nbsp;</p>
							</div>
						</div>
					</div>
		
					</form>
				</div>

		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	
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
				var phoneNbr = that.val().trim();
				if(/^1[3|4|5|6|7|8|9]\d{9}$/.test(phoneNbr)){
					$("#span_shortcutPhoneTip").hide().html(" *").fadeIn(500);
					$("#oBCardP").css("background-image", "url(images/images/xiayi.png)").attr("disabled", false);
				}else {
					$("#oBCardP").css("background-image", "url(images/images/fanhui.png)").attr("disabled", "disabled");
				}
			}
			
			$("#oBCardP").bind("click", function(){
				var phoneNbr = $("#payPhoneNbr").val().trim();
				document.forms[0].PayerPhoneNo.value = phoneNbr;
				document.forms[0].action = "otherQuickPayPre.do";
				document.forms[0].submit();
			});
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
</body>
</html>
