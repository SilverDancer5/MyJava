<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
这里是处理登陆页面
<%
String username = request.getParameter("uesrname");
String password = request.getParameter("password");
System.out.println(username + ":" + password);
%>
</body> 
</html>