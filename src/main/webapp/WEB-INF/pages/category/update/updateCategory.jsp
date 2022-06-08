<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Update Form</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Update Form</h2>
				<c:url var="updateCategory" value="/updateCategoryConfirm"></c:url>
				<form:form class="form clearfix" action="updateCategoryConfirm"
					method="POST" id="form" modelAttribute="category">
					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>
					<form:input type="hidden" path="category_id"
						value="${category.category_id}" />
					<label for="category_code">Category Code</label>
					<input class="input" type="text" name="category_code"
						value="${category.category_code}" />
					<label for="category_name">Category Name</label>
					<input class="input" name="category_name"
						value="${category.category_name}">
					<div class="clearfix update-btn">
						<button type="submit" class="cmn-btn">Update</button>
						<a class="cmn-btn"
							href="${pageContext.request.contextPath}/categoryList">Back</a> <a
							class="cmn-btn"
							href="${pageContext.request.contextPath}/updateCategory?id=${category.category_id}">Reset</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>