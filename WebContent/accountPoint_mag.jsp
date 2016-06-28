<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.AccountPoint"%>
<%@ page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String path = request.getContextPath(); 
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
	
</head>


<body>
<%
	PagingBean<AccountPoint> pageBean = (PagingBean<AccountPoint>) request.getAttribute("accountPointPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<AccountPoint> aps = pageBean.getList();
	//out.println(sellers.size());
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">用户管理</a></li>
	    <li><a href="#">用户积分</a></li>
	    <li><a href="#">所有积分</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="AccountPointDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=path %>/page/detail/accountPoint_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        <li><span><img src="<%=path %>/page/images/t03.png" onclick="delete_valid()"/></span>删除</li>
        </ul>
        
        <ul class="toolbar1">
        </ul>
    
    </div>
    
    <table class="tablelist">
    
    <thead>
    <tr>
    <th></th>
    <th>积分编号</th>
	<th>用户昵称</th>
	<th>当前积分</th>
	<th>最高积分</th>
	<th>最高周排行</th>
	<th>最高月排行</th>
	<th>最高总排行</th>
	<th>本周积分差</th>
	<th>本月积分差</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(aps.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(AccountPoint ap: aps) {
    %>
    			<tr>
    				<td><input type="checkbox" value="<%=ap.getAp_id()%>" name="Ids"></td>
					<td><%=ap.getAp_id() %></td>
					<td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=ap.getU_id() %>'"><%=ap.getUser_alias()%></a></td>
					<td><%=ap.getAp_curpoint()%></td>
					<td><%=ap.getAp_maxpoint()%></td>
					<td><%=ap.getAp_maxWeekRank()%></td>
					<td><%=ap.getAp_maxMonthRank()%></td>
					<td><%=ap.getAp_maxRank()%></td>
					<td><%=ap.getAp_curWeekDif()%></td>
					<td><%=ap.getAp_curMonthDif()%></td>
				    <td><a href="<%=path %>/page/detail/accountPoint_detail.jsp?condition=where ap_id='<%=ap.getAp_id() %>'" class="tablelink">修改</a>
				    &nbsp;&nbsp;<a href="AccountPointDBop?opType=delete&Ids=<%=ap.getAp_id() %>" class="tablelink">删除</a></td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">		
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="AccountPointPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="AccountPointPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="AccountPointPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    </form>
    </div>
</body>
</html>