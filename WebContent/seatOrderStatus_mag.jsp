<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.SeatOrderStatus"%>
<%@ page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SeatOrderStatus_mag</title>
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
	PagingBean<SeatOrderStatus> pageBean = (PagingBean<SeatOrderStatus>) request.getAttribute("seatOrderStatusPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<SeatOrderStatus> soss = pageBean.getList();
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">用户管理</a></li>
	    <li><a href="#">用户收藏</a></li>
	    <li><a href="#">商家收藏</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="SeatOrderStatusDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=path %>/page/detail/seatOrderStatus_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        <li onclick="delete_valid()"><span><img src="<%=path %>/page/images/t03.png" /></span>删除</li>
        </ul>
        
        <ul class="toolbar1">
        </ul>
    
    </div>
    
    <table class="tablelist">
    
    <thead>
    <tr>
    <th></th>
    <th>编号</th>
    <th>订单状态</th>
    <th>状态对应时间</th>
    <th>订单编号</th>
    <th>下单用户</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(soss.size() == 0) {
			out.println("<tr><td colspan='6'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(SeatOrderStatus sos: soss) {
    %>
    			<tr>
    				 <td><input name="Ids" type="checkbox" value="<%=sos.getSos_id() %>" /></td>
				    <td><%=sos.getSos_id() %></td>
				    <td><%=sos.getSos_status() %></td>
				    <td><%=sos.getSos_time() %></td>
				    <td><a href="<%=path %>/page/detail/seatOrder_detail.jsp?condition=where so_id='<%=sos.getSo_id() %>'"><%=sos.getSo_id() %></a></td>
				    <td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=sos.getU_id() %>'"><%=sos.getUser_alias()%></a></td>
				    <td><a href="<%=path %>/page/detail/seatOrderStatus_detail.jsp?condition=where sos_id='<%=sos.getSos_id() %>'" class="tablelink">修改</a>
				    &nbsp;&nbsp;<a href="SeatOrderStatusDBop?opType=delete&Ids=<%=sos.getSos_id() %>" class="tablelink">删除</a></td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">					<%-- &condition=<%=where %> --%>
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="SeatOrderStatusPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="SeatOrderStatusPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="SeatOrderStatusPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
 </form>
 </div>
    
</body>
</html>