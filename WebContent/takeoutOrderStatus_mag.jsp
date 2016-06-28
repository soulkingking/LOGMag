<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.TakeoutOrderStatus"%>
<%@ page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="<%=path%>/page/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path%>/page/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/page/js/pro.js"></script>
</head>


<body>
	<%
		PagingBean<TakeoutOrderStatus> pageBean = (PagingBean<TakeoutOrderStatus>) request
				.getAttribute("TakeoutOrderStatusPagingBean");
		int totalRows = pageBean.getTotalRows();
		int totalPages = pageBean.getTotalPages();
		int currPage = pageBean.getCurrPage();
		List<TakeoutOrderStatus> toss = pageBean.getList();
	%>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">用户管理</a></li>
			<li><a href="#">用户订单</a></li>
			<li><a href="#">外卖订单状态</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<form action="TakeoutOrderStatusDBop" method="post" id="multiDel">
			<input type="hidden" value="delete" name="opType">

			<div class="tools">
				<ul class="toolbar">
					<%-- <li class="click"><a
						href="<%=path%>/page/detail/takeoutOrderStatus_detail.jsp"><span><img
								src="<%=path%>/page/images/t01.png" /></span>添加</a></li>
					<li><span><img src="<%=path%>/page/images/t03.png"
							onclick="delete_valid()" /></span>删除</li> --%>
				</ul>

				<ul class="toolbar1">
				</ul>

			</div>

			<table class="tablelist">

				<thead>
					<tr>
						<th></th>
						<th>订单状态编号</th>
						<th>订单状态</th>
						<th>状态修改时间</th>
						<th>订单编号</th>
						<th>下单人名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

					<%
						if (toss.size() == 0) {
							out.println("<tr><td colspan='7'>");
							out.println("<h1>未查询到相关结果!</h1>");
							out.println("</td><tr>");
						} else {
							for (TakeoutOrderStatus tos : toss) {
					%>
					<tr>
						<td><input name="Ids" type="checkbox"
							value="<%=tos.getTos_id()%>" /></td>
						<td><%=tos.getTos_id()%></td>
						<td><%=tos.getTos_status()%></td>
						<td><%=tos.getTos_time()%></td>
						<td><a
							href="<%=path%>/page/detail/takeoutOrder_detail.jsp?condition=where to_id='<%=tos.getTo_id()%>'"><%=tos.getTo_id()%></a></td>
						<td><a
							href="<%=path%>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=tos.getU_id()%>'"><%=tos.getUser_alias()%></a></td>
						<td>
						<%if(tos.getTos_status().equals("已支付")||tos.getTos_status().equals("商家已接单")||tos.getTos_status().equals("配送中")) {
						%>
						<a
							href="<%=path%>/page/detail/takeoutOrderStatus_detail.jsp?condition=where tos_id='<%=tos.getTos_id()%>'"
							class="tablelink">修改</a> &nbsp;&nbsp;
							<%} %>
						<%-- 	<a
							href="TakeoutOrderStatusDBop?opType=delete&Ids=<%=tos.getTos_id()%>"
							class="tablelink">删除</a> --%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>

			<div class="pagin">
				<%-- &condition=<%=where %> --%>
				<div class="message">
					共<i class="blue"><%=totalRows%></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage()%>&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a
						href="TakeoutOrderStatusPaging.view?pageIndex=<%=currPage == 1 ? totalPages : currPage - 1%>"><span
							class="pagepre"></span></a></li>
					<%
						//输出页码的链接
						for (int j = 1; j <= totalPages; j++) {
					%>
					<li class="paginItem"><a
						href="TakeoutOrderStatusPaging.view?pageIndex=<%=j%>"><%=j%></a></li>
					<%
						}
					%>
					<li class="paginItem"><a
						href="TakeoutOrderStatusPaging.view?pageIndex=<%=currPage + 1 > totalPages ? 1 : currPage + 1%>"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>

		</form>
	</div>
</body>
</html>