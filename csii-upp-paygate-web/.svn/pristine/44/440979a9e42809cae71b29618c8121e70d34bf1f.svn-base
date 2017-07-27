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
    <link href="css/mobilecheck.css" rel="stylesheet" type="text/css"/>
    <!--     <script language="javascript" type="text/javascript" src="script.do"></script> -->
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script src="js/slides.min.jquery.js"></script>
	<script src="js/mobile.js"></script>
	<script src="js/index.js"></script>
	<script>

	function submit(){
		if($("#fosiradio1").attr("checked")){
			submitForFoison();
		}else if($("#fosiradio2").attr("checked")){
			submitForCard();
		}
		/* else if($("#fosiradio3").attr("checked")){
			submitForUnio();
		} */
		else{
			layer.msg("请选择支付方式");
			return;
		}
	}
        function submitForFoison() {
            document.getElementById("PayerPhoneNo").value = document.forms[0].PayerPhoneNo_tel.value;
            if (false == checkInputForFoison()) {
                return false;
            }
            document.forms[0].action = "CheckFoisonOtherAcct.do";
            document.forms[0].submit();
            /*post2SRV('CheckFoisonAcct.do', clickObj.form, clickObj, 'EEE', null);*/
        }

        function submitForCard() {
            document.getElementById("PayerAcctNbr2").value = document.forms[1].PayerAcctNbr2_tel.value;
            document.getElementById("PayerPhoneNo2").value = document.forms[1].PayerPhoneNo2_tel.value;
            if (false == checkInput()) {
                return false;
            }
            if(false==checkisnum()){
            	return false;
            }
            document.forms[1].action = "CheckCardInfo.do";
            document.forms[1].submit();
            /*post2SRV('CheckCardInfo.do', clickObj.form, clickObj, 'EEE', null);*/
        }
        /* function submitForUnio() {
            document.getElementById("payPhoneNbr").value = document.forms[2].PayerPhoneNo.value;
            if (false == checkInputForUnio()) {
                return false;
            }
            
            document.forms[2].submit();
            
        } 
    */

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

        function checkInputForFoison() {
            if (!checkLength("PayerPhoneNo", 11)) {
                layer.msg("手机号位数不足");
                return false;
            }
            var doc = document.getElementById("PayerPhoneNo");
            if (doc.value.substring(0, 1) != "1") {
                layer.msg("手机号格式有问题");
                return false;
            }
            return true;
        }
       /*  function checkInputForUnio() {
            if (!checkLength("payPhoneNbr", 11)) {
                layer.msg("手机号位数不足");
                return false;
            }
            var doc = document.getElementById("payPhoneNbr");
            if (doc.value.substring(0, 1) != "1") {
                layer.msg("手机号格式有问题");
                return false;
            }
            return true;
        } */


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
       	 var acctno =document.forms[1].PayerAcctNbr2_tel.value;
       	 var acctnoData19 = /^(6)[0-9]{18}$/;
			 var acctnoData16 = /^(6)[0-9]{15}$/;
			 if(acctnoData16.test(acctno)||acctnoData19.test(acctno)){
				 return true;
			 }else{
				 return false;
			 }
			 
       }
        function checkisnum1(){
          	var acctno =document.forms[1].PayerAcctNbr2_tel.value;
        	var acctnoData19 = /^(6)[0-9]{18}$/;
   			var acctnoData16 = /^(6)[0-9]{15}$/;
   			
   			if(document.forms[1].PayerAcctNbr2_tel.value!=''){
   				if(acctnoData16.test(acctno)||acctnoData19.test(acctno)){
   					
   				 }else{
   				 
   					layer.msg("输入卡号格式有问题");
   			 	}
   			 	
   			}else{
   				if (document.forms[1].PayerAcctNbr2_tel.value=='') {
   	        		document.forms[1].PayerAcctNbr2_tel.value='请输入您的支付卡号'
   	        		}
   	        		if (document.forms[1].PayerPhoneNo2_tel.value.value=='') {
   	        			document.forms[1].PayerPhoneNo2_tel.value='请输入您的手机号'
   	            		}
   			}
          }
        function checkisnum2(){
        	var phone =document.forms[1].PayerPhoneNo2_tel.value;
        	var reg = /^(12|13|14|15|17|18)[0-9]{9}$/;
  			 if(reg.test(phone)){
  				 
  			 }else{
  				layer.msg("手机号格式有问题");
  			 }
  			 
         }
        function checkisnum3(){
        	var phone =document.forms[0].PayerPhoneNo_tel.value;
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
			var AppointedPayType="${AppointedPayType}";
			var payphone = document.forms[0].PayerPhoneNo_tel.value;
			var payerac=document.forms[1].PayerAcctNbr2_tel.value;
            var payphone2= document.forms[1].PayerPhoneNo2_tel.value;
			if(payphone==""||(AppointedPayType!=1&&AppointedPayType!=2)){
				
				if(payphone==""){
					document.forms[0].PayerPhoneNo_tel.value="请输入您的手机号";
				}
			}
			if(payerac==""||(AppointedPayType!=1&&AppointedPayType!=2)){
				
				if(payerac==""){
					document.forms[1].PayerAcctNbr2_tel.value="请输入您的支付卡号";
				}
			}
			if(payphone2==""||(AppointedPayType!=1&&AppointedPayType!=2)){
				
				if(payphone2==""){
					document.forms[1].PayerPhoneNo2_tel.value="请输入您的手机号";
				}
			}
			
			
		}
		
        function LoadJs() {
        	
        	ifnull();
        	transChannelIdCheck();
            orderIdSafeDispose();
        }
    </script>
	<script>
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
			/* if (dic.payTypeCd.indexOf("3") > -1) {
				$(".midDle-right1").css({
					"display" : "table-row"
				});
			}  */
		if (dic.payTypeCd.indexOf("1") == -1&&dic.payTypeCd.indexOf("3")== -1) {
					if (dic.payTypeCd.indexOf("0") ==-1) {
						$(".midDle-right").css({
							"display" : "none"
						});
						$(".midDle-left").css({
							"display" : "none"
						});
						$(".main").css({
							"display": "none"}
						);
						layer.msg("支付方式没有开通");
						$("#buttonsub").attr("disabled",true);
					}else{
						
						$(".main").css({
							"display": "none"}
						);
						$(".midDle-left").css({
							"display" : "none"
						});
						changeChecked("fosiradio2");
						$(".main-1").css({"display": "table-row"});
			            $(".main-4").css({"display": "table-row"});
					}
				
				}
			else{
				if(dic.payTypeCd.indexOf("0") ==-1){
					$(".midDle-right").css({
						"display" : "none"
					});
				}
			} 
			 if(dic.payTypeCd.indexOf("0") >-1&&dic.payTypeCd.indexOf("1") >-1){
				 displayPayType("${OpenPayType}");
	             setDefaultPayType("${DefaultPayType}", "${OpenPayType}");  
			 }
				 
			 });
	
	</script>
	
