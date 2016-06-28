<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.RestMenuType"%>
<%@ page import="com.chdw.loc.domain.RestDish" %>
<%@ page import="com.chdw.loc.bean.PagingBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%
	String path = request.getContextPath(); 
	//out.println(path);
%>
	<title>无标题文档</title>
	<link href="<%=path %>/page/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path %>/page/js/jquery.js"></script>
	<script language="javascript">
	$(function(){	
		//导航切换
		$(".imglist li").click(function(){
			$(".imglist li.selected").removeClass("selected")
			$(this).addClass("selected");
		})	
	})	
	</script>
</head>


<body>
<%
	PagingBean<RestMenuType> restMenuTypePagingBean = (PagingBean<RestMenuType>) request.getAttribute("restMenuTypePagingBean");
	int restMenuTypeTotalRows = restMenuTypePagingBean.getTotalRows();
	int restMenuTypeTotalPages = restMenuTypePagingBean.getTotalPages();
	int restMenuTypeCurrPage = restMenuTypePagingBean.getCurrPage();
	List<RestMenuType> restMenuTypes = restMenuTypePagingBean.getList();
	//out.println(sellers.size());
	
	Map<Integer, PagingBean<RestDish>> allDishesForRest = (Map<Integer, PagingBean<RestDish>>) request.getAttribute("allDishesForRest");
	/* PagingBean<SellerDish> sellerDishPagingBean = (PagingBean<SellerDish>) request.getAttribute("sellerDishPagingBean");
	int sellerDishTotalRows = sellerDishPagingBean.getTotalRows();
	int sellerDishTotalPages = sellerDishPagingBean.getTotalPages();
	int sellerDishCurrPage = sellerDishPagingBean.getCurrPage();
	List<SellerDish> sellerDishes = sellerDishPagingBean.getList(); */
	
%>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="#">下单</a></li>
	    <li><a href="#">下馆子</a></li>
	    <li><a href="#">餐厅</a></li>
	    </ul>
    </div>
   
    <%
	    if(restMenuTypes.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(RestMenuType sellerMenuType: restMenuTypes) {
				List<RestDish> restDishes = allDishesForRest.get(sellerMenuType.getRmt_id()).getList();
    %>
    	<br><h1><%=sellerMenuType.getRmt_name() %></h1>
    	<div class="rightinfo">
    	<ul class="classlist">
    <%
	    if(restDishes.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			%>
			
			<%
			for(RestDish restDish: restDishes) {
				//if(sellerDish.getSmt_id() == sellerMenuType.getSmt_id()) {
    %>
    	
    	 <li>
	    	<span><img src="<%=restDish.getRd_icon() %>" /></span>
	    	<div class="lright">
	    		<h2><%=restDish.getRd_name() %></h2>
	    		<p>
	    		价格：￥<%=restDish.getRd_price() %><br />
	    	</div>
	    </li>
    	 		
	<% //}
				}%>
				
				
				<%
			} %>
			</ul>
    		</div>
    		<div class="clear"></div>
    		<br><br>
	    <%-- <div class="clear"></div>
	    <div class="pagin">					&condition=<%=where %>
	    	<div class="message">共<i class="blue"><%=sellerDishTotalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=sellerMenuTypePagingBean.getCurrPage() %>&nbsp;</i>页</div>
	        <ul class="paginList">
		        <li class="paginItem"><a href="sellerDishPaging?pageIndex=<%=sellerMenuTypeCurrPage==1?sellerMenuTypeTotalPages:sellerMenuTypeCurrPage - 1 %>&smtFk=<%=sellerMenuType.getSmt_id() %>"><span class="pagepre"></span></a></li>
		        <%
		      	//输出页码的链接
		    	for (int j = 1; j <= sellerDishTotalPages; j++) {
		        %>
		        	<li class="paginItem"><a href="sellerDishPaging?pageIndex=<%=j %>&smtFk=<%=sellerMenuType.getSmt_id() %>>"><%=j %></a></li>
		        <%
		    	}
		        %>
		        <li class="paginItem"><a href="sellerDishPaging?pageIndex=<%=sellerDishCurrPage + 1 > sellerDishTotalPages?1:sellerDishCurrPage+1 %>&smtFk=<%=sellerMenuType.getSmt_id() %>"><span class="pagenxt"></span></a></li>
	        </ul>
	    </div> --%>
	    
			<%
		}
	}
	%>
    
    

</body>
</html>