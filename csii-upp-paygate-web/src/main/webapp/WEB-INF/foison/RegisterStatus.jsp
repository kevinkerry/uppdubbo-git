<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=utf-8" session="true"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/foison/css.css" rel="stylesheet" type="text/css" />
<link href="css/foison/global.css" rel="stylesheet" type="text/css" />
<title>丰收e支付注册</title>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.4.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/slides.min.jquery.js"></script>
<style>
.Register {
	width: 880px;
	height: 160px;
	margin: auto;
	margin-top: 60px;
}

.Register li {
	float: left;
}

.Register li img {
	margin-left: 140px;
	margin-top: 40px;
}

.Register li p {
	margin-top: 60px;
	margin-left: 30px;
}
</style>
<script>
	function onload() {
		var AcctNo = $('#PayerAcctNbr').val();
		var acctno = AcctNo.substr(AcctNo.length - 4);
		$('#acct').text(acctno);
	}

	function gobackhistory() {
		window.history.back(-1);

	}

	function doIt() {
		document.forms[0].action = "FS03.do";
		document.forms[0].submit();
	}
</script>
</head>

<body onload="onload();">
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/foison/csii1.png">
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/foison/phone.png"><span class="net">
							<a href="http://www.csii.com/">www.csii.com </a>
					</span>
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
		<div class="content">
			<div class="con_r">
				<div class="con_r_main">
					<div class="dingw">您可以在此注册丰收e支付服务</div>
					<div class="sr_main" >
						<c:choose>
							<c:when test="${RespCode=='000000'}">
								<form name="form1" action="#" method="post">
									<input name="PayerAcctNbr" id="PayerAcctNbr" type="hidden"
										value="${PayerAcctNbr}" /> <input name="PayerPhoneNo"
										id="PayerPhoneNo" type="hidden" value="${PayerPhoneNo}" />
								</form>
								<div class="Register" style="margin-top: 20px;">
									<ul>
										<li style="margin-left: 65px; "><img src="images/phone/dui.png" style="margin-top: 10px;"/></li>
										<li style="margin-left: 20px; ">
											<p style="margin-top: 30px">
												您尾号为<a id="acct" name="acct"></a>的卡成功开通丰收e支付服务
											</p>
										</li>
									</ul>

								</div>
								<p align="center">
									浙江农信卡单笔交易限额为2000.00元，日累计限额为2000.00元，如需调整请点击<a onclick="doIt();"
										href="#">调整交易限额</a>
								</p>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${empty RespMessage}">
										<div class="cuo">
											<img src="images/phone/cuo.png"> <span class="text_w">注册失败</span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="Register">
											<ul>
												<li class="IMg"><img src="images/phone/cuo.png" /></li>
												<li class="text_Error"><p>
														<c:out value='${RespMessage}' />
													</p></li>
											</ul>

										</div>
										<!--  <p align="center"><a href="/paygate/FS01.do" ><span class="chong_bt">返回</span></a></p> -->
										<div class="btn_big"></div>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	</div>
</body>
</html>
