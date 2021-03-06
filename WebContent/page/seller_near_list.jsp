<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ page import="java.util.*" %>
    <%@ page import="com.chdw.loc.domain.Seller" %>
<%@ page import="com.chdw.loc.bean.PagingBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%
	String path = request.getContextPath(); 
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
	PagingBean<Seller> pageBean = (PagingBean<Seller>) request.getAttribute("sellerPagingBean");
	int totalRows = pageBean.getTotalRows();
	int totalPages = pageBean.getTotalPages();
	int currPage = pageBean.getCurrPage();
	List<Seller> sellers = pageBean.getList();
	//out.println(sellers.size());
%>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#" target="_parent">首页</a></li>
    <li><a href="#">下单</a></li>
    <li><a href="#">叫外卖</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <ul class="classlist">
    <%
	    if(sellers.size() == 0) {
			out.println("<tr><td colspan='7'>");
			out.println("<h1>未查询到相关结果!</h1>");
			out.println("</td><tr>");
		} else {
			for(Seller seller: sellers) {
    %>
    	 <li>
	    <span><img src="<%=path %><%=seller.getSeller_icon() %>" /></span>
	    <div class="lright">
	    <h2><%=seller.getSeller_name() %></h2>
	    <p>好评度：<%=seller.getSeller_degree() %><br />
	    	起送价：￥<%=seller.getSeller_sendprice() %><br />
	    	平均送达时间：<%=seller.getSeller_deliverytime() %>分钟</p>
	    <a href="SellerMenuTypePaging.view?sellerId=<%=seller.getS_id() %>" class="enter">进入商家</a>
	    </div>
	    </li>
    
	<% } 
	}
	%>
    
    </ul>
    
    <div class="clear"></div>
   
    <div class="pagin">					<%-- &condition=<%=where %> --%>
    	<div class="message">共<i class="blue"><%=totalRows %></i>条记录，当前显示第&nbsp;<i class="blue"><%=pageBean.getCurrPage() %>&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="SellerPaging.view?pageIndex=<%=currPage==1?totalPages:currPage - 1 %>"><span class="pagepre"></span></a></li>
        <%
      //输出页码的链接
    	for (int j = 1; j <= totalPages; j++) {
        %>
        	<li class="paginItem"><a href="SellerPaging.view?pageIndex=<%=j %>"><%=j %></a></li>
        <%
    	}
        %>
        
        <%-- <li class="paginItem"><a href="SellerPaging?pageIndex=1">1</a></li>
        <li class="paginItem current"><a href="SellerPaging?pageIndex=2">2</a></li>
        <li class="paginItem"><a href="SellerPaging?pageIndex=3">3</a></li>
        <li class="paginItem"><a href="SellerPaging?pageIndex=4">4</a></li>
        <li class="paginItem"><a href="SellerPaging?pageIndex=5">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;"><%=totalPages %>></a></li> --%>
        <li class="paginItem"><a href="SellerPaging.view?pageIndex=<%=currPage + 1 > totalPages?1:currPage+1 %>"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
   <!--  <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
	      <div class="tipinfo">
	        <span><img src="images/ticon.png" /></span>
	        <div class="tipright">
		        <p>是否确认对信息的修改 ？</p>
		        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
	        </div>
	      </div>
	        
	        <div class="tipbtn">
	        <input name="" type="button"  class="sure" value="确定" />&nbsp;
	        <input name="" type="button"  class="cancel" value="取消" />
	        </div>
	    
	    </div> -->
    </div>

</body>
</html>