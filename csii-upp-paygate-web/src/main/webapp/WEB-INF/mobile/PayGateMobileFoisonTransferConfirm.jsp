<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="GBK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta name="renderer" content="webkit"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>浙江农信手机支付</title>
    	<style>	
	html{
	overflow-x:hidden;
	}
	</style>
    <link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/remodal.css" />
	<link rel="stylesheet" type="text/css" href="css/remodal-default-theme.css" />
    <style>
	#btn_ConfirmPay, #btn_OpenPay, #btn_back,  #btn_addNewCard,#tr_phone,#duanxin,#cardSelect,#xieyiShow,#cardInputShow {
		display: none;
	}
	</style>
  	<script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script language="javascript" type="text/javascript" src="js/mobile.js"></script>
    <script language="javascript" type="text/javascript" src="js/remodal.js"></script>	
    <script src="js/style.js"></script>
    <script>
	    var checkcode="no";
	    function formatNum(s,n){
			n=n>0&&n<20?n:2;
			s=parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
			var l=s.split(".")[0].split("").reverse();
			r=s.split(".")[1];
			t="";
			for(i = 0;i<l.length;i++){
				t+=l[i]+((i+1)%3==0&&(i+1)!=l.length?",":"");
			}
			return t.split("").reverse().join("")+"."+r;
		}
    	
        function doIt(clickObj) {
        	if((document.getElementById('state').value)=="0"){
          		if(checkLength("_vTokenName",0)){
          			layer.msg("附加码为空，请输入!");
          			return;
          		}
          		if(checkcode!="yes"){
            		return;
        		}
          	}
        	document.getElementById("SmsCode").value = document.forms[0].MessageCode_tel.value;
            
        	if (false == checkAllValue()) {
                return false;
            }  
        	if((document.getElementById("checkbox").checked)){
    			$('#interalFlag').val('1');
    		}else{
    			$('#benciamt').val('');
    			$('#kehuhao').val('');
    			$('#hangshe').val('');
    			$('#interalFlag').val('');
    		}
            var input=$("#PayerAcctNbrDept").children("option:selected").val().trim();
            document.getElementById("PayerAcctNbr").value=input;
            document.forms[0].action = "MobileTransferConfirmForFoison.do";
            document.forms[0].submit();
            /*post2SRV('MobileTransferConfirmForFoison.do', clickObj.form, clickObj, 'EEE', null);*/
        }
        function doItnext(clickObj){
        	if(false==checkCard()){
	    		return false;
	    	}else if(false==checkPhone()){
	    		return false;
	    	}else if(!checkCheckBox("sign")){
	    		return false;
	        }
        	if (checkcode != "yes") {
				return;
			}
        	var input=$("#Card_tel").val();
        	var payerPhoneNbr=document.getElementById("PayerPhoneNo").value;
        	document.getElementById("PayerAcctNbr").value=input;
        	if(false==checkCardFlag(input)){
        	    document.forms[0].action = "FS01MobileLogin.do";
	            document.forms[0].submit();
				return;
        	}else{
        		if($("#ElecPortNotify").val() == "K1"){
        			layer.msg("涉及跨境商品不支持他行支付");
					return;
				}
        		if($("input[name='PayTypeCdStr']").val().indexOf("3") == -1){
					layer.msg("商户不支持此银联卡");
					return;
				}
        		submitForm("otherQuickOpenAndPay.do", input, payerPhoneNbr, "2");
        	}
        	
        }
        
        function change(){
        	var payercard=$("#PayerAcctNbrDept").children("option:selected").val().trim();
        	if(22==payercard.length||25==payercard.length){
        		payercard=payercard.substring(0,payercard.length-6);
        	}
        	if(true==checkCardFlag(payercard)){
        		$("#PayerAcctNbr").val(payercard);
        		document.forms[0].action = "CheckFoisonOtherAcct.do";
				document.forms[0].submit();
			}
        }
		function checkCardFlag(payerAcctNbr){
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
				return false;
			}
			if('0' == $("input[name='InnerCardFlag']").val()){
				return true;
			}
		}
        function submitForm(strAction, payerAcctNbr, payerPhoneNbr, isQueryOpenStatus) {
			showLoading();
			$.ajax({
				type : "POST",
				async : false,
				url : "qAOSPaygate.do",
				data : "PayerPhoneNo=" + payerPhoneNbr + "&PayerAcctNbr="
						+ payerAcctNbr + "&IsQueryOpenStatus=" + isQueryOpenStatus,
				success : function(msg) {
					if ("0000000" == msg["RespCode"] && msg["PayerCardTypCd"]) {
						$("input[name='PayerCardTypCd']").val(
								msg["PayerCardTypCd"]);
						$("input[name='PayerAcctNbr']").val(payerAcctNbr);
					} else if("PAY0054" == msg["RespCode"]){
						hideLoading();
						layer.msg("该卡已注册！");
						$("#Card_tel").val('请输入银行卡号');
						$("#_vTokenName").val('');
						checkcode="no";
	                    document.getElementById("rightImage").src="images/hongch.jpg";
	                    document.getElementById("rightImage").style.display="inline";
					}else {
						hideLoading();
						layer.msg(msg["RespMessage"]);
					}
				}
			});
			var val = $("input[name='PayerCardTypCd']").val();
			if (val && "" != val && "null" != val) {
				$("#form_id").attr("action", strAction);
				if ("otherQuickOpenAndPay.do" == strAction) {
					hideLoading();
					$("#btn_OpenPay").attr("disabled", "disabled");
					$("#form_id").submit();
					}
			}else{
				hideLoading();
			}
		}
        function checkCard(){
        	var	cardLength=document.forms[0].Card_tel.value.length;
        	if(cardLength<14||cardLength==17||cardLength==18){
        		layer.msg("卡号位数不对")
        		return false;
        	}
        }
        //校验手机号
        function checkPhone(){
        		var phoneLength=document.forms[0].PayerPhoneNo.value.length;
        		if(phoneLength<11){
        			layer.msg("手机号位数不足");
        			return false;
        		}
        	}
		function initInput(){
			$("#btn_ConfirmPay").show(500);
			$("#btn_addNewCard").show(500);
			$("#cardSelect").show(400);
			$("#duanxin").show(400);
			$("#tr_phone").show(400);
		}
		function showInput(){
			$("#btn_ConfirmPay").hide();
			$("#btn_addNewCard").hide();
			$("#cardSelect").hide();
			$("#tableIMG").hide();
			$("#duanxin").hide();
			$("#pointshow").hide();
			$("#btn_OpenPay").show(500);
			$("#btn_back").show();
			$("#cardInputShow").show(400);
			$("#xieyiShow").show(400);
			
			reloadTokenImg();
	        $("#tableIMG").removeAttr("style");
		}
		function showSelect(){
			$("#btn_OpenPay").hide();
			$("#btn_back").hide();
			$("#cardInputShow").hide();
			$("#xieyiShow").hide();
			$("#btn_ConfirmPay").show(500);
			$("#btn_addNewCard").show();
			$("#cardSelect").show(500);
			$("#duanxin").show(500);
			showPoint();
			
			if($("#state").val()=="0"){
	        	 reloadTokenImg();
	        	 $("#tableIMG").removeAttr("style");
	        }else{
	        	$("#tableIMG").attr("style","display:none");
	        }
		}
        function checkAllValue() {
            return checkAllValueForFoison();
        }

        function checkAllValueForFoison() {
            
        	
            if (false == checkPhoneMessageconfirm()) {
                layer.msg("未获取短信验证码，请获取!");
                return false;
            }
            if (true == checkPhoneMessageNull()) {
                layer.msg("无输入，请输入验证码！");
                return false;
            }
            if (false == checkPhoneMessageCode()) {
                layer.msg("短信验证码输入错误，请重新输入");
                return false;
                }
            
            return true;
        }
        function checkPhoneMessageconfirm() {
            if (!(curCount > 0 && curCount < 120)) {

                return false;
            }
            return true;
        }
        function checkPhoneMessageCode() {
            return checkLength("SmsCode", 6);
        }
        function checkPhoneMessageNull() {
            return checkLength("SmsCode", 0);
        }
        function checkLength(nodeName, length) {
            var node = document.getElementById(nodeName).value;
            if (length != node.length) {
                return false;
            }
            return true;
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

        var InterValObj; //timer变量,控制时间
        var count = 120; //间隔函数,1秒执行
        var curCount; //当前剩余秒数
        function getSMSFunction() {
            curCount = count;
            $("#getSMS").attr("disabled", "disabled");
//            $("#SMSbutton").css("background-image","url(images/images/fanhui.png)");
            var PayerPhoneNo = "${PayerPhoneNo}";
            var PayerAcctNbr =$("#PayerAcctNbrDept").children("option:selected").val().trim();
            var deptId = PayerAcctNbr.substring(PayerAcctNbr.length-6);
    		var acctNo = PayerAcctNbr.substring(0,PayerAcctNbr.length-6);
    		var transAmtPoint=(document.getElementById("AmtThisTime")==null)||(!document.getElementById("checkbox").checked)
    		?0:document.getElementById("AmtThisTime").value;
    		var transAmt = formatNum(document.forms[0].TransAmt.value -transAmtPoint,2);
    		var oparams = new Array(
                    new Pair("PayerPhoneNo", PayerPhoneNo),
        		    new Pair("PayerAcctDeptNbr", deptId),
                    new Pair("PayerAcctNbr", acctNo),
                    new Pair("TransAmt", transAmt),
                    new Pair("OperateType", "0"),
                    new Pair("TransTypCd", "UPP10003")
                   
            );

            postData2SRVWithCallback("SMS.do", PEGetPostData(oparams), function (success, message) {
             
            	if (!success) {
            		document.getElementById("getSMS").disabled = false;
            		layer.msg("短信发送异常，请检查网络，或联系管理员");
                    //throwValidationAtEEE(message,"EEE");
                } else {
                	if ("" == message) {
                        layer.msg("短信服务异常，请联系管理员");
                        document.getElementById("getSMS").disabled = false;
                    }
                	var obj = eval('('+message+')');
                	if(obj.SmsSqenbr != null){
						$("#SmsSqenbr").val(obj.SmsSqenbr);
					}
                	if("0000000"==obj.RespCode){
                    	$("#checkbox").attr("disabled", "disabled");
						$("#detail").removeAttr("href");
                        document.getElementById("getSMS").value="(" + curCount + "秒" + ")";
                        InterValObj=window.setInterval(SetRemainTime,1000);
                    }else if("PAY0100"==obj.RespCode){
                        curCount = 30;
                        layer.msg("30秒内不可以发送多条短信，请30s后重试");
                        document.getElementById("getSMS").value="(" + curCount + "秒" + ")";
                        InterValObj=window.setInterval(SetRemainTime,1000);
                    }else{
                    	document.getElementById("getSMS").disabled = false;
                    	layer.msg("短信发送失败");
                    }
                }
            });
        }
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);//停止计时器
                $("#getSMS").removeAttr("disabled");
                document.getElementById("getSMS").value = "获取验证码";
            } else {
                curCount--;
                document.getElementById("getSMS").value = "(" + curCount + "秒" + ")";
                document.getElementById("SmsCodeBack").value = curCount;
            }
        }

        function getPayerAcctNbr(str) {
            return getMapValue(str, "PayerAcctNbr");
        }
        function getDeptId(str) {
            return getMapValue(str, "DeptId");
        }
        function getAcctNo(str) {
            var acctInfoAndDeptId = getMapValue(str, "AcctInfo");
            var end = acctInfoAndDeptId.indexOf("C");
            if (end < 0) {
                end = acctInfoAndDeptId.indexOf("D");
            }
            return acctInfoAndDeptId.substring(0, end);
        }
        function getPayAcctType(str) {
            return getMapValue(str, "PayAcctType");
        }
        function getPayAcctInfo(str) {
            return getMapValue(str, "AcctInfo");
        }
        function getMapValue(str, nodeName) {
            var startIndex = str.indexOf(nodeName);
            var endIndex = str.indexOf(",", startIndex);
            if (endIndex < 0) {
                endIndex = str.indexOf("}", startIndex);
            }
            startIndex = startIndex + nodeName.length + 1; //位偏移，有个=，所以加1

            return str.substring(startIndex, endIndex);
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
        function checkTokenImgOnKeyUp() {
            var userInputToken = document.getElementById("_vTokenName").value;
            if(userInputToken.length == 0) {
                document.getElementById("rightImage").style.display="none";
            }else if(userInputToken.length == 4) {
                checkTokenImg();
            }
        }


        function checkTokenImg() {
            var userInputToken = document.getElementById("_vTokenName").value;
            if(userInputToken.length == 0) {
                document.getElementById("rightImage").style.display="none";
                return ;
            }
            var oparams = new Array(
                    new Pair("_vTokenName",userInputToken)
            );
            postData2SRVWithCallback("ImageTokenVerify.do", PEGetPostData(oparams), function(flag, answer) {
                if (flag && !answer) {
                	$("#SMSinfo").text("");
                	checkcode="yes";
                    document.getElementById("rightImage").src="images/lug.jpg";
                    document.getElementById("rightImage").style.display="inline";
                } else {
                	checkcode="no";
                    document.getElementById("rightImage").src="images/hongch.jpg";
                    document.getElementById("rightImage").style.display="inline";
                }
            });
        }
        $(function () {
            $('#open-box').click(function () {
                $('#fuc').removeClass('hide');
            });
            $('#close-box').click(function () {
                $('#fuc').addClass('hide');
            });
        });
        //积分抵扣详情弹出框
        $(function () {
            $('#open-PointBox').click(function () {
                $('#PointRule').removeClass('hide');
            });
            $('#close-PointBox').click(function () {
                $('#PointRule').addClass('hide');
            });
        });
        
 	
 	function isChecked(obj){
 		var transAmt = document.forms[0].TransAmt.value;
 		if($(obj).is(':checked')){
 			var deductionAmt = $('#isdedction:checked').parent().parent().find("input[id='AmtThisTime']").val();				
			var transAmtdeduction = transAmt-deductionAmt;
			document.getElementById("jane5").innerHTML=formatNum(transAmtdeduction,2);
 		}else{
 			document.getElementById("jane5").innerHTML=formatNum(transAmt,2);
 		}
 		
 	}
 	function showPoint(){
 		var dic = {
 					payTypeCd : "${PayTypeCdStr}"
 				};
 			
 				if (getCookie("payTypeCd")) {
 					if (!(dic.payTypeCd && (dic.payTypeCd != getCookie("payTypeCd")))) {
 						dic.payTypeCd = getCookie("payTypeCd");
 					}
 				}
 				 setCookie("payTypeCd", dic.payTypeCd);
 				if(dic.payTypeCd!=""&&dic.payTypeCd!=null&&dic.payTypeCd!=undefined){
 					if (dic.payTypeCd.indexOf("5") == -1) {
 						$("#pointshow").hide();
 					}else {
 						$("#pointshow").show();
 					}
 				} 
 				
 			}
        function LoadJs() {
        	 //phoneNumSafeDispose();
            orderIdSafeDispose();
            initInput();
            showPoint();
           if((document.getElementById('state').value)=="0"){
        	   reloadTokenImg();
        	 //document.getElementById('tableIMG').style.display="block";
        	 $("#tableIMG").removeAttr("style");
        	}
            
        }
      	$(function(){
    		var smsCodeBackv = document.getElementById("SmsCodeBack").value;
    		if(smsCodeBackv!=null&&parseInt(smsCodeBackv)<=120&&parseInt(smsCodeBackv)>0){
    			document.getElementById("getSMS").value = smsCodeBackv + "秒";
    			//$("#getSMS")
    			//.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
    			curCount = parseInt(smsCodeBackv);
    			document.getElementById("getSMS").value = curCount
    			+ "秒";
    			InterValObj = window.setInterval(SetRemainTime,
    				1000);
    		}
     		
     	});
    </script>
</head>


<body onload="LoadJs();">
<div class="box"></div>
<form name="form1" action="#" method="post" id="form_id">
	<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
   <pe:hidden fieldList="PayTypCd,CardList,PayerCardTypCd,PayerAcctDeptNbr,PayerAcctName,PlainContext,Plain,ChannelNbr,MerchantName,TransAmt,Signature,TransId,PayTypeCdStr"
					skipNULL="false" />
	<input type="hidden" name="state" id="state" value="${state}"/>	
	<input id="TransTypCd" name="TransTypCd" type="hidden" value="UPP10003"/>
	<input name="InnerCardFlag" type="hidden"/>
	<input  type="hidden" id="benciamt" name="AmtThisTime" value=""></input>
	<input  type="hidden" id="kehuhao" name="ClientNo" value=""></input>
	<input  type="hidden" id="hangshe" name="BranchNo" value=""></input>
	<input  type="hidden" id="interalFlag" name="interalFlag" value=""></input> 	
	<input type="hidden" value="${ElecPortNotify}" id="ElecPortNotify" name="ElecPortNotify"/>	
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
	<div class="pointDetial" id="pointshow" style="color: #666;display: none;">
       <ul style="left: 30px;">
                  				<li class="jf" style="padding-left: 20px;">
                  				<span>
                  				
                  				<input id="checkbox" name="checkbox" type="checkbox" class="mgc" style="width:16px; " onclick="isChecked(this)"/>
                  				</span>
                  				<span>
                  				&nbsp;&nbsp;您当前积分可抵扣金额为</span>￥ <input class="input1" type="text" name="jane" id="jane"  value="0"  readonly="true" style="width: 40px;"></input>&nbsp;<span style="display: none;" id="janemoney">0</span>
                  					
                  				</li>
                  				<li class="jf" >
                  				
                  				<image src="images/jxIco.png" align="absmiddle" ></image>
                  				还需付<span class="red" >￥</span><span id="jane5" class="red">${TransAmt}</span>
                  				<span style="padding-left: 0%">
                  					<a  href="#" id="open-PointBox">抵扣规则</a>
                  				</span>&nbsp;
                  				<span style="padding-left: 22%">	
                  					<a href="#modal" id="detail">抵扣详情</a> 
                  				</span>
                  				</li>
              				</ul>       				
   </div>
    <div class="main" id="zhifu">
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr id="cardSelect">
                <td class="right left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk">
                    <select id="PayerAcctNbrDept" name="PayerAcctNbrDept" onchange="change();">
                    <c:forEach items="${CardList}" var="cardInfo">
											<option value="${cardInfo.PayerAcctNbr}${cardInfo.PayerAcctDeptNbr}">${cardInfo.PayerAcctNo}</option>
										</c:forEach></select>
				</td>
            </tr>
            <tr id="cardInputShow">
                <td class="right left" >卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                  <td class="srk"><input id="PayerAcctNbr" name="PayerAcctNbr"
                   type="hidden" value=""/>
                    <input name="Card_tel" id="Card_tel" autocomplete="off" type="tel" value="请输入银行卡号" style="width: 100%"
                           onkeypress="return isNum(event)" maxlength="19"
                           onfocus="if(value=='请输入银行卡号') {value=''}"
                           onblur="if (value=='') {value='请输入银行卡号'}"/></td>
            </tr>
            <tr id="tr_phone">
                <td class="right left">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk"><input type="text" id="PayerPhoneNo" name="PayerPhoneNo" readonly="readonly" value="${PayerPhoneNo}"
                                       style="width: 100%"/></td>
            </tr>
			<tr id="duanxin">
                <td class="right left" nowrap>短信验证码：</td>
                <td class="srk">
                    <input id="SmsCode" name="SmsCode" type="hidden" value=""/>
                    <input name="MessageCode_tel" autocomplete="off" type="tel" value="" size="6" style="width: 45%"
                           onkeypress="return isNum(event)" maxlength="6"/>
                    <input id="getSMS" onclick="getSMSFunction()" class="hq" value="点击发送" type="button"/>
                     <input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                     	<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
                </td>
            </tr>
           <tr id="tableIMG" style="display: none;">
                           
                                <td class="right left" nowrap>附&nbsp;&nbsp;加&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
                                <td class="srk"><input autocomplete="off"  style="width:60px" id="_vTokenName" name="_vTokenName" onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" type="text" value="" size="6" maxlength="4" class="_vTokenName"/>
                                    <img id="rightImage" style="display: none; position:relative;width:20px;"/>
                                    <img id="_tokenImg" onclick="reloadTokenImg()" src=""  style="position:relative;width:50px;"/>
                                    <a style="font-size: 12px" onclick="reloadTokenImg()"></a>
                                    </td>
           </tr> 
           <tr id="xieyiShow">
				<td class="right left" style="padding-right:18px;"><input class="mgc" id="sign" name="sign" type="checkbox" value=""
                                         style="width: 16px;" /></td>
            	<td class="font14"><a href="#" id="open-box">我已阅读并接受《浙江农信丰收e支付服务协议》</a></td>
          </tr>   
        </table>
    </div>
    <div class="fuc hide" id="fuc" style="overflow: scroll">
        <div class="fuc_main"
             style="width: 100%; height: 80%; overflow: auto; border: 1px ; text-align: left">
            <div class="fuc_main_b"> 浙江省农村信用社（合作银行、商业银行）“丰收e支付”服务协议 <a href="###" id="close-box"><img
                    src="images/images/cha.png"/></a></div>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议是由浙江省农村信用社（合作银行、商业银行）（以下简称“我行”）与丰收卡（含丰收借记卡和丰收贷记卡）及他行银联卡持卡人（以下简称“您”）就“丰收e支付”服务（以下简称“本服务”）的使用等相关事项所订立的有效合约。“丰收e支付”是浙江省农村信用社（合作银行、商业银行）向丰收卡及他行银联卡持卡人提供的以“手机号+银行卡号+手机动态密码”作为支付的安全认证方式进行小额购物电子支付的一种支付方式。您通过互联网（Internet）点击确认或以其他方式选择接受本协议，即表示您同意接受本协议的全部约定内容，确认承担由此产生的一切责任。</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在接受本协议之前，请您仔细阅读本协议的全部内容（特别是以粗体下划线标注的内容）。如果您不同意本协议的任何内容，或者无法准确理解相关条款的解释，请不要进行后续操作。</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（1）您应确保您在使用本服务时所使用的银行卡为您本人所有，并确保您用其支付的行为合法、有效，未侵犯任何第三方合法权益；否则因此造成我行、持卡人损失的，您应负责赔偿并承担全部法律责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（2）我行仅为您提供安全可靠的网上支付服务，支付服务所关联的基础交易中的商品质量、送货服务等引起的争议和纠纷均由您和提供商品或服务的商户自行协商解决，我行对此不承担任何责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（3）您应妥善保管该卡开户户名、开户类型、与之相关的证件类型及证件号码、手机号码、固定电话、通信地址等与该卡有关的一切信息（以下简称“身份信息及卡信息”），不得将身份信息及卡信息转告他人，并在安全网络环境下使用，如您遗失银行卡、泄露身份信息及卡信息，您应及时通知发卡银行，以减少可能发生的损失。因您遗失手机、泄露银行卡号码、泄露手机验证码、泄露银行卡密码、数字证书、U盾、丢失银行卡等所致的风险及损失需由您自行承担。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（4）使用“丰收e支付”服务，丰收借记卡单笔网上消费交易额最高为人民币2000元，每日网上消费交易额最高为人民币2000元；丰收贷记卡单笔网上消费交易额最高为人民币2000元，每日网上消费交易额最高为人民币2000元；他行卡单笔交易最高额及每日网上消费交易最高额以发卡银行银联在线支付限额为准。使用丰收卡签约丰收e支付您可以根据支付需要和风险考虑，点击浙江农信官网（www.csii.com）上丰收e支付图标，进入丰收e支付业务维护页面，在我行统一设置的丰收e支付交易限额内调整您个人丰收卡的丰收e支付交易限额。我行可根据业务发展需要及风险控制需要设置或修改丰收卡的交易限额，无须对客户另行通知，请您留意我行有关公告及网页说明。如您对我行修改交易限额存在异议的，可申请终止使用本服务。如我行修改限额后，您仍然使用本功能的，视为您同意我行对限额所进行的修改和调整。在任何情况下，交易金额不应超过我行及他行发卡银行银联在线支付设置的交易限额。如实际支付金额大于支付限额，我行将拒绝执行交易指令，由此造成您的损失我行不承担责任。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（5）请确保并确认“丰收e支付”注册签约手机号码为您在发卡银行预留的本人手机号码并在本人实际控制之下。在支付过程中，我行将向您“丰收e支付”注册签约手机号码发送动态验证码追加验证，以确保您的交易安全。您应妥善保管移动电话所涉相关信息，并保持移动电话通讯和使用功能通畅。因您遗失移动电话、将移动电话转借他人使用、移动电话自身故障、或其他因您自身原因而导致其无法收取我行发送的动态验证码所产生的一切后果及损失，由您自行承担。如因网络通讯、黑名单等非我行原因造成您无法接收动态验证码的，我行不承担任何责任。如您的手机号码在移动运营商处注销，应及时至浙江农信官网（www.csii.com）点击丰收e支付图标，进入丰收e支付业务维护页面，冻结该手机号码注册签约的“丰收e支付”业务。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（6）您同意我行对您在使用本服务过程中所填写的包括但不限于姓名、银行卡卡号、手机号码、身份证号码等进行校对核验，同时我行保留随时变更上述确认要素作为校验标准的权利。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（7）您不得利用银行卡进行套现、虚假交易、洗钱等行为，且有义务配合我行进行相关调查，一旦您拒绝配合进行相关调查或我行认为您存在或涉嫌虚假交易、洗钱、套现或任何其他非法活动、欺诈或违反诚信原则的行为、或违反本协议约定的，我行有权采取以下一种、多种或全部措施：一、暂停或终止提供本协议项下“丰收e支付”服务；二、终止本协议；三、取消您的用卡资格。</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>（8）您同意我行有权变更、暂停本协议项下相关服务，有权修改、终止本协议，并于执行前通过我行网站进行公告。相关公告经网站发布视为您已收到。您在公告执行后继续办理相关业务的，视同接受有关本协议修改、变更的内容。本协议终止后，您在协议终止前发送我行进行处理的交易指令仍有效，您应承担其后果。</b>
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您使用丰收e支付业务，应遵守我行银行卡章程和我行在网站（www.csii.com）上公布的交易规则，如有冲突，以本协议为准。</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>

            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        </div>
    </div>
   
           
   <div class="bottom_botton">
        <input onclick="showInput();" id="btn_addNewCard" class="peory khzf" value="开通新卡" type="button"  />
        <input onclick="doIt(this);" id="btn_ConfirmPay" class="peory khzf" value="确认支付" type="button"/>
        <input onclick="showSelect();" id="btn_back" class="peory khzf" value="返回支付" type="button" />
		<input onclick="doItnext(this);" id="btn_OpenPay" class="peory khzf" value="开通并支付" type="button" />
        <%--        <input onclick="###'" class="peory2 peory" value="取消" type="submit" />--%>
    </div>
    <div class="bottom_botton"></div>
    <div class="bottom_botton"></div>
    <input id="integral" name="integral" type="hidden"
					value="${integral}"> </input> <input id="isFlag" name="isFlag"
					type="hidden" value="${isFlag}"> </input> <input id="deductibleAmt"
					name="deductibleAmt" type="hidden" value="${deductibleAmt} ">
				</input> <input id="realAmt" name="realAmt" type="hidden"
					value="${realAmt }"> </input>
</form>
	<div id="load_bg"></div>
	<div class="load_box">
		<div class='loader loader--glisteningWindow'></div>
		<!-- 		<div class='loader loader--audioWave'></div> -->
		<!-- 		<div class='loader loader--snake'></div> -->
		<!-- 		<div class='loader loader--spinningDisc'></div> -->
		<!-- 		<div class='loader loader--circularSquare'></div> -->
		<!-- 		<span style="font: 16px '微软雅黑';">处理中&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</span> -->
	</div>
	<div class="remodal" data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc" style="max-width: 100%;
	padding: 5px;border-top: 1px solid #5478D0;border-bottom: 1px solid #5478D0 ">
  <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
  <div>
    <h4 id="modal1Title">抵扣金额 <input class="input2" style="width: 50px;" type="text" name="deduction" id="deduction"  value="" readonly="true"></input>元</h2>
    <p id="modal1Desc" align="left">
    	您可以手动选择抵扣行社：
    	 <span style="float:right">(单位：元)</span>
    </p>
    <div>
    <table width="100%" border="1" cellpadding="2" cellspacing="0"	class="table-class" align="center" style="font-size:14px">
    	<tr class="trTitle" bgcolor="#c8c8ff">
		<td nowrap class="tdTitle" align="center">行社名称</td>
	    <td nowrap class="tdTitle" align="center" >可抵扣总额</td>
	    <td nowrap class="tdTitle" align="center" >本次抵扣金额</td>
		<td nowrap class="tdTitle" align="center">是否抵扣</td>
		</tr>
		<c:forEach var="item" items="${pointRecords}">
		<tr style="height: 30px;">
		<td class="tdValue" >${item.BranchName}</td>
		<td class="tdValue" width="50%" >${item.integralTotal}</td>
		<td class="tdValue" width="50%">${item.AmtThisTime}</td>
		<td class="tdValue" ><input type="radio" id="isdedction" name="isdedction" onclick="isdedction(this)"></input></td>	
		<input class="input5" type="hidden" id="AmtThisTime" name="AmtThisTime1" value="${item.AmtThisTime }"></input>
		<input class="input3" type="hidden" id="ClientNo" name="ClientNo1" value="${item.ClientNo}"></input>
		<input class="input4" type="hidden" id="BranchNo" name="BranchNo1" value="${item.BranchNo}"></input>
		</tr>
		</c:forEach>
    </table>
    </div>
    <c:choose>
	    <c:when test="${PointRespFlag=='1'}">
	    	<p style="color: red;font-size: 15px;">未获取到客户的积分信息！</p>
	    </c:when>
	    <c:when test="${PointRespFlag=='2'}">
	        <p style="color: red;font-size: 15px;">客户名下没有积分！</p>
	    </c:when>
    </c:choose>
    <p id="mark">
    	<p align="left">1.积分最高抵扣额度为订单金额的50%</p>
    	<p align="left">2.一笔支付订单只能选择一家行社的积分进行抵扣</p>
    </p>
  </div>
  <button data-remodal-action="confirm" class="remodal-confirm" style="background: #5478D0;padding: 6px;" >确认</button>
</div> 
<div class="fuc hide" id="PointRule" style="overflow: scroll">
  <div class="fuc_main" style="width: 100%; height: 80%; overflow: auto; border: 1px ; text-align: left">
	  <div class="fuc_main_b"> 积分抵扣规则
	      <div style="float:right;margin-right:8%"><a href="###" id="close-PointBox" style="position: fixed;"><img src="images/images/cha.png"/></a></div>
	  </div>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）积分专属浙江省农村信用社（合作银行、商业银行），仅限在丰收购、丰收家平台以浙江农信丰收卡通过丰收E支付时使用。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）买家持有多家浙江农信行社积分时，一笔支付订单只能使用一家行社的积分进行抵扣。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）买家因任何理由对购买的商品或服务发起退款时，退还的金额为买家购买商品或服务时实际支付的现金，积分不予退还。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）具体积分折算金额比例详见各行社积分管理办法。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）积分最小抵扣金额为1元，订单可抵扣金额少于1元时积分不可抵扣，抵扣金额保留整数。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（6）买家在商城使用积分时最高抵用额度为订单金额的50%。在限额范围内，您可选择使用积分进行抵扣或者全额现金支付。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（7）买家在使用积分时，优先消耗旧积分，积分有效期详见各行社积分有效期规定。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（8）积分不能进行任何形式的转让，不可兑换现金。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（9）积分所属各行社独立判断，买家是否存在恶意或伪造非真实交易骗取积分的行为；规定了其他不予积分情形的不得参与积分抵扣。</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（10）本积分规则条款及细则中如有任何争议，在法律许可范围内浙江省农村信用社（合作银行、商业银行）保留最终解释权。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  </div>
</div>
<script>
		function showLoading() {
			$("#load_bg").css({
				display : "block",
				height : $(document).height()
			});
			var $load_box = $('.load_box');
			$load_box.css({
				//设置弹出层距离左边的位置
				left : ($("body").width() - $load_box.width()) / 2 - 20 + "px",
				//设置弹出层距离上面的位置
				top : ($(window).height() - $load_box.height()) / 2
						+ $(window).scrollTop() + "px",
				display : "block"
			});
		}

		function hideLoading() {
			$("#load_bg").css({
				display : "none"
			});
			var $load_box = $('.load_box');
			$load_box.css({
				display : "none"
			});
		}
	</script>
</body>
</html>
