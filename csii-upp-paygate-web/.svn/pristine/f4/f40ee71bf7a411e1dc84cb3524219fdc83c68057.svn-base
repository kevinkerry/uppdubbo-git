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
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/password-widget.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script>
    	var checkcode="no";
        function doIt(clickObj) {
        	if((document.getElementById('state').value)=="0"){
          		if(checkLength("_vTokenName",0)){
          			layer.msg("附加码为空，请输入");
          			return;
          		}
	         	if(checkcode!="yes"){
	             $("#doNext").attr("disabled","disabled");
	             return;
	        	}
          	}
        	document.getElementById("SmsCode").value = document.forms[0].MessageCode_tel.value;
            var doc = document.getElementById("Password");
            doc.value = $getCiphertext();
            if (false == checkAllValue()) {
                return false;
            }

            document.forms[0].action = "MobileTransferConfirm.do";
            document.forms[0].submit();
            /*post2SRV('MobileTransferConfirm.do', clickObj.form, clickObj, 'EEE', null);*/
        }
        function checkAllValue() {
            return checkAllValueForDebitCard();
        }
        function checkAllValueForDebitCard() {
            if (false == checkPassword()) {
                layer.msg("密码输入位数不足");
                return false;
            }
              
            if (false == checkPhoneMessageconfirm()) {
                layer.msg("未获取短信验证码，请获取!");
                return false;
            }
            if (true == checkPhoneMessageNull()) {
                layer.msg("无输入，请输入验证码！");
                return false;
            }
            if (false == checkPhonesmsCode()) {
                layer.msg("短信验证码位数不足，请重新输入!");
                return false;
                } 
            
            /*            if(false == checkTokenValue()) {
             layer.msg("图片验证码位数不足");
             return false;
             }*/
            return true;
        }

        function checkPassword() {
            var length = $getPasswordLength();
            if (6 == length) {
                return true;
            }
            return false;
        }

        function checkPhonesmsCode() {
            return checkLength("SmsCode", 6);
        }
        function checkLength(nodeName, length) {
            var node = document.getElementById(nodeName).value;
            if (length != node.length) {
                return false;
            }
            return true;
        }

        function reloadTokenImg() {
            document.getElementById('_tokenImg').src = "GenTokenImg.do" + "?random=" + Math.random();
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

        function PayerAcctNbrSafeDispose() {
            var doc = document.getElementsByName("AcctNbr");
            var PayerPhoneNoValue = "${PayerAcctNbr}";
            PayerPhoneNoValue = PayerPhoneNoValue.substring(0, 5) + "****" + PayerPhoneNoValue.substring(PayerPhoneNoValue.length - 4, PayerPhoneNoValue.length);
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
    //        document.getElementById("getSMS").value = "(" + curCount + "秒" + ")";
   			var deptId = document.forms[0].PayerAcctDeptNbr.value;
            var PayerPhoneNo = "${PayerPhoneNo}";
            var acctNo = "${PayerAcctNbr}";
            var payAcctInfo = "${PayerAcctNbr}";
            //var acctNo = getAcctNo(cardInfo);
            //var deptId = getDeptId(cardInfo);
            var transAmt = "${TransAmt}";
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
        
        function checkPhoneMessageconfirm() {
            if (!(curCount > 0 && curCount < 120)) {

                return false;
            }
            return true;
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
        function checkPhoneMessageCode() {
            return checkLength("SmsCode", 6);
        }
        function checkPhoneMessageNull() {
            return checkLength("SmsCode", 0);
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
                    $("#doNext").attr("disabled",false);
                } else {
                	checkcode="no";
                    document.getElementById("rightImage").src="images/hongch.jpg";
                    document.getElementById("rightImage").style.display="inline";
                    $("#doNext").attr("disabled",true);
                }
            });
        }

        function LoadJs() {
            /*            reloadTokenImg();*/
            phoneNumSafeDispose();
            PayerAcctNbrSafeDispose();
            orderIdSafeDispose();
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


<body onload="LoadJs()" style="-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;user-select: none;">
<form name="form1" action="#" method="post">
<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
    <pe:hidden
            fieldList="PayTypCd,PayerCardTypCd,PayerAcctNbr,CustCifNbr,PayerPhoneNo,OrderId,MobileNo,PlainContext,Plain,PayerAcctDeptNbr,ChannelId,MerDate,MerchantName,TransAmt,ChannelNbr,PayerAcctName,Signature"
            skipNULL="false"/>
            <input id="TransTypCd" name="TransTypCd" type="hidden" value="UPP10003"/>
            <input type="hidden" name="state" id="state" value="${state}"/>	
    <div class="logo">
        <div class=""> 丰收卡支付 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
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
                <td class="right left">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk"><input type="text" id="PayerAcctNbr" name="AcctNbr" readonly="readonly" style="width: 100%"/>
                </td>
            </tr>
            <tr>
                <td class="right left">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                <td class="srk"><input type="text" id="PayerPhoneNo" name="PhoneNo" readonly="readonly"
                                       style="width: 100%"/></td>
            </tr>

            <tr>
                <td class="right left">交&nbsp;易&nbsp;密&nbsp;码：</td>
                 <td class="srk">
                    <div class="row-fluid userName">
                        <div class="input-outbox" onselectstart="return false;"
                             style="-moz-user-select:none;-khtml-user-select:none;-webkit-user-select:none;-ms-user-select: none;user-select: none;">
                            <div id="vPasswordWidget" onselectstart="return false;"
                                 onclick="document.getElementById('vPasswordWidget').scrollIntoView();"
                                 v-password-widget="/paygate/PasswordServlet"
                                 modulus-hex="a0d4c76453e0c06ae7be19b74a12bcd5f5aafb42c6820e7e6aa2b19988b11c8245fc8db41c6957a52bb5366e6c9f307114467a569c9bf88ca01bc058af6910e6d4c9495f253e58fe731a479df9cdddd9e13a46584446369a184289e0523b2edf15a5d62e95f04d7b02ea7fa7be7f6506fab144d92c984f5e8bf219fa9fd62a5f"
                                 v-minlength="6" v-maxlength="6" name="Password" v-model="Password"
                                 style="border:1px solid;width:150px;height:20px;text-align: left;">请输入密码
                            </div>
                        </div>
                    </div>
                </td> 
                <input type="hidden" id="Password" name="Password" value=""/>
            </tr>

            <tr>
                <td class="right left" nowrap>短信验证码：</td>
                <td class="srk">
                    <input id="SmsCode" name="SmsCode" type="hidden" value="" />
                    <input name="MessageCode_tel" autocomplete="off" type="tel" value="" size="6" style="width: 40%"
                           onkeypress="return isNum(event)" maxlength="6" />
                    <input id="getSMS" onclick="getSMSFunction()" class="hq" value="点击发送" type="button"/>
                     <input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                     	<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
                </td>
            </tr>
           <tr id="tableIMG" style="display: none;">
                           
                                <td class="right left" nowrap>附&nbsp;&nbsp;加&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
                                <td class="srk"><input autocomplete="off"  style="width:60px" id="_vTokenName" name="_vTokenName" onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" type="text" value="" size="6" maxlength="4" class="_vTokenName"/>
                                    <img id="rightImage" style="display: none; position:relative;width:20px;"/>
                                    <img id="_tokenImg" onclick="reloadTokenImg()" src=""  style="position:relative;width:80px;"/>
                                    <a style="font-size: 12px" onclick="reloadTokenImg()"></a>
                                    </td>
           </tr>    
        </table>
    </div>
    <div class="bottom_botton">
        <input onclick="doIt(this);" value="确认" type="button"/>
        <%--        <input onclick="###'" class="peory2 peory" value="取消" type="submit" />--%>
    </div>
    <div class="bottom_botton">

    </div>
    <div class="bottom_botton">

    </div>
</form>
</body>
</html>
