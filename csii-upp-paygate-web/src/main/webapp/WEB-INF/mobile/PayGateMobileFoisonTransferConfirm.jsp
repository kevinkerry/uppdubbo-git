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
    <title>�㽭ũ���ֻ�֧��</title>
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
          			layer.msg("������Ϊ�գ�������!");
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
        			layer.msg("�漰�羳��Ʒ��֧������֧��");
					return;
				}
        		if($("input[name='PayTypeCdStr']").val().indexOf("3") == -1){
					layer.msg("�̻���֧�ִ�������");
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
						layer.msg("�ÿ���ע�ᣡ");
						$("#Card_tel").val('���������п���');
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
        		layer.msg("����λ������")
        		return false;
        	}
        }
        //У���ֻ���
        function checkPhone(){
        		var phoneLength=document.forms[0].PayerPhoneNo.value.length;
        		if(phoneLength<11){
        			layer.msg("�ֻ���λ������");
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
                layer.msg("δ��ȡ������֤�룬���ȡ!");
                return false;
            }
            if (true == checkPhoneMessageNull()) {
                layer.msg("�����룬��������֤�룡");
                return false;
            }
            if (false == checkPhoneMessageCode()) {
                layer.msg("������֤�������������������");
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

        var InterValObj; //timer����,����ʱ��
        var count = 120; //�������,1��ִ��
        var curCount; //��ǰʣ������
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
            		layer.msg("���ŷ����쳣���������磬����ϵ����Ա");
                    //throwValidationAtEEE(message,"EEE");
                } else {
                	if ("" == message) {
                        layer.msg("���ŷ����쳣������ϵ����Ա");
                        document.getElementById("getSMS").disabled = false;
                    }
                	var obj = eval('('+message+')');
                	if(obj.SmsSqenbr != null){
						$("#SmsSqenbr").val(obj.SmsSqenbr);
					}
                	if("0000000"==obj.RespCode){
                    	$("#checkbox").attr("disabled", "disabled");
						$("#detail").removeAttr("href");
                        document.getElementById("getSMS").value="(" + curCount + "��" + ")";
                        InterValObj=window.setInterval(SetRemainTime,1000);
                    }else if("PAY0100"==obj.RespCode){
                        curCount = 30;
                        layer.msg("30���ڲ����Է��Ͷ������ţ���30s������");
                        document.getElementById("getSMS").value="(" + curCount + "��" + ")";
                        InterValObj=window.setInterval(SetRemainTime,1000);
                    }else{
                    	document.getElementById("getSMS").disabled = false;
                    	layer.msg("���ŷ���ʧ��");
                    }
                }
            });
        }
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);//ֹͣ��ʱ��
                $("#getSMS").removeAttr("disabled");
                document.getElementById("getSMS").value = "��ȡ��֤��";
            } else {
                curCount--;
                document.getElementById("getSMS").value = "(" + curCount + "��" + ")";
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
            startIndex = startIndex + nodeName.length + 1; //λƫ�ƣ��и�=�����Լ�1

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
        //���ֵֿ����鵯����
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
    			document.getElementById("getSMS").value = smsCodeBackv + "��";
    			//$("#getSMS")
    			//.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
    			curCount = parseInt(smsCodeBackv);
    			document.getElementById("getSMS").value = curCount
    			+ "��";
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
        <div class=""> ����e֧�� <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
                                                               onclick="goBack()"/></a>
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
	<div class="pointDetial" id="pointshow" style="color: #666;display: none;">
       <ul style="left: 30px;">
                  				<li class="jf" style="padding-left: 20px;">
                  				<span>
                  				
                  				<input id="checkbox" name="checkbox" type="checkbox" class="mgc" style="width:16px; " onclick="isChecked(this)"/>
                  				</span>
                  				<span>
                  				&nbsp;&nbsp;����ǰ���ֿɵֿ۽��Ϊ</span>�� <input class="input1" type="text" name="jane" id="jane"  value="0"  readonly="true" style="width: 40px;"></input>&nbsp;<span style="display: none;" id="janemoney">0</span>
                  					
                  				</li>
                  				<li class="jf" >
                  				
                  				<image src="images/jxIco.png" align="absmiddle" ></image>
                  				���踶<span class="red" >��</span><span id="jane5" class="red">${TransAmt}</span>
                  				<span style="padding-left: 0%">
                  					<a  href="#" id="open-PointBox">�ֿ۹���</a>
                  				</span>&nbsp;
                  				<span style="padding-left: 22%">	
                  					<a href="#modal" id="detail">�ֿ�����</a> 
                  				</span>
                  				</li>
              				</ul>       				
   </div>
    <div class="main" id="zhifu">
        <table width="100%" border="0" cellpadding="1" cellspacing="0">
            <tr id="cardSelect">
                <td class="right left">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</td>
                <td class="srk">
                    <select id="PayerAcctNbrDept" name="PayerAcctNbrDept" onchange="change();">
                    <c:forEach items="${CardList}" var="cardInfo">
											<option value="${cardInfo.PayerAcctNbr}${cardInfo.PayerAcctDeptNbr}">${cardInfo.PayerAcctNo}</option>
										</c:forEach></select>
				</td>
            </tr>
            <tr id="cardInputShow">
                <td class="right left" >��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ţ�</td>
                  <td class="srk"><input id="PayerAcctNbr" name="PayerAcctNbr"
                   type="hidden" value=""/>
                    <input name="Card_tel" id="Card_tel" autocomplete="off" type="tel" value="���������п���" style="width: 100%"
                           onkeypress="return isNum(event)" maxlength="19"
                           onfocus="if(value=='���������п���') {value=''}"
                           onblur="if (value=='') {value='���������п���'}"/></td>
            </tr>
            <tr id="tr_phone">
                <td class="right left">��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;�ţ�</td>
                <td class="srk"><input type="text" id="PayerPhoneNo" name="PayerPhoneNo" readonly="readonly" value="${PayerPhoneNo}"
                                       style="width: 100%"/></td>
            </tr>
			<tr id="duanxin">
                <td class="right left" nowrap>������֤�룺</td>
                <td class="srk">
                    <input id="SmsCode" name="SmsCode" type="hidden" value=""/>
                    <input name="MessageCode_tel" autocomplete="off" type="tel" value="" size="6" style="width: 45%"
                           onkeypress="return isNum(event)" maxlength="6"/>
                    <input id="getSMS" onclick="getSMSFunction()" class="hq" value="�������" type="button"/>
                     <input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                     	<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
                </td>
            </tr>
           <tr id="tableIMG" style="display: none;">
                           
                                <td class="right left" nowrap>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��</td>
                                <td class="srk"><input autocomplete="off"  style="width:60px" id="_vTokenName" name="_vTokenName" onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" type="text" value="" size="6" maxlength="4" class="_vTokenName"/>
                                    <img id="rightImage" style="display: none; position:relative;width:20px;"/>
                                    <img id="_tokenImg" onclick="reloadTokenImg()" src=""  style="position:relative;width:50px;"/>
                                    <a style="font-size: 12px" onclick="reloadTokenImg()"></a>
                                    </td>
           </tr> 
           <tr id="xieyiShow">
				<td class="right left" style="padding-right:18px;"><input class="mgc" id="sign" name="sign" type="checkbox" value=""
                                         style="width: 16px;" /></td>
            	<td class="font14"><a href="#" id="open-box">�����Ķ������ܡ��㽭ũ�ŷ���e֧������Э�顷</a></td>
          </tr>   
        </table>
    </div>
    <div class="fuc hide" id="fuc" style="overflow: scroll">
        <div class="fuc_main"
             style="width: 100%; height: 80%; overflow: auto; border: 1px ; text-align: left">
            <div class="fuc_main_b"> �㽭ʡũ�������磨�������С���ҵ���У�������e֧��������Э�� <a href="###" id="close-box"><img
                    src="images/images/cha.png"/></a></div>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��Э�������㽭ʡũ�������磨�������С���ҵ���У������¼�ơ����С�������տ��������ս�ǿ��ͷ��մ��ǿ����������������ֿ��ˣ����¼�ơ��������͡�����e֧�����������¼�ơ������񡱣���ʹ�õ������������������Ч��Լ��������e֧�������㽭ʡũ�������磨�������С���ҵ���У�����տ��������������ֿ����ṩ���ԡ��ֻ���+���п���+�ֻ���̬���롱��Ϊ֧���İ�ȫ��֤��ʽ����С������֧����һ��֧����ʽ����ͨ����������Internet�����ȷ�ϻ���������ʽѡ����ܱ�Э�飬����ʾ��ͬ����ܱ�Э���ȫ��Լ�����ݣ�ȷ�ϳе��ɴ˲�����һ�����Ρ�</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ڽ��ܱ�Э��֮ǰ��������ϸ�Ķ���Э���ȫ�����ݣ��ر����Դ����»��߱�ע�����ݣ����������ͬ�ⱾЭ����κ����ݣ������޷�׼ȷ����������Ľ��ͣ��벻Ҫ���к���������</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��1����Ӧȷ������ʹ�ñ�����ʱ��ʹ�õ����п�Ϊ���������У���ȷ��������֧������Ϊ�Ϸ�����Ч��δ�ַ��κε������Ϸ�Ȩ�棻�������������С��ֿ�����ʧ�ģ���Ӧ�����⳥���е�ȫ���������Ρ�</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��2�����н�Ϊ���ṩ��ȫ�ɿ�������֧������֧�������������Ļ��������е���Ʒ�������ͻ���������������;��׾��������ṩ��Ʒ�������̻�����Э�̽�������жԴ˲��е��κ����Ρ�</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��3����Ӧ���Ʊ��ܸÿ������������������͡���֮��ص�֤�����ͼ�֤�����롢�ֻ����롢�̶��绰��ͨ�ŵ�ַ����ÿ��йص�һ����Ϣ�����¼�ơ������Ϣ������Ϣ���������ý������Ϣ������Ϣת�����ˣ����ڰ�ȫ���绷����ʹ�ã�������ʧ���п���й¶�����Ϣ������Ϣ����Ӧ��ʱ֪ͨ�������У��Լ��ٿ��ܷ�������ʧ��������ʧ�ֻ���й¶���п����롢й¶�ֻ���֤�롢й¶���п����롢����֤�顢U�ܡ���ʧ���п������µķ��ռ���ʧ���������ге���</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��4��ʹ�á�����e֧�������񣬷��ս�ǿ������������ѽ��׶����Ϊ�����2000Ԫ��ÿ���������ѽ��׶����Ϊ�����2000Ԫ�����մ��ǿ������������ѽ��׶����Ϊ�����2000Ԫ��ÿ���������ѽ��׶����Ϊ�����2000Ԫ�����п����ʽ�����߶ÿ���������ѽ�����߶��Է���������������֧���޶�Ϊ׼��ʹ�÷��տ�ǩԼ����e֧�������Ը���֧����Ҫ�ͷ��տ��ǣ�����㽭ũ�Ź�����www.csii.com���Ϸ���e֧��ͼ�꣬�������e֧��ҵ��ά��ҳ�棬������ͳһ���õķ���e֧�������޶��ڵ��������˷��տ��ķ���e֧�������޶���пɸ���ҵ��չ��Ҫ�����տ�����Ҫ���û��޸ķ��տ��Ľ����޶����Կͻ�����֪ͨ���������������йع��漰��ҳ˵���������������޸Ľ����޶��������ģ���������ֹʹ�ñ������������޸��޶������Ȼʹ�ñ����ܵģ���Ϊ��ͬ�����ж��޶������е��޸ĺ͵��������κ�����£����׽�Ӧ�������м����з���������������֧�����õĽ����޶��ʵ��֧��������֧���޶���н��ܾ�ִ�н���ָ��ɴ����������ʧ���в��е����Ρ�</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��5����ȷ����ȷ�ϡ�����e֧����ע��ǩԼ�ֻ�����Ϊ���ڷ�������Ԥ���ı����ֻ����벢�ڱ���ʵ�ʿ���֮�¡���֧�������У����н�����������e֧����ע��ǩԼ�ֻ����뷢�Ͷ�̬��֤��׷����֤����ȷ�����Ľ��װ�ȫ����Ӧ���Ʊ����ƶ��绰���������Ϣ���������ƶ��绰ͨѶ��ʹ�ù���ͨ����������ʧ�ƶ��绰�����ƶ��绰ת������ʹ�á��ƶ��绰������ϡ���������������ԭ����������޷���ȡ���з��͵Ķ�̬��֤����������һ�к������ʧ���������ге�����������ͨѶ���������ȷ�����ԭ��������޷����ն�̬��֤��ģ����в��е��κ����Ρ��������ֻ��������ƶ���Ӫ�̴�ע����Ӧ��ʱ���㽭ũ�Ź�����www.csii.com���������e֧��ͼ�꣬�������e֧��ҵ��ά��ҳ�棬������ֻ�����ע��ǩԼ�ġ�����e֧����ҵ��</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��6����ͬ�����ж�����ʹ�ñ��������������д�İ��������������������п����š��ֻ����롢���֤����Ƚ���У�Ժ��飬ͬʱ���б�����ʱ�������ȷ��Ҫ����ΪУ���׼��Ȩ����</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��7���������������п��������֡���ٽ��ס�ϴǮ����Ϊ����������������н�����ص��飬һ�����ܾ���Ͻ�����ص����������Ϊ�����ڻ�������ٽ��ס�ϴǮ�����ֻ��κ������Ƿ������թ��Υ������ԭ�����Ϊ����Υ����Э��Լ���ģ�������Ȩ��ȡ����һ�֡����ֻ�ȫ����ʩ��һ����ͣ����ֹ�ṩ��Э�����¡�����e֧�������񣻶�����ֹ��Э�飻����ȡ�������ÿ��ʸ�</b>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>��8����ͬ��������Ȩ�������ͣ��Э��������ط�����Ȩ�޸ġ���ֹ��Э�飬����ִ��ǰͨ��������վ���й��档��ع��澭��վ������Ϊ�����յ������ڹ���ִ�к�����������ҵ��ģ���ͬ�����йر�Э���޸ġ���������ݡ���Э����ֹ������Э����ֹǰ�������н��д���Ľ���ָ������Ч����Ӧ�е�������</b>
			</p>
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʹ�÷���e֧��ҵ��Ӧ�����������п��³̺���������վ��www.csii.com���Ϲ����Ľ��׹������г�ͻ���Ա�Э��Ϊ׼��</p>

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
        <input onclick="showInput();" id="btn_addNewCard" class="peory khzf" value="��ͨ�¿�" type="button"  />
        <input onclick="doIt(this);" id="btn_ConfirmPay" class="peory khzf" value="ȷ��֧��" type="button"/>
        <input onclick="showSelect();" id="btn_back" class="peory khzf" value="����֧��" type="button" />
		<input onclick="doItnext(this);" id="btn_OpenPay" class="peory khzf" value="��ͨ��֧��" type="button" />
        <%--        <input onclick="###'" class="peory2 peory" value="ȡ��" type="submit" />--%>
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
		<!-- 		<span style="font: 16px '΢���ź�';">������&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.&nbsp;.</span> -->
	</div>
	<div class="remodal" data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc" style="max-width: 100%;
	padding: 5px;border-top: 1px solid #5478D0;border-bottom: 1px solid #5478D0 ">
  <button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
  <div>
    <h4 id="modal1Title">�ֿ۽�� <input class="input2" style="width: 50px;" type="text" name="deduction" id="deduction"  value="" readonly="true"></input>Ԫ</h2>
    <p id="modal1Desc" align="left">
    	�������ֶ�ѡ��ֿ����磺
    	 <span style="float:right">(��λ��Ԫ)</span>
    </p>
    <div>
    <table width="100%" border="1" cellpadding="2" cellspacing="0"	class="table-class" align="center" style="font-size:14px">
    	<tr class="trTitle" bgcolor="#c8c8ff">
		<td nowrap class="tdTitle" align="center">��������</td>
	    <td nowrap class="tdTitle" align="center" >�ɵֿ��ܶ�</td>
	    <td nowrap class="tdTitle" align="center" >���εֿ۽��</td>
		<td nowrap class="tdTitle" align="center">�Ƿ�ֿ�</td>
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
	    	<p style="color: red;font-size: 15px;">δ��ȡ���ͻ��Ļ�����Ϣ��</p>
	    </c:when>
	    <c:when test="${PointRespFlag=='2'}">
	        <p style="color: red;font-size: 15px;">�ͻ�����û�л��֣�</p>
	    </c:when>
    </c:choose>
    <p id="mark">
    	<p align="left">1.������ߵֿ۶��Ϊ��������50%</p>
    	<p align="left">2.һ��֧������ֻ��ѡ��һ������Ļ��ֽ��еֿ�</p>
    </p>
  </div>
  <button data-remodal-action="confirm" class="remodal-confirm" style="background: #5478D0;padding: 6px;" >ȷ��</button>
</div> 
<div class="fuc hide" id="PointRule" style="overflow: scroll">
  <div class="fuc_main" style="width: 100%; height: 80%; overflow: auto; border: 1px ; text-align: left">
	  <div class="fuc_main_b"> ���ֵֿ۹���
	      <div style="float:right;margin-right:8%"><a href="###" id="close-PointBox" style="position: fixed;"><img src="images/images/cha.png"/></a></div>
	  </div>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��1������ר���㽭ʡũ�������磨�������С���ҵ���У��������ڷ��չ������ռ�ƽ̨���㽭ũ�ŷ��տ�ͨ������E֧��ʱʹ�á�</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��2����ҳ��ж���㽭ũ���������ʱ��һ��֧������ֻ��ʹ��һ������Ļ��ֽ��еֿۡ�</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��3��������κ����ɶԹ������Ʒ��������˿�ʱ���˻��Ľ��Ϊ��ҹ�����Ʒ�����ʱʵ��֧�����ֽ𣬻��ֲ����˻���</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��4�����������������������������ֹ���취��</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��5��������С�ֿ۽��Ϊ1Ԫ�������ɵֿ۽������1Ԫʱ���ֲ��ɵֿۣ��ֿ۽���������</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��6��������̳�ʹ�û���ʱ��ߵ��ö��Ϊ��������50%�����޶Χ�ڣ�����ѡ��ʹ�û��ֽ��еֿۻ���ȫ���ֽ�֧����</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��7�������ʹ�û���ʱ���������ľɻ��֣�������Ч����������������Ч�ڹ涨��</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��8�����ֲ��ܽ����κ���ʽ��ת�ã����ɶһ��ֽ�</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��9��������������������жϣ�����Ƿ���ڶ����α�����ʵ����ƭȡ���ֵ���Ϊ���涨����������������εĲ��ò�����ֵֿۡ�</p>
    <p class="pointRules">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��10�������ֹ������ϸ���������κ����飬�ڷ�����ɷ�Χ���㽭ʡũ�������磨�������С���ҵ���У��������ս���Ȩ��</p>
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
				//���õ����������ߵ�λ��
				left : ($("body").width() - $load_box.width()) / 2 - 20 + "px",
				//���õ�������������λ��
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
