<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付冻结</title>
<script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
<script language="javascript" type="text/javascript" src="js/writeObject.js"></script>
<script src="js/style.js"></script>
	<script>
	var checkcode="no";
    function doIt() {
    	if (document.getElementById("getSMS").disabled == false) {
			$("#doNext").attr("disabled", "disabled");
			$('#MessageCodeInfo').text(" *请获取短信验证码");
			return;
		}
		if (true == checkLength("SmsCode", 0)) {
			$('#MessageCodeInfo').text("短信验证码为空");
			return;
		}

      	if((document.getElementById('state').value)=="0"){
      		/* if(checkLength("_vTokenName",0)){
      			$("#SMSinfo").text("附加码为空，请输入");
      			return;
      		}  */
     	if(checkcode!="yes"){
         $("#doNext").attr("disabled","disabled");
         return;
    	}
      	} 
        document.forms[0].submit();
    }
    
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

	    function checkCode(){
	        if(document.getElementById("getSMS").disabled==false){
	        $("#doNext").attr("disabled","disabled");
	       	 $('#MessageCodeInfo').text(" *请获取短信验证码");
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
	 var InterValObj; //timer变量,控制时间
     var count = 120; //间隔函数,1秒执行
     var curCount; //当前剩余秒数
     function getSMSFunction(){
    	 
    	 $("#SMSinfo").text(" ");
    	 $('#MessageCodeInfo').text("");
    	 $("#doNext").attr("disabled",false);
    	 
    	 curCount=count;
    	 
    	 $("#getSMS").attr("disabled","disabled");
    		$("#getSMS")
        	.css("background-image", "url(images/images/fanhui.png)").css(
        			"color", "#004595");
    		
    	 document.getElementById("getSMS").value=curCount+"秒";
    	 
    	 var PayerPhoneNo = "${PayerPhoneNo}";
    	 
         var acctNo = document.forms[0].PayerAcctNbr.value;
    	 var oparams = new Array(
        		  new Pair("PayerPhoneNo",PayerPhoneNo),
        		  new Pair("PayerAcctDeptNbr", "999000"),
                  new Pair("PayerAcctNbr", acctNo),
                  new Pair("OperateType", "0"),
                  new Pair("TransTypCd", "UPP10005")
                  
                 
         );
    	 
         postData2SRVWithCallback("SMS.do",PEGetPostData(oparams),function(success, message){
        	 if(!success){
        		 短信验证码服务器断开
  				 $("#SMSbutton").removeAttr("disabled");//启用按钮
  				$("#SMSbutton").css("background-image","url(images/images/xiayi.png)");
  				document.getElementById("SMSbutton").value="重新获取";

  				$('#MessageCodeInfo').text("短信发送失败");
  			}
  			else {
  			
  				var obj = eval('('+message+')');
  				if(obj.SmsSqenbr != null){
					$("#SmsSqenbr").val(obj.SmsSqenbr);
				}
  				if("PAY0100"==obj.RespCode){
  					curCount = 30;
  					$("#MessageCodeInfo").text(obj.RespMessage);
  					document.getElementById("getSMS").value=curCount+"秒";
  					InterValObj=window.setInterval(SetRemainTime,1000);
  				} else {
  					document.getElementById("getSMS").value=curCount+"秒";
  					InterValObj=window.setInterval(SetRemainTime,1000);
  				}
  			}
  	 	});
     }
     function SetRemainTime() {
         if(curCount == 0){
             window.clearInterval(InterValObj);//停止计时器
             $("#getSMS").removeAttr("disabled");
         	$("#getSMS").css("background-image",
			"url(images/images/xiayi.png)");
             document.getElementById("getSMS").value="重新获取";
         } else {
             curCount--;
             document.getElementById("getSMS").value=curCount+"秒";
             document.getElementById("SmsCodeBack").value = curCount;
         }
     }
 	function hiddenSomePhoneNo(){
		var phone = document.forms[0].PayerPhoneNo.value;
		var heddenPhone = phone.substring(0,3)+"****"+phone.substring(phone.length-4,phone.length)
		document.getElementById("PhoneSpan").innerHTML=heddenPhone;
		}
   function onload(){  
	   LoadJs();
	   hiddenSomePhoneNo();
	   }
   function Cancel(){
       window.location.href="FS04.do";
  		}
   function LoadJs() {
   	if((document.getElementById('state').value)=="0"){
   		reloadTokenImg();
   	 document.getElementById('tableIMG').style.display="block";
   	/*  $("#doNext").attr("disabled",true); */
   	}
   }
	$(function(){
		var smsCodeBackv = document.getElementById("SmsCodeBack").value;
		if(smsCodeBackv!=null&&parseInt(smsCodeBackv)<=120&&parseInt(smsCodeBackv)>0){
			document.getElementById("getSMS").value = smsCodeBackv + "秒";
			$("#getSMS")
			.css("background-image", "url(images/images/fanhui.png)").css("color", "#004595");
			curCount = parseInt(smsCodeBackv);
			document.getElementById("getSMS").value = curCount
			+ "秒";
			InterValObj = window.setInterval(SetRemainTime,
				1000);
		}
	});
	</script>
	<style>
	 #tableIMG{ width: 570px; margin:auto; margin-left: 32%}	
	</style>
</head>
<body  onload="onload();">
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
        <form id="form1" name="form1" method="post" action="FS04Confirm.do"  >  
           <pe:hidden fieldList="SignNbr,SignTypCd,ChannelNbr,PayTypCd,PayerCardPwd" skipNULL="true"/> 
           <input id="TransTypCd" name="TransTypCd" type="hidden" value="UPP10005"/>
           <Input name="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}"></Input>
           <Input name="PayerAcctNbr" type="hidden" value="${PayerAcctNbr}"></Input>
           <input type="hidden" name="state" id="state" value="${state}"/>
           <div class="con_r">
             <div class="con_r_main">
                       <div class="dingw">您可以在此冻结丰收e支付服务</div>
                       
                        <div class="sr_main">
                        <table width="620" border="0" 
								style="margin: auto; margin-top: 20px">
								
								<tr style="height:50px";>
									<td colspan="2">
										<div class="tis" style="margin-left: 14%; width: 65%;">
											请输入您手机号码（<span id="PhoneSpan"></span>）接收到的短信验证码
										</div>
									</td>
								</tr>
								<tr style="height:50px" ;>
									<td class="right" style="width: 33%; ">短信验证码：</td>
									<td width="67%"><input autocomplete="off" id="SmsCode"
										style="width: 100px" name="SmsCode" type="text" value=""
										maxlength="6"  /> <input id="getSMS"
										class="chongx" onclick="getSMSFunction()" value="获取验证码"
										type="button"
										style="background-image: url(images/images/xiayi.png); width: 124px; height: 43px; border: none; color: #fff; font-size: 16px; font-weight: bold;" />
										<span id="MessageCodeInfo" class="cheng">*</span>
										<input type="hidden" id="SmsSqenbr"  name="SmsSqenbr" value="${SmsSqenbr}" />
										<input type="hidden" id="SmsCodeBack"  name="SmsCodeBack" value="${SmsCodeBack}" /></td>
								</tr>
							</table>
						<div style="display:none;margin: auto;" id="tableIMG" >
						<table>
                            <tr>
                                <td  class="right" style="width: 179px;height: 50px">附&nbsp;&nbsp;加&nbsp;&nbsp;码&nbsp;&nbsp;：</td>
                                <td  ><input autocomplete="off"  style="width:90px" id="_vTokenName" name="_vTokenName" onkeyup="checkTokenImgOnKeyUp()" onblur="checkTokenImg()" type="text" value="" size="6" maxlength="4" class="_vTokenName"/>
                                    <img id="rightImage" style="display: none; position:relative;top:10px;"/>
                                    <img id="_tokenImg" onclick="reloadTokenImg()" src=""  style=" position:relative;top:10px;"/>
                                    <a style="font-size: 12px" onclick="reloadTokenImg()">看不清楚，换一张</a>
                                    </td>
                            </tr>
                        </table>
                        </div>
          	            <div id="QQQ" > <span  id="SMSinfo" class="cheng"  style="margin-left: 41%;" ></span>	
              		    </div></div>        
                        <div class="btn_big">
                               <input type="button"  value="取消" class="xia_btn" id="doitbtn" onclick="Cancel();"/>
                              <input type="button"  value="下一步" class="xia_btn"  id="doNext"  onclick="doIt();"/>
                        </div>
                        
                        <div class="wxts">
                             <div class="tub"></div>
                              <div class="wxts_main">
     
                              </div>
                        </div>
             </div>

           </div>
           </form>
      </div>
    
      <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
   </div>
 
 <div class="fuc hide" id="fuc">
       <div class="fuc_main">
            <div class="fuc_main_b"> 浙江农村信用社银行"卡密支付"服务协议 <a href="###" id="close-box"><img src="images/cha.png"></a></div>

       </div>
 </div>
</body>
</html>












