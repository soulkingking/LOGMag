<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" errorPage="page/error.jsp"%>
<%@ page import="java.util.*"%> 
<%@ page import="com.chdw.loc.domain.FoodRing"%>
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
		
	</script>
</head>

<body>
<%
	PagingBean<FoodRing> pageBean = (PagingBean<FoodRing>) request.getAttribute("foodRingPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<FoodRing> frViews = pageBean.getList();
	//out.println(sellers.size());
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">美食圈管理</a></li>
		    <li><a href="#">所有圈子</a></li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    <form action="<%=path %>/FoodRingDBop" method="post" id="multiDel">
    <input type="hidden" value="delete" name="opType">
    
    <div class="tools">
    	<ul class="toolbar">
        <li class="click"><a href="<%=path %>/page/detail/foodRing_detail.jsp"><span><img src="<%=path %>/page/images/t01.png" /></span>添加</a></li>
        <li onclick="delete_valid()"><span><img src="<%=path %>/page/images/t03.png" /></span>删除</li>
        </ul>
        
    </div>
    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th></th>
    <th width="100px">图片</th>
    <th>美食圈编号</th>
    <th>美食圈名称</th>
    <th>创建时间</th>
    <th>是否可见</th>
    <th>创建者</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    
    <%
	    if(frViews.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(FoodRing frv: frViews) {
    %>
    			<tr>
    				<td><input name="Ids" type="checkbox" value="<%= frv.getFr_id()%>" /></td>
				    <td class="imgtd"><img src="<%=path %><%=frv.getFr_icon() %>" /></td>
				    <td><%=frv.getFr_id() %></td>
				    <td><a href="<%=path %>/page/detail/foodRing_detail.jsp?condition=where fr_id='<%= frv.getFr_id()%>'"><%=frv.getFr_name() %></a></td>
				    <td><%=frv.getFr_createtime() %></td>
				    <td><%=frv.isFr_visible() %></td>
				    <td><a href="<%=path %>/page/detail/userInfo_detail.jsp?condition=where u_id='<%=frv.getU_id() %>'"><%=frv.getUser_alias() %></a></td>
				    <td>
					    <a href="<%=path %>/page/detail/foodRing_detail.jsp?condition=where fr_id='<%= frv.getFr_id()%>'" class="tablelink">修改</a>
						&nbsp;&nbsp;<a href="<%=path %>/FoodRingDBop?opType=delete&Ids=<%=frv.getFr_id() %>" class="tablelink" onclick="deleteInfo()">删除</a>
					</td>
			    </tr>
    <%		}
		} %>
    </tbody>
    </table>
    
    <div class="pagin">	
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="FoodRingPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="FoodRingPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <li class="paginItem"><a href="FoodRingPagingview?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    </form>
  </div>
	
    
</body>
</html>