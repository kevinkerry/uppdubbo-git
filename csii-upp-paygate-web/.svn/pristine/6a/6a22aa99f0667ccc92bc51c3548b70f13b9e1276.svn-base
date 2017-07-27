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
<title>丰收e支付解冻</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="js/style.js"></script>
<script language="javascript" type="text/javascript" src="script.do"></script>
<script>
	var checkcode="no";
	function doIt() {
		if (checkcode != "yes") {
			$("#doitbtn").attr("disabled", "disabled");
			return;
		}
		if ("*" == $('#PayerAcctNbrInfo').text()
				&& "*" == $('#PayerPhoneNoInfo').text()) {
			document.forms[0].submit();
		}
	}
	function load()
	{
		reloadTokenImg();
	}
	function checkTokenImgOnKeyUp() {
		var userInputToken = document.getElementById("_vTokenName").value;
		if (userInputToken.length == 0) {
			document.getElementById("rightImage").style.display = "none";
		} else if (userInputToken.length == 4) {
			checkTokenImg();
		}
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
						checkcode = "yes";
						document.getElementById("rightImage").src = "images/lug.jpg";
						document.getElementById("rightImage").style.display = "inline";
						$("#doitbtn").attr("disabled", false);
					} else {
						document.getElementById("rightImage").src = "images/hongch.jpg";
						document.getElementById("rightImage").style.display = "inline";
						$("#doitbtn").attr("disabled", true);
					}
				});
	}
	function reloadTokenImg(clickObj) {
		if ($('#_vTokenName').val() && clickObj)
			$('#_vTokenName').trigger("blur");
		var img=document.getElementById('_tokenImg');
		if(img){ img.src = "GenTokenImg.do"
				+ "?random=" + Math.random();
		}
		
	}
</script>
</head>
<body onload="load();">
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
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
      </div>
      <div class="content">         
           <div class="con_r">               
             <div class="con_r_main">
                       <div class="dingw">您可以在此解冻丰收e支付服务</div>
                      
                        <div class="sr_main">
                       <form id="form1" name="form1" method="post" action="FS05ThawAcctPre.do"  onsubmit="return beforeSubmit();" > 
                              <table width="550" border="0" style="margin:20px auto;" >
                                <tr>
                                  <td class="right" width="25%" style="letter-spacing: 0.1px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="70%" ><input autocomplete="off" name="PayerAcctNbr" id="PayerAcctNbr"   onblur="checkPayerAcctNbr();" /><span id="PayerAcctNbrInfo" class="cheng"> *${RespMessage}</span> </td>
                                </tr>
                                <tr>
                                  <td class="right" width="25%">手机号码：</td>
                                  <td width="70%"><input autocomplete="off" name="PayerPhoneNo" id="PayerPhoneNo"   onblur="checkPhone();"/><span id="PayerPhoneNoInfo" class="cheng"> *${RespMessage2}</span></td>
                                </tr>
                               <tr>
									<td class="right" style="letter-spacing: 3.76px">附加码：</td>
									<td td width="78%"><input onkeyup="checkTokenImgOnKeyUp()"
										onblur="checkTokenImg()" autocomplete="off" id="_vTokenName"
										maxlength="4" name="_vTokenName" type="text"
										class="small_sr" value="" /> <img id="rightImage"
										style="display: none; position: relative; top: 10px;" /> <img
										id="_tokenImg" src="" class="yzm_img" /> <span><a
											href="#" onclick="reloadTokenImg(this);"
											style="font-size: 11px">看不清楚，换一张</a></span></td>
								</tr>
                              </table>  	
                    </div>
                      </br>
                        <div class="btn_big">
                              <input type="button" onclick="doIt();" value="下一步" class="xia_btn" id="doitbtn"/>
                           
                        </div>
                        </form>
                        <div class="wxts">
                             <div class="tub"></div>
                              <div class="wxts_main">
                                   <h3></h3>                                                                 
                              </div>
                        </div>
             </div>
           </div>
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












