<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Category Update Form</h2>
				<c:url var="updateCategoryConfirm" value="/editCategory"></c:url>
				<form:form class="form" action="editCategory" method="POST"
					id="form" modelAttribute="updateCategoryForm">
					<form:input type="hidden" path="category_id" />


					<ul class="list-group list-group-unbordered mb-3">
						<li class="list-group-item"><b>Category Code</b> <a
							class="float-right">${updateCategoryForm.category_code} <input
								type="hidden" name="category_code"
								value="${updateCategoryForm.category_code}" class="form-control" />
						</a></li>
						<li class="list-group-item"><b>Category Name</b> <a
							class="float-right">${updateCategoryForm.category_name} <input
								type="hidden" name="category_name"
								value="${updateCategoryForm.category_name}" class="form-control" />
						</a></li>
					</ul>
					<button type="submit" class="btn" name="update">Update</button>
					<button type="submit" class="btn" name="cancel">Cancel</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>