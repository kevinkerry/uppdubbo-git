<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 丰收e支付注册并支付页面 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/global.css" type="text/css" />
<title>浙江农信丰收e支付注册并支付</title>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script language="javascript" type="text/javascript"src="js/slides.min.jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/foison/writeObject.js"></script>
<script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
<script>
var checkcode="no";
//获取服务器时间戳
var ts="<%=System.currentTimeMillis()%>";

function doIt(obj){
	if("格式错误"==tellerNoInfo){
		$('#tellerNoInfo').text("");
     	document.forms[0].TellerNo.value ="";
     }
	var PayerIdTypCd = $("#PayerIdTypCd").val();
	if(true == checkLength("payerIdNbr",0)) {
		$("#payerIdNbrInfo").text("证件号位数不足");
		 return ;
    }
	
    
    if((PayerIdTypCd!="101")||(true==checkLength("payerIdNbr", 18)&&PayerIdTypCd=="101")){
    	//获取密码密文
        var PayerCardPwd =getIBSPayerCardPwd("powerpass", ts ,"PayerCardPwdMessage", "");
        if(null == PayerCardPwd){
        	//$("#PayerCardPwdMessage").text("密码为空，请输入");
        	return;
        }else{
        	$("#PayerCardPwdMessage").text("*");
        }
        
        
        if(!(curCount>0&&curCount<120)){
        	$("#smscodeinfo").text("请获取短信");
        	return;	
        }else{
        	$("#smscodeinfo").text("*");
        }
        if(true == checkLength("SmsCode",0)){
    		$("#smscodeinfo").text("短信验证码不足");
    		return ;
    	}else{
    		$("#smscodeinfo").text("*");
    	}
        if((document.getElementById('state').value)=="0"){
      		if(checkLength("_vTokenName",0)){
      			//$("#SMSinfo").text("附加码为空，请输入");
      			return;
      		}
     	if(checkcode!="yes"){
         $("#doNext").attr("disabled","disabled");
         return;
    		}
      	}
        
        document.forms[0].PayerCardPwd.value = PayerCardPwd;
        document.forms[0].action="fsRegisterAndEpayConfirm.do";
        document.forms[0].submit();
    }
}

function reloadTokenImg() {
    document.getElementById('_tokenImg').src="GenTokenImg.do"+"?random="+Math.random();
}

function checkLength(nodeName, length) {
    var node = document.getElementById(nodeName).value;
    if (length != node.length) {
        return false;
    }
    return true;
}

