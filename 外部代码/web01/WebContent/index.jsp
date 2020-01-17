<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
欢迎你！
<%
	out.println("当前登陆用户：SilverDancer");
	out.println(new Date());
//--%>

<br/>
登陆
<form action="login.jsp" method="post">
<input type="text" name="uesrname"/>
<input type="password" name="password"/>
<input type="submit"/>
</form>

</body>
</html>