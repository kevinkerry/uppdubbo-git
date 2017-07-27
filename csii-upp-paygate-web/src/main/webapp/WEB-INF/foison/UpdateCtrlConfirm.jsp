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
<title>丰收e支付调整交易限额</title>
<script src="js/jquery-1.4.4.min.js"></script>
<script src="js/slides.min.jquery.js"></script>
	<script>
	function formatamt(){
		var perTransLimit='${PerTransLimit}';
		var perDayLimit='${PerDayLimit}';
		if(perTransLimit.indexOf('.')>-1){
			perTransLimit=perTransLimit.substring(0, perTransLimit.indexOf('.'));
		}
		if(perDayLimit.indexOf('.')>-1){
			perDayLimit=perDayLimit.substring(0, perDayLimit.indexOf('.'));
		}
		document.getElementById('PerTransLimit').placeholder='您当前单笔交易限额'+perTransLimit+'.00';
		document.getElementById('PerDayLimit').placeholder='您当前日累计限额'+perDayLimit+'.00';
	}
	function Cancel1(){
	      window.location.href="FS03.do";
			}

	  function isNum(e) {
          var k = window.event ? e.keyCode : e.which;
          if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {

          } else {
              if (window.event) {
                  window.event.returnValue = false;
              }
              else {
                  e.preventDefault();
              }
          }
      }
	
	function checkAmt(){
		 $('#error').text("");
		 var perTransMax=document.getElementById('perTransMax').value;
		 var perDayMax=document.getElementById('perDayMax').value;
		 var perTransNow=document.getElementById('perTransNow').value;
		 var perDayNow=document.getElementById('perDayNow').value;
		 var PerTransLimit=document.getElementById('PerTransLimit').value;
		 var PerDayLimit=document.getElementById('PerDayLimit').value;
		 var max1=parseInt(perTransMax);
		 var max2=parseInt(perDayMax);
		 var str1 =parseInt($('#PerTransLimit').val());
		 var str2 =parseInt($('#PerDayLimit').val());
	       
      if((PerTransLimit==""||PerTransLimit==null)&&(PerDayLimit==""||PerDayLimit==null)){
    	  $('#error').text("*您当前没有任何输入");
    	  return false;
        }else if((PerTransLimit!=""&&PerTransLimit!=null)&&(PerDayLimit!=""&&PerDayLimit!=null)){
    
        	 if((str1<0||str1>max1)||(str2<0||str2>max2)){
    				 $('#error').text("*调整金额应在规定范围内");
    				 return false;
    		 }else{
    			 if(str1<=str2){
    				 return true;
    			 }
    			 $('#error').text("*单笔交易限额不能超过日累计交易限额");
    			 return false;
    		 }
    	}else if((PerTransLimit==""||PerTransLimit==null)||(PerDayLimit==""||PerDayLimit==null)){
    		if((PerTransLimit==""||PerTransLimit==null)){
    	        document.getElementById('PerTransLimit').value=perTransNow;
    	        if(str2<0||str2>max2){
    	        	 $('#error').text("*超过该卡丰收e支付的日累计交易限额");
	        			return false;
    	               }else{
    	            	  str1 =parseInt($('#PerTransLimit').val());
    	            	  if(str1<=str2){
    	      				 return true;
    	      			 }
    	      			 $('#error').text("*单笔交易限额不能超过日累计交易限额");
    	      			 return false;
    	               }  
    		}
    		if((PerDayLimit==""||PerDayLimit==null)){
    	     document.getElementById('PerDayLimit').value=perDayNow;
    	     if(str1<0||str1>max1){
	        	 $('#error').text("*该笔交易金额超过该卡丰收e支付的单笔交易限额");
        			return false;
	               }else{
	            	  str2 =parseInt($('#PerDayLimit').val());
	            	  if(str1<=str2){
	      				 return true;
	      			 }
	      			 $('#error').text("*单笔交易限额不能超过日累计交易限额");
	      			 return false;
	               }  	  	     
    		}
    	}else{
        	return false;
        	}    	
  	return false;
	}
	</script>
	<style>
	.inputtext{width: 200px}
	
	</style>
</head>
<body  onload="formatamt();">
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
              <li><a style="background:url(images/foison/top_bg2.png) repeat-x;text-decoration:none;border-radius:5px;color:#e17b27;" href="/paygate/FS03.do">调整交易限额</a></li>
            <li><a href="/paygate/FS04.do">冻结</a></li>
            <li><a href="/paygate/FS05.do">解冻</a></li>
            <li><a href="/paygate/FS07.do">注销</a></li>
         </ul>    
      </div>
      <div class="content">  
      <form id="form1" name="form1" method="post" action="FS03Confirm.do" onsubmit="return checkAmt()"> 
                        <pe:hidden fieldList="AcctType,CifNo,DeptId" skipNULL="true"/> 
                        <input name="PayerAcctNbr" id="textfield" value="${PayerAcctNbr}" type="hidden"/>
                        <input name="PayerPhoneNo" id="textfield" value="${PayerPhoneNo}" type="hidden"/>
                         <input  id="perTransMax"  value="${PerTransMax}" type="hidden"/>
                          <input id="perDayMax" value="${PerDayMax}" type="hidden"/>
                          <input id="perTransNow"  value="${PerTransLimit}" type="hidden"/>
                          <input id="perDayNow" value="${PerDayLimit}" type="hidden"/>       
           <div class="con_r">
             <div class="con_r_main">
                       <div class="dingw">您可以在此调整丰收e支付交易限额</div>                    
                        <div class="sr_main">
                       
                              <table width="600px" border="0" style="margin:20px auto;" >
                                <tr>
                                  <td width="200px" class="right" style="letter-spacing: 2.15px;">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                                  <td width="200px">${PayerAcctNbr}</td>
                                   <td width="200px"><span class="cheng"> </span></td>
                                </tr>
                           <tr>
                                  <td width="200px" class="right" style="letter-spacing: 4.1px">手&nbsp;机&nbsp;号&nbsp;码：</td>
                                  <td width="100px">${PayerPhoneNo}</td>
                                     <td width="200px"><span class="cheng"> </span></td>
                                </tr>
                                  <tr>
                                 <td width="200px" class="right" style="letter-spacing: 2.3px">单笔交易限额：</td>
                                 <td width="100px">
                                  <input onkeypress="return isNum(event)"  placeholder="您当前单笔交易限额${PerTransLimit}"   autocomplete="off"  name="PerTransLimit" id="PerTransLimit"  style="width: 170px;"/></td>
                                 <td width="200px"><span class="cheng">(最大限额${PerTransMax}元) </span></td>
                                </tr>
                                
                             <tr>
                                 <td width="200px" class="right">日累计交易限额：</td>
                                 <td width="100px">
                                  <input onkeypress="return isNum(event)"    autocomplete="off"  placeholder="您当前日累计限额${PerDayLimit}"   name="PerDayLimit" id="PerDayLimit"  style="width: 170px;"/></td>
                                 <td width="200px"><span class="cheng">(最大限额${PerDayMax}元)  </span></td>
                                </tr>
                         
                                <tr align="center"> 
                                <td colspan="3"><span id="error" class="cheng">${RespMessage2}</span>  
                                </td>
                                 
                                </tr>
   
                              </table>  	
               </div>
                        <div class="btn_big">
                             <input type="button"  value="取消"  onclick="Cancel1()" class="xia_btn"/>
                              <input type="submit"  value="确认" class="xia_btn"/>
                        </div>
                        </form>
                        <div class="wxts">
                              <div class="tub"></div>
                              <div class="wxts_main">
                                  
                                   
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












