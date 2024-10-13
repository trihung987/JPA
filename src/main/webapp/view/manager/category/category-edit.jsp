<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Category</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<form action="<c:url value='/manager/category/update'></c:url>"
		method="post" enctype="multipart/form-data">
		<input type="text" id="CategoryId" name="CategoryId" hidden="true"
			value="${cate.categoryId }"><br> <label
			for="Categoryname">Category name:</label><br> <input type="text"
			id="Categoryname" name="Categoryname" value="${cate.categoryname }"><br>
		<label for="Images">Images:</label><br>
		<c:if test="${cate.images.substring(0,5) == 'https' }">
			<c:url value="${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${cate.images.substring(0,5) != 'https' }">
			<c:url value="/image?from=cate&fname=${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" /> <input type="file"
			id="Images" name="Images" value="${cate.images }"><br> <br>
		<label for="status">Status:</label> <br> <label for="Status">Đang
			hoạt động</label> <input type="radio" id="ston" name="Status" value="1"
			${cate.status==1?'checked' : '' }> <br> <br> <label
			for="Status">Khóa</label> <input type="radio" id="stoff"
			name="Status" value="0" ${cate.status!=1?'checked' : '' }><br>
		<br> <input type="submit" value="Update">
	</form>
<script src = "../../js/updateImg.js"> </script>
</body>
</html>