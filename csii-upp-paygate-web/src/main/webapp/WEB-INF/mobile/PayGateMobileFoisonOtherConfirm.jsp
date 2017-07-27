<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="utf-8" %>
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
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script src="js/style.js"></script>
    <script>
   		
        function doIt(clickObj) {
        	var payerAcctNbr=$("#PayerAcctNbr").val();
        	payerAcctNbr=payerAcctNbr.substring(0,payerAcctNbr.length-6); 
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
    				document.forms[0].action = "CheckFoisonAcct.do";
    				document.forms[0].submit();
    				return;
    			}
    			if('0' == $("input[name='InnerCardFlag']").val()){
    				document.forms[0].action = "OtherMobilPayPre.do";
    				document.forms[0].submit();
    				return;
    			}
    		
        	
           
        }

		

        function phoneNumSafeDispose() {
            var doc = document.getElementsByName("PhoneNo");
            var PayerPhoneNoValue = "${PayerPhoneNo}";
            PayerPhoneNoValue = PayerPhoneNoValue.substring(0, 3) + "****" + PayerPhoneNoValue.substring(7, 11);
            var i;
            for (i = 0; i < doc.length; i++) {
                var item = doc[i];
                item.value = PayerPhoneNoValue;
            }
        }

       
		function goBack() {
            document.forms[0].action = "IPEM.do";
            document.forms[0].submit();
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
        

        
        function LoadJs() {
        	 phoneNumSafeDispose();
            orderIdSafeDispose();
          
            }
      	
    </script>
</head>


<body onload="LoadJs();">
<form name="form1" action="#" method="post">
	
   <pe:hidden fieldList="Plain,Signature,ChannelId,OrderId,MerchantName,TransAmt,PayerCardTypCd,MerNbr,ChannelNbr" skipNULL="true"/>
		<input type="hidden" name="PayerPhoneNo" value="${PayerPhoneNo}"/>	
		<input  type="hidden" name="InnerCardFlag"/> 	
    <div class="logo">
        <div class=""> 丰收e支付 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
                                                               onclick="goBack()"/></a>
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
                <td class="order_id" style="padding-left: 0px;"><input type="text" name="OrderSafeId" readonly="readonly" style="width: 100%"/>
                </td>
            </tr>
    </table>
	</div>
    <div class="main">
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr>
                <td class="right left">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk"><input type="text" id="PayerPhoneNo" name="PhoneNo" readonly="readonly" 
                                       style="width: 100%"/></td>
            </tr>

            <tr>
                <td class="right left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk">
                    <select id="PayerAcctNbr" name="PayerAcctNbr"/>
                    <c:forEach items="${CardList}" var="cardInfo">
											<option value="${cardInfo.PayerAcctNbr}${cardInfo.PayerAcctDeptNbr}">${cardInfo.PayerAcctNo}</option>
										
										</c:forEach>
										
                </td>
            </tr>

           <tr class="right left" style="text-align: center;">
					<td colspan="2" class="srk" style="text-align: center; font-size: 10px;width: 100%;color:#5478D0;"><span
						>友情提示：您可以选择您签约的卡号</span></td>
		 </tr>
           
             
        </table>
    </div>
   
           
           <div class="bottom_botton">
        <input onclick="doIt(this);" class="peory" value="下一步" type="button"/>
        <%--        <input onclick="###'" class="peory2 peory" value="取消" type="submit" />--%>
    </div>
    <div class="bottom_botton"></div>
    <div class="bottom_botton"></div>
</form>
</body>
</html>
