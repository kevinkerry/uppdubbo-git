/**
 * Created by XQY on 2015/12/28.
 */

function changeChecked(radioId){
	/*$("++++++[for='" + radioId + "']").addClass('on');
    $("label[for!='" + radioId + "']").removeClass('on');*/
    
	$("input[id='" + radioId + "']").attr("checked",  true);
    $("input[id!='" + radioId + "']").attr("checked", false);
    $("input[id='" + radioId + "']").prop("checked",  true);
    $("input[id!='" + radioId + "']").prop("checked", false);
}

//初始加载
function displayPayType(openPayType) {
    if ("" == openPayType) {
        return;
    }
    var foisonOpenStatus = openPayType.substr(0, 1);
    var bankOpenStatus = openPayType.substr(1, 1);
    if ("0" == foisonOpenStatus) {
        if ("1" == bankOpenStatus) {
           // $(".midDle").css({"display": "none"});
            $(".midDle-left").css({"display": "none"});
            $(".midDle-right").css({"display": "table-row"});
            $(".main").css({"display": "none"});
            $(".main-1").css({"display": "table-row"});
            $(".main-4").css({"display": "table-row"});
            changeChecked("fosiradio2");
            /*$("#select1").attr("class","radiochecded unchecked");
            $("#select2").attr("class","radiochecded");*/
            return;
        }
        if ("0" == bankOpenStatus) {
            layer.msg("not the open mode");
            $(".midDle-left").css({"display": "none"});
            $(".midDle-right").css({"display": "none"});
            $(".main").css({"display": "none"});
            return;
        }
    }
    if ("1" == foisonOpenStatus) {
        if ("0" == bankOpenStatus) {
            //$(".midDle").css({"display": "none"});
            $(".midDle-left").css({"display": "table-row"});
            $(".midDle-right").css({"display": "none"});
            $(".main").css({"display": "table-row"});
            $(".main-1").css({"display": "none"});
            $(".main-4").css({"display": "none"});
           // document.getElementById("top").innerHTML = "丰收e支付";
            return;
        }
    }
}
function setDefaultPayType(defaultPayType, openPayType) {
    if ("" == openPayType) {
        return;
    }
    var foisonOpenType = openPayType.substr(0, 1);
    var bankOpenType = openPayType.substr(1, 1);
    if ("" == defaultPayType) {
        return;
    }
    if ("1" == defaultPayType && "0" == foisonOpenType || "2" == defaultPayType && "0" == bankOpenType) {
        layer.msg("set default error");
        return;
    }
    if ("2" == defaultPayType && foisonOpenType == "1" && bankOpenType == "1") {
        /*$(".midDle").css({"display": "none"});*/
       // $(".main").css({"display": "none"});
       // $(".main-1").css({"display": "block"});
       // $(".midDle-right").addClass("active");
       // $(".midDle-left").removeClass("active");
    	 $(".main").css({"display": "none"});
        $(".main-1").css({"display": "table-row"});
        $(".main-4").css({"display": "table-row"});
        changeChecked("fosiradio2");
        /*$("#select2").attr("class","radiochecded");
        $("#select1").attr("class", "radiochecded unchecked");*/
        
    }
}

//通用
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
function setNodeListByName(nodeListName, nodeValue) {
    var doc = document.getElementsByName(nodeListName);
    for (var i = 0; i < doc.length; i++) {
        doc.item(i).value = nodeValue;
    }
}
function formatMoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1],
        t = "";
    for (var i = 0; i < l.length; i++) {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}
