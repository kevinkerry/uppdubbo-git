<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8" session="true" %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     

<html><!-- InstanceBegin template="/Templates/list.dwt" codeOutsideHTMLIsLocked="true" -->

<head>
<title>批量代发结果查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/global.css" type="text/css" />
	
	<script language="javascript" type="text/javascript"  src="js/jquery-1.4.4.min.js"></script>
	<script language="javascript" type="text/javascript"  src="js/slides.min.jquery.js"></script>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<center><h1>代收结果查询</h1></center>
<body >
<table width="100%" border="1" cellpadding="2" cellspacing="0"	class="table-class" align="center">
	<tr class="trTitle">
		<td nowrap class="tdTitle" align="center">客户姓名</td>
		<!-- <td nowrap class="tdTitle" align="center">证件类型</td> -->
		<td nowrap class="tdTitle" align="center">证件编号</td>
		<td nowrap class="tdTitle" align="center">代收金额</td>
		<td nowrap class="tdTitle" align="center">卡号</td>
		<td nowrap class="tdTitle" align="center">客户类型</td>
		<td nowrap class="tdTitle" align="center">客户手机号</td>
		<td nowrap class="tdTitle" align="center">代收结果</td>
		 
		
		
		

	</tr>
	
	<c:forEach items="${Authlist}" var="row" varStatus="status">
		<tr class="trValue">
			<td class="tdValue" align="center" > 
				${row.customName}
			</td>
			<%-- <td class="tdValue" align="center" > 
				${row.Certtyp}
			</td> --%>
			<td class="tdValue" align="center" > 
				${row.Certno}
			</td>
			<td class="tdValue" align="center" > 
				${row.Cardtyp}
			</td>
			<td class="tdValue" align="center" > 
				${row.Cardno}
			</td>
			<td class="tdValue" align="center" > 
				${row.Customtyp}
			</td>
			<td class="tdValue" align="center" > 
				${row.Phoneno}
			</td>
			<td class="tdValue" align="center" > 
				<c:if test="${row.Transtatus=='0'}">交易成功</c:if>
				<c:if test="${row.Transtatus=='6'}">交易成功</c:if>
			</td>
	</tr>
	</c:forEach>
</table> 
</body>
</html>