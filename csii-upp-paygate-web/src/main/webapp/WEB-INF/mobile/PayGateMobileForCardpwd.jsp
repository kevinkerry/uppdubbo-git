<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>浙江农信手机支付</title>
    <link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
<!--     <script language="javascript" type="text/javascript" src="script.do"></script> -->
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script src="js/slides.min.jquery.js"></script>
	<script src="js/mobile.js"></script>
    <script>

        function submitForCard(clickObj) {
            document.getElementById("PayerAcctNbr2").value = document.forms[0].PayerAcctNbr2_tel.value;
            document.getElementById("PayerPhoneNo2").value = document.forms[0].PayerPhoneNo2_tel.value;
            if (false == checkInput()) {
                return false;
            }
            if(false==checkisnum()){
            	return false;
            }
            document.forms[0].action = "CheckCardInfo.do";
            document.forms[0].submit();
            /*post2SRV('CheckCardInfo.do', clickObj.form, clickObj, 'EEE', null);*/
        }

        function checkInput() {
            if (!(checkLength("PayerAcctNbr2", 14) || checkLength("PayerAcctNbr2", 15) || checkLength("PayerAcctNbr2", 16) || checkLength("PayerAcctNbr2", 19))) {
                layer.msg("卡号位数不对，14，15，16，19为有效输入");
                return false;
            }
            if (!checkLength("PayerPhoneNo2", 11)) {
                layer.msg("手机号位数不足");
                return false;
            }
            var doc = document.getElementById("PayerPhoneNo2");
            if (doc.value.substring(0, 1) != "1") {
                layer.msg("手机号格式有问题");
                return false;
            }
            return true;
        }

         function checkLength(nodeName, length) {
            var node = document.getElementById(nodeName).value;
            if (length != node.length) {
                return false;
            }
            return true;
        }

        function isNum(e) {
            var k = window.event ? e.keyCode : e.which;
            if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {

            } else {
                if (window.event) {
                    window.event.returnValue = false;
                }
                else {
                    e.preventDefault();
                }
            }
        }
        function checkisnum(){
       	 var acctno =document.forms[0].PayerAcctNbr2_tel.value;
       	 var acctnoData19 = /^(6)[0-9]{18}$/;
			 var acctnoData16 = /^(6)[0-9]{15}$/;
			 if(acctnoData16.test(acctno)||acctnoData19.test(acctno)){
				 return true;
			 }else{
				 return false;
			 }
			 
       }
        function checkisnum1(){
          	var acctno =document.forms[0].PayerAcctNbr2_tel.value;
        	var acctnoData19 = /^(6)[0-9]{18}$/;
   			var acctnoData16 = /^(6)[0-9]{15}$/;
   			
   			if(document.forms[0].PayerAcctNbr2_tel.value!=''){
   				if(acctnoData16.test(acctno)||acctnoData19.test(acctno)){
   					
   				 }else{
   				 
   					layer.msg("输入卡号格式有问题");
   			 	}
   			 	
   			}else{
   				if (document.forms[0].PayerAcctNbr2_tel.value=='') {
   	        		document.forms[0].PayerAcctNbr2_tel.value='请输入您的支付卡号'
   	        		}
   	        		if (document.forms[0].PayerPhoneNo2_tel.value.value=='') {
   	        			document.forms[0].PayerPhoneNo2_tel.value='请输入您的手机号'
   	            		}
   			}
          }
        function checkisnum2(){
        	var phone =document.forms[0].PayerAcctNbr2_tel.value;
        	var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;
  			 
        	if(reg.test(phone)){
  				 
  			 }else{
  				layer.msg("手机号格式有问题");
  			 }
  			 
         }
        function transChannelIdCheck() {
            var channelId = "${ChannelId}";

           /*  if (null == channelId || "" == channelId) {
                layer.msg("ChannelId ERROR，请重新进入");
                return;
            } */

            /*            if ("05" == channelId) {
             $(".midDle").css({"display": "none"});
             $(".main").css({"display": "none"});
             $(".main-1").css({"display": "block"});
             document.getElementById("top").innerHTML="丰收卡支付";
             }*/

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
		function ifnull(){
			/* $(".midDle").css({"display": "none"});
            $(".main").css({"display": "none"}); */
            $(".main-1").css({"display": "inline-block"});
            var payerac=document.forms[0].PayerAcctNbr2_tel.value;
            var payphone2= document.forms[0].PayerPhoneNo2_tel.value;
			if(payerac==""){
				$("#PayerAcctNbr2_tel").removeAttr("readonly");
				document.forms[0].PayerAcctNbr2_tel.value="请输入您的支付卡号";
			}
			if(payphone2==""){
				$("#PayerPhoneNo2_tel").removeAttr("readonly");
				document.forms[0].PayerPhoneNo2_tel.value="请输入您的手机号";
			}
			
			
		}
		
        function LoadJs() {
        	
        	ifnull();
        	transChannelIdCheck();
            orderIdSafeDispose();
        }
    </script>

</head>

<body onload="LoadJs()">

<div class="logo">
    <div id="top" class="one"> 丰收卡支付<a href="###" class="leftBt"><img src="images/phone/left.png" width="16"
                                                                        height="23" onclick="gobackhistory()"/></a>
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
<div class="main-1">
    <form id="form1" name="form1" action="CheckCardInfo.do" method="post">
        <pe:hidden fieldList="Plain,Signature,ChannelId,OrderId,MerchantName,TransAmt,PayerCardTypCd,MerNbr,ChannelNbr" skipNULL="true"/>
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr>
                <td class="right left">卡&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk"><input id="PayerAcctNbr2" name="PayerAcctNbr" type="hidden" value=""/>
                    <input name="PayerAcctNbr2_tel" autocomplete="off" type="tel" value="${PayerAcctNbr}" style="width: 100%"
                           id="PayerAcctNbr2_tel"
                           readonly="readonly"
                           onkeypress="return isNum(event)" maxlength="19"
                           onfocus="if(value=='请输入您的支付卡号') {value=''}"
                           onblur="checkisnum1()"/></td>
            </tr>
            <tr> 
                <td class="right left">手机号：</td>
                <td class="srk"><input id="PayerPhoneNo2" name="PayerPhoneNo" type="hidden" value=""/>
                    
                   <input name="PayerPhoneNo2_tel" autocomplete="off" type="tel"  value="${PayerPhoneNo}"  style="width: 80%"
                                   id="PayerPhoneNo2_tel"
                                   readonly="readonly"
                                   onkeypress="return isNum(event)" maxlength="11"
                                   onfocus="if(value=='请输入您的手机号') {value=''}"
                                   onblur="checkisnum2()'}"/>
                       
                </td>
            </tr>
        </table>
        
    </form>

</div>
		<div class="bottom_botton">
            <input onclick="submitForCard(this)" value="下一步" type="button"/>
        </div>
</form>
</body>
</html>
