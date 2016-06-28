<%@page import="com.chdw.loc.dao.impl.DishTableDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.DishTable"%>
<%@ page import="com.chdw.loc.dao.impl.DishTableDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String queryCondition = request.getParameter("condition");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商家菜单类别详细信息</title>
<link href="<%=path%>/page/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path%>/page/js/jquery.js"></script>
<script language="javascript">
	$(function() {
		//导航切换
		$(".imglist li").click(function() {
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
			<li><a href="#">商家管理信息</a></li>
			<li><a href="#">商家菜单类别</a></li>

			<%
				if (queryCondition == null || queryCondition.equals("")) {
			%>
			<li><a href="#">添加类别</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加类别</span>
		</div>

		<form action="<%=path%>/DishTableDBop" method="post"
			id="addInfo">
			<input type="hidden" value="add" name="opType">
			<ul class="forminfo">
				<li><label>当前菜肴的数量</label><input name="d_count" type="text"
					class="dfinput" value="" /></li>
				<li><label>外卖订单编号</label><input name="to_id" type="text"
					class="dfinput" value="" /></li>
				<li><label>商家菜肴编号</label><input name="sd_id" type="text"
					class="dfinput" value="" /></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>
			</ul>
		</form>

		<%
			} else {
				DishTableDaoImpl dishTableDaoImpl = new DishTableDaoImpl();
				DishTable dt = dishTableDaoImpl.findAll(
						queryCondition).get(0);
		%>
		<li><a href="#">菜单类别详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>菜单类别详细信息</span>
		</div>


		<form action="<%=path%>/DishTableDBop" method="post"
			id="updateInfo">

			<input type="hidden" value="update" name="opType">
			<ul class="forminfo">
				<li><label>点菜表编号</label><input name="dt_id" type="text"
					class="dfinput" value="<%=dt.getDt_id() %>" readonly="readonly" /></li>
					<li><label>当前菜肴的数量</label><input name="d_count" type="text"
					class="dfinput" value="<%=dt.getDt_count() %>" /></li>
				<li><label>外卖订单编号</label><input name="to_id" type="text"
					class="dfinput" value="<%=dt.getTo_id() %> readonly="readonly" /></li>
					<li><label>商家菜肴编号</label><input name="sd_id" type="text"
					class="dfinput" value="<%=dt.getSd_id() %> readonly="readonly" /></li>
				
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