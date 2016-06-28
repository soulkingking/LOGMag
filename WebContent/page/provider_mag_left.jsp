<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background: #f0f9fd;">
	<div class="lefttop">
		<span></span>商户信息管理
	</div>

	<dl class="leftmenu">
		<!-- 叫外卖 -->
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>商家管理
			</div>
			<ul class="menuson">
				<% if(session.getAttribute("identityId")==null){
				%>
				<li class="active"><cite></cite><a href="../SellerPaging.view"
					target="rightFrame">所有商家</a><i></i></li>
				<li><cite></cite><a href="../SellerMenuTypePaging.view"
					target="rightFrame">商家菜单类别</a><i></i></li>
				<li><cite></cite><a href="../SellerDishPaging.view"
					target="rightFrame">商家菜肴信息</a><i></i></li>
				<li><cite></cite><a href="../SellerCommentPaging.view"
					target="rightFrame">所有商家评价</a><i></i></li>
				<% }else{%>
				<li><cite></cite><a href="../SellerMenuTypePaging.view"
					target="rightFrame">商家菜单类别</a><i></i></li>
				<li><cite></cite><a href="../SellerDishPaging.view"
					target="rightFrame">商家菜肴信息</a><i></i></li>
				<li><cite></cite><a href="../SellerCommentPaging.view"
					target="rightFrame">所有商家评价</a><i></i></li>
				<% }%>


			</ul>
		</dd>

		<!-- 下馆子 -->
		<!-- <dd>
			<div class="title">
				<span><img src="images/leftico02.png" /></span>餐厅管理
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a
					href="../RestaurantPaging.view" target="rightFrame">所有餐厅</a><i></i></li>
				<li><cite></cite><a href="../RestMenuTypePaging.view"
					target="rightFrame">餐厅菜单类别</a><i></i></li>
				<li><cite></cite><a href="../RestDishPaging.view"
					target="rightFrame">餐厅菜肴信息</a><i></i></li>
				<li><cite></cite><a href="../RestCommentPaging.view"
					target="rightFrame">所有餐厅评价</a><i></i></li>
			</ul>
		</dd> -->
	</dl>

</body>
</html>
