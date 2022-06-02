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
				<h2 class="cmn-ttl">Medicine Update Form</h2>
				<c:url var="updateMedicineConfirm" value="/editMedicine"></c:url>
				<form:form class="form" action="editMedicine" method="POST"
					id="form" modelAttribute="updateMedicineForm">
					<c:if test="${errorMsg != null }">
						<div class="alert alert-danger">
							<strong>${errorMsg }</strong>
						</div>
					</c:if>

					<input type="hidden" name="id" value="${updateMedicineForm.id }" />


					<ul class="list-group list-group-unbordered mb-3">
						<li class="list-group-item"><b>Medicine Code</b> <a
							class="float-right">${updateMedicineForm.medicine_code} <input
								type="hidden" name="medicine_code"
								value="${updateMedicineForm.medicine_code}" class="form-control" />
						</a></li>
						<li class="list-group-item"><b>Medicine Name</b> <a
							class="float-right">${updateMedicineForm.medicine_name} <input
								type="hidden" name="medicine_name"
								value="${updateMedicineForm.medicine_name}" class="form-control" />
						</a></li>

						<li class="list-group-item"><b>Medicine Description</b> <a
							class="float-right">${updateMedicineForm.medicine_description}
								<input type="hidden" name="medicine_description"
								value="${updateMedicineForm.medicine_description}"
								class="form-control" />
						</a></li>

						<li class="list-group-item"><b>Category Name</b> <a
							class="float-right">${updateMedicineForm.category.category_name}
								<input type="hidden" name="category.category_id"
								value="${updateMedicineForm.category.category_id}"
								class="form-control" />
						</a></li>

						<li class="list-group-item"><b>Unit In Stock</b> <a
							class="float-right">${updateMedicineForm.unit_in_stock} <input
								type="hidden" name="unit_in_stock"
								value="${updateMedicineForm.unit_in_stock}" class="form-control" />
						</a></li>


						<li class="list-group-item"><b>Price</b> <a
							class="float-right">${updateMedicineForm.amount} <input
								type="hidden" name="amount" value="${updateMedicineForm.amount}"
								class="form-control" />
						</a></li>

						<li class="list-group-item"><b>Image</b> <a
							class="float-right"><img src="${updateMedicineForm.image}"
								id="medicine_image"
								class="profile-user-img img-fluid img-circle" /><input
								type="hidden" name="image" value="${updateMedicineForm.image}"
								class="form-control" /></a></li>

					</ul>
					<button type="submit" class="btn" name="update">Update</button>
					<button type="submit" class="btn" name="cancel">Cancel</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>