//检验证件号位数
function checkPaperNo() {
	var PayerIdTypCd = $("#PayerIdTypCd").val();
	if (PayerIdTypCd == "101") {
		var length = document.getElementById("payerIdNbr").value.length;
		if (length <18) {
			document.getElementById("payerIdNbrInfo").innerHTML = "证件号位数不足";
		} else if (length>18) {
			document.getElementById("payerIdNbrInfo").innerHTML = "证件号位数错误";
		} else {
			document.getElementById("payerIdNbrInfo").innerHTML = "*";
		}
	}
}
function checkTellerNo() {
	var tellerNo = $("#TellerNo").val();
	if(tellerNo.length == 0) {
		$('#tellerNoInfo').text("");
		
		return true;
	}
	var reg = /^[0-9]{7}$/;
	 if(!reg.test(tellerNo)){
		 $('#tellerNoInfo').text("格式错误");
	 }else{
		 $('#tellerNoInfo').text("");
	 }
}
function clearNo() {
	document.getElementById("payerIdNbr").value = "";
	document.getElementById("payerIdNbrInfo").innerHTML = "*";
}
//短信验证码
	var InterValObj; //timer变量,控制时间
	var count = 120; //间隔函数,1秒执行
	var curCount; //当前剩余秒数
	function getSMS(){
		curCount=count;
		$("#SMSbutton").attr("disabled","disabled");//禁用按钮
		$("#SMSbutton").css("background-image","url(images/images/fanhui.png)").css("color","#004595");
		document.getElementById("SMSbutton").value=curCount+"秒";
		var phone = document.forms[0].PayerPhoneNo.value;
		var PayerAcctNbr = document.forms[0].PayerAcctNbr.value;
		var payerAcctDeptNbr = document.forms[0].PayerAcctDeptNbr.value;
		var transAmt = document.forms[0].realAmt.value;
		var oparams = new Array(
				new Pair("PayerPhoneNo", phone),
				new Pair("PayerAcctDeptNbr", payerAcctDeptNbr),
				new Pair("PayerAcctNbr", PayerAcctNbr),
				new Pair("TransAmt",transAmt),
				new Pair("OperateType", "0"),
				new Pair("TransTypCd","RegisterAndEpay")
		);
		postData2SRVWithCallback("SMS.do",PEGetPostData(oparams),function(success, message){
	 		if(!success){}else{
					var obj = eval('(' + message + ')');
					if(obj.SmsSqenbr != null){
						$("#SmsSqenbr").val(obj.SmsSqenbr);
					}
	 		}
	 	});
	 	//每秒调用
	 	InterValObj=window.setInterval(SetRemainTime,1000);
	}
	function SetRemainTime() {
		if(curCount == 0){
			window.clearInterval(InterValObj);//停止计时器
			$("#SMSbutton").removeAttr("disabled");//启用按钮
			$("#SMSbutton").css("background-image","url(images/images/xiayi.png)");
			document.getElementById("SMSbutton").value="重新获取";
		} else {
			curCount--;
			document.getElementById("SMSbutton").value=curCount+"秒";
			$("#SMSbutton").css("color","#004595");
			document.getElementById("SmsCodeBack").value = curCount;
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
                 document.getElementById("rightImage").src="images/hongch.jpg";
                 document.getElementById("rightImage").style.display="inline";
                 $("#doNext").attr("disabled",true);
             }
         });
     }
	 function checkTokenImgOnKeyUp() {
         var userInputToken = document.getElementById("_vTokenName").value;
         if(userInputToken.length == 0) {
             document.getElementById("rightImage").style.display="none";
         }else if(userInputToken.length == 4) {
             checkTokenImg();
         }
     }

	 function LoadJs1() {
     	
     	if((document.getElementById('state').value)=="0"){
     		reloadTokenImg();
     		$("#tableIMG").removeAttr("style");
     	// document.getElementById('tableIMG').style.display="block";
     //	 $("#doNext").attr("disabled",true);
     	}
     }
	 $(function(){
		 var smsCodeBackv = document.getElementById("SmsCodeBack").value;
		 if(smsCodeBackv!=null&&parseInt(smsCodeBackv)<=120&&parseInt(smsCodeBackv)>0){
		 	document.getElementById("SMSbutton").value = smsCodeBackv + "秒";
		 	$("#SMSbutton")
		 	.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
		 	curCount = parseInt(smsCodeBackv);
		 	document.getElementById("SMSbutton").value = curCount
		 	+ "秒";
		 	InterValObj = window.setInterval(SetRemainTime,
		 		1000);
		 }
		 }
		 );

</script>
</head>

