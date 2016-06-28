<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.UserInfo"%>
<%@ page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String path = request.getContextPath(); 
	String dir=getServletContext().getRealPath("/upload/UserIcon");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="<%=path %>/page/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
<script type="text/javascript">
		function deleteInfo(){
			 if(confirm('确定删除吗?')) {
				 $("#multiDel").submit();
				 return true;
			 }
		}
		
	</script>
</head>


<body>
	<%
	PagingBean<UserInfo> pageBean = (PagingBean<UserInfo>) request.getAttribute("userInfoPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<UserInfo> userInfos = pageBean.getList();
%>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">用户管理</a></li>
			<li><a href="#">用户信息</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<form action="<%=path %>/UserInfoDBop" method="post" id="multiDel"
			enctype="multipart/form-data">
			<input type="hidden" value="delete" name="opType">

			<div class="tools">
				<ul class="toolbar">
					<li class="click"><a
						href="<%=path %>/page/detail/userInfo_detail.jsp"><span><img
								src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
					<li><span><img src="<%=path %>/page/images/t03.png"
							onclick="delete_valid()" /></span>删除</li>
				</ul>

				<form action="<%=path %>/UserInfoPaging.view" method="post" id="">
					<input name="" type="text" value="" />
					<ul style="float: right;">
						<li><input id="queryId" type="text" class="searchinput"
						     name="condition"
							value="" placeholder="请输入用户名："/>&nbsp; 
							<input id="search" type="submit"
							class="searchbtn" value="查询" /></li>
					</ul>
				</form>

			</div>

			<table class="tablelist">

				<thead>
					<tr>
						<th></th>
						<th width="100px">图片</th>
						<th>用户编号</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>性别</th>
						<th>年龄</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>

					<%
	    if(userInfos.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(UserInfo userInfo: userInfos) {
    %>
					<tr>
						<td><input type="checkbox" value="<%=userInfo.getU_id() %>"
							name="Ids" /></td>
						<td class="imgtd"><img
							src="<%=path %><%=userInfo.getUser_icon() %>" /></td>
						<td><%=userInfo.getU_id() %></td>
						<td><%=userInfo.getUsername() %></td>
						<td><a
							href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=userInfo.getU_id() %>'"><%=userInfo.getUser_alias() %></a></td>
						<td><%=userInfo.getUser_sex() %></td>
						<td><%=userInfo.getUser_age() %></td>
						<td><a
							href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=userInfo.getU_id() %>'"
							class="tablelink">修改</a> &nbsp;&nbsp;<a
							href="<%=path %>/UserInfoDBop?opType=delete&Ids=<%=userInfo.getU_id() %>"
							class="tablelink" onclick="deleteInfo()">删除</a></td>
					</tr>
					<%		}
		} %>
				</tbody>
			</table>

			<div class="pagin">
				<div class="message">
					共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页
				</div>
				<ul class="paginList">
					<li class="paginItem"><a
						href="UserInfoPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span
							class="pagepre"></span></a></li>
					<%
	      //输出页码的链接
	    	for (int j = 1; j <= totalPages; j++) {
	        %>
					<li class="paginItem"><a
						href="UserInfoPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
					<%
	    	}
	        %>

					<li class="paginItem"><a
						href="UserInfoPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span
							class="pagenxt"></span></a></li>
				</ul>
			</div>

		</form>
	</div>
</body>
</html>