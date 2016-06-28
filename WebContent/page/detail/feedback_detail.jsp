<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.Feedback"%>
<%@ page import="com.chdw.loc.dao.impl.FeedbackDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>反馈详细信息</title>
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
	<li><a href="#">添加反馈</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加反馈</span></div>
    <ul class="forminfo">
    	
    	
    	<form  action="<%=path %>/FeedbackDBop" method="post" id="addInfo">
    		<input type="hidden" value="add" name="opType">
		    <li><label>反馈内容</label><textarea name="fb_content" cols="" rows="" class="textinput" ></textarea></li>
		    <li><label>用户编号</label><input name="u_id" type="text" class="dfinput" value=""/></li>
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
	    </form>
    </ul>
<%
	} else {
		FeedbackDaoImpl feedbackDaoImpl = new FeedbackDaoImpl();
		Feedback fb = feedbackDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">反馈详细信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>反馈详细信息</span></div>
    <ul class="forminfo">
    	
    	<form  action="<%=path %>/FeedbackDBop" method="post" id="updateInfo">
    		<input type="hidden" value="update" name="opType">
		    <li><label>反馈编号</label><input name="fb_id" type="text" class="dfinput" value="<%=fb.getFb_id()%>" readonly="readonly"/></li>
		    <li><label>反馈内容</label><textarea name="fb_content" cols="" rows="" class="textinput" readonly="readonly" ><%=fb.getFb_content() %></textarea></li>
		    <li><label>反馈日期</label><input name="fb_date" type="text" class="dfinput" value="<%=fb.getFb_date() %>" readonly="readonly"/></li>
		    <li><label>反馈用户编号</label><input name="u_id" type="text" class="dfinput" value="<%=fb.getU_id() %>" readonly="readonly"/></li>
		    <li><label>反馈用户昵称</label><input name="user_alias" type="text" class="dfinput" value="<%=fb.getUser_alias()%>" readonly="readonly"/></li>
		   <!--  <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交修改" onclick="commitUpdateInfo()" /></li> -->
	    </form>
    </ul>
<%
	}
%>    
    
    </div>
    

</body>
</html>