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
    <script language="javascript" type="text/javascript" src="script.do"></script>
    <script language="javascript" type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
    <script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/layer-v1.9.3/layer.js"></script>
    <script>

        function Cancel() {
            window.location.href = "FS01.do";
        }
        function doSubmit() {
            document.forms[0].submit();
        }
        function doCancel() {
            window.location.href = "FS01.do";
        }

        function LoadJs() {
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
            <li><a style="text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS01.do">注册</a></li>
            <li><a href="/paygate/FS02.do">查询交易明细</a></li>
            <li><a href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
        </ul>
    </div>
    <div class="content">
        <form id="form1" name="form1" method="post" action="FS01CancelAcctConfirm.do">
            <pe:hidden fieldList="PayerAcctNbr,PayerPhoneNo,payerCardTypCd,CustCifNbr,PayerAcctDeptNbr"
                       skipNULL="true"/>
            <div class="con_r">
                <div class="con_r_main">
                    <div class="dingw">您可以在此注册丰收e支付服务</div>
                    <div class="sr_main">
                        <div class="Register">
                        <br/>
								<table align="center">
									<tr>
										<td align="center"><img src="images/phone/warning.png" /></td>
										<td align="center">
											<ul>
											<li>该卡已签约丰收e支付，签约手机号为${LocalMobilePhone}</li>
											<li>如需更换签约的手机号请继续，继续将注销原手机号的签约关系，否则请点击取消</li>
											</ul>
										</td>
									</tr>
								</table>
							</div>
							<br/><br/>
                        <div class="btn_big">
                            <input type="button" value="取消" class="xia_btn" onclick="doCancel()"/>
                            <input id="doNext" type="button" onclick="doSubmit()" value="继续" class="xia_btn"/>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="footer">北京科蓝软件系统股份有限公司 版权所有  © CSII 1999-2015 京ICP备12008164号</div>
</div>
</body>
</html>


