function isNull(obj) {
    if(!obj) {
        return true;
    }
    return false;
}
function isNotNull(obj) {
    if(!obj) {
        return false
    }
    return true;
}
function isEmpty(str) {
    if(isNull(str)) {
        //layer.msg("not supported");
        throw new Error("Null Error");
    }
    if(0 == str.replace(/(^s*)|(s*$)/g,"").length) {
        return true;
    }
    return false;
}
function isNullOrEmpty(obj) {
    if(isNull(obj) || isEmpty(obj)) {
        return true;
    }
    return false;
}
//返回页面
function ToMerchant(result, channelId, merUrl1) {
    //layer.msg(channelId);
    if ("05" == channelId) {
        ToO2OMessage(result);
    } else if ("04" == channelId) {
        ToDianShang(result);
    } else {
        ToMerUrl(merUrl1);
    }
}
function ToDianShang(result) {
    if ("Android" == mobileType) {
        if (0 == result) {
            //noinspection JSUnresolvedFunction,JSUnresolvedVariable
            window.mobileEpay.payResult(1);
        } else if (1 == result) {
            //noinspection JSUnresolvedFunction,JSUnresolvedVariable
            window.mobileEpay.payResult(0);
        }
    } else if ("iPhone" == mobileType) {
        if (0 == result) {
            window.location.href = "ios://returnIOSSuccess";
        } else if (1 == result) {
            window.location.href = "ios://returnIOSFail";
        }
    } else if ("iPad" == mobileType) {
        if (0 == result) {
            window.location.href = "ios://returnIOSSuccess";
        } else if (1 == result) {
            window.location.href = "ios://returnIOSFail";
        }
    }
}
function ToO2OMessage(result) {
    if ("Android" == mobileType) {
        if (0 == result) {
            //noinspection JSUnresolvedFunction,JSUnresolvedVariable
            Native.JavaScriptCall_payResult("paySuccess");
        } else if (1 == result) {
            //noinspection JSUnresolvedFunction,JSUnresolvedVariable
            Native.JavaScriptCall_payResult("payError");
        }
    } else if ("iPhone" == mobileType) {
        if (0 == result) {
            window.location.href = "http://localhost/LocalActions/paySuccess";
        } else if (1 == result) {
            window.location.href = "http://localhost/LocalActions/payError";
        }
    } else if ("iPad" == mobileType) {
        if (0 == result) {
            window.location.href = "http://localhost/LocalActions/paySuccess";
        } else if (1 == result) {
            window.location.href = "http://localhost/LocalActions/payError";
        }
    }
}
function ToMerUrl(merUrl1) {
    location.href = merUrl1;
}
function generatorMobileType() {
    var agent = navigator.userAgent;
    if ((-1 < agent.indexOf('Android'))) {
        return "Android";
    } else if (-1 < agent.indexOf('iPhone')) {
        return "iPhone";
    } else if (-1 < agent.indexOf('iPad')) {
        return "iPad";
    } else if ("") {
        return "unknown";
    }
}

//安全处理
function orderIdSafeDispose(orderId, channelId) {
    if ("04" == channelId) {
        return orderId;
    } else {
        orderId = orderId.substring(0, 7) + "****" + orderId.substring(orderId.length - 6, orderId.length);
    }
    return orderId;
}
function phoneNumSafeDispose(mobileNo) {
    return mobileNo.substring(0, 3) + "****" + mobileNo.substring(7, 11);
}
function acctNoSafeDispose(nodeName) {
    return nodeName.substring(0, 5) + "****" + nodeName.substring(nodeName.length - 4, nodeName.length);
}

//检查处理
function checkPhoneMessageGet() {
    if (!(curCount > 0 && curCount < 120)) {
        layer.msg("请获取验证码");
        return false;
    }
    return true;
}
function checkPhoneMessageNotNull(nodeName) {
    if (checkLength(nodeName, 0)) {
        layer.msg("请输入验证码");
        return false;
    }
    return true;
}
function checkPassword() {
    var passwordLength = $getPasswordLength();
    if (6 != passwordLength) {
        layer.msg("密码位数不足");
        return false;
    }
    return true;
}
function checkPaperNo(paperType, paperNo) {
    var paperTypeValue = document.getElementById(paperType).value;

    if (checkLength(paperNo, 0)) {
        layer.msg("证件号不能为空");
        return false;
    }
    if (!"居民身份证" == paperTypeValue) {
        return true;
    }
    if (!checkLength(paperNo, 18)) {
        layer.msg("不支持非18位的身份证");
        return false;
    }
    return true;
}
function checkAcctNo(nodeName) {
    if (!(checkLength(nodeName, 16) || checkLength(nodeName, 19))) {
        layer.msg("卡号位数不对，16，19为有效输入");
        return false;
    }
    return true;
}
function checkMobilePhone(nodeName) {
    if (!checkLength(nodeName, 11)) {
        layer.msg("手机号位数不足");
        return false;
    }
    var doc = document.getElementById(nodeName);
    if (doc.value.substring(0, 1) != "1") {
        layer.msg("手机号格式有问题");
        return false;
    }
    return true;
}
function checkCardValid(cardValidM, cardValidY) {
    if (!checkLength(cardValidM, 2) || !checkLength(cardValidY, 2) || "--" == cardValidM.value || "--" == cardValidY.value) {
        layer.msg("信用卡有效期格式不合法");
        return false;
    }
    return true;
}

