<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.RestDish"%>
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
	PagingBean<RestDish> pageBean = (PagingBean<RestDish>) request.getAttribute("restDishPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<RestDish> rds = pageBean.getList();
	//out.println(sellers.size());
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">餐厅管理</a></li>
	    <li><a href="#">餐厅管理</a></li>
	    <li><a href="#">餐厅菜肴</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="<%=path %>/RestDishDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=path %>/page/detail/restDish_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        <li><span><img src="<%=path %>/page/images/t03.png" onclick="delete_valid()"/></span>删除</li>
        </ul>
    
    </div>
    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th></th>
    <th>图片</th>
    <th>餐厅菜肴编号</th>
    <th>餐厅菜肴名称</th>
    <th>价格</th>
    <th>所属类别</th>
    <th>所属餐厅</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(rds.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(RestDish rd: rds) {
    %>
    			<tr>
    				 <td><input name="Ids" type="checkbox" value="<%=rd.getRd_id() %>" /></td>
    				<td class="imgtd"><img src="<%=path %><%=rd.getRd_icon() %>" /></td>
				    <td><%=rd.getRd_id() %></td>
				    <td><%=rd.getRd_name() %></td>
				    <td><%=rd.getRd_price() %></td>
				    <td><a href="<%=path %>/page/detail/restMenuType_detail.jsp?condition=where rmt_id='<%=rd.getRmt_id() %>'"><%=rd.getRmt_name() %></a></td>
				    <td><a href="<%=path %>/page/detail/restaurant_detail.jsp?condition=where r_id='<%=rd.getR_id() %>'"><%=rd.getR_name() %></a></td>
				    <td>
					    <a href="<%=path %>/page/detail/restDish_detail.jsp?condition=where rd_id='<%=rd.getRd_id() %>'" class="tablelink">修改</a>
					    &nbsp;&nbsp;<a href="RestDishDBop?opType=delete&Ids=<%=rd.getRd_id() %>" class="tablelink">删除</a>
				    </td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">	
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="RestDishPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="RestDishPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="RestDishPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    </form>
    </div>
    
</body>
</html>