<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.Remark"%>
<%@ page import="com.chdw.loc.bean.PagingBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
	String path = request.getContextPath(); 
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>所有备注</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path %>/page/js/pro.js"></script>
	
</head>

<body>
<%
	PagingBean<Remark> pageBean = (PagingBean<Remark>) request.getAttribute("remarkPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<Remark> remarks = pageBean.getList();
	//out.println(sellers.size());
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">商户管理</a></li>
	    <li><a href="#">商家管理</a></li>
	    <li><a href="#">商家菜肴</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="RemarkDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        	<li class="click"><a href="<%=path %>/page/detail/remark_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        	<li><span><img src="<%=path %>/page/images/t03.png" onclick="delete_valid()"/></span>删除</li>
        </ul>
        
        <ul class="toolbar1">
        </ul>
    
    </div>
    
    <table class="tablelist">
    
    <thead>
    <tr>
    <th></th>
    <th>备注编号</th>
    <th>备注用户</th>
    <th>所属美食圈</th>
    <th>被备注用户</th>
    <th>备注名</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(remarks.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(Remark remark: remarks) {
    %>
    			<tr>
    				 <td><input name="Ids" type="checkbox" value="<%=remark.getRe_id() %>" /></td>
    				 <td><%=remark.getRe_id() %></td>
    				 <td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=remark.getU_id() %>'"><%=remark.getRemark_name() %></a></td>
				    <td><a href="<%=path %>/page/detail/foodRing_detail.jsp?condition=where fr_id='<%=remark.getFr_id() %>'"><%=remark.getFr_name() %></a></td>
				    <td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=remark.getRemarked_id() %>'"><%=remark.getRemarked_name() %></a></td>
				    <td><%=remark.getRe_name() %></td>
				    <td><a href="<%=path %>/page/detail/remark_detail.jsp?condition=where re_id='<%=remark.getRe_id() %>'" class="tablelink">修改</a>
				    &nbsp;&nbsp;<a href="RemarkDBop?opType=delete&Ids=<%=remark.getRe_id() %>" class="tablelink">删除</a></td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">	
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="RemarkPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="RemarkPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="RemarkPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    </form>
    </div>
    
</body>
</html>