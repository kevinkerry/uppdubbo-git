<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付冻结</title>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script src="js/jquery-1.10.1.min.js"></script>	
<script src="js/slides.min.jquery.js"></script>
<script src="js/style.js"></script>
<script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
<script language="javascript" type="text/javascript" src="js/foison/writeObject.js"></script>

 <script>
 //获取服务器时间戳
  function checkLength(nodeName, length) {
        var node = document.getElementById(nodeName).value;
        if (length != node.length) {
            return false;
        }
        return true;
    }
    
    function checkTokenImgOnKeyUp() {
        var userInputToken = document.getElementById("_vTokenName").value;
        if(userInputToken.length == 0) {
            document.getElementById("rightImage").style.display="none";
        }else if(userInputToken.length == 4) {
            checkTokenImg();
        }
    }
    
    
    	//检验证件号位数
		function checkPayerIdNbr() {
    	
			var PayerIdTypCd = $("#PayerIdTypCd").val();
			if (PayerIdTypCd == "101"||PayerIdTypCd == "01") {
				var length = document.getElementById("PayerIdNbr").value.length;
				if (length <18) {
					document.getElementById("PayerIdNbrInfo").innerHTML = "证件号位数不足";
				} else if (length>18) {
					document.getElementById("PayerIdNbrInfo").innerHTML = "证件号位数错误";
				} else {
					document.getElementById("PayerIdNbrInfo").innerHTML = "*";
				}
			}
		}

		function clearNo() {
			document.getElementById("PayerIdNbr").value = "";
			document.getElementById("PayerIdNbrInfo").innerHTML = "*";
		}

		function checkTokenImg() {
			var userInputToken = document.getElementById("_vTokenName").value;
			if (userInputToken.length == 0) {
				document.getElementById("rightImage").style.display = "none";
				checkcode="no";
				return;
			}
			var oparams = new Array(new Pair("_vTokenName", userInputToken));
			postData2SRVWithCallback(
					"ImageTokenVerify.do",
					PEGetPostData(oparams),
					function(flag, answer) {
						if (flag && !answer) {
							document.getElementById("rightImage").src = "images/lug.jpg";
							document.getElementById("rightImage").style.display = "inline";
							$("#doNext").attr("disabled", false);
						} else {
							document.getElementById("rightImage").src = "images/hongch.jpg";
							document.getElementById("rightImage").style.display = "inline";
							$("#doNext").attr("disabled", true);
						}
					});
		}

		var ts = "<%=System.currentTimeMillis()%>";
 function doIt(obj){
	 if(true == checkLength("PayerIdNbr",0)) {
         $("#PayerIdNbrInfo").text("证件号位数不足");
         return ;
     }else if(false == checkLength("_vTokenName",4)) {
		
         return;
     }else if("*"!=$("#PayerIdNbrInfo").text()){
    	 return;
     }
	 var length = document.getElementById("PayerIdNbr").value.length;
	 var PayerIdTypCd = $("#PayerIdTypCd").val();
	 if((PayerIdTypCd=="101"&&length==18)||(length==18&&PayerIdTypCd=="01")||(length!=0)){
		//获取密码密文
	     var PayerCardPwd =getIBSPayerCardPwd("powerpass", ts ,"PayerCardPwdInfo", "");
	     if(null == PayerCardPwd){
	         return;
	     }
	     document.forms[0].PayerCardPwd.value = PayerCardPwd;
	     document.forms[0].submit();
	 }
 }
 function Cancel(){
     window.location.href="FS04.do";
		}
 </script>