function checkCVV2() {
    if (false == checkLength("CVV2", 3)) {
        layer.msg("信用卡安全码位数不足");
        return false;
    }
    return true;
}
function checkPhoneMessage(nodeName) {
    if (!checkPhoneMessageGet()) {
        return false;
    }
    if (!checkPhoneMessageNotNull(nodeName)) {
        return false;
    }
    return true;
}
function checkCheckBox(nodeName) {
    var checkBox = document.getElementById(nodeName);
    if (!checkBox.checked) {
        layer.msg("请您选择阅读并接受协议");
        return false;
    }
    return true;
}

//丰收e支付Map处理
function getMapValue(str, nodeName) {
    var startIndex = str.indexOf(nodeName);
    var endIndex = str.indexOf(",", startIndex);
    if (endIndex < 0) {
        endIndex = str.indexOf("}", startIndex);
    }
    startIndex = startIndex + nodeName.length + 1; //位偏移，有个=，所以加1
    return str.substring(startIndex, endIndex);
}
function getPayAcctNoFromMap(str) {
    return getMapValue(str, "PayAcctNo");
}
function getDeptIdFromMap(str) {
    return getMapValue(str, "DeptId");
}
function getAcctNoFromMap(str) {
    var acctInfoAndDeptId = getMapValue(str, "AcctInfo");
    var end = acctInfoAndDeptId.indexOf("C");
    if (end < 0) {
        end = acctInfoAndDeptId.indexOf("D");
    }
    return acctInfoAndDeptId.substring(0, end);
}
function getPayAcctTypeFromMap(str) {
    return getMapValue(str, "PayAcctType");
}
function getPayAcctInfoFromMap(str) {
    return getMapValue(str, "AcctInfo");
}

