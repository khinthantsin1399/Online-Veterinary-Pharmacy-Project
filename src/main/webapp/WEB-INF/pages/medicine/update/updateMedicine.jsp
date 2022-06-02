<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h2 class="cmn-ttl">Medicine Update Form</h2>

				<form:form class="form"
					action="${pageContext.request.contextPath}/updateMedicineConfirm"
					method="POST" id="form" modelAttribute="medicine">
					<input type="hidden" name="id" value="${medicine.id }" />

					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>

					<label for="medicine_code">Medicine Code</label>
					<input name="medicine_code" class="form-control" type="text"
						value="${medicine.medicine_code}" />

					<label for="medicine_name">Medicine Name</label>
					<input name="medicine_name" class="form-control" type="text"
						value="${medicine.medicine_name}" />

					<label for="medicine_description">Medicine Description</label>
					<input name="medicine_description"
						value="${medicine.medicine_description}" />

					<label for="category.category_id">Category Name</label>
					<form:select path="category.category_id"
						name="medicine.category.category_id">
						<form:option value="${category.category_id}">${medicine.category.category_name}</form:option>
						<c:forEach items="${CategoryList}" var="category">
							<form:option value="${category.category_id}">${category.category_name}</form:option>
						</c:forEach>
					</form:select>
					<br>

					<label for="unit_in_stock">Unit In Stock</label>
					<input name="unit_in_stock" class="form-control" type="text"
						value="${medicine.unit_in_stock}" />

					<label for="amount">Price</label>
					<input name="amount" class="form-control" type="text"
						value="${medicine.amount}" />

					<label for="image">Image</label>
					<input type="file" name="fileUpload" id="fileUpload"
						accept="image/*" value="${imageData}"
						onchange="showImage.call(this)" />
					<input name="imageData" type="hidden" id="imageData" value="" />
					<a class="float-right"> <img src="${medicine.image}"
						id="medicine_image" class="profile-user-img img-fluid img-circle" /></a>

					<button type="submit" class="btn btn-info">Update</button>
					<a class="btn"
						href="${pageContext.request.contextPath}/medicineList">Back</a>
					<a class="btn"
						href="${pageContext.request.contextPath}/updateMedicine?id=${medicine.id}">Reset</a>


				</form:form>

			</div>
		</div>
	</div>
</body>
</html>