<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
			id="search" name="search" placeholder="Tìm kiếm...">

<input class="search-btn" type="submit" value="Search">
			
</div>
</form> 
<a href="<c:url value='/manager/video/insert'/>">Thêm video</a>

<table border="1">
	<tr>
		<th>STT</th>
		<th>Poster</th>
		<th>Video</th>
		<th>Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Active</th>
		<th>CategoryID</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="video" varStatus="STT" >
		<tr> 
			<td>${STT.index+1 }</td>
			<c:if test="${video.poster.substring(0,5) == 'https' }">
				<c:url value="${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.poster.substring(0,5) != 'https' }">
				<c:url value="/image?from=vid&fname=${video.poster}" var="imgUrl"></c:url>
			</c:if>
			<td><img height="150" width="200" src="${imgUrl}" /></td>
			
			<c:url value="/video?fname=${video.videoId}" var="videoUrl"></c:url>
			<td><video width="200" height="150" controls> <source src ="${videoUrl}"> </video> </td>
			
			<td>${video.title}</td>
			<td>${video.description}</td>
			<td>${video.views}</td>
			<td>${video.active}</td>
			<td>${video.category.categoryId}</td>
			<td>
				<a href="<c:url value='/manager/video/edit?id=${video.id}'/>">Sửa</a>
			| 	<a href="<c:url value='/manager/video/delete?id=${video.id}'/>">Xóa</a>
			</td>
		</tr>
	</c:forEach>
</table> 