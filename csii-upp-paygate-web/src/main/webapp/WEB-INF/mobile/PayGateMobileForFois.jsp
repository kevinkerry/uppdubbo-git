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
    <title>�㽭ũ���ֻ�֧��</title>
    <link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
<!--     <script language="javascript" type="text/javascript" src="script.do"></script> -->
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script src="js/slides.min.jquery.js"></script>
	<script src="js/mobile.js"></script>
    <script>

        function submitForFoison(clickObj) {
            document.getElementById("PayerPhoneNo").value = document.forms[0].PayerPhoneNo_tel.value;
            if (false == checkInputForFoison()) {
                return false;
            }
            document.forms[0].action = "CheckFoisonAcct.do";
            document.forms[0].submit();
            /*post2SRV('CheckFoisonAcct.do', clickObj.form, clickObj, 'EEE', null);*/
        }

         function checkInputForFoison() {
            if (!checkLength("PayerPhoneNo", 11)) {
                layer.msg("�ֻ���λ������");
                return false;
            }
            var doc = document.getElementById("PayerPhoneNo");
            if (doc.value.substring(0, 1) != "1") {
                layer.msg("�ֻ��Ÿ�ʽ������");
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
     
        
        
        function checkisnum3(){
        	var phone =document.forms[0].PayerPhoneNo_tel.value;
        	var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;
  			 
        	if(reg.test(phone)){
  				 
  			 }else{
  				layer.msg("�ֻ��Ÿ�ʽ������");
  			 }
  			 
         }
        function transChannelIdCheck() {
            var channelId = "${ChannelId}";

           /*  if (null == channelId || "" == channelId) {
                layer.msg("ChannelId ERROR�������½���");
                return;
            } */

            /*            if ("05" == channelId) {
             $(".midDle").css({"display": "none"});
             $(".main").css({"display": "none"});
             $(".main-1").css({"display": "block"});
             document.getElementById("top").innerHTML="���տ�֧��";
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
	        $(".main").css({"display": "block"});
	        $(".main-1").css({"display": "none"}); */
	        var payphone = document.forms[0].PayerPhoneNo_tel.value;
			/* var payerac=document.forms[1].PayerAcctNbr2_tel.value;
            var payphone2= document.forms[1].PayerPhoneNo2_tel.value; */
			if(payphone==""){
				
				$("#PayerPhoneNo_tel").removeAttr("readonly");
				document.forms[0].PayerPhoneNo_tel.value="�����������ֻ���";
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
    <div id="top" class="one"> ����e֧�� <a href="###" class="leftBt"><img src="images/phone/left.png" width="16"
                                                                        height="23" onclick="gobackhistory()"/></a>
    </div>
</div>
<div class="order">
	<table width="100%" border="0" cellpadding="1" cellspacing="0" style="background-color: rgba(158, 202, 241, 0.12);">
            <tr>
                <td class="right" style="width: 28%;text-align: left;">��&nbsp;&nbsp;&nbsp;����</td>
                <td style="padding-left: 0px;text-align: left;"><c:out value='${MerchantName}'/></td>
            </tr>
            <tr>
                <td class="right" style="width: 28%;text-align: left;">��&nbsp;&nbsp;&nbsp;�</td>
                <td class="red" style="padding-left: 0px;text-align: left;">��<fmt:formatNumber type="number" value="${TransAmt}" pattern="#,###,##0.00"/></td>
            </tr>
            <tr>
                <td class="right" style="width: 28%;text-align: left;">�����ţ�</td>
                <td class="order_id" style="padding-left: 0px;"><input type="text" name="OrderSafeId" readonly="readonly" style="width: 100%"/>
                </td>
            </tr>
    </table>
</div>
<div class="main">
    <form id="from1" name="form1" action="CheckFoisonOtherAcct.do" method="post">
        <pe:hidden fieldList="Plain,Signature,ChannelNbr,OrderId,PayerCardTypCd,MerchantName" skipNULL="true"/>
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr>
                <td class="right left">�ֻ��ţ�</td>
                <td class="srk"><input id="PayerPhoneNo" name="PayerPhoneNo" type="hidden" value=""/>
                    
                            <input name="PayerPhoneNo_tel" autocomplete="off" type="tel"  value="${PayerPhoneNo}" style="width: 80%"
                                   id="PayerPhoneNo_tel"
                                   readonly="readonly"
                                   onkeypress="return isNum(event)" maxlength="11"
                                   onfocus="if(value=='�����������ֻ���') {value=''}"
                                   onblur="checkisnum3()'}"/>
                        
                </td>
            </tr>

        </table>

        <!-- <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr>
                <td class="right">��ܰ��ʾ��</td>
                <td >����δ��ͨ����e֧���������ֻ��ź����</td>
            </tr>
        </table> -->
        
      <!-- <div class="fuc_main">
            <div class="fuc_main_b"> ϵͳ��ʾ </div>
            <p>������Ѿ���ͨ����e֧���������������ѿ�ͨ����e֧�����ֻ��Ž���֧��</p>
            <p>�������δ��ͨ����e֧������������������Ԥ���ֻ��ţ��������һ����ֱ�ӽ���ע�Ტ֧��</p>
        </div> -->
    </form>
</div>
<div class="bottom_botton">
            <input onclick="submitForFoison(this)" value="��һ��" type="button"/>
</div>
<div  style="text-align: left;">
       <span style="padding-left: 20px;color: rgba(66, 96, 197, 0.9);font-size: 14px;" >��ܰ��ʾ��</span> 	
       <p style="padding-left: 20px;color: #333;font-size: 12px;">1.&nbsp;����e֧��֧�ַ��տ���������������</p>
       <p style="padding-left: 20px;color: #333;font-size: 12px;">2.&nbsp;�ֻ�����Ϊ������Ԥ���ֻ��š�</p>
            
</div>
</body>
</html>