//短信处理
function sendShortMessageToPayGate(acctNo, mobilePhone, transId, transAmt, deptId, nodeName) {
//            $("#SMSbutton").css("background-image","url(images/images/fanhui.png)");
    /*    var param = new Array(
     new Pair("MobilePhone", mobilePhone),
     new Pair("PayAcctNo", acctNo),
     new Pair("TransAmt", transAmt),
     //new Pair("OperateType", "0"),
     new Pair("TransType", transId),
     new Pair("DeptId", deptId)
     );*/

    /*    postData2SRVWithCallback("SMS.do", PEGetPostData(param), function (success, message) {
     if (!success) {
     layer.msg("短信发送失败，请检查网络或联系系统管理员");
     } else {
     var obj = eval('(' + message + ')');
     if ("SMS001" == obj.RespCode) {
     curCount = 30;
     layer.msg("30秒内不可以发送多条短信，请30s后重试");
     document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
     //window.setTimeout(function(){SetRemainTime(nodeName)}, 1000);
     InterValObj = window.setInterval(function () {
     SetRemainTime(nodeName)
     }, 1000);
     } else {
     curCount = defaultCount;
     document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
     //layer.msg(nodeName);
     //window.setTimeout(function(){SetRemainTime(nodeName)}, 1000);
     InterValObj = window.setInterval(function () {
     SetRemainTime(nodeName)
     }, 1000);
     }
     }
     },3000);*/

    document.getElementById(nodeName).disabled = true;
    $.ajax({
        type: "POST",
        url: "SMS.do",
        timeout: 30000,
        error: function (XMLHttpRequest, textStatus) {
            if ("error" == textStatus) {
                layer.msg("短信发送失败，请检查网络");
            } else if ("timeout" == textStatus) {
                layer.msg("短信发送超时，请检查网络，或联系管理员");
            } else {
                layer.msg("短信发送失败，请检查网络，或联系管理员，错误码为：" + textStatus);
            }
            document.getElementById(nodeName).disabled = false;
        },
        data: "MobilePhone=" + mobilePhone + "&PayAcctNo=" + acctNo + "&TransAmt=" + transAmt + "&TransType=" + transId + "&DeptId=" + deptId,
        //success:function(){layer.msg("短信发送后台成功");},
        success: function (data, textStatus) {
            if ("" == data) {
                layer.msg("短信服务异常，请联系管理员");
                document.getElementById(nodeName).disabled = false;
                return false;
            }
            var obj;
            try {
                obj = eval("(" + data + ")");
                //obj = eval(data);
            } catch (e) {
                layer.msg("发送短信，后台返回异常，请联系管理员");
                document.getElementById(nodeName).disabled = false;
                return false;
            }

            //noinspection JSUnresolvedVariable
            if (isNullOrEmpty(obj.RespCode)) {
                //noinspection JSUnresolvedVariable
                layer.msg("短信发送失败，返回错误码：" + obj.RespCode + "，请联系管理员");
                document.getElementById(nodeName).disabled = false;
                return true;
            }

            //noinspection JSUnresolvedVariable
            if ("000000" == obj.RespCode) {
                //layer.msg("短信发送成功");
                curCount = defaultCount;
                //curCount = obj.ReOperationCount;
                document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
                InterValObj = window.setInterval(function () {
                    SetRemainTime(nodeName)
                }, 1000);
                return true;
            }
            if ("SMS001" == obj.RespCode) {
                layer.msg("30秒内不可以发送多条短信，请30s后重试");
                curCount = 30;
                document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
                InterValObj = window.setInterval(function () {
                    SetRemainTime(nodeName)
                }, 1000);
                return true;
            } else {
                layer.msg("返回错误码：" + obj.RespCode + "，请联系管理员");
                document.getElementById(nodeName).disabled = false;
                return true;
            }

/*            //noinspection JSUnresolvedVariable
            layer.msg(obj.RespMessage);
            curCount = obj.ReOperationCount;
            //noinspection JSUnresolvedVariable
            if(isNullOrEmpty(obj.ReOperationCount)) {
                document.getElementById(nodeName).disabled = false;
                return true;
            }
            curCount = 30;
            document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
            InterValObj = window.setInterval(function () {
                SetRemainTime(nodeName)
            }, 1000);
            return true;*/
        }
    });
}
function SetRemainTime(nodeName) {
    if (0 == curCount) {
        window.clearInterval(InterValObj);//停止计时器
        document.getElementById(nodeName).disabled = false;
        document.getElementById(nodeName).value = "获取验证码";
    } else {
        curCount--;
        document.getElementById(nodeName).value = "(" + curCount + "秒" + ")";
        //window.setTimeout(function(){SetRemainTime(nodeName)}, 1000);
    }
}
function isdedction(obj){ 
	if($(obj).val="on"){
		var deductionAmt = $(obj).parent().parent().find("input[id='AmtThisTime']").val();
		var clientNo = $(obj).parent().parent().find("input[id='ClientNo']").val();
		var branchNo = $(obj).parent().parent().find("input[id='BranchNo']").val();
		$('#benciamt').val(deductionAmt);
		$('#kehuhao').val(clientNo);
		$('#hangshe').val(branchNo);
		document.getElementById("deduction").value = deductionAmt;
		document.getElementById("jane").value = formatNum(deductionAmt,2);
		var transAmt = document.forms[0].TransAmt.value;	
		var transAmtdeduction = transAmt-deductionAmt;
		document.getElementById("jane5").innerHTML=formatNum(transAmtdeduction,2);
		document.getElementById("janemoney").innerHTML=formatNum(deductionAmt,2);				
		if(!$('#checkbox').is(':checked'))
			$('#checkbox').prop("checked",true);
		$("#span_viewamt2").html("￥" +formatNum(transAmtdeduction,2));												
	}	 
}
function getCookie(sName) {
	var sRE = "(?:;)?" + sName + "=([^;]*);?";
	var oRE = new RegExp(sRE);
	if (oRE.test(document.cookie)) {
		return decodeURIComponent(RegExp["$1"]);
	} else {
		return null;
	}
}
function setCookie(sName, sValue, oExpires, sPath, sDomain, bSecure) {
	var sCookie = sName + "=" + encodeURIComponent(sValue);
	if (oExpires) {
		sCookie += "; expires=" + oExpires.toGMTString();
	}
	if (sPath) {
		sCookie += "; path=" + sPath;
	}
	if (sDomain) {
		sCookie += "; domain=" + sDomain;
	}
	if (bSecure) {
		sCookie += "; secure";
	}
	document.cookie = sCookie;

}
function gobackhistory() {
	if(navigator.userAgent.indexOf("MSIE")>0){
		if(navigator.userAgent.indexOf("MSIE6.0")>0){
			window.opener=null;
			window.close();
		}else{
			window.open('', '_top');
			window.top.close();
		}
	}else if(navigator.userAgent.indexOf("Firefox")>0){
		window.location.href='about:blank';
		window.close();
	}else{
		window.opener=null;
		window.open('', '_self','')
		window.close();
	} 
	
}
