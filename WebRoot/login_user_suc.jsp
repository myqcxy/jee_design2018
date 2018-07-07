<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body><center>
  登录成功！你好！
  <% String uname=(String)session.getAttribute("uname");System.out.println(uname); 
  out.print(uname);
  %><a href="logout">注销</a>
    <%-- <s:property value="${'uname' } "/> --%><br><br>
    <a href="rentHouse">我要租房</a><br><br>
    <a href="house_add.jsp">我要提供租房</a><br><br>
    <a href="myCollection">我的收藏</a><br><br>
     <a href="myHouse">我的房屋</a><br><br>
    </center>
  </body>
</html>
