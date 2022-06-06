<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Medicine Update Confirm Form</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Medicine Update Form</h2>
				<c:url var="updateMedicineConfirm" value="/editMedicine"></c:url>
				<form:form class="form clearfix" action="editMedicine" method="POST"
					id="form" modelAttribute="updateMedicineForm">
					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>
					<input type="hidden" name="id" value="${updateMedicineForm.id }" />
					<ul class="confirm-list">
						<li><b>Medicine Code</b> <a class="input">${updateMedicineForm.medicine_code}
								<input type="hidden" name="medicine_code"
								value="${updateMedicineForm.medicine_code}" />
						</a></li>
						<li><b>Medicine Name</b> <a class="input">${updateMedicineForm.medicine_name}
								<input type="hidden" name="medicine_name"
								value="${updateMedicineForm.medicine_name}" />
						</a></li>
						<li><b>Medicine Description</b> <a class="input">${updateMedicineForm.medicine_description}
								<input type="hidden" name="medicine_description"
								value="${updateMedicineForm.medicine_description}" />
						</a></li>
						<li><b>Category Name</b> <a class="input">${updateMedicineForm.category.category_name}
								<input type="hidden" name="category.category_id"
								value="${updateMedicineForm.category.category_id}" />
						</a></li>
						<li><b>Unit In Stock</b> <a class="input">${updateMedicineForm.unit_in_stock}
								<input type="hidden" name="unit_in_stock"
								value="${updateMedicineForm.unit_in_stock}" />
						</a></li>
						<li><b>Price</b> <a class="input">${updateMedicineForm.amount}
								<input type="hidden" name="amount"
								value="${updateMedicineForm.amount}" />
						</a></li>
						<li><b>Image</b><img src="${updateMedicineForm.image}"
							id="medicine_image" class="medicine-img input" /><input
							type="hidden" name="image" value="${updateMedicineForm.image}" /></li>
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