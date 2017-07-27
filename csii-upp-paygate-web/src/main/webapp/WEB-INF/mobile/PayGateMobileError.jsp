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
            layer.msg(channelId);
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
            ToMerchant(0);
        }
        function ToFailedMerchant() {
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

        function goBack() {
            var resp = "${RespCode}";
            if ("100124" == resp) {
                document.forms[0].action = "IPEM.do";
                document.forms[0].submit();
            } else if ("0000000" != resp) {
                document.forms[0].action = "CheckCardInfo.do";
                document.forms[0].submit();
            }
        }

        function LoadJs() {
            GeneratorMobileType();
            orderIdSafeDispose();
        }
    </script>
</head>

<body onload="LoadJs()">
<form name="form1" action="#" method="post">
    <pe:hidden fieldList="Plain,Signature,OrderId,MerchantName,TransAmt" skipNULL="true"/>
    <div class="logo">
        <input type="hidden" name="PayerAcctNbr" value="${PayerAcctNbr}"/>
        <input type="hidden" name="PayerPhoneNo" value="${PayerPhoneNo}"/>

        <div class=""> 丰收卡支付 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
                                                               onclick="goBack()"/></a>
        </div>
    </div>
    <div class="main">
        <%--处理页面异常--%>
        <input id="getSMS" class="hq" type="hidden"/>
        <c:choose>
            <c:when test="${RespCode=='0000000'}">
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
                        <td><c:out value='${PayerAcctNbr}'/></td>
                    </tr>
                    <tr>
                        <td class="right">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                        <td><c:out value='${PayerPhoneNo}'/></td>
                    </tr>
                </table>
                <div class="bottom_botton">
                    <input onClick="goBack();" value="返回" type="button"/>
                </div>

            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty RespMessage}">
                        <div class="cuo">
                            <img src="images/phone/cuo.png">
                            <span class="text_w">支付失败</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="cuo_long">
                            <img src="images/phone/cuo.png">
                            <span class="text_long_w"><c:out value='${RespMessage}'/></span>
                        </div>
                    </c:otherwise>
                </c:choose>
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
                </table>
                <div class="bottom_botton">
                    <input onClick="goBack();" value="返回" type="button"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</form>
</body>
</html>
