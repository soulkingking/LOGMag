<%@page import="com.chdw.loc.dao.impl.TakeoutOrderStatusDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.TakeoutOrderStatus"%>
<%@ page import="com.chdw.loc.dao.impl.TakeoutOrderStatusDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String queryCondition = request.getParameter("condition");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>外卖订单状态详细信息</title>
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
			<li><a href="#">添加外卖订单状态</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加外卖订单状态</span>
		</div>

		<form action="<%=path%>/TakeoutOrderStatusDBop" method="post"
			id="addInfo">
			<input type="hidden" value="add" name="opType">
			<ul class="forminfo">
				<li><label>订单状态</label><input name="tos_status" type="text"
					class="dfinput" value="" /></li>
				<li><label>外卖订单编号</label><input name="to_id" type="text"
					class="dfinput" value="" /></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>
			</ul>
		</form>

		<%
			} else {
				TakeoutOrderStatusDaoImpl takeoutOrderStatusDaoImpl = new TakeoutOrderStatusDaoImpl();
				TakeoutOrderStatus tos = takeoutOrderStatusDaoImpl.findAll(queryCondition).get(0);
		%>
		<li><a href="#">外卖订单状态详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>外卖订单状态详细信息</span>
		</div>


		<form action="<%=path%>/TakeoutOrderStatusDBop" method="post"
			id="updateInfo">

			<input type="hidden" value="update" name="opType">
			<ul class="forminfo">
				<li><label>订单状态编号</label><input name="tos_id" type="text"
					class="dfinput" value="<%=tos.getTos_id()%>" readonly="readonly" /></li>
				<li><label>订单状态</label> <%-- <input name="tos_status" type="text"
					class="dfinput" value="<%=tos.getTos_status()%>" /> --%> 
					<select
					name="tos_status" class="dfinput">
						<%-- <option value="订单已下达" <%if (tos.getTos_status().equals("订单已下达")){
							%>
							selected="selected"
							<%
							}%>>订单已经下达</option>
						<option value="未支付" <%if (tos.getTos_status().equals("未支付")){
							%>
							selected="selected"
							<%
							}%>>未支付</option> --%>
						<option value="已支付" <%if (tos.getTos_status().equals("已支付")){
							%>
							selected="selected"
							<%
							}%>>已支付</option>
						<option value="商家已接单" <%if (tos.getTos_status().equals("商家已接单")){
							%>
							selected="selected"
							<%
							}%>>商家已接单</option>
						<option value="配送中" <%if (tos.getTos_status().equals("配送中")){
							%>
							selected="selected"
							<%
							}%>>配送中</option>
						<option value="已完成" <%if (tos.getTos_status().equals("已完成")){
							%>
							selected="selected"
							<%
							}%>>已完成</option>
				</select></li>
				<li><label>外卖订单编号</label><input name="to_id" type="text"
					class="dfinput" value="<%=tos.getTo_id()%>" readonly="readonly" /></li>
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