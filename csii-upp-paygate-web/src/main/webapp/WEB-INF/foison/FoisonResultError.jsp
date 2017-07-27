<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe"%>
<%
	java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String timestamp = timeFormat.format(new java.util.Date());
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/css2.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/global2.css" type="text/css" />
<title>浙江农信丰收e支付错误页面</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
<script src="js/common.js" language="javascript" type="text/javascript"></script>
<script>
	
	function gobackhistory() {
		url = document.referrer;
		var murl = url.substring(url.lastIndexOf('/') + 1, url.length);
		var RespCode = document.getElementById('RespCode').value;
		window.location.href = murl.substring(0, 4) + ".do";
	}

	function initString() {
		var aa=${RespCode};
		
		if ((document.getElementById('RespMessage').value).length > 40) {
			var str = document.getElementById('RespMessage').value;
			var startIndex = str.indexOf("。") + 1;
			var str1 = str.substring(0, startIndex);
			document.getElementById('IMG8').className = "IMG8";
			$('#str12').text(str1);
			var str2 = str.substring(startIndex);
			/*  var s1=str2.substring(0,17);
			 var s2=str2.substring(17,str2.length);
			 var s3=s1+"<br>"+s2;
			document.getElementById('str13').innerHTML+=s3; */
			$('#str13').text(str2);

		} else {
			var str = document.getElementById('RespMessage').value;
			document.getElementById('IMG8').className = "IMG9";
			document.getElementById('text_Error1').className = "text_Error";
			$('#RespMessage1').text(str);
		}

	}
</script>
<style>
/* #error{ width: 580px;margin:auto; padding: 10px; height: 160px; font-size: 24px; padding-top: 80px;}
.error_a{ width:100%; height:40px; background:url(../../images/foison/top_bg1.png) repeat-x;margin:0 auto;border-radius:5px;} */
.text_Error {
	margin-left: 10px;
	margin-top: 15px;
}

.Register {
	width: 880px;
	height: 120px;
	margin: auto;
	margin-top: 60px;
}

.Register li {
	float: left;
}

chong_bt {
	width: 124px;
	height: 43px;
	background: url(images/foison/fanhui.png) no-repeat;
	color: #666;
}

#center_p {
	width: 800px;
	margin: auto;
}

#center_p p {
	margin: auto;
	width: 124px;
	height: 43px;
}

#center_p p a {
	margin: auto;
	width: 124px;
	height: 43px;
	padding: 10px 62px 20px 48px;
}

#IMG8 {
	height: 83px;
	margin: 0 auto;
}

.IMG8 {
	height: 83px;
	width: 700px;
	margin: 0 auto;
}

.IMG9 {
	height: 83px;
	width: 650px;
	margin: 0 auto;
}

.IMg {
	height: 83px;
	width: 83px;
	line-height: 83px;
}

#str12 {
	height: 20px;
}
</style>
</head>

<body onload="initString()">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/csii1.png" />
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/phone.png" /><span class="net"> <a
						href="http://www.csii.com/">www.csii.com </a></span>
				</div>
			</div>
		</div>
		<div class="main-nav">
			<ul class="main-nav-in">
				<li><a href="/paygate/FS01.do">注册</a></li>
				<li><a href="/paygate/FS02.do">查询交易明细</a></li>
				<li><a href="/paygate/FS03.do">调整交易限额</a></li>
				<li><a href="/paygate/FS04.do">冻结</a></li>
				<li><a href="/paygate/FS05.do">解冻</a></li>
				<li><a href="/paygate/FS07.do">注销</a></li>
			</ul>

		</div>
		<div class="content" style="height: 600px;">

			<div class="con_r">
				<input name="RespCode" id="RespCode" value="${RespCode}"
					type="hidden" />
				<form id="form1" name="form1" method="post">
					<pe:hidden
					fieldList="PayerAcctNbr,PayerPhoneNo,PayerCardTypCd,PayerAcctDeptNbr,CustCifNbr,PayerIdNbr,PayerCardPwd,PayerIdTypCd,PayerCardExpiredDate,PayerAcctName,SmsSqenbr"
					skipNULL="true" />
					<input type="hidden" name="state" id="state" value="${state}" />
				</form>

				<div class="con_r_main">
					<div class="dingw">错误页面</div>
					<c:if test="${ RespCode=='PAY0046'}">
					<input type="hidden" name="RespMessage" id="RespMessage"
						value="该卡未注册"/>
					</c:if>
				<%-- 	<c:if test="${ RespCode=='PAY0035'}">
					<input type="hidden" name="RespMessage" id="RespMessage"
						value="如您输入的客户信息与银行预留不一致"/>
					</c:if> --%>
					<c:if test="${ RespCode!='PAY0046'}">
					<input type="hidden" name="RespMessage" id="RespMessage"
						value="${RespMessage}"+/>
					</c:if>
					<div class="sr_main">
						<div class="Register">
							<ul id="IMG8">
								<li class="IMg"><img src="images/phone/cuo.png" /></li>
								<li class="text_Error"><p id="str12" name="str12" />
									<p id="str13" name="str13" align="center" /></li>
								<li class="text_Error" id="text_Error1"><p
										id="RespMessage1" name="RespMessage1" /></li>
							</ul>
						</div>
					</div>
					
					<div class="btn_big">

						<input type="button" value="返回" class="xia_btn" id="doitbtn"
							onclick="gobackhistory()" />

					</div>
					
					<div class="wxts">
						<div class="tub"></div>
						<div class="wxts_main">
							<h3></h3>

							<p></p>

						</div>
					</div>
					<div align="center">
						<c:if test="${ RespCode=='PAY0046'}">
                 		   		如您尚未开通，请点击<a href="/paygate/FS01.do">注册丰收e支付</a>或进入浙江农信官网（<a
								href="/paygate/FS01.do">www.csii.com</a>）点击丰收e支付进行注册
                 		 </c:if>

					</div>

				</div>
			</div>
			<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
		</div>

		<div class="fuc hide" id="fuc">
			<div class="fuc_main">
				<div class="fuc_main_b">
					浙江农村信用社银行"卡密支付"服务协议 <a href="###" id="close-box"><img
						src="images/cha.png" /></a>
				</div>

			</div>
		</div>
	</div>
</body>
</html>




