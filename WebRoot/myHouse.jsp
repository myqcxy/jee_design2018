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
<s:debug/>

	 <table border="1" style="border:1px solid green"><tr><td>城市<td>区<td>厅室<td>面积（平米）<td>出租方式<td>租金<td>描述<td>电话<td>状态<td>action
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
      <td><s:property value="#row.state"/>
      <td>
      <s:url var="editUrl" action="editHouse" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${editUrl}">修改</a>
      
    <s:url var="delUrl" action="delMyHouse" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${delUrl}" onClick="return readyDel();">删除</a>
    </s:iterator>
    </table></center>
    <script>
      function readyDel(){
        return confirm("是否真的删除?");
      }
    </script>
  </body>
</html>
