<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="pe" uri="http://www.csii.com.cn/tag/pe" %>
<%
java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String timestamp = timeFormat.format(new java.util.Date());
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global.css" type="text/css"/>
	<title>浙江农信网银支付</title>
	<style>
	.btn_big1{width:540px;text-align:center;}
	.btn_big1 input{width:124px; height:43px; background:url(../images/images/fanhui.png) no-repeat; border:0; font-size:16px;color:#fff;font-family:"微软雅黑";font-weight:bold; margin-right: 20px;}
	
	</style>
	<script language="javascript" type="text/javascript" src="script.do"></script>
	<script language="javascript" type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/slides.min.jquery.js"></script>
	<script><!--
		$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'img/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true,
				animationStart: function(){
					$('.caption').animate({
						bottom:-35
					},100);
				},
				animationComplete: function(current){
					$('.caption').animate({
						bottom:0
					},200);
					if (window.console && console.log) {
						// example return of current slide number
						console.log(current);
					};
				}
			});
			//$('#doItButton').click(function() {
				//if("*"==$('#PayerAcctNbrTip').text() && "*"==$('#PayerPhoneNoTip').text()){
				//	document.forms[0].action="foisonPre.do";
			//		document.forms[0].submit();
			//	}else{
		 //			return;
		//		}
			//});

			//阅读并同意协议内容方可提交付款
			$('#checkbox').click(function(){
				if($('#checkbox').attr("checked")){
					$("#doItButton").removeAttr("disabled");//启用按钮
					$("#doItButton").css("background-image","url(images/images/xiayi.png)");
				}else{
					$("#doItButton").attr("disabled","disabled");//禁用按钮
					$("#doItButton").css("background-image","url(images/images/fanhui.png)");
				}
			});
			//卡号手机号失去焦点事件
			 $('#PayerAcctNbr').blur(function() {
				 var acctno = $('#PayerAcctNbr').val();
				 var acctnoData19 = /^(6)[0-9]{18}$/;
				 var acctnoData16 = /^(6)[0-9]{15}$/;
				 if(acctnoData16.test(acctno)||acctnoData19.test(acctno)){
					 $('#PayerAcctNbrTip').text("*");
				 }else{
					 $('#PayerAcctNbrTip').text("卡号输入错误");
				 }
				
			});
			 $('#PayerPhoneNo').blur(function() {
				 var phone = $('#PayerPhoneNo').val();
				 var reg = /^(13|14|15|17|18)[0-9]{9}$/;
				 if(!reg.test(phone)){
					 $('#PayerPhoneNoTip').text("手机号输入错误");
				 }else{
					 $('#PayerPhoneNoTip').text("*");
				 }
			});
		});
		function isAgree(){
			if($('#checkbox').attr("checked")){
				$("#doItButton").removeAttr("disabled");//启用按钮
				$("#doItButton").css("background-image","url(images/images/xiayi.png)");
			}else{
				$("#doItButton").attr("disabled","disabled");//禁用按钮
				$("#doItButton").css("background-image","url(images/images/fanhui.png)");
			}
			}

		//2015年7月3日   ZGB    个人网银支付
		function doItPbank(obj){
			document.forms[0].action="PbankPay.do";//.do  http-paygate.xml
			document.forms[0].submit();
		}
		function doItEbank(obj){
			document.forms[0].action="EbankPay.do";//bankPay.do?id='e'
			document.forms[0].submit();
		}

		
	--></script>
</head>

<body  onload="isAgree();">
   <div class="main">
      <div class="top">
           <div class="logo"><img src="images/images/csii1.png" /></div>
           <div class="top_r">
                <div class="phone"><img src="images/images/phone.png" /><span class="net"> <a href="http://www.csii.com/">www.csii.com </a></span></div>
                <div class="time"><div class="time1">${MerDate }</div></div>
           </div>
      </div>
      <div class="content">
      <form name="form1" action="#" method="post">
      <pe:hidden fieldList="AcctType,PlainContext,Plain,ChannelId,OrderId,MerDate,MerchantName,Signature" skipNULL="true"/>
           <div class="con_l">
                 <div class="con_l_t"><img src="images/images/wddd.png" style="margin-top:-2px;" /></div>
                 <div class="con_l_m">
                     <table width="280" align="center" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td class="right hui">商   户：</td>
                        <td class="blue">${ MerchantName}</td>
                      </tr>
                      <tr>
                        <td class="right hui">金   额：</td>
                        <td class="cheng">￥${ TransAmt} 元</td>
                      </tr>
                      <tr>
                        <td class="right hui">日   期：</td>
                        <td>${MerDate }</td>
                      </tr>
                      <tr>
                        <td class="right hui">订单号：</td>
                        <td  class="blue">${ OrderId}</td>
                      </tr>
                      <tr>
                        <td class="right hui">币   种：</td>
                        <td>人民币</td>
                      </tr>
                    </table> 
                    
                 </div>
