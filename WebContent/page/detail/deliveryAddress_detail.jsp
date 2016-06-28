<%@page import="com.chdw.loc.dao.impl.DeliveryAddressDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.DeliveryAddress"%>
<%@ page import="com.chdw.loc.dao.impl.DeliveryAddressDaoImpl"%>
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
			<li><a href="#">添加送餐地址</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加送餐地址</span>
		</div>

		<form action="<%=path%>/DeliveryAddressDBop" method="post"
			id="addInfo">
			<input type="hidden" value="add" name="opType">
			<ul class="forminfo">
				<li><label>点餐人姓名</label><input name="da_name" type="text"
					class="dfinput" value="" /></li>
				<li><label>点餐人手机号</label><input name="da_phone" type="text"
					class="dfinput" value="" /></li>
				<li><label>送餐地址</label><input name="da_address" type="text"
					class="dfinput" value="" /></li>
				<li><label>用户编号</label><input name="u_id" type="text"
					class="dfinput" value="" /></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>
			</ul>
		</form>

		<%
			} else {
				DeliveryAddressDaoImpl deliveryAddressDaoImpl = new DeliveryAddressDaoImpl();
				DeliveryAddress da = deliveryAddressDaoImpl.findAll(
						queryCondition).get(0);
		%>
		<li><a href="#">送餐地址详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>送餐地址详细信息</span>
		</div>


		<form action="<%=path%>/DeliveryAddressDBop" method="post"
			id="updateInfo">

			<input type="hidden" value="update" name="opType">
			<ul class="forminfo">
				<li><label>送餐地址编号</label><input name="da_id" type="text"
					class="dfinput" value="<%=da.getDa_id()%>" readonly="readonly" /></li>
				<li><label>点餐人姓名</label><input name="da_name" type="text"
					class="dfinput" value="<%=da.getDa_name()%>" /></li>
				<li><label>点餐人手机号</label><input name="da_phone" type="text"
					class="dfinput" value="<%=da.getDa_phone()%>" /></li>
				<li><label>送餐地址</label><input name="da_address" type="text"
					class="dfinput" value="<%=da.getDa_address()%>" /></li>
				<li><label>用户编号</label><input name="u_id" type="text"
					class="dfinput" value="<%=da.getU_id()%>" readonly="readonly" /></li>
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