<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.Seller"%>
<%@ page import="com.chdw.loc.dao.impl.SellerDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String queryCondition = request.getParameter("condition");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户详细信息</title>
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
			<li><a href="#">商家管理</a></li>

			<%
				if (queryCondition == null || queryCondition.equals("")) {
			%>
			<li><a href="#">添加商家</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加商家</span>
		</div>

		<form action="<%=path%>/SellerDBop" method="post" id="addInfo">
			<input type="hidden" value="add" name="opType">

			<ul class="forminfo">
				<li><label>商家名称</label><input name="seller_name" type="text"
					class="dfinput" value="" /></li>
				<li><label>好评度</label><input name="seller_degree" type="text"
					class="dfinput" value="" /></li>
				<li><label>起送价</label><input name="seller_sendprice"
					type="text" class="dfinput" value="" />元</li>
				<li><label>配送费</label><input name="seller_df" type="text"
					class="dfinput" value="" />元</li>
				<li><label>平均送达时间</label><input name="seller_deliverytime"
					type="text" class="dfinput" value="" />分钟</li>
				<li><label>联系方式</label><input name="seller_contact" type="text"
					class="dfinput" value="" /></li>
				<li><label>营业时间</label><input name="seller_starttime"
					type="text" class="dfinput" value="" /> ~ <input
					name="seller_endtime" type="text" class="dfinput" value="" /></li>
				<li><label>商家公告</label> <textarea name="seller_notice" cols=""
						rows="" class="textinput"></textarea></li>
				<li><label>商家简介</label> <textarea name="seller_intro" cols=""
						rows="" class="textinput"></textarea></li>
				<li><label>经度</label><input name="seller_longitude" type="text"
					class="dfinput" value="" /></li>
				<li><label>纬度</label><input name="seller_latitude" type="text"
					class="dfinput" value="" /></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>
			</ul>
		</form>
		<%
			} else {
				SellerDaoImpl sellerDaoImpl = new SellerDaoImpl();
				Seller seller = sellerDaoImpl.findAll(queryCondition).get(0);
		%>
		<li><a href="#">商家详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>商家详细信息</span>
		</div>

		<form action="<%=path%>/SellerDBop" method="post" id="updateInfo">
			<input type="hidden" value="update" name="opType"> <input
				type="hidden" value="<%=seller.getSeller_icon()%>" name="sellerIcon">

			<ul class="forminfo">
				<li><label>商家图片</label> <img
					src="<%=path%><%=seller.getSeller_icon()%>" /> <a
					href="<%=path%>/upload/sellerIconUpload.jsp?condition= where s_id='<%=seller.getS_id()%>'">修改头像</a>
				</li>

				<li><label>商家编号</label><input name="s_id" type="text"
					class="dfinput" value="<%=seller.getS_id()%>" readonly="readonly" /></li>
				<li><label>商家名称</label><input name="seller_name" type="text"
					class="dfinput" value="<%=seller.getSeller_name()%>" /></li>
				<li><label>好评度</label><input name="seller_degree" type="text"
					class="dfinput" value="<%=seller.getSeller_degree()%>" /></li>
				<li><label>起送价</label><input name="seller_sendprice"
					type="text" class="dfinput"
					value="<%=seller.getSeller_sendprice()%>" />元</li>
				<li><label>配送费</label><input name="seller_df" type="text"
					class="dfinput" value="<%=seller.getSeller_df()%>" />元</li>
				<li><label>平均送达时间</label><input name="seller_deliverytime"
					type="text" class="dfinput"
					value="<%=seller.getSeller_deliverytime()%>" />分钟</li>
				<li><label>联系方式</label><input name="seller_contact" type="text"
					class="dfinput" value="<%=seller.getSeller_contact()%>" /></li>
				<li><label>营业时间</label><input name="seller_starttime"
					type="text" class="dfinput"
					value="<%=seller.getSeller_starttime()%>" /> ~ <input
					name="seller_endtime" type="text" class="dfinput"
					value="<%=seller.getSeller_endtime()%>" /></li>
				<li><label>商家公告</label> <textarea name="seller_notice" cols=""
						rows="" class="textinput"><%=seller.getSeller_notice()%></textarea></li>
				<li><label>商家简介</label> <textarea name="seller_intro" cols=""
						rows="" class="textinput"><%=seller.getSeller_intro()%></textarea></li>
				<li><label>经度</label><input name="seller_longitude" type="text"
					class="dfinput" value="<%=seller.getSeller_longitude()%>" /></li>
				<li><label>纬度</label><input name="seller_latitude" type="text"
					class="dfinput" value="<%=seller.getSeller_latitude()%>" /></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="提交修改" onclick="commitUpdateInfo()" /></li>
			</ul>
		</form>
		<%
			}
		%>

	</div>
</body>
</html>