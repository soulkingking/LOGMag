<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎登录后台管理系统</title>
<link href="<%=path%>/page/css/style.css" rel="stylesheet"
	type="text/css" />
<script language="JavaScript" src="<%=path%>/page/js/jquery.js"></script>
<script src="<%=path%>/page/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});

	function login() {
		$('#loginInfo').submit();
	}
</script>
</head>
<body
	style="background-color: #1c77ac; background-image: url(page/images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="loginbody">
		<span class="systemlogo"></span>
		<form id="loginInfo" action="LoginServlet" method="post">
			<div class="loginbox">
				<ul>
					<li><input name="uname" type="text" class="loginuser"
						value="用户名" onclick="JavaScript:this.value=''" /></li>
					<li><input name="pword" type="password" class="loginpwd"
						onclick="JavaScript:this.value=''" /></li>
					<li><input name="Identity" type="radio" value="管理员" checked="checked"/>管理员&nbsp;&nbsp;&nbsp;&nbsp;<input name="Identity" type="radio" value="商家"/>商家</li>
					<li><input name="" type="button" class="loginbtn" value="登录"
						onclick="login()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</div>
		</form>
	</div>

</body>
</html>