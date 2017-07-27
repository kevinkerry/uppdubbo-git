<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true"
	pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>浙江农信手机支付</title>
<link href="css/cellphone.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="script.do"></script>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/layer-v1.9.3/layer.js"></script>
<script type="text/javascript">
	var curCount = 0;
	var mobileType;
	function ToMerchant(result) {
		var channelId = "${ChannelId}";
		if ("05" == channelId) {
			ToO2OMessage(result);

		} else if ("04" == channelId) {
			ToDianShang(result);
		}
	}
	function ToDianShang(result) {
		if ("Android" == mobileType) {
			if (0 == result) {
				window.mobileEpay.payResult(1);
			} else if (1 == result) {
				window.mobileEpay.payResult(0);
			}
		} else if ("iPhone" == mobileType) {
			if (0 == result) {
				window.location.href = "ios://returnIOSSuccess";
			} else if (1 == result) {
				window.location.href = "ios://returnIOSFail";
			}
		} else if ("iPad" == mobileType) {
			if (0 == result) {
				window.location.href = "ios://returnIOSSuccess";
			} else if (1 == result) {
				window.location.href = "ios://returnIOSFail";
			}
		}
	}
	function ToO2OMessage(result) {
		if ("Android" == mobileType) {
			if (0 == result) {
				Native.JavaScriptCall_payResult("paySuccess");
			} else if (1 == result) {
				Native.JavaScriptCall_payResult("payError");
			}
		} else if ("iPhone" == mobileType) {
			if (0 == result) {
				window.location.href = "http://localhost/LocalActions/paySuccess";
			} else if (1 == result) {
				window.location.href = "http://localhost/LocalActions/payError";
			}
		} else if ("iPad" == mobileType) {
			if (0 == result) {
				window.location.href = "http://localhost/LocalActions/paySuccess";
			} else if (1 == result) {
				window.location.href = "http://localhost/LocalActions/payError";
			}
		}
	}
	function GeneratorMobileType() {
		var agent = navigator.userAgent;
		if ((-1 < agent.indexOf('Android'))) {
			mobileType = "Android";
		} else if (-1 < agent.indexOf('iPhone')) {
			mobileType = "iPhone";
		} else if (-1 < agent.indexOf('iPad')) {
			mobileType = "iPad";
		} else if ("") {
			mobileType = "unknown";
		}
	}

	function orderIdSafeDispose() {
		var doc = document.getElementsByName("OrderSafeId");
		var orderId = "${OrderId}";
		var channelId = "${ChannelId}";
		if ("04" == channelId) {

		} else {
			orderId = orderId.substring(0, 7) + "****"
					+ orderId.substring(orderId.length - 6, orderId.length);
		}
		var i;
		for (i = 0; i < doc.length; i++) {
			var item = doc[i];
			item.value = orderId;
		}
	}

	$(function() {
		$("#btn_back").bind("click", function() {
			var channelId = $("input[name='ChannelId']").val();
			if ("04" != channelId && "05" != channelId) {
				var merURL1 = $("input[name='MerURL1']").val();
				if(merURL1==null||merURL1==""){
            		return;
            	}
				location.href = merURL1;
				return;
			}
			ToMerchant(0);
		});
	});

	function LoadJs() {
		GeneratorMobileType();
		orderIdSafeDispose();
	}
</script>
</head>

<body onload="LoadJs()">
	<form name="form1" action="#" method="post">
		<input type="hidden" name="ChannelId" value="${ChannelId}" /> <input
			type="hidden" name="MerURL1" value="${MerURL1}" />
		<div class="logo">
			<div class="">跨行智能支付</div>
		</div>
		<div class="main">
			<div class="dui">
				<img src="images/phone/dui.png" /> <span class="text_w">订单已受理</span>
			</div>
			<table width="100%" border="0" class="wanc">
				<tr>
					<td class="right">商&nbsp;&nbsp;&nbsp;户：</td>
					<td><c:out value='${MerchantName}' /></td>
				</tr>
				<tr>
					<td class="right">订单号：</td>
					<td class="order_id"><input type="text" name="OrderSafeId"
						readonly="readonly" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="right">金&nbsp;&nbsp;&nbsp;额：</td>
					<td><fmt:formatNumber type="number" value="${TransAmt}"
							pattern="#,###,##0.00" /></td>
				</tr>
			</table>
			
			<div class="bottom_botton">
			<input id="btn_back" value="返回" type="button" /> 
			</div> 
		</div>
	</form>
</body>
</html>
