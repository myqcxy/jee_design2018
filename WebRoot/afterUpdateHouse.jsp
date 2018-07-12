<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<!--  Place place;
	String room;
	int area;
	String mode;
	float rent;
	String description;
	String phone; -->
<body>
	<center>
		<a href="login_user_suc.jsp">返回</a>
		<h3>添加的房屋信息为</h3>
		<br>
		<table border="1" style="border:1px solid green"><tr><th>城市
	 <th>区<th>厅室<th>面积（平米）<th>出租方式<th>租金<th>描述<th>电话<th>状态</tr>
   
    <tr>
    	<td><s:property value="house.city.name"/>
    	<td><s:property value="house.zone.name"/>
      <td><s:property value="house.room"/>
      <td><s:property value="house.area"/>
      <td><s:property value="house.mode"/>
      <td><s:property value="house.rent"/>
      <td><s:property value="house.description"/>
      <td><s:property value="house.phone"/>
      <td><s:property value="house.state"/>
     

    </table>
	</center>
</body>
</html>
