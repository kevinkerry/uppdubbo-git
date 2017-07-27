<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <meta name="viewport"
       content="width=device-width, initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>浙江农信手机支付</title>
	<link href="css/cellphone.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script language="javascript" type="text/javascript" src="js/password-widget.min.js"></script>
    <script src="js/slides.min.jquery.js"></script>
	
	<script type="text/javascript">
	
	function doIt(obj){
		if(2==$("#tellFlag").val()){
    		document.forms[0].TellerNo.value ="";
        }
        document.getElementById("SmsCode").value=document.forms[0].MessageCode_tel.value;
        if (false == checkAllValue()) {
        	return false;
        }
        var password = document.getElementById("Password").value;
        password = $getCiphertext();
        if(null == password){
        	layer.msg("密码输入位数不足");
        	return;
        }
        document.forms[0].Password.value = password;
        document.forms[0].action = "FS01MobileConfirm.do";
        document.forms[0].submit();
    }
	
	function checkAllValue() {
        return checkAllValueForFoison();
    }
    function checkAllValueForFoison() {
        
        var paperType = $("#PayerIdTypCd").val();
 		if (paperType == "01") {
 			var length = document.getElementById("PayerIdNbr").value.length;
 			if(length==0){
 				layer.msg("证件号不能为空");
 				return false;
 			}else if(length <18) {
 				layer.msg("证件号位数不足");
 				return false;
 			} else if (length>18) {
 				layer.msg("证件号位数错误");
 				return false;
 			} 
 		}
 		if (false == checkPassword()) {
            layer.msg("密码输入位数不足");
            return false;
        }
 		if (false == checkCardValid()) {
            layer.msg("有效期位数不足");
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
         if (false == checkPhoneMessageCode()) {
             layer.msg("短信验证码位数不足，请获取!");
             return false;
             }
        return true;
    }
    function checkCardValid() {
        if (false == checkLength("CardValidM", 2)) {
            return false;
        }
        return checkLength("CardValidY", 2);
    }
	function checkPhoneMessageCode() {
        return checkLength("SmsCode", 6);
    }
    function checkPhoneMessageNull() {
        return checkLength("SmsCode", 0);
    }
    function checkPassword() {
        var length = $getPasswordLength();
        if (6 == length) {
            return true;
        }
        return false;
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
		
	var InterValObj; //timer变量,控制时间
    var count = 120; //间隔函数,1秒执行
    var curCount; //当前剩余秒数
    function getSMSFunction() {
        curCount = count;
        $("#getSMS").attr("disabled", "disabled");
//        $("#SMSbutton").css("background-image","url(images/images/fanhui.png)");
       // document.getElementById("getSMS").value = "(" + curCount + "秒" + ")";
        var PayerPhoneNo = "${PayerPhoneNo}";
        var deptId = document.forms[0].PayerAcctDeptNbr.value;
        var acctNo = "${PayerAcctNbr}";
        var payAcctInfo = "${PayerAcctNbr}";
        //var acctNo = getAcctNo(cardInfo);
        //var deptId = getDeptId(cardInfo);
        var transAmt = "${TransAmt}";
        var oparams = new Array(
        		   new Pair("PayerPhoneNo", PayerPhoneNo),
        		   new Pair("PayerAcctDeptNbr", deptId),
                   new Pair("PayerAcctNbr", acctNo),
                   new Pair("OperateType", "0"),
                   new Pair("TransTypCd", "UPP10001")
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
    
    function checkPhoneMessageconfirm() {
        if (!(curCount > 0 && curCount < 120)) {
            return false;
        }
        return true;
    }
    function checkTellerNo() {
		var tellerNo = $("#TellerNo").val();
		if(tellerNo.length == 0) {
			$("#tellFlag").val("1");
			return true;
		}
		var reg = /^[0-9]{7}$/;
		 if(!reg.test(tellerNo)){
			 $("#tellFlag").val("2");
			 layer.msg("柜员号格式错误");
		 }else{
			 $("#tellFlag").val("1");
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
         orderIdSafeDispose();
     }
     function clearNo() {
			document.getElementById("PayerIdNbr").value = "";
			/* document.getElementById("paperNoInfo").innerHTML = "*"; */
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
	<form name="form1" action="#" method="post">
	<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
	<pe:hidden fieldList="MerSeqNo,PayerCardTypCd,PaperType,PayerAcctDeptNbr,PayerAcctNbr,PayerPhoneNo,PayerAcctName,CustCifNbr,PlainContext,Plain,ChannelNbr,OrderId,MerchantName,Signature,TransAmt"
	skipNULL="false"/>
	<input id="TransTypCd" name="TransTypCd" type="hidden" value="UPP10001"/>
	<input id="tellFlag" type="hidden" value=""/>
	<div class="logo">
        <div class=""> 丰收e支付注册 <a href="###" class="leftBt"><img src="images/phone/left.png" width="16" height="23"
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
                <td><c:out value='${PayerAcctNbr}'/></td>
            </tr>
    		<tr>
                <td class="right left">手&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;号：</td>
                <td><c:out value='${PayerPhoneNo}'/></td>
            </tr>
            <tr>
                <td class="right left">证&nbsp;件&nbsp;种&nbsp;类：</td>
                <td class="srk">
                	<select name="PayerIdTypCd" id="PayerIdTypCd" onchange="clearNo()">
                                    <option value="01">居民身份证</option>
                                    <option value="03">军官证</option>
                                    <option value="04">台湾来往内地通行证</option>
                                    <option value="06">港澳来往内地通行证</option>
                                    <option value="10">护照</option>
                                    <option value="17">户口簿</option>
                                    <option value="21">警官证</option>
                                    <option value="22">士兵证</option>
                                    <option value="20">其它</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="right left">证&nbsp;件&nbsp;号&nbsp;码：</td>
                <td ><input autocomplete="off"  type="text" name="PayerIdNbr" id="PayerIdNbr" style="width: 150px;;"/>
			</tr>
			<tr>
                <td class="right left">查&nbsp;询&nbsp;密&nbsp;码：</td>
                <input type="hidden" id="Password" name="PayerCardPwd" value=""/>
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
            </tr>
            <tr>
                <td class="right left">有&nbsp;&nbsp;&nbsp;效&nbsp;&nbsp;&nbsp;期：</td>
                <td class="srk">
                    <select id="CardValidM" name="CardValidM" style="width: 30%">
                        <option value="">--</option>
                        <option value="01">01</option>
                        <option value="02">02</option>
                        <option value="03">03</option>
                        <option value="04">04</option>
                        <option value="05">05</option>
                        <option value="06">06</option>
                        <option value="07">07</option>
                        <option value="08">08</option>
                        <option value="09">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>月
                    <select id="CardValidY" name="CardValidY" style="width: 30%">
                        <option value="">--</option>
                        <c:forEach items="${YearList}" var="year">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>年
                </td>
            </tr>            
            <tr>
                            <td class="right left">柜&nbsp;员&nbsp;号&nbsp;码：</td>
                            <td class="srk"><input autocomplete="off"  type="text" size="10" maxlength="7" onblur="checkTellerNo()" placeholder="邀请人柜员号，可不填"  name="TellerNo" id="TellerNo" style="width: auto;height: 24px;"/>
                            </td>
            </tr>
            <tr>
                <td class="right left" nowrap>短信验证码：</td>
                <td class="srk">
                    <input id="SmsCode" name="SmsCode" type="hidden" value=""/>
                    <input name="MessageCode_tel" autocomplete="off" type="tel" value="" size="6" style="width: 40%"
                           onkeypress="return isNum(event)" maxlength="6"/>
                    <input id="getSMS" onclick="getSMSFunction()" class="hq" value="点击发送" type="button"/>
                    <input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                    <input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" />
                </td>
            </tr>
            
    	</table>
    </div>
    <div class="bottom_botton">
            <input onclick="doIt(this)" value="下一步" type="button" class="peory"/>
    </div>
     <div class="bottom_botton"></div>
    <div class="bottom_botton"></div>
	</form>

</body>
</html>