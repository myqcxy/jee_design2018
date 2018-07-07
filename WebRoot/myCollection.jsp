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
 <!--  Place place;
	String room;
	int area;
	String mode;
	float rent;
	String description;
	String phone; -->
  <body><center>
<s:debug/><b>我的收藏如下：</b><a href="login_user_suc.jsp">返回我的主页</a>
	 <table border="1" style="border:1px solid green"><tr><td>城市<td>区<td>厅室<td>面积（平米）<td>出租方式<td>租金<td>描述<td>电话<td>action
    <s:iterator value="houses" var="row">
    <tr>
    	<td><s:property value="#row.city.name"/>
    	<td><s:property value="#row.zone.name"/>
      <td><s:property value="#row.room"/>
      <td><s:property value="#row.area"/>
      <td><s:property value="#row.mode"/>
      <td><s:property value="#row.rent"/>
      <td><s:property value="#row.description"/>
      <td><s:property value="#row.phone"/>
      <td>
      <s:url var="rmCollectUrl" action="rmCollection" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${rmCollectUrl}">取消收藏</a>
      <tr><td colspan="10"><img alt="暂时没有图片" src="${row.photosUrl}"></tr>
      <!-- <a href="" onClick="return readyDel() --><!-- ;">del</a> -->
    </s:iterator>
    </table></center>
  </body>
</html>
