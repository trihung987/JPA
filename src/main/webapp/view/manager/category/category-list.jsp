<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
<style>
body {
  background-color: linen;
}

.search {
	display: flex;
	width: 120%;
}

.search-btn{
	transform: translateX(13px)
}
</style>
</head>
<br>
 <form action="<c:url value='/manager/category/search'></c:url>"
		method="get" enctype="multipart/form-data">
<div class="input-search">
<input type="text"
			id="search" name="search" placeholder="Tìm kiếm..." value='${search}'>

<input class="search-btn" type="submit" value="Search">
			
</div>
</form> 
<a href="<c:url value='/manager/category/insert'/>">Thêm category</a>
<br>	
<table border="1">
	<tr>
		<th>STT</th>
		<th>Image</th>
		<th>Category Name</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT" >
		<tr> 
			<td>${STT.index+1 }</td>
			<c:if test="${cate.images.substring(0,5) == 'https' }">
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0,5) != 'https' }">
				<c:url value="/image?from=cate&fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryname }</td>
			<td>${cate.status }</td>
			<td>
				<a href="<c:url value='/manager/category/edit?id=${cate.categoryId }'/>">Sửa</a>
			| 	<a href="<c:url value='/manager/category/delete?id=${cate.categoryId }'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table> 
</html>