<body onload="LoadJs1()" oncontextmenu="return false"
	ondragstart="return false">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/csii1.png" />
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/images/phone.png" /><span class="net"> <a
						href="http://www.csii.com/">www.csii.com </a></span>
				</div>
				<div class="time">
					<div class="time1"></div>${MerDate }
				</div>
			</div>
		</div>
		<div class="content">
			<form action="#" method="post"><input type="hidden" name="state" id="state" value="${state}"/>
				<input id="timeStampToken"  name="timeStampToken" value="${timeStampToken }" type="hidden" />
				<pe:hidden
					fieldList="realAmt,PayerAcctName,PayerAcctDeptNbr,PayerAcctNbr,PayerPhoneNo,PayerCardTypCd,PlainContext,Plain,OrderId,MerDate,MerchantName,Signature,CustCifNbr,TransAmt"
					skipNULL="false" />
				<div class="con_l">
					<div class="con_l_t">
						<img src="images/images/wddd.png" style="margin-top: -2px;" />
					</div>
					<div class="con_l_m">
						<table width="280" align="center" border="0" cellspacing="0"
							cellpadding="0">
							<tr>
								<td class="right hui">商 户：</td>
								<td class="blue">${MerchantName }</td>
							</tr>
							<tr>
								<td class="right hui">金 额：</td>
								<td class="cheng">￥${realAmt }</td>
							</tr>
							<tr>
								<td class="right hui">日 期：</td>
								<td>${MerDate }</td>
							</tr>
							<tr>
								<td class="right hui">订单号：</td>
								<td class="blue">${OrderId }</td>
							</tr>
							<tr>
								<td class="right hui">币 种：</td>
								<td>人民币</td>
							</tr>
						</table>

					</div>

					<!--图片轮播开始-->
					<div id="container">
						<div id="example"></div>
					</div>
					<!--图片轮播结束-->
				</div>
     

				<div class="con_r">
					<div class="con_r_t">丰收e支付</div>
					<div class="con_r_main">
						<div class="dingw2 dingw">您的位置：丰收e支付 >注册并支付</div>
						<div class="buz2a buz">
							<div class="buz1">1.填写信息</div>
							<div class="white buz2">2.注册并支付</div>
							<div class="buz3">3.支付成功</div>
						</div>
                     <div class="sr_main" style="line-height: 48px;">

                

                    <table width="500" border="0" style="margin:0px auto;">
                        <tr>
                            <td class="right" style="width: 20%">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                            <td width="295">${PayerAcctNbr}</td>
                        </tr>

                        <tr>
                            <td class="right" style="width: 20%">手机号码：</td>
                            <td>${PayerPhoneNo}</td>
                        </tr>
                      
                        <tr>
                            <td class="right" style="width: 20%">证件种类：</td>
                            <td>
                                <select name="PayerIdTypCd" id="PayerIdTypCd" onchange="clearNo()">
                                   <option value="101">居民身份证</option>
                                    <option value="102">户口簿</option>
                                    <option value="103">护照</option>
                                    <option value="104">军官证</option>
                                    <option value="105">士兵证</option>
                                    <option value="106">警官证</option>
                                    <option value="111">港澳来往内地通行证</option>
                                    <option value="112">台湾来往内地通行证</option>
                                    <option value="113">外国人居留证</option>
                                    <option value="120">社会保障证</option>
                                    <option value="121">个人纳税证</option>
                                    <option value="199">个人其它证件</option>
                                </select>
                                <span class="cheng"> *</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="right" style="width: 20%">证件号码：</td>
                            <td><input autocomplete="off"  type="text"  onblur="checkPaperNo()" name="payerIdNbr" id="payerIdNbr" style="width: auto;height: 26px;"/>
                                <span id="payerIdNbrInfo" class="cheng"> *</span></td>
                        </tr>

                        <tr>
                            <td class="right" style="width: 20%">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                            <input type="hidden" name="PayerCardPwd" value="" />
                            <td>
                                <script type="text/javascript">writePassObject("powerpass","right",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>
                                <span id="PayerCardPwdMessage" name="PayerCardPwdMessage" class="cheng">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="right" style="width: 20%">柜员号码：</td>
                            <td><input autocomplete="off"  type="text" onblur="checkTellerNo()" placeholder="邀请人柜员号，可不填"  name="TellerNo" id="TellerNo" style="width: auto;height: 26px;"/>
                                <span id="tellerNoInfo" name="tellerNoInfo" class="cheng">  </span></td>
                        </tr>

                                <tr>
                                  <td class="right" style="width: 20%">短信验证码： </td>
                                  <td><input name="SmsCode" id="SmsCode" type="text" class="small_sr" style="height: 26px;"/> 
                                   <input id="SMSbutton" class="chongx" type="button"  onclick="getSMS();" value="点击获取" style="background-image:url(images/images/xiayi.png);width:124px;height:43px;border:none;color:#fff;font-size:16px;font-weight:bold;" />
                                  <span class="cheng" id="smscodeinfo">*</span>
                                  	<input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
                                  	<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" /></td>
                                </tr>
                                <tr style="display:none" id="tableIMG">
                                <td  class="right" style="width: 20%">附&nbsp;&nbsp;加&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
                                <td  ><input autocomplete="off"  style="width:90px" id="_vTokenName" name="_vTokenName" onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" type="text" value="" size="6" maxlength="4" class="_vTokenName"/>
                                    <img id="rightImage" style="display: none; position:relative;top:10px;"/>
                                    <img id="_tokenImg" onclick="reloadTokenImg()" src="" style="position:relative;top:10px;"/>
                                    <a style="font-size: 12px" onclick="reloadTokenImg()">看不清楚，换一张</a>
                                    </td>
                            </tr>
                                 
                    </table>
                     
                </div>
                <div  >
						<table>
                            
                        </table>
                        </div>
                <div class="btn_big">
                              <input type="button"    value="下一步" onclick="doIt();" class="xia_btn" style="background-image:url(images/images/xiayi.png);"/>
                             <br></br>
                        </div>
                
</div>
				</div>
			</form>
		</div>
		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	</div>

</body>
</html>
