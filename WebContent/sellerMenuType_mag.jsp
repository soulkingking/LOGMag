<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.SellerMenuType"%>
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
	PagingBean<SellerMenuType> pageBean = (PagingBean<SellerMenuType>) request.getAttribute("sellerMenuTypePagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<SellerMenuType> smts = pageBean.getList();
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">商户管理</a></li>
	    <li><a href="#">商家管理</a></li>
	    <li><a href="#">商家菜单类别</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="SellerMenuTypeDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    <div class="tools">
    	<ul class="toolbar">
        	<li class="click"><a href="<%=path %>/page/detail/sellerMenuType_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        	<li onclick="delete_valid()"><span><img src="<%=path %>/page/images/t03.png" /></span>删除</li>
        </ul>
        
        <ul class="toolbar1">
        </ul>
    </div>
    
    <table class="tablelist">
    
    <thead>
    <tr>
    <th></th>
    <th>商家菜单类别编号</th>
    <th>商家菜单类别名称</th>
    <th>商家名称</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(smts.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(SellerMenuType smt: smts) {
    %>
    			<tr>
    				<td><input name="Ids" type="checkbox" value="<%=smt.getSmt_id() %>" /></td>
				    <td><%=smt.getSmt_id()%></td>
				    <td><a href="<%=path %>/page/detail/sellerMenuType_detail.jsp?condition=where smt_id='<%=smt.getSmt_id() %>'"><%=smt.getSmt_name() %></a></td>
				    <td><%=smt.getSeller_name() %></td>
				    <td>
				    	<a href="<%=path %>/page/detail/sellerMenuType_detail.jsp?condition=where smt_id='<%=smt.getSmt_id() %>'" class="tablelink">修改</a>
				    	&nbsp;&nbsp;<a href="SellerMenuTypeDBop?opType=delete&Ids=<%=smt.getSmt_id() %>" class="tablelink">删除</a>
				    </td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">					<%-- &condition=<%=where %> --%>
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="SellerMenuTypePaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="SellerMenuTypePaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="SellerMenuTypePaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    </form>
    </div>
    
	<script type="text/javascript">
		$('.imgtable tbody tr:odd').addClass('odd');
	</script>
    
</body>
</html>