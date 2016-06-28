<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" errorPage="page/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
</head>
<%
	String path = request.getContextPath(); 
%>
<frameset rows="88,*,30" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=path %>/page/main_top.html" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="<%=path %>/page/main_index.html" name="indexFrame" id="indexFrame" title="indexFrame" /><!-- scrolling="No" noresize="noresize"  -->
  <frame src="<%=path %>/page/main_bottom.html" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<body>
</body>
</html>