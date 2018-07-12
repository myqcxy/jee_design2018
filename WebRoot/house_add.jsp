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
  <body><center><s:debug></s:debug>
	<a href="login_user_suc.jsp">返回</a>
	
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
	<s:form name="addHouse" action="addHouse" method="post" enctype="multipart/form-data">		
		<s:select list="#mode" name="house.mode" label="出租方式" headerKey="" headerValue="请选择出租方式"/>
		<s:textfield name="house.area" label="房屋面积（平米）" requiredLabel="true" requiredPosition="left"/>
		<s:select list="#room" name="house.room" label="房屋类型" headerKey="" headerValue="请选择房屋类型"/>		
		<s:textfield name="house.rent" label="价格" requiredLabel="true" requiredPosition="left"/>
		<s:textfield name="house.phone" label="联系电话"/>
		<s:doubleselect label="请选择所在城市" list="#area.keySet()" name="house.city.name"
	   doubleName="house.zone.name"  doubleList="#area[top]" />
		<s:textarea name="house.description" label="描述" 
		 headerValue="----请选择----" emptyOption="true"/>		
		<s:submit value="添加"/>
	</s:form>
	</center>
  </body>
</html>
