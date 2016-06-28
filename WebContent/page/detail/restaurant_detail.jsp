<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.Restaurant"%>
<%@ page import="com.chdw.loc.dao.impl.RestaurantDaoImpl"%>
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
	<script language="javascript">
	function commitAddInfo(){
		 if(confirm('确定添加吗?')) {
			 $("#addInfo").submit();
			 return true;
		 }
	}
	
	function commitUpdateInfo(){
		 if(confirm('确定修改吗?')) {
			 $("#updateInfo").submit();
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
    <li><a href="#">餐厅管理</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加餐厅</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加餐厅</span></div>
	
	<form  action="<%=path %>/RestaurantDBop" method="post" id="addInfo">
    		<input type="hidden" value="add" name="opType">
	
    <ul class="forminfo">
    	<li><label>餐厅图片</label>
	    		<img src="<%=path %>/upload/RestIcon/default.jpg" />
    	</li>
	    <li><label>餐厅名称</label><input name="r_name" type="text" class="dfinput" value=""/></li>
	    <li><label>好评度</label><input name="r_degree" type="text" class="dfinput" value=""/></li>
	    <li><label>具体地址</label><input name="r_address" type="text" class="dfinput" value=""/></li>
	    <li><label>联系方式</label><input name="r_contact" type="text" class="dfinput" value=""/></li>
	    <li><label>可提前预定天数</label><input name="r_advandays" type="text" class="dfinput" value=""/></li>
	    <li><label>营业时间</label><input name="r_starttime" type="text" class="dfinput" value=""/> ~ <input name="r_endtime" type="text" class="dfinput" value=""/></li>
	    <li><label>营业状态</label><cite><input name="r_status" type="radio" value="" checked="checked" />正在营业&nbsp;&nbsp;&nbsp;&nbsp;<input name="r_status" type="radio" value="" />暂停营业</cite></li>
	    <li><label>餐厅公告</label><textarea name="r_notice" cols="" rows="" class="textinput" ></textarea></li>
	    <li><label>餐厅简介</label><textarea name="r_intro" cols="" rows="" class="textinput" ></textarea></li>
	    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
    </ul>
    </form>
<%
	} else {
		RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();
		Restaurant rest = restaurantDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">用户详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>详细信息</span></div>
    
   	<form action="<%=path %>/RestaurantDBop" method="post" id="updateInfo">
   	<input type="hidden" value="update" name="opType">
   	<input type="hidden" value="<%=rest.getR_icon() %>" name="r_icon">
   	
	    <ul class="forminfo">	
	    		<li><label>餐厅图片</label>
    				<img src="<%=path %><%=rest.getR_icon() %>" />
    				<a href="<%=path %>/upload/restIconUpload.jsp?condition= where r_id='<%=rest.getR_id()%>'">修改头像</a>
    			</li>	
	    		<li><label>餐厅编号</label><input name="r_id" type="text" class="dfinput" value="<%=rest.getR_id()%>"/></li>
			    <li><label>餐厅名称</label><input name="r_name" type="text" class="dfinput" value="<%=rest.getR_name()%>"/></li>
			    <li><label>好评度</label><input name="r_degree" type="text" class="dfinput" value="<%=rest.getR_degree()%>"/></li>
			    <li><label>具体地址</label><input name="r_address" type="text" class="dfinput" value="<%=rest.getR_address()%>"/></li>
			    <li><label>联系方式</label><input name="r_contact" type="text" class="dfinput" value="<%=rest.getR_contact()%>"/></li>
			    <li><label>可提前预定天数</label><input name="r_advandays" type="text" class="dfinput" value="<%=rest.getR_advandays()%>"/></li>
			    <li><label>营业时间</label><input name="r_starttime" type="text" class="dfinput" value="<%=rest.getR_starttime()%>"/> ~ <input name="r_endtime" type="text" class="dfinput" value="<%=rest.getR_endtime()%>"/></li>
			    <li><label>营业状态</label><cite><input name="r_status" type="radio" value="true" checked="checked" />正在营业&nbsp;&nbsp;&nbsp;&nbsp;<input name="r_status" type="radio" value="false" />暂停营业</cite></li>
			    <li><label>餐厅公告</label><textarea name="r_notice" cols="" rows="" class="textinput" ><%=rest.getR_notice()%></textarea></li>
			    <li><label>餐厅简介</label><textarea name="r_intro" cols="" rows="" class="textinput" ><%=rest.getR_intro()%></textarea></li>
			    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改" onclick="commitUpdateInfo()"/></li>
	    </ul>
   	</form>
<%
	}
%>    
    
    </div>
</body>
</html>