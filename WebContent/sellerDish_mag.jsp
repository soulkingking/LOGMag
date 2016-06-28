<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.SellerDish"%>
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
	<script type="text/javascript">
		function deleteInfo(){
			 if(confirm('确定删除吗?')) {
				 $("#multiDel").submit();
				 return true;
			 }
		}
		
		function deleteItem(){
			 if(confirm('确定删除吗?')) {
				 $("#itemDel").submit();
				 return true;
			 }
		}
		
	</script>
</head>


<body>
<%
	PagingBean<SellerDish> pageBean = (PagingBean<SellerDish>) request.getAttribute("sellerDishPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<SellerDish> sds = pageBean.getList();
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
    
     <form action="<%=path %>/SellerDishDBop" method="post" enctype="multipart/form-data" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=path %>/page/detail/sellerDish_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        <li onclick="delete_valid()"><span><img src="<%=path %>/page/images/t03.png" /></span>删除</li>
        </ul>
    </div>
    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th></th>
    <th></th>
    <th>商家菜肴编号</th>
    <th>商家菜肴名称</th>
    <th>已销售数量</th>
    <th>价格</th>
    <th>所属类别</th>
    <th>所属商家</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(sds.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(SellerDish sd: sds) {
    %>
    			<tr>
    				<td><input name="Ids" type="checkbox" value="<%=sd.getSd_id() %>" /></td>
    				<td class="imgtd"><img src="<%=path %><%=sd.getSd_icon() %>" /></td>
				    <td><%=sd.getSd_id() %></td>
				    <td><%=sd.getSd_name() %></td>
				    <td><%=sd.getSd_saledCount() %></td>
				    <td><%=sd.getSd_price() %></td>
				    <td><a href="<%=path %>/page/detail/sellerMenuType_detail.jsp?condition=where smt_id='<%=sd.getSmt_id() %>'"><%=sd.getSmt_name() %></a></td>
				    <td><a href="<%=path %>/page/detail/seller_detail.jsp?condition=where s_id='<%=sd.getS_id() %>'"><%=sd.getSeller_name() %></a></td>
				    <td>
				    	<form action="<%=path %>/SellerDishDBop" method="post" enctype="multipart/form-data" id="itemDel">
				    		<input type="hidden" value="delete" name="opType">
					    	<input type="hidden" value="<%=sd.getSd_id() %>" name="Ids">
					    	<a href="<%=path %>/page/detail/sellerDish_detail.jsp?condition=where sd_id='<%=sd.getSd_id() %>'" class="tablelink">修改</a>
					    	&nbsp;&nbsp;<a href="<%=path %>/SellerDishDBop?opType=delete&Ids=<%=sd.getSd_id() %>" class="tablelink" onclick="deleteInfo()">删除</a>
				    	</form>
				    </td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">	
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="SellerDishPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="SellerDishPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="SellerDishPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    </form>
    </div>
</body>
</html>