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
		<h3>请设置位置信息</h3>
		<br>
		<s:set var="area"
			value="#{			'郑州':{'高新技术开发区','经济技术产业开发区','郑东新区','中原区','金水区','二七区','管城回族区','上街区','惠济区'},
                     '洛阳':{'西工区','洛龙区','涧西区','瀍河回族区','吉利区','老城区','伊滨区'},
                     '西安':{'新城区','碑林区','莲湖区','灞桥区','未央区','雁塔区','阎良区','临潼区','长安区','杨凌示范区'}
                 }" />
		
		<s:form name="updateHouse" action="setlocation" method="post">
			
			<s:doubleselect label="请选择所在城市" list="#area.keySet()"
				name="city" doubleName="zone"
				doubleList="#area[top]" />

			<s:submit value="更新" />
		</s:form>
	</center>
</body>
</html>
