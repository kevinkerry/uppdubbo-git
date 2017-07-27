<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/global.css" type="text/css" />
<title>浙江农信统一支付</title>
<script language="javascript" type="text/javascript"
	src="js/jquery-1.4.4.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/slides.min.jquery.js"></script>
<script>
	$(function() {
		closePage();
		var count = 1;
		var objInterval = window.setInterval(delTimes, 1000);

		function delTimes() {
			if (count == 0) {
				closePage();
				window.clearInterval(objInterval);
			}
			$("#span_five").html(count--)
		}

		function closePage() {
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
					window.opener = null;
					window.close();
				} else {
					window.open("", "_top");
					window.top.close();
				}
			} else if (navigator.userAgent.indexOf("Firefox") > 0) {
				window.location.href = "about:blank";
			} else {
				window.opener = null;
				window.open("", "_self", "");
				window.close();
			}
		}
	});
</script>
</head>

<body>
	<div class="main">
		<div class="top">
			<div class="logo">
				<img src="images/images/csii1.png" />
			</div>
			<div class="top_r">
				<div class="phone">
					<img src="images/images/phone.png" /><span class="net"> <a
						href="http://www.csii.com/">www.csii.com </a></span>
				</div>
				<div class="time">
					<div class="time1">${MerDate}</div>
				</div>
			</div>
		</div>
		<div class="content" style="height: 600px;">
			<form action="#" method="post">
				<pe:hidden
					fieldList="AcctType,PayerAcctNbr,CifNo,PayerPhoneNo,PlainContext,Plain,ChannelId,MerDate,MerchantName,TransAmt,DeptId,AcctName,Signature"
					skipNULL="false" />
				<div class="con_l">
					<div class="con_l_t">
						<img src="images/images/wddd.png" style="margin-top: -2px;" />
					</div>
					<div class="con_l_m">
						<table width="280" align="center" border="0" cellspacing="0"
							cellpadding="0">
						</table>

					</div>
					<div id="container">
						<div id="example"></div>
					</div>
				</div>
				<div class="con_r">
					<div class="con_r_main">
						<div class="sr_main3 sr_main2">
							<div class="duig">
								<img src="images/images/duig.png" />
							</div>
							<div class="duig_r">
								<h3>
									请到支付页确认支付<br />
									<br />结果
								</h3>
								<p>
									提示：该页面将在<span id="span_five"></span>秒后自动关闭
								</p>
							</div>

						</div>

					</div>
				</div>
			</form>
		</div>
		<div style="height: 670px;"></div>
		<div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
	</div>
</body>
</html>
