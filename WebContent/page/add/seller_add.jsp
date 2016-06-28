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
    <li><a href="#">商户管理</a></li>
    <li><a href="#">商家管理</a></li>
    <li><a href="#">添加商家</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>详细信息</span></div>
    
    <ul class="forminfo">
    	<li><label>商家头像</label>
    		<img src="/LOGMag/upload/SellerIcon/default.jpg" />
    		<input name="pic" type="file">
    	</li>
	    <li><label>商家名称</label><input name="sellername" type="text" class="dfinput" value=""/></li>
	    <li><label>好评度</label><input name="degree" type="text" class="dfinput" value=""/></li>
	   	<li><label>平均送达时间</label><input name="" type="text" class="dfinput" value=""/></li>
	    <li><label>联系方式</label><input name="contact" type="text" class="dfinput" value=""/></li>
	    <li><label>营业状态</label><cite><input name="status" type="radio" value="" checked="checked" />true&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="" />false</cite></li>
	    <li><label>商家公告</label><textarea name="signature" cols="" rows="2" class="textinput"></textarea></li>
	    <li><label>商家简介</label><textarea name="signature" cols="" rows="" class="textinput"></textarea></li>
	    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"/></li>
    </ul>
    </div>
    

</body>
</html>