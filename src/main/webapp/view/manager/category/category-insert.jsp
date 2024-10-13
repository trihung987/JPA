<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value='/manager/category/insert'></c:url>"
		method="post" enctype="multipart/form-data">
		<label for="Categoryname">Category name:</label> <input type="text"
			id="Categoryname" name="Categoryname" value="${cate.categoryname }"><br>
		<label for="Images">Images:</label>
		<c:if test="${cate.images.substring(0,5) == 'https' }">
			<c:url value="${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${cate.images.substring(0,5) != 'https' }">
			<c:url value="/image?from=cate&fname=${cate.images }" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" /> <input type="file"
			id="Images" name="Images"><br>
		<br> <label for="status">Status:</label><br> <label
			for="Status">Đang hoạt động</label> <input type="radio" id="ston"
			name="Status" value="1" checked><br>
		<br> <label for="Status">Khóa</label> <input type="radio"
			id="stoff" name="Status" value="0"><br>
		<br> <input type="submit" value="Insert">
	</form>

</body>
</html>