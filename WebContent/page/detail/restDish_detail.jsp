<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	<title>商家菜单类别详细信息</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script language="javascript">
		$(function(){	
			$(".imglist li").click(function(){
				$(".imglist li.selected").removeClass("selected")
				$(this).addClass("selected");
			})	
		})	
		
		function commitAddInfo(){
			 if(confirm('确定添加吗?')) {
				 $("#addInfo").submit();
				 $("#iconAdd").submit();
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
    <li><a href="#">餐厅菜肴</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加菜肴(餐厅)</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加菜肴</span></div>
	
    <form  action="<%=path %>/RestDishDBop" method="post" id="addInfo">
    <input type="hidden" value="add" name="opType">
    
    <ul class="forminfo">		
		    <li><label>菜肴名称</label><input name="rd_name" type="text" class="dfinput" value=""/></li>
		    <li><label>价格</label><input name="rd_price" type="text" class="dfinput" value=""/></li>
		    <li><label>所属类别编号</label><input name="rmt_id" type="text" class="dfinput" value=""/></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
	    
    </ul>
    </form>
<%
	} else {
		RestDishDaoImpl restDishDaoImpl = new RestDishDaoImpl();
		RestDish rd = restDishDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">菜单类别详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>菜肴详细信息</span></div>
    
    <form  action="<%=path %>/RestDishDBop" method="post" id="updateInfo">
    <input type="hidden" value="update" name="opType">
    <input type="hidden" value="<%=rd.getRd_icon() %>" name="rd_icon">
    
	    <ul class="forminfo">
	    	<li>
	    		<label>菜肴图片</label>
		    	<img src="<%=path %><%=rd.getRd_icon() %>" />
		    	<a href="<%=path %>/upload/restDishIconUpload.jsp?condition= where rd_id='<%=rd.getRd_id()%>'">修改图片</a>
	    	</li>
	    </ul>
	    	
	    <ul class="forminfo">		
	   		<li><label>菜肴编号</label><input name="rd_id" type="text" class="dfinput" value="<%=rd.getRd_id()%>" readonly="readonly"/></li>
		   	<li><label>菜肴名称</label><input name="rd_name" type="text" class="dfinput" value="<%=rd.getRd_name()%>"/></li>
		    <li><label>价格</label><input name="rd_price" type="text" class="dfinput" value="<%=rd.getRd_price()%>"/></li>
		    <li><label>所属类别编号</label><input name="rmt_id" type="text" class="dfinput" value="<%=rd.getRmt_id()%>" readonly="readonly"/></li>
		    <li><label>所属类别名称</label><input name="" type="text" class="dfinput" value="<%=rd.getRmt_name()%>" readonly="readonly"/></li>
		    <li><label>所属餐厅编号</label><input name="" type="text" class="dfinput" value="<%=rd.getR_id()%>" readonly="readonly"/></li>
		    <li><label>所属餐厅名称</label><input name="" type="text" class="dfinput" value="<%=rd.getR_name()%>" readonly="readonly"/></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"  onclick="commitUpdateInfo()"/></li>
	    </ul>
    </form>
<%
	}
%>    
    
    </div>
    

</body>
</html>