<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.chdw.loc.domain.RestComment"%>
<%@page import="java.util.List"%>
<%@page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>餐厅评价</title>
	<link href="<%=path%>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
</head>
<body>
<%
	PagingBean<RestComment> pageBean = (PagingBean) request.getAttribute("restCommentPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<RestComment> rcs = pageBean.getList();
%>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">餐厅评价</a></li>
		</ul>
	</div>
		<div class="rightinfo">
		
			<form action="RestCommentDBop" method="post" id="multiDel">
			<input type="hidden" value="delete" name="opType">
			
			<div class="tools">
				<ul class="toolbar">
					<!--  <li><a href="<%=path %>/page/detail/restComment_detail.jsp"><span><img src="<%=path%>/page/images/t01.png" /></span>添加</a></li>-->
					<li><span><img src="<%=path%>/page/images/t03.png" onclick="delete_valid()"/></span>删除</li>
				</ul>
				
		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th></th>
					<th>评价编号</th>
					<th>餐厅名</th>
					<th>饮食满意度</th>
					<th>服务满意度</th>
					<th>环境满意度</th>
					<th>用户名</th>
					<th>操作</th>
				</tr>
			</thead>
			<%
				if (rcs.size() == 0) {
					out.println("<tr><td colspan='7'>");
					out.println("<h1>未查询到相关结果!</h1>");
					out.println("</td><tr>");
				} else {
					for (RestComment rc : rcs) {
			%>
			<tbody>
				<tr>
					<td><input name="Ids" type="checkbox" value="<%=rc.getRc_id()%>"></td>
					<td><%=rc.getRc_id()%></td>
					<td><a href="<%=path %>/page/detail/restaurant_detail.jsp?condition=where r_id='<%=rc.getR_id() %>'"><%=rc.getRest_name() %></a></td>
					<td><%=rc.getRc_eat()%></td>
					<td><%=rc.getRc_service()%></td>
					<td><%=rc.getRc_env()%></td>
					<td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=rc.getU_id() %>'"><%=rc.getUser_alias() %></a></td>
					<td>
					<a href="<%=path %>/page/detail/RestComment_detail.jsp?condition=where rc_id='<%=rc.getRc_id()%>'" class="tablelink">修改</a>
					&nbsp;&nbsp;&nbsp;&nbsp;<a href="RestCommentDel?opType=delete&Ids=<%=rc.getRc_id()%>" class="tablelink">删除</a>
					</td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
		
		<div class="pagin">
    	<div class="message">共<i class="blue"><%=totalRows%></i>条记录、<i class="blue"><%=totalPages%></i>页,当前显示第&nbsp;<i class="blue"><%=currPage%>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="RestCommentPaging.view?pageIndex=<%=currPage - 1 == 0 ? 1 : currPage - 1%>"><span class="pagepre"></span></a></li>
        <%
	        //输出页码的链接
	    	for (int j = 1; j <= totalPages; j++) {
	        %>
	        	<li class="paginItem"><a href="RestCommentPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
	        <%
    		}
		 %>
        <li class="paginItem"><a href="RestCommentPaging.view?pageIndex=<%=currPage + 1 > totalPages ? totalPages : currPage + 1%>"><span class="pagenxt"></span></a></li>
        </ul>
    </div> 
</form>
</div>
</body>
</html>