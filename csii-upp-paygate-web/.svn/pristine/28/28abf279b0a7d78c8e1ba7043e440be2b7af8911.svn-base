<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c1" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- 银行卡支付页面 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css2.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global2.css" type="text/css"/>
<title>丰收e支付冻结</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
	<script>
	function Cancel1(){
	      window.location.href="FS04.do";
			}
	</script>
</head>

<body>
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
                      <form id="form1" name="form1" method="post" action="FS04Confirm.do">
                        <div class="sr_main">
                        
                        <pe:hidden fieldList="PayerCardPwd,PayerCardTypCd,PayTypCd,PayerIdTypCd,PayerAcctNbr,PayerIdNbr,PayerPhoneNo" skipNULL="true"/> 
                              <table width="414" border="0" style="margin-top: 20px;margin-left: 320px;" >
                                <tr>
                                  <td width="109" class="right">卡&emsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;号：</td>
                                  <td width="295">${PayerAcctNbr} <span class="cheng"> </span></td>
                                </tr>
                           <tr>
                                  <td width="109" class="right">手机号码：</td>
                                  <td width="295">${PayerPhoneNo}<span class="cheng"> </span></td>
                                </tr>
                                  <tr>
                                  <td class="right">证件种类：</td>
                                  <c:if test="${PayerCardTypCd=='1'}">
                                   <td width="295">
                                   <c:if test="${PayerIdTypCd=='101'}">居民身份证</c:if>
                                   <c:if test="${PayerIdTypCd=='102'}">户口簿</c:if>
                                   <c:if test="${PayerIdTypCd=='103'}">护照</c:if>
                                   <c:if test="${PayerIdTypCd=='104'}">军官证</c:if>
                                   <c:if test="${PayerIdTypCd=='105'}">士兵证</c:if>
                                   <c:if test="${PayerIdTypCd=='106'}">警官证</c:if>
                                   <c:if test="${PayerIdTypCd=='111'}">港澳来往内地通行证</c:if>
                                   <c:if test="${PayerIdTypCd=='112'}">台湾来往内地通行证</c:if>
                                   <c:if test="${PayerIdTypCd=='113'}">外国人居留证</c:if>
                                   <c:if test="${PayerIdTypCd=='120'}">社会保障证</c:if>
                                   <c:if test="${PayerIdTypCd=='121'}">个人纳税证</c:if>
                                   <c:if test="${PayerIdTypCd=='199'}">个人其它证件</c:if>
                                   </td>
                                  </c:if>
                                  <c:if test="${PayerCardTypCd=='2'}">
                                   <td width="295">
                                   <c:if test="${PayerIdTypCd=='01'}">居民身份证</c:if>
                                   <c:if test="${PayerIdTypCd=='03'}">军官证</c:if>
                                   <c:if test="${PayerIdTypCd=='04'}">台湾来往内地通行证</c:if>
                                   <c:if test="${PayerIdTypCd=='06'}">港澳来往内地通行证</c:if>
                                   <c:if test="${PayerIdTypCd=='10'}">护照</c:if>
                                   <c:if test="${PayerIdTypCd=='17'}">户口簿</c:if>
                                   <c:if test="${PayerIdTypCd=='21'}">警官证</c:if>
                                   <c:if test="${PayerIdTypCd=='22'}">士兵证</c:if>
                                   <c:if test="${PayerIdTypCd=='20'}">其它</c:if>
                                  </td>
                                  </c:if>
                                </tr>                            
                              <tr>
                                  <td width="109" class="right">证件号码：</td>
                                  <td width="295">${PayerIdNbr} <span class="cheng" id="paperNoInfo"> </span></td>
                                </tr>
                              
                              </table>  	
               </div>
                        <div class="btn_big">
                             <input type="button"  value="取消"   onclick="Cancel1();" class="xia_btn"/>
                              <input type="submit"  value="确认" class="xia_btn"/>
                        </div>
                        </form>
                        <div class="wxts">
                              <div class="tub"><img src="images/tishi.png" /></div>
                              <div class="wxts_main">
                                   <h3>温馨提示</h3>
                                   <p>尊敬的客户：如果您发现您的丰收e支付功能有可能被盗用，可以通过本功能
                                   自主冻结丰收e支付服务，冻结后将不能够使用丰收e支付功能</p>
                                   
                              </div>
                        </div>
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












