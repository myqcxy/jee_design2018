<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
	
	background-size:100% 100%; 
	background-attachment: 80%;
	color:#0D2A49;
	margin-left:60px;
	}
	a{
	text-decoration:none ;
	
	}
</style>
  </head>
  
  <body>
  <center>
  <s:debug/>
  
    <s:form action="checkUser" method ="post">
       <s:textfield name="user.uname"  label="用户名" requiredLabel="true" requiredPosition="left"/>
       <s:password name="user.upass" label="密码" errorPosition="bottom"/>
       <s:submit value="登录" /><a href="user_regist.jsp">去注册</a>
    </s:form>
    </center>
  </body>
</html>

