<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login_admin_fail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<s:debug/>
<%-- <s:form action="doubleselectTag">
       <s:doubleselect label="请选择所在省市" name="provice" list="place"
        listKey="String" listValue="name" doubleList="cityMap.get(top.id)" doubleListKey="id"
        doubleListValue="name" doubleName="city"
        headerValue="----请选择----" emptyOption="true" />
  </s:form> --%>
 <%--  <s:form name="form1">
  <s:doubleselect label="地域" list="#place.keySet()" name="city"
   doubleName="zone" formName="form1" doubleList="#place[top]" />
</s:form> --%>
  </body>
</html>