</head>

<body onload="LoadJs()">

<div class="logo">
    <div id="top" class="one"> 浙江农信网络支付平台 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16"
                                                                        height="23"  onclick="gobackhistory()"/></a>
    </div>
</div>
<!-- <div class="midDle" id="midDleId">
   	<a href="#" class="midDle-left">丰收e支付</a>
    <a href="#" class="midDle-right">丰收卡支付</a>
    <a href="#" class="midDle-right1" style="display: none;" >跨行智能支付</a>
</div> -->
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


 <div class="paytpflag">
 	<table width="100%" border="0" cellpadding="1" cellspacing="0" >
 		<tr>
 			<td style="width:40%;text-align: left;padding-left: 2px;font-size: 18px;padding-top: 3px;padding-bottom:3px; ">选择支付方式</td>
 			<td>&nbsp;</td>
 		</tr>
 	</table>
	
</div> 
<div class="paytp"style="margin-top: 3px;">
            
            <table width="100%" border="0" cellpadding="1" cellspacing="0" >
			<tr class="midDle-left" style="background-color:rgba(158, 202, 241, 0.12);" >
                <td class="right" style="padding-left: 2px;width: 0.9%;text-align: left;"></td>
                <td style="text-align: left;padding-left: 5px;font-size: 16px;"><span style="width: 100px;">丰收e支付</span>
                <span class="span-radio"><input type="checkbox"  id="fosiradio1" checked="checked" class="mgc mgc-primary mgc-circle"/></span>
                </td>
                
            </tr>
            <form id="from1" name="form1" action="CheckFoisonOtherAcct.do" method="post">
            <input type="hidden" value="${ElecPortNotify }" id="ElecPortNotify" name="ElecPortNotify"/>
            <pe:hidden fieldList="Plain,Signature,ChannelNbr,OrderId,PayerCardTypCd,MerchantName,TransAmt,PayTypeCdStr" skipNULL="true"/>
        	<tr class="main" style="font-size: 15px;">
                <td class="right" style="width: 0.9%;"></td>
                <td class="srk" style="text-align: left;padding-left: 5px;"><input id="PayerPhoneNo" name="PayerPhoneNo" type="hidden" value=""/>
                     		 
                     		 <span >手机号：</span>
                             <input name="PayerPhoneNo_tel" autocomplete="off" type="tel"  value="${MobileNo}" style="width: 50%;"
                                   id="PayerPhoneNo_tel"
                                  
                          		   onkeypress="return isNum(event)" maxlength="11"
                                   onfocus="if(value=='请输入您的手机号') {value=''}"
                                   onblur="checkisnum3()"/>
                       
                </td>
            </tr>
        	</form>
        
            <tr class="midDle-right">
                <td class="right" style="padding-left: 2px;width: 0.9%;text-align: left;"></td>
                <td style="text-align: left;padding-left: 5px;font-size: 16px;"><span>丰收卡支付</span>
                <span class="span-radio"><input type="checkbox" id="fosiradio2" class="mgc mgc-primary mgc-circle"/></div></span>
                </td>
               
            </tr>
            <form id="form2" name="form2" action="CheckCardInfo.do" method="post">
       		 <pe:hidden fieldList="Plain,Signature,ChannelId,OrderId,MerchantName,TransAmt,PayerCardTypCd,MerNbr,ChannelNbr" skipNULL="true"/>
       
            
            <tr class="main-1" style="font-size: 15px;">
                <td class="right" style="width: 0.9%"></td>
                <td class="srk" style="text-align: left;padding-left: 5px;"><input id="PayerAcctNbr2" name="PayerAcctNbr" type="hidden" value=""/>
                    <span>卡&nbsp;&nbsp;&nbsp;号：</span>
                    <input name="PayerAcctNbr2_tel" autocomplete="off" type="tel" value="请输入您的支付卡号" style="width: 72%"
                           id="PayerAcctNbr2_tel"
                           
                           onkeypress="return isNum(event)" maxlength="19"
                           onfocus="if(value=='请输入您的支付卡号') {value=''}"
                           onblur="checkisnum1()"/>
                           
                        
               </td>
                     
            </tr>
            <tr class="main-4" style="font-size: 15px;"> 
                <td class="right" style="width: 0.9%"></td>
                <td class="srk" style="text-align: left;padding-left: 5px;"><input id="PayerPhoneNo2" name="PayerPhoneNo" type="hidden" value=""/>
                   <span>手机号：</span>
                   <input name="PayerPhoneNo2_tel" autocomplete="off" type="tel"  value="${MobileNo}"  style="width: 50%"
                                   id="PayerPhoneNo2_tel"
                                   onkeypress="return isNum(event)" maxlength="11"
                                  
                                   onfocus="if(value=='请输入您的手机号') {value=''}"
                                   onblur="checkisnum2()"/>
                  
                    
                </td>
            </tr>
        
       
   		 </form>
            
             <%-- <tr class="midDle-right1" style="display: none;">
                <td class="right" style="padding-left: 2px;width: 0.9%;text-align: left;"></td>
                <td style="text-align: left;padding-left: 5px;font-size: 16px;"><span>跨行智能支付</span>
                <span class="span-radio"><div class="radiochecded unchecked" id="select3"><input type="radio" name="paytpcd" id="fosiradio3" /></div></span>
                </td>
               
            </tr>
           
    	<form id="form3" name="form3" action="otherQuickPayPre.do" method="post">
        <pe:hidden fieldList="Plain,Signature,ChannelId,OrderId,MerchantName,TransAmt,PayerCardTypCd,MerNbr,ChannelNbr" skipNULL="true"/>
        
            
            <tr class="main-2" style="font-size: 15px;"> 
                <td class="right" style="width: 0.9%"></td>
                <td class="srk" style="text-align: left;padding-left: 5px;">
                   <span>手机号：</span>
                   <input name="PayerPhoneNo" autocomplete="off" type="tel"  style="width: 50%"
                                   id="payPhoneNbr" maxlength="11"
                                   value="请输入您的手机号"
                                   onfocus="if(value=='请输入您的手机号') {value=''}"
                                   />
                </td>
            </tr> 
      
       
    	</form> --%>


        </table>