</head>
<body onload="LoadJs();">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/csii1.png"></div>
           <div class="top_r">
                <div class="phone"><img src="images/phone.png"><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1"></div><div class="tuic"></div></div>
           </div>
      </div>
      <div class="main-nav">
        <ul class="main-nav-in"> 
  <li><a href="/paygate/FS01.do">注册</a></li>
            <li><a href="/paygate/FS02.do">查询交易明细</a></li>
              <li><a href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
         </ul> 
      
      </div>
      <div class="content">     
           <div class="con_r">             
             <div class="con_r_main">
                       <div class="dingw">您可以在此冻结丰收e支付服务</div>
                      
                        <div class="sr_main">
                       <form id="form1" name="form1" method="post" action="FS04Input.do" >
                               <pe:hidden fieldList="AcctType,CifNo,DeptId,PayerPhoneNo,PayerCardTypCd,PayerAcctNbr" skipNULL="true"/> 
                              <table width="650" border="0" style="margin:20px auto;" >
                            <tr>
                                  <td width="30%" class="right" style="letter-spacing: 0.1px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="50%">${PayerAcctNbr}</td>
                                  <td width="20%"></td>
                                </tr>
                                <tr>
                                  <td width="30%" class="right">手机号码：</td>
                                  <td width="50%">${PayerPhoneNo}</td>
                                  <td width="20%"></td>
                                </tr>
                                <tr>
                                    <c:if test="${PayerCardTypCd=='1'}">
                                        <td width="30%" class="right" style="letter-spacing: 0.1px;">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                                    </c:if>
                                    <c:if test="${PayerCardTypCd=='2'}">
                                        <td width="30%" class="right">查询密码：</td>
                                    </c:if>
                                  <td width="50%">
                                   <input type="hidden" name="PayerCardPwd" value="" />
                                    <script type="text/javascript">writePassObject("powerpass","right",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>     
                               <span id="PayerCardPwdInfo" class="cheng">*</span>
                                </td> 
                                <td width="20%"></td>
                                </tr>                    
                         <tr>
                            <td class="right" width="30%">证件种类：</td>
                            <c:if test ="${PayerCardTypCd=='1'}">
                                <td width="50%">
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
                                  </td>
                                  <td width="20%"></td>
                                </c:if>
                            <c:if test="${PayerCardTypCd=='2'}">
                            <td  width="50%">
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
                                </c:if>
                                   <td width="20%"></td>
                        </tr>
                                
                              <tr>
                                 <td width="30%" class="right">证件号码：</td>
                                <td width="60%"><input autocomplete="off"  style="width:220px; height:20px;" onblur="checkPayerIdNbr();" name="PayerIdNbr" id="PayerIdNbr" onkeydown="if(event.keyCode==13){return false;}"/>
                                <span id="PayerIdNbrInfo" class="cheng">*</span>
                                 </td>
                                  <td width="10%"></td>                          
                                </tr>
                                <tr>
                                   <td  width="30%" class="right" style="letter-spacing: 3.76px">附加码：</td>
                                   <td width="50%">
                                   <input onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" autocomplete="off" style="width:90px; height:20px" id="_vTokenName" name="_vTokenName" type="text"  maxlength="4" /> 
                                   <img id="rightImage" style="display: inline; position: relative; top: 10px;" />
                                   <img title="点击刷新附加码" style="margin-bottom:-7px; height:24px"   id="_tokenImg" onclick="reloadTokenImg()" src=""/>
                                   <a id="change" style="font-size:12px" onclick="reloadTokenImg()">看不清楚，换一张</a>
                            	   </td>                                
                                   <td width="20%"><div id="QQQ">
								<span id="SMSinfo" class="cheng" style="margin-left: 41%;"></span>
							</div></td>                              
                                   </tr>                             
                              </table>  	   
               </div>
                        <div class="btn_big">
                             <input type="button"  value="取消" class="xia_btn"  onclick="Cancel();"/>
                              <input type="button"  value="提交" disabled="disabled" class="xia_btn" id="doNext" onclick="doIt(this);"/>
                        </div>
                        </form>
                        <c:if test="${AcctType=='D'}">
                             <!--  <div class="wxts">
                              <div class="tub"><img src="images/tishi.png" /></div>
                              <div class="wxts_main">
                                   <h3>温馨提示</h3>
                                    <p></p>
                                    <p></p>
                              </div>
                              </div> -->
                         </c:if>
                         <c:if test="${AcctType=='C'}">
                               <div class="wxts">
                              <div class="tub"><img src="images/tishi.png" /></div>
                              <div class="wxts_main">
                                   <h3>温馨提示</h3>
                                    <p>查询密码是您登录电话银行进行账户信息查询时所需要输入的密码</p>
                                    <p>若您忘记或未设置过查询密码，可使用预留电话致电浙江农信客服热线4008896596重新设置</p>
                              </div>
                              </div>
                          </c:if>
             </div>
                 

           </div>
      </div>
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
 <script type="text/javascript">
 	$(function(){
		$('#open-box').click(function(){
			$('#fuc').removeClass('hide');
			});
		$('#close-box').click(function(){
			$('#fuc').addClass('hide');
		});
		});
 </script>
 <div class="fuc hide" id="fuc">
       <div class="fuc_main">
            <div class="fuc_main_b"> 浙江农村信用社银行"卡密支付"服务协议 <a href="###" id="close-box"><img src="images/cha.png"></a></div>

       </div>
 </div>
</body>
</html>












