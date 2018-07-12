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
 <!--  Place place;
	String room;
	int area;
	String mode;
	float rent;
	String description;
	String phone; -->
  <body ><center><a href="login_user_suc.jsp">返回</a>
<s:set var="area"
			value="#{			'郑州':{'高新技术开发区','经济技术产业开发区','郑东新区','中原区','金水区','二七区','管城回族区','上街区','惠济区'},
                     '洛阳':{'西工区','洛龙区','涧西区','瀍河回族区','吉利区','老城区','伊滨区'},
                     '西安':{'新城区','碑林区','莲湖区','灞桥区','未央区','雁塔区','阎良区','临潼区','长安区','杨凌示范区'}
                 }" />
     <s:set var="mode"
     value="#{'按月出租':'按月出租','按周出租':'按周出租','按天出租':'按天出租','合租':'合租' }"
     />
      <s:set var="room"
     value="#{'单个房间':'单个房间','一室一厅':'一室一厅','两室一厅':'两室一厅','三室一厅':'三室一厅',
     		'三室两厅':'三室两厅'
      }"
     />
     <h4>模糊查找</h4>
	<s:form label="模糊查找" action="searchHouse" method ="post">
		<s:textfield name="keyInfo" label="筛选条件"/>
		<s:submit value="搜索"/>
	</s:form>
	 <h4>精确查找</h4>
	<s:form label="精确查找" action="seekHouse" method ="post">
		<s:select list="#mode" name="house.mode" label="出租方式" headerKey="" headerValue="请选择出租方式"/>
		<s:select list="#room" name="house.room" label="房屋类型" headerKey="" headerValue="请选择房屋类型"/>		
		<s:textfield name="house.rent" label="最低价格"/>
		<s:textfield name="higestPrice" label="最高价格"/>
		<s:doubleselect label="请选择所在城市" list="#area.keySet()" name="house.city.name"
	   doubleName="house.zone.name"  doubleList="#area[top]" />
	   <s:submit value="搜索"/>
	   </s:form>
		<br><br>
		<div id="main" style="width:800px; height:400px;">
	<div id="left" style="float:left ; width:50%; height:100%;">
	<h4>本地房屋</h4>
     <s:iterator value="houses" var="row">
     <table border="1" style="border:1px solid green"><tr><th>城市
	 <th>区<th>厅室<th>面积（平米）<th>出租方式</tr>
    <tr>
    <s:url var="searchPlaceUrl" action="searchPlace" >
         <s:param name="city" value="#row.city.name"/>
         <s:param name="zone" value="#row.zone.name"/>
         
      </s:url>
     <s:url var="searchCityUrl" action="searchPlace" >
         <s:param name="city" value="#row.city.name"/>
         
      </s:url>
    	<td><a href="${searchCityUrl}"><s:property value="#row.city.name"/></a>
    	<td><a href="${searchPlaceUrl}"><s:property value="#row.zone.name"/></a>
    	<s:url var="searchRoomUrl" action="searchRoom" >
         <s:param name="room" value="#row.room"/>
         
      </s:url>
      <td><a href="${searchRoomUrl}"><s:property value="#row.room"/></a>
      <td><s:property value="#row.area"/>
      <td>
       <s:url var="searchModeUrl" action="searchMode" >
         <s:param name="mode" value="#row.mode"/>
         
      </s:url>
      <a href="${searchModeUrl}"><s:property value="#row.mode"/></a>
     
      
      <tr><th>租金<th>描述<th>电话<th>状态<th>操作<tr>
      <td><s:property value="#row.rent"/>
      <td><s:property value="#row.description"/>
      <td><s:property value="#row.phone"/>
      <td><s:property value="#row.state"/>
      <td>
      <s:url var="collectUrl" action="collect" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${collectUrl}">收藏</a>
      
    <!--   <td> -->
      <%-- <s:url var="rentUrl" action="rentHouse" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${rentUrl}">收藏</a> --%>
      <!-- <a href="" onClick="return readyDel() --><!-- ;">del</a> -->
      <tr><td colspan="10"><img alt="暂时没有图片" src="${row.photosUrl}"></tr></table>
    </s:iterator>
    </div>
	<div id="right" style="margin:10px,100px;float:left ; width:50%; height:100%;">
	<h4>精品房屋</h4>
	<s:iterator value="allhouses" var="row">
     <table border="1" style="border:1px solid green"><tr><th>城市
	 <th>区<th>厅室<th>面积（平米）<th>出租方式</tr>
    <tr>
    	<s:url var="searchPlaceUrl" action="searchPlace" >
         <s:param name="city" value="#row.city.name"/>
         <s:param name="zone" value="#row.zone.name"/>
         
      </s:url>
     <s:url var="searchCityUrl" action="searchPlace" >
         <s:param name="city" value="#row.city.name"/>
         
      </s:url>
    	<td><a href="${searchCityUrl}"><s:property value="#row.city.name"/></a>
    	<td><a href="${searchPlaceUrl}"><s:property value="#row.zone.name"/></a>
      <s:url var="searchRoomUrl" action="searchRoom" >
         <s:param name="room" value="#row.room"/>
         
      </s:url>
      <td><a href="${searchRoomUrl}"><s:property value="#row.room"/></a>
      <td><s:property value="#row.area"/>
      <td> <s:url var="searchModeUrl" action="searchMode" >
         <s:param name="mode" value="#row.mode"/>
         
      </s:url>
      <a href="${searchModeUrl}"><s:property value="#row.mode"/></a><tr><th>租金<th>描述<th>电话<th>状态<th>操作<tr>
      <td><s:property value="#row.rent"/>
      <td><s:property value="#row.description"/>
      <td><s:property value="#row.phone"/>
      <td><s:property value="#row.state"/>
      <td>
      <s:url var="collectUrl" action="collect" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${collectUrl}">收藏</a>
      
    <!--   <td> -->
      <%-- <s:url var="rentUrl" action="rentHouse" >
         <s:param name="house.id" value="#row.id"/>
      </s:url>
      <a href="${rentUrl}">收藏</a> --%>
      <!-- <a href="" onClick="return readyDel() --><!-- ;">del</a> -->
      <tr><td colspan="10"><img alt="暂时没有图片" src="${row.photosUrl}"></tr></table>
    </s:iterator></div>

</div>
	</center>
  </body>
</html>