</div>


<div class="bottom_botton" style="padding: 10px 0;">
        	<!-- <div style="margin-bottom: 10px;padding-bottom: 10px;height: 38px;">
        		<span style="width: 100%;font-size: 12px;color: rgba(66, 96, 197, 0.9);">温馨提示：如您未开通丰收e支付，输入手机号后继续</span>
        	</div> -->
            <input  value="下一步" type="button" onclick="submit();" id="buttonsub"/>
</div>
<div  style="text-align: left;">
       <span style="padding-left: 20px;color: rgba(66, 96, 197, 0.9);font-size: 14px;" >温馨提示：</span> 	
       <p style="padding-left: 20px;color: #333;font-size: 12px;">1.&nbsp;丰收e支付支持丰收卡及它行银联卡。</p>
       <p style="padding-left: 20px;color: #333;font-size: 12px;">2.&nbsp;手机号码为发卡行预留手机号。</p>
            
</div>
<!--浮层开始-->
<script type="text/javascript">
	/* $(function(){
		$("#payPhoneNbr").bind("blur",function(){
			checkPhone($(this));
		});
		function checkPhone(that){
			var phoneNbr = that.val();
			if(/^1[3|4|5|6|7|8|9]\d{9}$/.test(phoneNbr)){
				$("#btn_quickpay").attr("disabled", false);
			}else {
				layer.msg("手机号输入格式不正确");
			};
		};
		
		$("#btn_quickpay").bind("click", function(){
			if(!(/^1[3|4|5|6|7|8|9]\d{9}$/.test($("#payPhoneNbr").val()))){
				layer.msg("手机号输入格式不正确");
				return;
			}
			$("#form3").submit();
		});
	}); */

	$(".midDle-left").click(function () {
		   $(".main").css({"display": "table-row"});
		   $(".main-1").css({"display": "none"});
		   $(".main-2").css({"display": "none"});
		   $(".main-4").css({"display": "none"});
		   //changeChecked("fosiradio1");
		   $("#fosiradio1").attr("checked",  true);
		   $("#fosiradio2").attr("checked",  false);
		   $("#fosiradio1").prop("checked",  true);
		   $("#fosiradio2").prop("checked", false);
		   $(".midDle-left").css({"background-color": "rgba(158, 202, 241, 0.12)"});
		   $(".midDle-right").css({"background-color": ""});
		   $(".midDle-right1").css({"background-color": ""});
		 /*$("#select1").attr("class", "radiochecded");
		   $("#select2").attr("class","radiochecded unchecked");
		   $("#select3").attr("class", "radiochecded unchecked"); */
	   }
	);
	$(".midDle-right").click(function () {
		    $(".main").css({"display": "none"});
		    $(".main-1").css({"display": "table-row"});
		    $(".main-2").css({"display": "none"});
		    $(".main-4").css({"display": "table-row"});
		    //changeChecked("fosiradio2");
		    $("#fosiradio2").attr("checked",  true);
		   $("#fosiradio1").attr("checked",  false);
		   $("#fosiradio2").prop("checked",  true);
		   $("#fosiradio1").prop("checked", false);
		    $(".midDle-right").css({"background-color": "rgba(158, 202, 241, 0.12)"});
		    $(".midDle-left").css({"background-color": ""});
		    $(".midDle-right1").css({"background-color": ""});
		 
	    }
	);
	
	
	/* $(".midDle-right1").click(function () {
	        $(".main").css({"display": "none"});
	        $(".main-1").css({"display": "none"});
	        $(".main-2").css({"display": "table-row"});
	        $(".main-4").css({"display": "none"});
	        changeChecked("fosiradio3");
	        $(".midDle-right1").css({"background-color": "rgba(158, 202, 241, 0.12)"});
	        $(".midDle-left").css({"background-color": ""});
	        $(".midDle-right").css({"background-color": ""});
	        $("#select1").attr("class", "radiochecded unchecked");
			$("#select2").attr("class","radiochecded unchecked");
			$("#select3").attr("class", "radiochecded ");
	    }  
	);    */
</script>

<!--浮层结束-->
</form>
</body>
</html>
