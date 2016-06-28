<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.RestComment"%>
<%@ page import="com.chdw.loc.dao.impl.RestCommentDaoImpl"%>
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
		$(function(){	
			//导航切换
			$(".imglist li").click(function(){
				$(".imglist li.selected").removeClass("selected")
				$(this).addClass("selected");
			})	
		})	
		
		function commitAddInfo() {
			if (confirm('确定添加吗?')) {
				$("#addInfo").submit();
				return true;
			}
		}

		function commitUpdateInfo() {
			if (confirm('确定修改吗?')) {
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
    <li><a href="#">商户管理</a></li>
    <li><a href="#">餐厅管理</a></li>
    <li><a href="#">餐厅评价</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加餐厅评价</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加餐厅评价</span></div>
	
    	<form  action="<%=path %>/RestCommentDBop" method="post" id="addInfo">
    	<input type="hidden" value="add" name="opType">
    <ul class="forminfo">		
    		<li><label>餐厅编号</label><input name="r_id" type="text" class="dfinput" value=""/></li>
		    <li><label>评价者编号</label><input name="u_id" type="text" class="dfinput" value=""/></li>
		    <li><label>饮食满意度</label><input name="rc_eat" type="text" class="dfinput" value=""/></li>
		    <li><label>服务满意度</label><input name="rc_service" type="text" class="dfinput" value=""/></li>
		    <li><label>环境满意度</label><input name="rc_env" type="text" class="dfinput" value=""/></li>
		    <li><label>评价内容</label><textarea name="rc_content" cols="" rows="" class="textinput" ></textarea></li>
		    
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
    	</ul>
    </form>
<%
	} else {
		RestCommentDaoImpl restCommentDaoImpl = new RestCommentDaoImpl();
		RestComment rc = restCommentDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">餐厅评价信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>餐厅评价信息</span></div>
    
    	<form  action="<%=path %>/RestCommentDBop" method="post" id="updateInfo">
    	<input type="hidden" value="update" name="opType">
    <ul class="forminfo">
    		
    		<li><label>评价编号</label><input name="rc_id" type="text" class="dfinput" value="<%=rc.getRc_id()%>" readonly="readonly"/></li>
    		<li><label>餐厅编号</label><input name="r_id" type="text" class="dfinput" value="<%=rc.getR_id()%>" readonly="readonly"/></li>
    		<li><label>餐厅名称</label><input name="rest_name" type="text" class="dfinput" value="<%=rc.getRest_name()%>" readonly="readonly"/></li>
		    <li><label>评价者编号</label><input name="u_id" type="text" class="dfinput" value="<%=rc.getU_id()%>" readonly="readonly"/></li>
		    <li><label>评论者</label><input name="user_alias" type="text" class="dfinput" value="<%=rc.getUser_alias()%>" readonly="readonly"/></li>
		    <li><label>饮食满意度</label><input name="rc_eat" type="text" class="dfinput" value="<%=rc.getRc_eat()%>"/></li>
		    <li><label>服务满意度</label><input name="rc_service" type="text" class="dfinput" value="<%=rc.getRc_service()%>"/></li>
		    <li><label>环境满意度</label><input name="rc_env" type="text" class="dfinput" value="<%=rc.getRc_env()%>"/></li>
		    <li><label>评价内容</label><textarea name="sc_content" cols="" rows="" class="textinput" ><%=rc.getRc_content()%></textarea></li>
		    
		    <li><label>&nbsp;</label><input type="button" class="btn" value="提交修改" onclick="commitUpdateInfo()"/></li>
    </ul>
    </form>
<%
	}
%>    
    
    </div>
</body>
</html>