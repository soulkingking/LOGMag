<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><?xml version="1.0" encoding="UTF-8"?>
<videonews> 
	<c:forEach items="${userInfoPagingBean}" var="pagingBean">
		<PagingBean pageSize="${pagingBean.pageSize}"> 
			<currPage>${pagingBean.currPage}</currPage>
			<totalRows>${pagingBean.totalRows}</totalRows> 
			<totalPages>${pagingBean.totalPages}</totalPages>
		</PagingBean>
	</c:forEach>
</videonews>