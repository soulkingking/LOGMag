<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chdw.loc.domain.SellerMenuType"%>
<%@ page import="com.chdw.loc.domain.SellerDish" %>
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
	PagingBean<SellerMenuType> sellerMenuTypePagingBean = (PagingBean<SellerMenuType>) request.getAttribute("sellerMenuTypePagingBean");
	int sellerMenuTypeTotalRows = sellerMenuTypePagingBean.getTotalRows();
	int sellerMenuTypeTotalPages = sellerMenuTypePagingBean.getTotalPages();
	int sellerMenuTypeCurrPage = sellerMenuTypePagingBean.getCurrPage();
	List<SellerMenuType> sellerMenuTypes = sellerMenuTypePagingBean.getList();
	//out.println(sellers.size());
	
	Map<Integer, PagingBean<SellerDish>> allDishesForSeller = (Map<Integer, PagingBean<SellerDish>>) request.getAttribute("allDishesForSeller");
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
	    <li><a href="#">叫外卖</a></li>
	    <li><a href="#">商家</a></li>
	    </ul>
    </div>
   
    <%
	    if(sellerMenuTypes.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(SellerMenuType sellerMenuType: sellerMenuTypes) {
				List<SellerDish> sellerDishes = allDishesForSeller.get(sellerMenuType.getSmt_id()).getList();
    %>
    	<br><div class="place">
    			<span><%=sellerMenuType.getSmt_name() %></span>
    		</div>
    	<div class="rightinfo">
    	<ul class="classlist">
    <%
	    if(sellerDishes.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			%>
			
			<%
			for(SellerDish sellerDish: sellerDishes) {
				//if(sellerDish.getSmt_id() == sellerMenuType.getSmt_id()) {
    %>
    	
    	 <li>
	    	<span><img src="<%=sellerDish.getSd_icon() %>" /></span>
	    	<div class="lright">
	    		<h2><%=sellerDish.getSd_name() %></h2>
	    		<p>已销售数量：<%=sellerDish.getSd_saledCount() %><br />
	    		价格：￥<%=sellerDish.getSd_price() %><br />
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