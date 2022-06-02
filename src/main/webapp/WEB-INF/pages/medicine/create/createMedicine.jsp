<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Veterinary Pharmacy | ADD FORM</title>
<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

</head>
<body>
	<div class="wrapper">

		<div class="container">
			<div class="sec-form">
				<h2 class="cmn-ttl">Medicine Add Form</h2>
				<c:url var="createMedicine" value="/createMedicineConfirm"></c:url>
				<form:form class="form" action="createMedicineConfirm" method="POST"
					id="form" modelAttribute="createMedicineForm">
					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>

					<label for="medicine_code">Medicine Code</label>
					<form:input path="medicine_code"
						value="${createMedicineForm.medicine_code}" class="input"
						placeholder="Enter Medicine Code" />
					<form:errors path="medicine_code" class="text-danger" />

					<label for="medicine_name">Medicine Name</label>
					<form:input path="medicine_name"
						value="${createMedicineForm.medicine_name}" class="input"
						placeholder="Enter Medicine Name" />
					<form:errors path="medicine_name" cssClass="text-danger" />


					<label for="medicine_description">Medicine Description</label>
					<form:input path="medicine_description"
						value="${createMedicineForm.medicine_description}" class="input"
						placeholder="Enter Medicine Description" />
					<form:errors path="medicine_description" cssClass="text-danger" />

					<label for="category.category_id">Category Name</label>
					<form:select path="category.category_id"
						value="${createMedicineForm.category.category_id}">
						<c:forEach items="${CategoryList}" var="category">
							<form:option value="${category.category_id}">${category.category_name}</form:option>
						</c:forEach>
					</form:select>
					<br>
					<br>
					<br>

					<label for="unit_in_stock">Unit In Stock</label>
					<input name="unit_in_stock" type="number"
						value="${createMedicineForm.unit_in_stock}" class="input"
						placeholder="Enter Unit In Stock" />
					<form:errors path="unit_in_stock" cssClass="text-danger" />


					<label for="amount">Price</label>
					<input name="amount" type="number"
						value="${createMedicineForm.amount}" class="input"
						placeholder="Enter Price" />
					<form:errors path="amount" class="text-danger" />



					<label for="image"></label>
					<input type="file" name="fileUpload" id="fileUpload"
						accept="image/*" value="${imageData}"
						onchange="showImage.call(this)" />
					<input name="imageData" type="hidden" id="imageData" value="" />
					<a class="float-right"> <img src="${createMedicineForm.image}"
						id="medicine_image" /></a>
					<form:input path="image" type="hidden" value="${imageData}" />

					<button type="submit" class="btn" name="confirmMedicine">Confirm</button>

					<button type="reset" class="btn" name="clear">Reset</button>
				</form:form>
			</div>
		</div>
	</div>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
	<script src="<c:url value="/resources/js/imagePreview.js"/>"></script>
</body>
</html>