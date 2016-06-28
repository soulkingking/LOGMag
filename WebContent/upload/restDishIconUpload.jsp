<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.RestDish"%>
<%@ page import="com.chdw.loc.dao.impl.RestDishDaoImpl"%>
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
		RestDish rd = new RestDishDaoImpl().findAll(queryCondition).get(0);
%>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">商户管理</a></li>
	    <li><a href="#">餐厅菜肴</a></li>
	    <li><a href="#">图片上传</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="<%=path %>/RestDishIconUpload" method="post" enctype="multipart/form-data">
    	<input type="hidden" value="<%=rd.getRd_id() %>" name="userId">
	    <ul class="imglist">
		    <li class="selected">
		    		<img src="<%=path %><%=rd.getRd_icon() %>" />
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