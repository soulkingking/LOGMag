<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>添加反馈</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script language="javascript">
	$(function(){	
		//导航切换
		$(".imglist li").click(function(){
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		});	
	});	
	</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">用户管理</a></li>
	    <li><a href="#">用户反馈管理</a></li>
	    <li><a href="#">添加反馈</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>详细信息</span></div>
	    <ul class="forminfo">
	    	<li><label>反馈人</label><input name="fbHunman" type="text" class="dfinput" value=""/></li>
		    <li><label>反馈内容</label><textarea name="fbContent" cols="" rows="" class="textinput"></textarea></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认提交"/></li>
	    </ul>
    </div>

</body>
</html>