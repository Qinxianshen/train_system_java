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
<style type="text/css">
.houtai{
color:#ffffff;
    border-radius: 20px;
    width: 300px;
    height: 350px;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
}
</style>
<title>后台管理</title>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/img/login/0.jpg); background-size: cover;">
	
	<div class="houtai">
	<h2 style="color:#ffffff">欢迎来到后台管理系统</h2>
	 <form action="/TrainQuery/admin/addTrainStation">
        火车<input name="trainName" type="text">
        <br>
        站名<input name="stationName" type="text">
        <br>
        初始站距离<input name="distance" type="text">
        <br>
        到达时间<input name="arriveTime" type="time">
        <br>
        出发时间<input name="leaveTime" type="time">
        <input type="submit" value="submit">
    </form>
    <form action="/TrainQuery/admin/deleteTrainStation">
        火车<input name="trainName" type="text">
        <br>
        站名<input name="stationName" type="text">
        <br>
        <input type="submit" value="submit">
    </form>
    <form action="/TrainQuery/admin/addStation" method="post">
        增加站名<input name="stationName" type="text">
        <input type="submit" value="submit">
    </form>
    <form action="/TrainQuery/admin/deleteStation" method="post">
        删除站名<input name="stationName" type="text">
        <input type="submit" value="submit">
    </form>
    <form action="/TrainQuery/admin/addTrain" method="post">
        增加列车<input name="trainName" type="text">
        列车站点<input name="trainRoute" type="text">
        <input type="submit" value="submit">
    </form>
    <form action="/TrainQuery/admin/deleteTrain" method="post">
        删除列车<input name="trainName" type="text">
        <input type="submit" value="submit">
    </form>
    <c:forEach var="all_station" items="${AllStation}">
        <tr>
            <td>${all_station}</td>
            <br>
        </tr>
    </c:forEach>
    <c:forEach var="all_train" items="${AllTrain}">
        <tr>
            <td>${all_train.trainName}</td>
            <td>${all_train.trainRoute}</td>
            <br>
        </tr>
    </c:forEach>
    <label><a style="color:#ffffff" href="${pageContext.request.contextPath}/jsp/success.jsp"> 查询</a></label>
	</div>

</body>
</html>