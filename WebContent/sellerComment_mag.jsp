<%@page import="com.chdw.loc.domain.SellerComment"%>
<%@page import="java.util.List"%>
<%@page import="com.chdw.loc.bean.PagingBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	PagingBean<SellerComment> pageBean = (PagingBean) request.getAttribute("sellerCommentPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<SellerComment> scs = pageBean.getList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商家评价管理</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
	
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">商家评价列表</a></li>
		</ul>
	</div>
	
		<div class="rightinfo">
		
			<form action="SellerCommentTypeDBop" method="post" id="multiDel">
			<div class="tools">

				<ul class="toolbar">
					<!-- <li class="click"><a href="<%=path %>/page/detail/sellerComment_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li> -->
        			<li><span><img src="<%=path %>/page/images/t03.png" onclick="delete_valid()"/></span>删除</li>
				</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th></th>
					<th>评价编号</th>
					<th>商家名</th>
					<th>饮食评价</th>
					<th>服务评价</th>
					<th>评价者</th>
					<th>操作</th>
				</tr>
			</thead>
			<!-- 数据的显示区 -->
			<%
				if (scs.size() == 0) {
					out.println("<tr><td colspan='7'>");
					out.println("<h1>未查询到相关结果!</h1>");
					out.println("</td><tr>");
				} else {
					for (SellerComment sc : scs) {
			%>
			<tbody>
				<tr>
					<td><input type="checkbox" value="<%=sc.getSc_id()%>" name="Ids"></td>
					<td><%=sc.getSc_id()%></td>
					<td><a href="<%=path %>/page/detail/seller_detail.jsp?condition=where s_id='<%=sc.getS_id() %>'"><%=sc.getSeller_name()%></a></td>
					<td><%=sc.getSc_eat()%></td>
					<td><%=sc.getSc_service()%></td>
					<td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=sc.getU_id() %>'"><%=sc.getUser_alias()%></a></td>
					<td>
						<a href="SellerCommentDBop?opType=delete&Ids=<%=sc.getSc_id() %>" class="tablelink">删除</a>
					</td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
		<!-- 分页显示区 -->
		<div class="pagin">
    	<div class="message">共<i class="blue"><%=totalRows%></i>条记录、<i class="blue"><%=totalPages%></i>页,当前显示第&nbsp;<i class="blue"><%=currPage%>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="SellerCommentPaging.view?pageIndex=<%=currPage - 1 == 0 ? 1 : currPage - 1%>"><span class="pagepre"></span></a></li>
        <!-- 页序号显示 --> 
        <%
      	//输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="SellerCommentPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        <li class="paginItem"><a href="SellerCommentPaging.view?pageIndex=<%=currPage + 1 > totalPages ? totalPages : currPage + 1%>"><span class="pagenxt"></span></a></li>
        </ul>
    </div> 
</form>
</div>
</body>
</html>