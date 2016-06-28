<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户详细信息</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script language="javascript">
	$(function(){	
		//导航切换
		$(".imglist li").click(function(){
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		})	
	})	
	</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户信息</a></li>
    <li><a href="#">添加用户</a></li>
    </ul>
    </div>
    
   <%--  <div class="rightinfo">
    
	    <ul class="imglist">
		    <li>
			    <span><img src="<%=path %><%=ui.getUser_icon() %>" /></span>
		    </li>
	    </ul>
    </div> --%>
    
    <div class="formbody">
    <div class="formtitle"><span>详细信息</span></div>
    
    <ul class="forminfo">
    	<li><label>用户头像</label>
    		<img src="/LOGMag/upload/UserIcon/default.png" />
    		<input name="pic" type="file">
    	</li>
	    <li><label>用户名</label><input name="username" type="text" class="dfinput" value=""/></li>
	    <li><label>密码</label><input name="password" type="text" class="dfinput" value=""/></li>
	    <li><label>昵称</label><input name="alias" type="text" class="dfinput" value=""/></li>
	    <li><label>性别</label><cite><input name="sex" type="radio" value="" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="" />女</cite></li>
	    <li><label>年龄</label><input name="age" type="text" class="dfinput" value=""/></li>
	    <li><label>个性签名</label><textarea name="signature" cols="" rows="" class="textinput"></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"/></li>
    </ul>
    </div>
    

</body>
</html>