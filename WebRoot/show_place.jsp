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
  
  <body>
<s:debug/>
<%-- <s:form action="doubleselectTag">
       <s:doubleselect label="请选择所在省市" name="provice" list="place"
        listKey="String" listValue="name" doubleList="cityMap.get(top.id)" doubleListKey="id"
        doubleListValue="name" doubleName="city"
        headerValue="----请选择----" emptyOption="true" />
  </s:form> --%>
 <%--  <s:form name="form1">
  <s:doubleselect label="地域" list="a" name="city"
   doubleName="zone" formName="form1" doubleList="place[top]" />
</s:form> --%>
<%-- <s:set var="area"
   value="#{'郑州':{'金水区','高新区','中原区','二七区'},
                     '天津':{'天津'}
                    
                 }" />
<s:form name="form1">
  <s:doubleselect label="地域" list="#place.keySet()" name="province"
   doubleName="city" formName="form1" doubleList="#place[top]" />
</s:form> --%>
  <s:form action="doubleselectTag">
       <s:doubleselect label="请选择所在城市" name="city" list="citys"
        listKey="id" listValue="name" doubleList="zoneMap.get(top.id)" doubleListKey="id"
        doubleListValue="name" doubleName="zone"
        headerValue="----请选择----" emptyOption="true" />
  </s:form>
  <!--
      需要注意的点：
        1、list 是doubleSelectTagAction中第一个泛型集合的名称
        2、listKey 是City类的属性id    
        3、listValue 是City类的属性name
        4、doubleList  是读取map集合，top是当前list中集合的对象，注意 cityMap.get(top.id)中的id和listkey的值必须相同，cityMap是集合名称
        5、doublelistkey  是map集合对象的id
        
        如果DoubleSelectTagAction中 map集合存放的键是对象的话，那么doubleList就必须改为对象【doubleList="cities"】
   -->
  </body>
</html>
