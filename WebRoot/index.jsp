<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	body{
	background:url(photos/bg.jpg) no-repeat;
	background-size:100% 100%; 
	background-attachment: 80%;
	color:red;
	margin-left:60px;
	}
	a{
	text-decoration:none ;
	color:gray;
	}
</style>
  </head>
  
  <body>
  <center>
  <h1>网上房屋出租系统</h1><b>
   <a href="login_admin.jsp" style="color:black">管理员登录</a><br><br>
   <a href="login_user.jsp">普通用户登录</a><br><br>
   <a href="user_regist.jsp"style="color:black">普通用户注册</a></b></center>
  </body>
</html>
