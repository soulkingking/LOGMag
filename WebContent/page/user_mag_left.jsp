<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>


</head>

<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>用户信息管理
	</div>
<% if(session.getAttribute("identityId")==null){
	 %>
	<dl class="leftmenu">

		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户信息
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../UserInfoPaging.view" target="rightFrame">所有用户</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户订单
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../TakeoutOrderPaging.view" target="rightFrame">外卖订单</a><i></i></li>
				<li><cite></cite><a href="../TakeoutOrderStatusPaging.view"
					target="rightFrame">外卖订单状态</a><i></i></li>
				<!-- <li><cite></cite><a href="../SeatOrderPaging.view"
					target="rightFrame">餐厅订单</a><i></i></li>
				<li><cite></cite><a href="../SeatOrderStatusPaging.view"
					target="rightFrame">餐厅订单状态</a><i></i></li> -->
			</ul>
		</dd>

		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户收藏
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../SellerCollectionPaging.view" target="rightFrame">用户收藏</a><i></i></li>
				<!-- <li><cite></cite><a href="../RestCollectionPaging.view"
					target="rightFrame">餐厅收藏</a><i></i></li> -->
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户反馈
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../FeedbackPaging.view" target="rightFrame">所有反馈</a><i></i></li>
			</ul>
		</dd>

	</dl>
<%}else{
	%>
	<dl class="leftmenu">
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户订单
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../TakeoutOrderPaging.view" target="rightFrame">外卖订单</a><i></i></li>
				<li><cite></cite><a href="../TakeoutOrderStatusPaging.view"
					target="rightFrame">外卖订单状态</a><i></i></li>
				<!-- <li><cite></cite><a href="../SeatOrderPaging.view"
					target="rightFrame">餐厅订单</a><i></i></li>
				<li><cite></cite><a href="../SeatOrderStatusPaging.view"
					target="rightFrame">餐厅订单状态</a><i></i></li> -->
			</ul>
		</dd>
		<dd>
	</dl>
<% }%>
</body>
</html>
