<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Update Confirm</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Update Confirm Form</h2>
				<c:url var="updateCategoryConfirm" value="/editCategory"></c:url>
				<form:form class="form clearfix" action="editCategory" method="POST"
					id="form" modelAttribute="updateCategoryForm">
					<form:input type="hidden" path="category_id" />
					<ul class="confirm-list">
						<li><b>Category Code</b> <a class="input">${updateCategoryForm.category_code}
								<input type="hidden" name="category_code"
								value="${updateCategoryForm.category_code}" />
						</a></li>
						<li><b>Category Name</b> <a class="input">${updateCategoryForm.category_name}
								<input type="hidden" name="category_name"
								value="${updateCategoryForm.category_name}" />
						</a></li>
					</ul>
					<div class="clearfix">
						<button type="submit" class="cmn-btn" name="update">Update</button>
						<button type="submit" class="cmn-btn" name="cancel">Cancel</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>