<!--图片轮播开始-->
     <div id="container">
		<div id="example"> 
			<!--<div id="slides">
				<div class="slides_container">
					<div>
						<a href="###" title="145.365 - Happy Bokeh Thursday! | Flickr - Photo Sharing!" target="_blank"><img src="images/images/guangao.png" width="262" height="178" alt="Slide 1" /></a> 
					</div>
					<div>
						<a href="###" title="Taxi | Flickr - Photo Sharing!" target="_blank"><img src="images/images/guangao.png" width="262" height="178" alt="Slide 2" /></a> 
					</div> 
					 
					 
				</div>
				<a href="#" class="prev"><img src="images/images/arrow-prev.png" width="19" height="43" alt="Arrow Prev" /></a>
				<a href="#" class="next"><img src="images/images/arrow-next.png" width="19" height="43" alt="Arrow Next" /></a>
			</div> 
		--></div> 
	</div> 
<!--图片轮播结束-->
    
    
           </div>
           <div class="con_r">
                  <div class="con_r_t">网银支付</div>
                  <div class="con_r_main">
                       <div class="dingw">您的位置：网银支付 > 填写信息</div>
                       <div class="buz">
                            <div class="buz1">1.选择网银</div>  
                            <div class="buz2">2.登录支付</div>        
                            <div class="buz3">3.支付成功</div>
                    </div>
                        <div class="sr_main">
                              <!--<table width="540" border="0" >
                                <tr>
                                  <td class="right">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td><input id="PayerAcctNbr" name="PayerAcctNbr" value="" type="text" /> <span id="PayerAcctNbrTip" class="cheng"> *</span></td>
                                </tr>
                                <tr>
                                  <td class="right">手机号码：</td>
                                  <td><input id="PayerPhoneNo" name="PayerPhoneNo" value="" type="text" /> <span id="PayerPhoneNoTip" class="cheng"> *</span></td>
                                </tr>
                                <tr>
                                  <td colspan="2"> 
                                      <div class="tis">请输入您在银行开户或办理业务时留存手机号。</div>   
                                  </td>
                                </tr>
                                 <tr>
                                  <td class="right" style="padding-right:15px;"><input id="checkbox" name="checkbox" type="checkbox" value="" style="width:20px;" onclick="check()"/></td>
                                  <td class="font14"><a href="###" id="open-box">我已阅读并接受《浙江农信“丰收卡支付”协议》</a></td>
                                </tr>
                              </table>  	
                        -->
                        <div class="btn_big1">
                              <input type="button"  id="cancelCredit" value="个人网银" onclick="doItPbank(this);"  class="chong_btn" style="background-image:url(images/images/xiayi.png);color:#fff;" />
                              <input type="button"  id="cancelCredit" value="企业网银" onclick="doItEbank(this);"  class="chong_btn" style="background-image:url(images/images/xiayi.png);color:#fff;" />
                        </div>
                        </div><!--
                        <div class="btn_big">
                              <input type="button" id="doItButton" value="下一步" class="xia_btn" disabled="disabled" />
                        </div>
                        --><div class="wxts">
                              <div class="wxts_main">
	                              <h3>&nbsp;</h3>
	                              <P>&nbsp;</P>
	                              <P>&nbsp;</P>
	                              <P>&nbsp;</P>
	                              <P>&nbsp;</P>
                              </div>
                        </div>
                  </div>
                  
           </div>
           </form>
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
 <div class="fuc hide" id="fuc" style="overflow:scroll">
     <div class="fuc_main" style="width:980px;height:600px;overflow:auto;border:1px">
            <div class="fuc_main_b">浙江省农村信用社（合作银行、商业银行）“丰收卡支付”协议 <a href="###" id="close-box"><img src="images/images/cha.png" /></a></div>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议是由浙江省农村信用社（合作银行、商业银行）（以下简称“我行”）与我行持卡人（以下简称“您”）就“丰收卡支付”服务（以下简称“本服务”）的使用等相关事项所订立的有效合约。“丰收卡支付”是浙江省农村信用社（合作银行、商业银行）向浙江省农村信用社（合作银行、商业银行）持卡人提供的凭银行卡号和密码支付的电子支付结算方式。您通过互联网（Internet）点击确认或以其他方式选择接受本协议，即表示您同意接受本协议的全部约定内容，确认承担由此产生的一切责任。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在接受本协议之前，请您仔细阅读本协议的全部内容（特别是以粗体下划线标注的内容）。如果您不同意本协议的任何内容，或者无法准确理解相关条款的解释，请不要进行后续操作。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（1）您应确保您在使用本服务时所使用的银行卡为您本人所有，并确保您用其支付的行为合法、有效，未侵犯任何第三方合法权益；否则因此造成我行、持卡人损失的，您应负责赔偿并承担全部法律责任。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（2）我行仅为您提供安全可靠的网上支付服务，支付服务所关联的基础交易中的商品质量、送货服务等引起的争议和纠纷均由您和提供商品或服务的商户自行协商解决，我行对此不承担任何责任</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（3）您应妥善保管该卡开户户名、开户类型、与之相关的证件类型及证件号码、手机号码、固定电话、通信地址等与该卡有关的一切信息（以下简称“身份信息及卡信息”），不得将身份信息及卡信息转告他人，并在安全网络环境下使用，如您遗失银行卡、泄露身份信息及卡信息，您应及时通知我行，以减少可能发生的损失。因您泄露密码、数字证书、U盾、丢失银行卡等所致的风险及损失需由您自行承担。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（4）使用“丰收卡支付”服务，丰收借记卡单笔网上消费交易额最高为人民币20000元，每日网上消费交易额最高为人民币50000元；丰收信用卡单笔网上消费交易额最高为人民币20000元，每日网上消费交易额最高为人民币50000元，我行可根据业务发展需要及风险控制需要设置或修改支付限额，无须对客户另行通知，请您留意我行有关公告及网页说明。如您对我行修改限额存在异议的，可申请终止使用本服务。如我行修改限额后，您仍然使用本功能的，视为您同意我行对限额所进行的修改和调整。在任何情况下，支付金额不应超过我行设置的支付限额。如实际支付金额大于支付限额，我行将拒绝执行交易指令。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（5）在支付过程中，我行将向您在我行预留的手机号码发送动态验证码的方式追加验证，以确保您的交易安全。请确保并确认您在我行预留的手机号码为您本人手机号码并在本人实际控制之下。您应妥善保管移动电话所涉相关信息，并保持移动电话通讯和使用功能通畅。因您遗失移动电话、将移动电话转借他人使用、移动电话自身故障、或其他因您自身原因而导致其无法收取我行发送的动态验证码所产生的一切后果及损失，由您自行承担。如因网络通讯、黑名单等非我行原因造成您无法接收动态验证码的，我行不承担任何责任。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（6）您同意我行对您在使用本服务过程中所填写的包括但不限于姓名、银行卡卡号、手机号码、身份证号码等进行校对核验，同时我行保留随时变更上述确认要素作为校验标准的权利。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（7）您不得利用银行卡进行套现、虚假交易、洗钱等行为，且有义务配合我行进行相关调查，一旦您拒绝配合进行相关调查或我行认为您存在或涉嫌虚假交易、洗钱、套现或任何其他非法活动、欺诈或违反诚信原则的行为、或违反本协议约定的，我行有权采取以下一种、多种或全部措施：一、暂停或终止提供本协议项下“丰收卡支付”服务；二、终止本协议；三、取消您的用卡资格。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（8）您同意，我行有权变更、暂停本协议项下相关服务，有权修改、终止本协议，并于执行前通过我行网站进行公告。相关公告经网站发布视为您已收到。您在公告执行后继续办理相关业务的，视同接受有关本协议修改、变更的内容。本协议终止后，您在协议终止前发送我行进行处理的交易指令仍有效，您应承担其后果。</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议与本行银行卡章程或协议相冲突部分，以本协议为准。</p>

    
       </div>
 </div>
</body>
</html>
    