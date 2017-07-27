<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- 生产测试 -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>浙江农信付款卡名金额查询</title>
    <link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
     <script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script type="text/javascript">
    	
        
        function goBack() {
            var resp = "${RespCode}";
            
            if ("PAY0124" == resp||resp =="PAY0131"||resp =="PAY0037") {
                document.forms[0].action = "IPEM.do";
                document.forms[0].submit();
            } else if ("0000000" != resp) {
                document.forms[0].action = "CheckCardInfo.do";
                document.forms[0].submit();
            }
        }
        
	       
        function LoadJs() {
        	
            }
       
    </script>
</head>

<body onload="LoadJs()">
<form name="form1" action="#" method="post">
    <pe:hidden fieldList="Plain,Signature,OrderId,MerchantName,TransAmt,ChannelNbr" skipNULL="true"/>
    <div class="logo">
        <input type="hidden" name="PayerAcctNbr" value="${PayerAcctNbr}"/>
        <input type="hidden" name="PayerPhoneNo" value="${PayerPhoneNo}"/>
		<div class=""> 交易金额查询<a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
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
                    <span class="text_w">付款卡名正常</span>
                </div>
                <table width="100%" border="0" class="wanc">
                     <tr>
                        <td class="right">商&nbsp;&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                        <td><c:out value='310020151012160000'/></td>
                    </tr>
                    <tr>
                        <td class="right">付&nbsp;&nbsp;款&nbsp;&nbsp;卡&nbsp;&nbsp;名：</td>
                        <td><c:out value='${PayerAcctName}'/></td>
                    </tr>
                    <tr>
                        <td class="right">交&nbsp;&nbsp;易&nbsp;&nbsp;金&nbsp;&nbsp;额：</td>
                        <td><c:out value='${TransAmt}'/></td>
                    </tr>
            
                </table>
               
                <div class="bottom_botton">
                    <input onclick="javascript:history.go(-1);" value="返回" type="button"/>
                </div>

            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${empty RespMessage}">
                        <div class="cuo">
                            <img src="images/phone/cuo.png">
                            <span class="text_w">状态异常</span>
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
                        <td class="right">付&nbsp;&nbsp;款&nbsp;&nbsp;卡&nbsp;&nbsp;名：</td>
                        <td><c:out value='${PayerAcctName}'/></td>
                    </tr>
                    <tr>
                        <td class="right">交&nbsp;&nbsp;易&nbsp;&nbsp;金&nbsp;&nbsp;额：</td>
                        <td><c:out value='${TransAmt}'/></td>
                    </tr>
                </table>
                
                <div class="bottom_botton">
                    <input value="返回" type="button" onclick="javascript:window.location.href='MerNbrLogin.jsp';"  />
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</form>
</body>
</html>
