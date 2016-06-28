<%@page import="com.chdw.loc.dao.impl.UserInfoDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.chdw.loc.domain.AccountPoint"%>
<%@ page import="com.chdw.loc.dao.impl.AccountPointDaoImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath(); 
	String queryCondition = request.getParameter("condition");
%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>用户详细信息</title>
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
		
		
		function commitAddInfo() {
			if (confirm('确定添加吗?')) {
				$("#addInfo").submit();
				return true;
			}
		}

		function commitUpdateInfo() {
			if (confirm('确定修改吗?')) {
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
    <li><a href="#">用户管理</a></li>
    <li><a href="#">用户积分</a></li>
    <li><a href="#">积分信息</a></li>
    
<%
	if(queryCondition == null || queryCondition.equals("")) {
%>
	<li><a href="#">添加积分信息</a></li>
    </ul>
    </div>
    <div class="formbody">
	<div class="formtitle"><span>添加积分信息</span></div>
	
    	<form  action="<%=path %>/AccountPointDBop" method="post" id="addInfo">
    	<input type="hidden" value="add" name="opType">
    <ul class="forminfo">		
		    <li><label>当前积分</label><input name="ap_curpoint" type="text" class="dfinput" value=""/></li>
		    <li><label>最高积分</label><input name="ap_maxpoint" type="text" class="dfinput" value=""/></li>
		    <li><label>最高周排行</label><input name="ap_maxWeekRank" type="text" class="dfinput" value=""/></li>
		    <li><label>最高月排行</label><input name="ap_maxMonthRank" type="text" class="dfinput" value=""/></li>
		    <li><label>最高总排行</label><input name="ap_maxRank" type="text" class="dfinput" value=""/></li>
		    <li><label>本周积分差</label><input name="ap_curWeekDif" type="text" class="dfinput" value=""/></li>
		    <li><label>本月积分差</label><input name="ap_curMonthDif" type="text" class="dfinput" value=""/></li>
		    <li><label>所属者编号</label><input name="u_id" type="text" class="dfinput" value=""/></li>
		    
		    <li><label>&nbsp;</label><input type="button" class="btn" value="确认添加" onclick="commitAddInfo()"/></li>
    	</ul>
    </form>
<%
	} else {
		AccountPointDaoImpl accountPointDaoImpl = new AccountPointDaoImpl();
		AccountPoint ap = accountPointDaoImpl.findAll(queryCondition).get(0);
%>
	<li><a href="#">积分信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div class="formtitle"><span>积分信息</span></div>
    
    	<form  action="<%=path %>/AccountPointDBop" method="post" id="updateInfo">
    	<input type="hidden" value="update" name="opType">
    <ul class="forminfo">		
    		<li><label>积分编号</label><input name="ap_id" type="text" class="dfinput" value="<%=ap.getAp_id()%>" readonly="readonly"/></li>
    		<li><label>当前积分</label><input name="ap_curpoint" type="text" class="dfinput" value="<%=ap.getAp_curpoint()%>"/></li>
		    <li><label>最高积分</label><input name="ap_maxpoint" type="text" class="dfinput" value="<%=ap.getAp_maxpoint()%>"/></li>
		    <li><label>最高周排行</label><input name="ap_maxWeekRank" type="text" class="dfinput" value="<%=ap.getAp_maxWeekRank()%>"/></li>
		    <li><label>最高月排行</label><input name="ap_maxMonthRank" type="text" class="dfinput" value="<%=ap.getAp_curMonthDif()%>"/></li>
		    <li><label>最高总排行</label><input name="ap_maxRank" type="text" class="dfinput" value="<%=ap.getAp_maxRank()%>"/></li>
		    <li><label>本周积分差</label><input name="ap_curWeekDif" type="text" class="dfinput" value="<%=ap.getAp_curWeekDif()%>"/></li>
		    <li><label>本月积分差</label><input name="ap_curMonthDif" type="text" class="dfinput" value="<%=ap.getAp_curMonthDif()%>"/></li>
		    <li><label>所属者编号</label><input name="u_id" type="text" class="dfinput" value="<%=ap.getU_id()%>" readonly="readonly"/></li>
		    <li><label>所属者名称</label><input name="user_alias" type="text" class="dfinput" value="<%=ap.getUser_alias()%>" readonly="readonly"/></li>
		    
		    <li><label>&nbsp;</label><input type="button" class="btn" value="提交修改" onclick="commitUpdateInfo()"/></li>
    </ul>
    </form>
<%
	}
%>    
    </div>
</body>
</html>