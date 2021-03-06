<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.SellerMenuType"%>
<%@ page import="com.chdw.loc.dao.impl.SellerMenuTypeDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商家菜单类别详细信息</title>
<link href="<%=path %>/page/css/style.css" rel="stylesheet"
	type="text/css" />
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
			<li><a href="#">商户管理</a></li>
			<li><a href="#">商家管理信息</a></li>
			<li><a href="#">商家菜单类别</a></li>

			<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
			<li><a href="#">添加类别</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加类别</span>
		</div>

		<form action="<%=path %>/SellerMenuTypeDBop" method="post"
			id="addInfo">
			<input type="hidden" value="add" name="opType">

			<ul class="forminfo">

				<li><label>菜单类别名称</label><input name="smt_name" type="text"
					class="dfinput" value="" /></li>
				<li><label>所属商家编号</label> <% if(session.getAttribute("identityId")!=null){%>
					<input name="s_id" type="text" class="dfinput"
					value="<%=session.getAttribute("identityId")%>" /> <%}else { %> <input
					name="s_id" type="text" class="dfinput" value="" /> <%} %></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>

			</ul>
		</form>
		<%
	} else {
		SellerMenuTypeDaoImpl sellerMenuTypeDaoImpl = new SellerMenuTypeDaoImpl();
		SellerMenuType smt = sellerMenuTypeDaoImpl.findAll(queryCondition).get(0);
%>
		<li><a href="#">菜单类别详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>菜单类别详细信息</span>
		</div>

		<form action="<%=path %>/SellerMenuTypeDBop" method="post"
			id="updateInfo">
			<input type="hidden" value="update" name="opType">
			<ul class="forminfo">

				<li><label>菜单类别编号</label><input name="smt_id" type="text"
					class="dfinput" value="<%=smt.getSmt_id()%>" readonly="readonly" /></li>
				<li><label>菜单类别名称</label><input name="smt_name" type="text"
					class="dfinput" value="<%=smt.getSmt_name() %>" /></li>
				<li><label>所属商家编号</label><input name="s_id" type="text"
					class="dfinput" value="<%=smt.getS_id() %>" readonly="readonly" /></li>
				<li><label>所属商家名称</label><input name="seller_name" type="text"
					class="dfinput" value="<%=smt.getSeller_name() %>"
					readonly="readonly" /></li>
				<li><label>&nbsp;</label><input name="" type="button"
					class="btn" value="提交修改" onclick="commitUpdateInfo()" /></li>
			</ul>
		</form>
		<%
	}
%>

	</div>


</body>
</html>