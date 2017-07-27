<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>       
<%@ taglib prefix="pe" uri="/WEB-INF/tlds/powerengine2.tld" %>
<FIELDSET style="padding-top:15px;padding-bottom:10px;">
	<table width="100%"  >
	<c:if test="${ RecordNumber!='0'}" >	
			<tr align="right" >
				<td width="100%"  style="color: blue;" >
				当前第${PageNum}页&nbsp;共${TotalPage}页&nbsp;${RecordNumber}条记录&nbsp;&nbsp;
				<a style="text-decoration: underline; cursor:pointer" onclick="prePage();">上一页</a>
				 <a style="text-decoration: underline; cursor:pointer" onclick="nextPage()">下一页</a> 
				 <a style="text-decoration: underline;cursor:pointer" onclick="goPage()">跳</a><input type="text" style="width:20px;height:23px" /name="gopage">页
				</td>
			</tr>
		</table>
		<input type="hidden" name="PageNum" value="${PageNum}"/>
		<input type="hidden" name="TotalPage" value="${TotalPage}"/>
	<table width="100%" border="1" style="font-size: 14px;">
		<tr height="15" align="center"  bgcolor="#dedede">
        <td  >交易时间</td>
       <!--<td>收款人</td>
        <td>收款人账号</td>
          --> 
        <td>金额</td>
        <td>手续费</td>
        <td>交易状态</td>
        <td colspan="2" align="center">操作</td>
        	</c:if>
        	<c:if test="${ RecordNumber=='0'}" >
        	
        	<table width="100%"  style="color:red; font-size: 14px;">
        	<tr align="center"><td >在指定日期内没有交易记录，感谢您的使用！</td></tr>
        		</table>
        	</c:if>
		</tr>
		<c:forEach items="${TransList}" var="row" varStatus="status">
			<tr align="center" height="13">
				<td>${row.TransTime}</td>
			   <!--  <td>${row.RCVACCTNAME}</td>      
				<td>${row.RCVACCTNO}</td>-->
				<td><fmt:formatNumber type="number" value="${row.TransAmt}" pattern="#,###,##0.00"/></td>				
				<td><fmt:formatNumber type="number" value="${row.FeeAmt}" pattern="#,###,##0.00"/></td>      
				<td>${row.TransStatus}</td>
				<td ><a style="text-decoration: underline; color: blue;cursor:pointer" onclick="TrsDetailQryDetail('${row.TransTime}','${row.PayerAcctNbr}','${row.TransAmt}','${row.PayeeAcctNbr}',
				'${row.FeeAmt}','${row.TransStatus}','${row.CurrencyCd}','${row.PayeeAcctName}','${row.PayerAcctName}')">详情</a></td> 
				<td><a style="text-decoration: underline; color: blue;cursor:pointer"  onclick="QueryElectronicReceipt('${row.TransTime}','${row.PayerAcctNbr}','${row.TransAmt}','${row.PayeeAcctNbr}',
					'${row.FeeAmt}','${row.TransStatus}','${row.CurrencyCd}','${row.PayeeAcctName}','${row.PayerAcctName}')">回单</a></td>     
			</tr>
		</c:forEach>
	
</table>
</FIELDSET>