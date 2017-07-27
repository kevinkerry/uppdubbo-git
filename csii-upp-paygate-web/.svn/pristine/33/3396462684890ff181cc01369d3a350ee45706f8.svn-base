<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="css/foison/css.css" rel="stylesheet" type="text/css"/>
    <link href="css/foison/global.css" rel="stylesheet" type="text/css"/>
    <title>丰收e支付注册</title>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/foison/writeObject.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script>
        //获取服务器时间戳
        var ts="<%=System.currentTimeMillis()%>";
        
        function doIt(obj){
            var CardValidM = $("#CardValidM").val();
            var CardValidY = $("#CardValidY").val();
            var tellerNoInfo=$("#tellerNoInfo").html();
            if(CardValidM == "" || CardValidY == "" ){
            	document.getElementById("CardValid").innerHTML = "有效期不能为空";
            	return;
            }else{
            	document.getElementById("CardValid").innerHTML = "*";
            }
            if("格式错误"==tellerNoInfo){
       		$('#tellerNoInfo').text("");
            	document.forms[0].TellerNo.value ="";
            }
        	var paperType = $("#PayerIdTypCd").val();
            if(true == checkLength("PayerIdNbr",0)) {
            	$("#paperNoInfo").text("证件号位数不足");
                return ;
            }else if((paperType!="01")||(true==checkLength("PayerIdNbr", 18)&&paperType=="01")){
            	//获取密码密文
                var password =getIBSPayerCardPwd("powerpass", ts ,"PasswordMessage", "");
                if(null == password){
                    return;
                }else{
                	$("#PasswordMessage").text("*");
                }
                document.forms[0].PayerCardPwd.value = password;
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

      //检验证件号位数
		function checkPaperNo() {
			var PayerIdTyCd = $("#PayerIdTypCd").val();
			if (PayerIdTyCd == "01") {
				var length = document.getElementById("PayerIdNbr").value.length;
				if (length <18) {
					document.getElementById("paperNoInfo").innerHTML = "证件号位数不足";
				} else if (length>18) {
					document.getElementById("paperNoInfo").innerHTML = "证件号位数错误";
				} else {
					document.getElementById("paperNoInfo").innerHTML = "*";
				}
			}
		}

		function clearNo() {
			document.getElementById("PayerIdNbr").value = "";
			document.getElementById("paperNoInfo").innerHTML = "*";
		}

        function LoadJs() {

        }
        
        function gobackhistory(){
        	 window.location.href="FS01.do";
        	
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
    </script>
</head>

<body onload="LoadJs()">
<div class="main">
    <div class="top">
        <div class="logo"><img src="images/foison/csii1.png"></div>
        <div class="top_r">
            <div class="phone"><img src="images/foison/phone.png"><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span>
            </div>
        </div>
    </div>
    <div class="main-nav">
        <ul class="main-nav-in">
           <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS01.do">注册</a></li>
            <li><a href="/paygate/FS02.do">查询交易明细</a></li>
            <li><a href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
        </ul>
    </div>
    <div class="content">
        <form id="form1" name="form1" method="post" action="FS01RegisterInput.do">
        <input type="hidden" name="state" id="state" value="${state}"/>
            <div class="con_r_main">
                <div class="dingw">您可以在此注册丰收e支付服务</div>
                <div class="sr_main" style="margin-top: 20px">

                     <pe:hidden fieldList="PayerAcctNbr,PayerPhoneNo,PayerCardTypCd,PayerAcctDeptNbr,CustCifNbr" skipNULL="true"/>

                    <table width="500" border="0" style="margin:20px auto;">
                        <tr>
                            <td class="right" style="letter-spacing: 0.1px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                            <td width="295">${PayerAcctNbr}</td>
                        </tr>
                        <tr>
                            <td class="right">手机号码：</td>
                            <td>${PayerPhoneNo}</td>
                        </tr>
                       <!--<tr>
                            <td class="right">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
                            <td><input autocomplete="off"  type="text" name="UserName" id="UserName" style="width: 60%;"/>
                                <span class="cheng"> *</span></td>
                        </tr>
                         -->
                        <tr>
                            <td class="right">证件种类：</td>
                            <td>
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
                                <span class="cheng"> *</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="right">证件号码：</td>
                            <td><input autocomplete="off"  type="text"  onblur="checkPaperNo()" name="PayerIdNbr" id="PayerIdNbr" style="width: auto; height: 20px"/>
                                <span id="paperNoInfo" class="cheng"> *</span></td>
                        </tr>

                        <tr>
                            <td class="right">查询密码：</td>
                            <input type="hidden" name="PayerCardPwd" value="" />
                            <td>
                                <script type="text/javascript">writePassObject("powerpass","right",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>
                                <span id="PasswordMessage" name="PasswordMessage" class="cheng">*</span>
                            </td>
                        </tr>
                        <tr>
                             <td class="right">有&ensp;效&ensp;期：</td>
                             <td>
                                 <input autocomplete="off" id="CardValidM" type="text" name="CardValidM" size="3" style="width: auto;height: 20px "
                                        maxlength="2" value="" onkeypress="return isNum(event)"/>月
                                 <input autocomplete="off" id="CardValidY" type="text" name="CardValidY" size="3" style="width: auto; height: 20px"
                                        maxlength="2" value="" onkeypress="return isNum(event)"/>年
                                 <span id="CardValid" class="cheng">*</span>                                 
                             </td>
                          </tr>
                          <tr>
                            <td class="right">柜员号码：</td>
                            <td><input autocomplete="off"  type="text"  onblur="checkTellerNo()"  placeholder="邀请人柜员号，可不填"  name="TellerNo" id="TellerNo" style="width: auto;height: 24px;"/>
                                <span id="tellerNoInfo" class="cheng"> </span></td>
                        </tr> 
                    </table>
                </div>
                <div class="btn_big" style="margin-left: 312px;">
                    <input type="button" value="上一步" class="xia_btn" onclick="gobackhistory();" />
                    <input id="Next" type="button" value="下一步" class="xia_btn" onclick="doIt(this)" />
                </div>
                <div class="wxts">
                    <div class="tub"><img src="images/tishi.png"/></div>
                    <div class="wxts_main">
                        <h3>温馨提示</h3>

                        <p>查询密码是您登录电话银行进行账户信息查询时所需要输入的密码</p>
                        <p>若您忘记或未设置过查询密码，可使用预留电话致电浙江农信客服热线4008896596重新设置</p>

                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
</div>
</body>
</html>









