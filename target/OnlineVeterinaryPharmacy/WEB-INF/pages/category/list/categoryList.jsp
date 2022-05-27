<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Veterinary Pharmacy | Category List</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">
		<div class="sec-list">
			<div class="container">
				<h2 class="cmn-ttl">Medicine List</h2>
				<table class="tbl-student">
					<tr>
						<th>ID</th>
						<th>Category Name</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${CategoryList}" var="category" varStatus="loop">
						<tr>
							<td>${category.id }</td>
							<td>${category.category_name}</td>
							<td><a
								href="${pageContext.request.contextPath}/updateCategory?id=${category.id}"
								></a> <a
								href="${pageContext.request.contextPath}/deleteCategory?id=${category.id }"
								class="cmn-link"
								onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false"></a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>