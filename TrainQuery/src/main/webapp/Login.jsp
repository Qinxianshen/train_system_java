<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/login/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/login/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/login/component.css" />
<title>login</title>
</head>
<body
	style="background-image: url(img/login/0.jpg); background-size: cover;">

	<div class="logo_box">
		<h3>火车订票系统</h3>
		<form action="/TrainQuery/index/Login" name="f" id="fm" method="post">
			<div class="input_outer">
				<span class="u_user"></span> <input name="userName" class="text"
					style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
			</div>
			<div class="input_outer">
				<span class="us_uer"></span> <input name="password" class="text"
					style="color: #FFFFFF !important; position: absolute; z-index: 100;"
					value="" type="password" placeholder="请输入密码">
			</div>
			<div class="mb2">
				<a class="act-but submit"
					href="javascript:document.getElementById('fm').submit();"
					style="color: #FFFFFF">登录</a>
			</div>
		</form>
	</div>

	<script src="js/login/TweenLite.min.js"></script>
	<script src="js/login/EasePack.min.js"></script>
	<script src="js/login/rAF.js"></script>
	<script src="js/login/demo-1.js"></script>
</body>
</html>