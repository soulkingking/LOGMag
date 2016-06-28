<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.RestMenuType"%>
<%@ page import="com.chdw.loc.dao.impl.RestMenuTypeDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>商家菜单类别详细信息</title>
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
    <li><a href="#">餐厅管理</a></li>
    <li><a href="#">餐厅管理信息</a></li>
    <li><a href="#">餐厅菜单类别</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加类别</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加类别</span></div>
    
    	<form  action="<%=path %>/RestMenuTypeDBop" method="post" id="addInfo">
    		<input type="hidden" value="add" name="opType">
    <ul class="forminfo">		
		    <li><label>菜单类别名称</label><input name="rmt_name" type="text" class="dfinput" value=""/></li>
		    <li><label>所属餐厅编号</label><input name="r_id" type="text" class="dfinput" value=""/></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
    </ul>
    </form>
<%
	} else {
		RestMenuTypeDaoImpl restMenuTypeDaoImpl = new RestMenuTypeDaoImpl();
		RestMenuType rmt = restMenuTypeDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">菜单类别详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>菜单类别详细信息</span></div>
    
    	
    	<form  action="<%=path %>/RestMenuTypeDBop" method="post" id="updateInfo">
    		<input type="hidden" value="update" name="opType">
    <ul class="forminfo">		
    		<li><label>菜单类别编号</label><input name="rmt_id" type="text" class="dfinput" value="<%=rmt.getRmt_id()%>" readonly="readonly"/></li>
    		<li><label>菜单类别名称</label><input name="rmt_name" type="text" class="dfinput" value="<%=rmt.getRmt_name()%>"/></li>
		    <li><label>所属餐厅编号</label><input name="r_id" type="text" class="dfinput" value="<%=rmt.getR_id() %>" readonly="readonly"/></li>
		    <li><label>所属餐厅名称</label><input name="r_name" type="text" class="dfinput" value="<%=rmt.getR_name()%>" readonly="readonly"/></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"  onclick="commitUpdateInfo()"/></li>
	    
    </ul>
    </form>
<%
	}
%>    
    
    </div>
    

</body>
</html>