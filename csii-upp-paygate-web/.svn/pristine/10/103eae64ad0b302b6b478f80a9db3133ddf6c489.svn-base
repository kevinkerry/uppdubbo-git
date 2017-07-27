<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 <script language="javascript" type="text/javascript" src="js/foison/writeObject.js"></script>
<script >
//获取服务器时间戳
var ts="<%=System.currentTimeMillis()%>";
function doIt(obj){
	var length = document.getElementById("PaperNo").value.length;
	var paperType = $("#PayerIdTypCd").val();
	if((paperType!="101")||(length==18&&paperType=="101")){
		 //获取密码密文
	    var password =getIBSPayerCardPwd("powerpass", ts ,"PassWordInfo", "");
	    if(null == password){
	        return;
	    }
	    document.forms[0].PayerCardPwd.value = password;
	    document.forms[0].submit();
	}
}

function gobackhistory(){
	  document.forms[0].action="FS05.do";
	  document.forms[0].submit();
	
}


//检验证件号位数
function checkPaperNo() {
	var paperType = $("#PayerIdTypCd").val();
	if (paperType == "101") {
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
</script>
</head>
<body >
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
         </ul> 
      
      </div>
      <div class="content">
           
           <div class="con_r">
              
             <div class="con_r_main">
              <form id="form1" name="form1" method="post" action="FS05thawInput.do" >
                       <div class="dingw">您可以在此解冻丰收e支付服务</div>
                        <div class="sr_main">
                      
                                 <pe:hidden fieldList="PayerPhoneNo,PayerCardTypCd,PayerAcctDeptNbr,CustCifNbr,PayerIdNbr,PayerIdTypCd" skipNULL="true"/>
                              <input name="PayerAcctNbr" id="textfield" value="${PayerAcctNbr}" type="hidden" />      
                           <table width="650" border="2" style="margin:20px auto;" >
                            <tr>
                                  <td width="30%" class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="50%">${PayerAcctNbr}</td>
                                  <td width="20%"></td>
                                </tr>
                                <tr>
                                  <td width="30%" class="right">手&nbsp;机&nbsp;号：</td>
                                  <td width="50%">${PayerPhoneNo}</td>
                                  <td width="20%"></td>
                                </tr>
                                <tr>
                                    <c:if test="${PayerCardTypCd=='2'}">
                                        <td width="30%" class="right">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                                    </c:if>
                                    <c:if test="${PayerCardTypCd=='1'}">
                                        <td width="30%" class="right">查询密码：</td>
                                    </c:if>
                                  <td width="50%">
                                   <input type="hidden" name="PayerCardPwd" value="" />
                                    <script type="text/javascript">writePassObject("powerpass","right",{"width":120,"height":30,"maxLength":6,"minLength":6,"accepts":"[:graph:]+"});</script>     
                               <span id="PassWordInfo" class="cheng">*</span>
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
                                <td width="60%"><input autocomplete="off"  style="width:220px;"  onblur="checkPaperNo()"
                                onkeydown="if(event.keyCode==13){return false;}"
                                 name="PayerIdNbr" id="PayerIdNbr" />
                                <span id="paperNoInfo" class="cheng">*</span>
                                 </td>
                                  <td width="10%"></td>                          
                                </tr>
                                                  
                              </table>  
                              
               </div>
                	
                        <div class="btn_big">
                               <input type="button"  value="上一步" class="xia_btn"  onclick="gobackhistory();" />
                             <input type="button"  value="提交" class="xia_btn" id="doNext" onclick="doIt(this)"/>
                               <input type="reset"  value="重置" class="xia_btn" />
                        </div>
                      
                         <c:if test="${PayerCardTypCd=='2'}">
                             <!--  <div class="wxts">
                              <div class="tub"><img src="images/tishi.png" /></div>
                              <div class="wxts_main">
                                   <h3>温馨提示</h3>
                                    <p></p>
                                    <p></p>
                              </div>
                              </div> -->
                         </c:if>
                         <c:if test="${PayerCardTypCd=='1'}">
                               <div class="wxts">
                              <div class="tub"><img src="images/tishi.png" /></div>
                              <div class="wxts_main">
                                   <h3>温馨提示</h3>
                                    <p>查询密码是您登录电话银行进行账户信息查询时所需要输入的密码</p>
                                    <p>若您忘记或未设置过查询密码，可使用预留电话致电浙江农信客服热线4008896596重新设置</p>
                              </div>
                              </div>
                          </c:if>
                        </form>
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












