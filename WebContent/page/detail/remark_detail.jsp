<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.Remark"%>
<%@ page import="com.chdw.loc.dao.impl.RemarkDaoImpl"%>
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
	<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
	<script type="text/javascript">
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
    <li><a href="#">美食圈管理</a></li>
    <li><a href="#">备注管理</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加备注</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加备注</span></div>
	
	<form  action="<%=path %>/RemarkDBop" method="post" id="addInfo">
    <input type="hidden" value="add" name="opType">
	
    <ul class="forminfo">
    	
		    <li><label>当前用户编号</label><input name="u_id" type="text" class="dfinput" value=""/></li>
		    <li><label>所属美食圈编号</label><input name="fr_id" type="text" class="dfinput" value=""/></li>
		    <li><label>备注用户编号</label><input name="re_userid" type="text" class="dfinput" value=""/></li>
		    <li><label>备注名</label><input name="re_name" type="text" class="dfinput" value=""/></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
	    
    </ul>
    </form>
<%
	} else {
		RemarkDaoImpl remarkDaoImpl = new RemarkDaoImpl();
		Remark remark = remarkDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">备注详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>备注详细信息</span></div>
    
    <form  action="<%=path %>/RemarkDBop" method="post" id="updateInfo">
    <input type="hidden" value="update" name="opType">
    <ul class="forminfo">
    	
    		<li><label>备注编号</label><input name="re_id" type="text" class="dfinput" value="<%=remark.getRe_id()%>" readonly="readonly"/></li>
		    <li><label>当前用户编号</label><input name="u_id" type="text" class="dfinput" value="<%=remark.getU_id()%>"/></li>
		    <li><label>当前用户昵称</label><input name="" type="text" class="dfinput" value="<%=remark.getRemark_name() %>" readonly="readonly"/></li>
		    <li><label>所属美食圈编号</label><input name="fr_id" type="text" class="dfinput" value="<%=remark.getFr_id()%>"/></li>
		    <li><label>所属美食圈名称</label><input name="" type="text" class="dfinput" value="<%=remark.getFr_name()%>" readonly="readonly"/></li>
		    <li><label>备注用户编号</label><input name="re_userid" type="text" class="dfinput" value="<%=remark.getRemarked_id()%>"/></li>
		    <li><label>备注用户昵称</label><input name="" type="text" class="dfinput" value="<%=remark.getRemark_name()%>" readonly="readonly"/></li>
		    <li><label>备注名</label><input name="re_name" type="text" class="dfinput" value="<%=remark.getRe_name()%>"/></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"  onclick="commitUpdateInfo()"/></li>
    </ul>
    </form>
<%
	}
%>    
    </div>

</body>
</html>