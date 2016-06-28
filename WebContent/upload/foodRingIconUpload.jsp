<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.FoodRing"%>
<%@ page import="com.chdw.loc.dao.impl.FoodRingDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户头像上传</title>
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
<%
	if(queryCondition == null || queryCondition.equals("")) {
		
	} else {
		FoodRing fr = new FoodRingDaoImpl().findAll(queryCondition).get(0);
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户信息</a></li>
    <li><a href="#">头像上传</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="<%=path %>/FoodRingIconUpload" method="post" enctype="multipart/form-data">
    	<input type="hidden" value="<%=fr.getFr_id() %>" name="frId">
	    <ul class="imglist">
		    <li class="selected">
		    		<img src="<%=path %><%=fr.getFr_icon() %>" />
		    		<br>
		    		<input name="pic" type="file">
		    		<br><br>
		    		<label>&nbsp;</label><input name="" type="submit" class="btn" value="上传头像"/>
			</li>
	    </ul>
    </form>
    
    </div>
<%
	}
%>  
</body>
</html>