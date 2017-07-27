<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>科蓝一码付</title>
	<link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
    <link href="css/mobilecheck.css" rel="stylesheet" type="text/css"/>
    <!--     <script language="javascript" type="text/javascript" src="script.do"></script> -->
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" type="text/javascript" src="js/weChatPay.js"></script>
	<script type="text/javascript" type="text/javascript"  src="js/tyjs.js"></script>
    <script src="js/style.js"></script>
	<script src="js/mobile.js"></script>
	<script src="js/index.js"></script>
	<script>
	function doIt(clickObj,amt,code,mername) {
    	 $.ajax({
				type: "POST",
				async: false,
				url: "/paygate2/createOrder",
				data: "TransName=QRCO&TransAmt="+amt+"&code="+code+"&MerchantName="+mername,
				success: function(msg){
					
					weChatPay.getWX('123','1',msg["prepay_id"],msg["noncestr"],msg["timestamp"],msg["signature"],msg["paySign"]);
				}
			}); 
    	}
	</script>
</head>
<body>
	<div class="order">
		<table width="100%" border="0" cellpadding="1" cellspacing="0" style="background-color: rgba(158, 202, 241, 0.12);">
            <tr>
                <td class="right" style="width: 28%;text-align: left;">商&nbsp;&nbsp;&nbsp;户：</td>
                <td style="padding-left: 0px;text-align: left;"><c:out value='test'/></td>
            </tr>
            <tr>
                <td class="right" style="width: 28%;text-align: left;">金&nbsp;&nbsp;&nbsp;额：</td>
                <td class="red" style="padding-left: 0px;text-align: left;">￥<fmt:formatNumber type="number" value="${TransAmt}" pattern="#,###,##0.00"/></td>
            </tr>
    	</table>
	</div>
	<div class="bottom_botton">
        <input onclick="doIt(this,${TransAmt},'${code}','${MerchantName}');" class="peory" value="确认支付" type="button"/>
        <%--        <input onclick="###'" class="peory2 peory" value="取消" type="submit" />--%>
    </div>
    <div class="bottom_botton"></div>
    <div class="bottom_botton"></div>
</body>
</html>