<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.chdw.loc.domain.UserInfo"%>
<%@ page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户详细信息</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script type="text/javascript">
		function commitAddInfo(){
			 if(confirm('确定添加吗?')) {
				 $("#iconAdd").submit();
				 return true;
			 }
		}
		
		function commitUpdateInfo(){
			 if(confirm('确定修改吗?')) {
				 $("#iconUpdate").submit();
				 return true;
			 }
		}
	</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户信息</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加用户</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加用户信息</span></div>
	
	<form action="<%=path %>/UserInfoDBop" method="post" id="iconAdd">
		<input type="hidden" value="add" name="opType">
		<ul class="forminfo">
		    <li><label>用户名</label><input name="username" type="text" class="dfinput" value=""/></li>
		    <li><label>密码</label><input name="password" type="password" class="dfinput" value=""/></li>
		    <li><label>昵称</label><input name="alias" type="text" class="dfinput" value=""/></li>
		    <li><label>性别</label><cite><input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="女" />女</cite></li>
		    <li><label>年龄</label><input name="age" type="text" class="dfinput" value="20"/></li>
		    <li><label>个性签名</label><textarea name="signature" cols="" rows="" class="textinput" ></textarea></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
	    </ul>
    </form>
<%
	} else {
		UserInfo ui = new UserInfoDaoImpl().findAll(queryCondition).get(0);
%>
	<li><a href="#">用户详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>详细信息</span></div>
    
    <form action="<%=path %>/UserInfoDBop" method="post" id="iconUpdate">
	<input type="hidden" value="update" name="opType">
	<input type="hidden" value="<%=ui.getUser_icon() %>" name="userIcon">
	
	    <ul class="forminfo">
	    	<li><label>用户头像</label>
		    		<img src="<%=path %><%=ui.getUser_icon() %>" />
		    		<a href="<%=path %>/upload/userIconUpload.jsp?condition= where u_id='<%=ui.getU_id()%>'">修改头像</a>
	    	</li>
		    <li><label>用户编号</label><input name="userId" type="text" class="dfinput" value="<%=ui.getU_id()%>" readonly="readonly"/></li>
		    <li><label>用户名</label><input name="username" type="text" class="dfinput" value="<%=ui.getUsername() %>"/></li>
		    <li><label>密码</label><input name="password" type="password" class="dfinput" value="<%=ui.getPassword() %>"/></li>
		    <li><label>昵称</label><input name="alias" type="text" class="dfinput" value="<%=ui.getUser_alias() %>"/></li>
		    <li><label>性别</label><cite><input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="女" />女</cite></li>
		    <li><label>年龄</label><input name="age" type="text" class="dfinput" value="<%=ui.getUser_age() %>"/></li>
		    <li><label>个性签名</label><textarea name="signature" cols="" rows="" class="textinput" ><%=ui.getUser_signature() %></textarea></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"  onclick="commitUpdateInfo()"/></li>
	    </ul>
    </form>
<%
	}
%>    
    
    </div>
</body>
</html>