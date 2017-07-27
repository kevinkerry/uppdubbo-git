<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>浙江农信手机支付</title>
    <link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
     <script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
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
        function ToSuccessMerchant() {
		var channel = "${ChannelId}";
            if ("05"!=channel&&"04"!=channel) {
            	var merURL1="${MerURL1}";
            	if(merURL1==null||merURL1==""){
            		return;
            	}
            	else{
            		location.href=merURL1;
            	}
            }
            ToMerchant(0);
        }
        function ToFailedMerchant() {
        	
		
		if("04"!=channel&&"05"!=channel) {
            	var merURL1="${MerURL1}";
            	
            	if(merURL1==null||merURL1==""){
            		return;
            	}
            	else{
            		location.href=merURL1;
            	}
            }
		
            ToMerchant(1);
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
                orderId = orderId.substring(0, 7) + "****" + orderId.substring(orderId.length - 6, orderId.length);
            }
            var i;
            for (i = 0; i < doc.length; i++) {
                var item = doc[i];
                item.value = orderId;
            }
        }
        
        function phoneNumSafeDispose() {
            var PayerPhoneNoValue ="${PayerPhoneNo}";
             PayerPhoneNoValue = PayerPhoneNoValue.substring(0, 3) + "****" + PayerPhoneNoValue.substring(7, 11);
             document.getElementById("PayerPhoneNo").innerHTML=PayerPhoneNoValue;
         }
         function payernbrNumSafeDispose() {
             var PayerCardNoValue = "${PayerAcctNbr}";
             PayerCardNoValue = PayerCardNoValue.substring(0, 4) + "******" + PayerCardNoValue.substring(PayerCardNoValue.length-4);
             document.getElementById("PayerAcctNbr").innerHTML=PayerCardNoValue;;
         } 

        function goBack() {
            
         document.forms[0].action = "IPEM.do";
         document.forms[0].submit();
            
        }
        
        var t=5;
		var inter=setInterval("countDown()",1000);
		function countDown(){
	       var time=document.getElementById("time");
	       time.innerHTML=t;
	       t--;
	         if(t<=0){
	        	clearInterval(inter);
	        }
		}
	       function startup(){
	    	  
				setTimeout(function(){
					$('#goback1').click();
					},5000); 
			}
        function LoadJs() {
        	startup();
            GeneratorMobileType();
            orderIdSafeDispose();
            countDown();
            phoneNumSafeDispose();
            payernbrNumSafeDispose();
            }
    </script>
</head>

<body onload="LoadJs()">
<form name="form1" action="#" method="post">
    <pe:hidden fieldList="Plain,Signature,OrderId,MerchantName,TransAmt,ChannelNbr" skipNULL="true"/>
    <div class="logo">
        <input type="hidden" name="PayerAcctNbr" value="${PayerAcctNbr}"/>
        <input type="hidden" name="PayerPhoneNo" value="${PayerPhoneNo}"/>
		<input type="hidden" name="state" id="state" value="${state}"/>	
        <div class=""> 丰收卡支付 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
                                                               onclick="goBack()"/></a>
        </div>
    </div>
    <div class="main">
        <%--处理页面异常--%>
        <input id="getSMS" class="hq" type="hidden"/>
        
                <div class="dui">
                    <img src="images/phone/dui.png">
                    <span class="text_w">恭喜您，支付完成</span>
                </div>
                <table width="100%" border="0" class="wanc">
                    <tr>
                        <td class="right">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：</td>
                        <td><c:out value='${MerchantName}'/></td>
                    </tr>
                    <tr>
                        <td class="right">订&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;号：</td>
                        <td class="order_id"><input type="text" name="OrderSafeId" readonly="readonly"
                                                    style="width: 100%"/></td>
                    </tr>
                    <tr>
                        <td class="right">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
                        <td><fmt:formatNumber type="number" value="${TransAmt}" pattern="#,###,##0.00"/></td>
                    </tr>
                    <tr>
                        <td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                        <td id="PayerAcctNbr"></td>
                    </tr>
                    <tr>
                        <td class="right">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                        <td id="PayerPhoneNo"></td>
                    </tr>
                </table>
                <a> </a>
                <div style="margin-bottom: 20px;">
                （<span id="time" style="width:5px;font-size:16px; color: red">5</span>秒后自动返回，点击按钮立即返回） 
                </div>
                <div class="bottom_botton">
                    <input onClick="ToSuccessMerchant();" value="返回" type="button" id="goback1"/>
                </div>

         
    </div>
</form>
</body>
</html>
