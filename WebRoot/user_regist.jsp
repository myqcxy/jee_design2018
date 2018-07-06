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
<s:head/>
  </head>
  
  <body>
  <center>
  <s:debug/><a href="index.jsp">返回主页面</a><br><a href="login_user.jsp">已有账户，点击登录</a>
      <s:form action="addUser" method ="post">
       <s:textfield name="user.uname"  label="用户名" requiredLabel="true" requiredPosition="left"/>
       <s:password name="user.upass" label="密码" errorPosition="bottom"/>
       <s:submit value="注册" />
    </s:form>
    </center>
  </body>
</html>

