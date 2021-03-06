<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.SeatOrder"%>
<%@ page import="com.chdw.loc.dao.impl.SeatOrderDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>订座订单详细信息</title>
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
		
		function commitAddInfo(){
			 if(confirm('确定添加吗?')) {
				 $("#addInfo").submit();
				 return true;
			 }
		}
		
		function commitUpdateInfo(){
			 if(confirm('确定修改吗?')) {
				 $("#updateInfo").submit();
				 return true;
			 }
		}
	</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">商户管理</a></li>
    <li><a href="#">商家管理信息</a></li>
    <li><a href="#">商家菜单类别</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加订座订单类别</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加订座订单类别</span></div>
    <ul class="forminfo">
    	
    	
    	<form  action="<%=path %>/SeatOrderDBop" method="post" id="addInfo">
    		<input type="hidden" value="add" name="opType">
    		
		    <li><label>订座订单人数</label><input name="so_count" type="text" class="dfinput" value=""/></li>
		    <li><label>订座订单要求</label><input name="so_extra" type="text" class="dfinput" value=""/></li>
		    <li><label>下单人姓名</label><input name="so_name" type="text" class="dfinput" value=""/></li>
		    <li><label>下单人手机号</label><input name="so_phone" type="text" class="dfinput" value=""/></li>
		    <li><label>餐厅编号</label><input name="r_id" type="text" class="dfinput" value=""/></li>
		    <li><label>用户编号</label><input name="u_id" type="text" class="dfinput" value=""/></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
	    </form>
    </ul>
<%
	} else {
		SeatOrderDaoImpl seatOrderDaoImpl = new SeatOrderDaoImpl();
		SeatOrder so = seatOrderDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">菜单类别详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>菜单类别详细信息</span></div>
    <ul class="forminfo">
    	
    	<form  action="<%=path %>/SeatOrderDBop" method="post" id="updateInfo">
    		<input type="hidden" value="update" name="opType">
		    <li><label>订座订单编号</label><input name="so_id" type="text" class="dfinput" value="<%=so.getSo_id()%>" readonly="readonly"/></li>
		    <li><label>订座订单时间</label><input name="so_date" type="text" class="dfinput" value="<%=so.getSo_eatTime()%>" readonly="readonly"/></li>
		    <li><label>订座订单数量</label><input name="so_count" type="text" class="dfinput" value="<%=so.getSo_count()%>" /></li>
		    <li><label>订座订单要求</label><input name="so_extra" type="text" class="dfinput" value="<%=so.getSo_extra()%>" /></li>
		    <li><label>订座订单名</label><input name="so_name" type="text" class="dfinput" value="<%=so.getSo_name()%>" /></li>
		    <li><label>订座订单手机号</label><input name="so_phone" type="text" class="dfinput" value="<%=so.getSo_phone()%>" /></li>
		    <li><label>餐厅编号</label><input name="r_id" type="text" class="dfinput" value="<%=so.getR_id()%>" readonly="readonly"/></li>
		    <li><label>用户编号</label><input name="u_id" type="text" class="dfinput" value="<%=so.getU_id()%>" readonly="readonly"/></li>
		    <li><label>餐厅名称</label><input name="r_name" type="text" class="dfinput" value="<%=so.getR_name()%>" readonly="readonly"/></li>
		    <li><label>用户昵称</label><input name="user_alias" type="text" class="dfinput" value="<%=so.getUser_alias() %>" readonly="readonly"/></li>
		    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改"  onclick="commitUpdateInfo()"/></li>
	    </form>
    </ul>
<%
	}
%>    
    
    </div>
</body>
</html>