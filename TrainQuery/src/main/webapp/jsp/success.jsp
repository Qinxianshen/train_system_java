<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/normalize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/component.css" />

<title>Success</title>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/img/login/0.jpg); background-size: cover;">

	<div class="logo_box">
		<h3>火车订票系统</h3>
		<h2>--------登录成功，欢迎${username}---</h2>
	</div>
</body>
</html>