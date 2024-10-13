<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Video Insert</title>
</head>
<body>
	<form action="<c:url value='/manager/video/insert'></c:url>"
		method="post" enctype="multipart/form-data">
		<label for="videotitle">Video title:</label> <input type="text"
			id="videotitle" name="videotitle" value="${video.title}"><br>
		<br>
		<label for="images">Poster:</label>
		<c:if test="${video.poster.substring(0,5) == 'https' }">
			<c:url value="${video.poster}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${video.poster.substring(0,5) != 'https' }">
			<c:url value="/image?from=vid&fname=${video.poster }" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" /> <input type="file"
			id="images" name="images"><br>
			
		<br>
		<c:url value="/video?fname=${video.videoId}" var="videoUrl"></c:url>
		
		<label for="videos">Video: </label>
		<video width="200" height="150" controls> <source src ="${videoUrl}"> </video>
		<input type="file"
			id="videos" name="videos"><br>
		
		<br>
		<label for="videoactive">Video active: </label>
		 <input type="text"
			id="videoactive" name="videoactive" value="${video.active}"><br>
		<br>
		<label for="videodes">Description: </label>
		<textarea id="videodes" name="videodes" rows="4" cols="50" >${video.description} </textarea><br>
		<br>
		<label for="videoviews">Video views: </label>
		 <input type="text"
			id="videoviews" name="videoviews" value="${video.views}"><br>
		<br>
		<label for="videocateid">Video category id: </label>
		 <input type="text"
			id="videocateid" name="videocateid" value="${video.category.categoryId}"><br>
		<br> <input type="submit" value="Insert">
	</form>

</body>
</html>