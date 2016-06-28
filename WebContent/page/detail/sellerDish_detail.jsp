<%@page import="com.chdw.loc.domain.SellerMenuType"%>
<%@page import="java.util.List"%>
<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.SellerDish"%>
<%@ page import="com.chdw.loc.dao.impl.SellerDishDaoImpl"%>
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
<script language="javascript">
		$(function(){	
			//导航切换
			$(".imglist li").click(function(){
				$(".imglist li.selected").removeClass("selected")
				$(this).addClass("selected");
			})	
		})	
		
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
			<li><a href="#">商家菜肴</a></li>

			<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
			<li><a href="#">添加菜肴(商家)</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加菜肴</span>
		</div>

		<form action="<%=path %>/SellerDishDBop" method="post" id="addInfo">
			<input type="hidden" value="add" name="opType">

			<ul class="forminfo">
				<li><label>菜肴名称</label><input name="sd_name" type="text"
					class="dfinput" value="" /></li>
				<li><label>已销售数量</label><input name="sd_salecount" type="text"
					class="dfinput" value="" /></li>
				<li><label>价格</label><input name="sd_price" type="text"
					class="dfinput" value="" /></li>
				<li><label>所属类别编号</label> <% if(session.getAttribute("identityId")!=null){
				if(session.getAttribute("SellerMenuType")!=null) {%>
				<select name="smt_id" class="dfinput">
				<%List<SellerMenuType> sellerMenuTypes=(List<SellerMenuType>)session.getAttribute("SellerMenuType");
				for (SellerMenuType sellerMenuType : sellerMenuTypes) {
				%>
				<option value="<%=sellerMenuType.getSmt_id()%>"><%=sellerMenuType.getSmt_name()%></option>
				<%} %>
				</select>
				<%}else {%>
				 <input name="smt_id" type="text" class="dfinput" value="" />
				<%}
				}else{%>
				 <input name="smt_id" type="text" class="dfinput" value="" />
					<%} %></li>
				<li><label>&nbsp;</label><input type="button" class="btn"
					value="确认添加" onclick="commitAddInfo()" /></li>
			</ul>
		</form>
		<%
	} else {
		SellerDishDaoImpl sellerDishDaoImpl = new SellerDishDaoImpl();
		SellerDish sd = sellerDishDaoImpl.findAll(queryCondition).get(0);
%>
		<li><a href="#">菜单类别详细信息</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>菜肴详细信息</span>
		</div>

		<form action="<%=path %>/SellerDishDBop" method="post" id="updateInfo">
			<input type="hidden" value="update" name="opType"> <input
				type="hidden" value="<%=sd.getSd_icon() %>" name="sd_icon">

			<ul class="forminfo">
				<li><label>菜肴图片</label> <img
					src="<%=path %><%=sd.getSd_icon() %>" /> <a
					href="<%=path %>/upload/sellerDishIconUpload.jsp?condition= where sd_id='<%=sd.getSd_id()%>'">修改图片</a>
				</li>
				<li><label>菜肴编号</label><input name="sd_id" type="text"
					class="dfinput" value="<%=sd.getSd_id()%>" readonly="readonly" /></li>
				<li><label>菜肴名称</label><input name="sd_name" type="text"
					class="dfinput" value="<%=sd.getSd_name()%>" /></li>
				<li><label>已销售数量</label><input name="sd_salecount" type="text"
					class="dfinput" value="<%=sd.getSd_saledCount()%>" /></li>
				<li><label>价格</label><input name="sd_price" type="text"
					class="dfinput" value="<%=sd.getSd_price()%>" /></li>
				<li><label>所属类别编号</label><input name="smt_id" type="text"
					class="dfinput" value="<%=sd.getSmt_id()%>" readonly="readonly" /></li>
				<li><label>所属类别名称</label><input name="" type="text"
					class="dfinput" value="<%=sd.getSmt_name()%>" readonly="readonly" /></li>
				<li><label>所属商家编号</label><input name="" type="text"
					class="dfinput" value="<%=sd.getS_id()%>" readonly="readonly" /></li>
				<li><label>所属商家名称</label><input name="" type="text"
					class="dfinput" value="<%=sd.getSeller_name()%>"
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