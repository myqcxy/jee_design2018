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
	<a href="login_user_suc.jsp">返回我的主页</a>
	 <table border="1" style="border:1px solid green"><tr><th>城市
	 <th>区<th>厅室<th>面积（平米）<th>出租方式<th>租金<th>描述<th>电话<th>状态<th>action</tr>
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
      <a href="${delUrl}" onClick="return readyDel();">删除</a></tr>
      <tr><td colspan="10"><img alt="#row.photosUrl" src="${row.photosUrl}"></tr>
    </s:iterator>
    </table></center>
    <script>
      function readyDel(){
        return confirm("是否真的删除?");
      }
    </script>
  </body>
</html>
